package me.shouheng.utils.ktx

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import me.shouheng.utils.UtilsApp
import me.shouheng.utils.stability.L
import java.io.Serializable
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList

/** 事件广播发送的时候使用的 Key */
private const val GLOBAL_BROADCAST_EVENT_DATA_KEY = "global_broadcast_data"

/** 默认所有的广播都是单进程的 */
private const val GLOBAL_BROADCAST_DEFAULT_MULTI_PROCESS = false

/** 关注事件，支持自动取消注册 */
fun <T> LifecycleOwner.observeEvent(
    type: Class<T>,
    multiProcess: Boolean = GLOBAL_BROADCAST_DEFAULT_MULTI_PROCESS,
    job: (event: T?) -> Unit
) {
    val eventAction = GlobalBroadcast.getEventAction(type)
    L.d("Registered event action: $eventAction")
    observeBroadcast(eventAction, multiProcess) { _, intent ->
        job.invoke(GlobalBroadcast.getEvent(intent))
    }
}

/** 关注广播，支持自动取消注册 */
fun LifecycleOwner.observeBroadcast(
    action: String,
    multiProcess: Boolean = GLOBAL_BROADCAST_DEFAULT_MULTI_PROCESS,
    job: (context: Context?, intent: Intent?) -> Unit
)  {
    observeBroadcast(action, multiProcess, object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            job.invoke(context, intent)
        }
    })
}

/** 关注广播，支持自动取消注册 */
fun LifecycleOwner.observeBroadcast(
    action: String,
    multiProcess: Boolean = GLOBAL_BROADCAST_DEFAULT_MULTI_PROCESS,
    receiver: BroadcastReceiver
) {
    this.lifecycle.addObserver(object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun onCreate() {
            GlobalBroadcast.registerReceiver(receiver, action, multiProcess)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy() {
            GlobalBroadcast.unregisterReceiver(receiver)
        }
    })
}

/**
 * 全局广播
 *
 * @Author wangshouheng
 * @Time 2022/1/7
 */
object GlobalBroadcast {

    /** 全局的 Action => 对应的全局广播 */
    private val globals = ConcurrentHashMap<String, BroadcastReceiver>()

    /** 全局广播 => 该广播对应的子广播 */
    private val receivers = ConcurrentHashMap<BroadcastReceiver, CopyOnWriteArrayList<BroadcastReceiver>>()

    /** 注册的广播 => 该广播的 Action */
    private val actions = ConcurrentHashMap<BroadcastReceiver, String>()

    /** 注册的广播 => 该广播是否为多进程的 */
    private val processes = ConcurrentHashMap<BroadcastReceiver, Boolean>()

    /** 私有锁，保证操作的原子性 */
    private var lock = Any()

    /** 发送事件，在接收广播的地方使用 [getEvent] 获取具体的事件。 */
    fun sendEvent(event: Serializable, multiProcess: Boolean = GLOBAL_BROADCAST_DEFAULT_MULTI_PROCESS) {
        val eventAction = getEventAction(event.javaClass)
        L.d("Send event action: $eventAction")
        sendBroadcast(Intent().putExtra(GLOBAL_BROADCAST_EVENT_DATA_KEY, event), multiProcess)
    }

    /** 发送事件 action */
    fun sendBroadcast(action: String, multiProcess: Boolean = GLOBAL_BROADCAST_DEFAULT_MULTI_PROCESS) {
        sendBroadcast(Intent(action), multiProcess)
    }

    /** 发送广播 */
    fun sendBroadcast(intent: Intent, multiProcess: Boolean = GLOBAL_BROADCAST_DEFAULT_MULTI_PROCESS) {
        if (multiProcess) {
            UtilsApp.getApp().sendBroadcast(intent)
        } else {
            LocalBroadcastManager.getInstance(
                UtilsApp.getApp()
            ).sendBroadcast(intent)
        }
    }

    /** 获取指定事件的 action */
    fun getEventAction(type: Class<*>): String = "GLOBAL_BROADCAST_ACTION_${type.name}"

    /** 获取事件对象 */
    fun <T> getEvent(intent: Intent?): T? = intent?.getSerializableExtra(GLOBAL_BROADCAST_EVENT_DATA_KEY) as? T

    /** 注册事件广播 */
    fun <T> registerReceiver(
        receiver: BroadcastReceiver,
        type: Class<T>,
        multiProcess: Boolean = GLOBAL_BROADCAST_DEFAULT_MULTI_PROCESS
    ) {
        registerReceiver(receiver, getEventAction(type), multiProcess)
    }

    /** 注册广播 */
    fun registerReceiver(
        receiver: BroadcastReceiver,
        action: String,
        multiProcess: Boolean = GLOBAL_BROADCAST_DEFAULT_MULTI_PROCESS
    ) {
        if (multiProcess) {
            synchronized(lock) {
                var globalReceiver = globals[action]
                if (globalReceiver == null) {
                    // 获取全局广播用来分发子广播
                    globalReceiver = generateGlobalBroadcastReceiver()
                    globals[action] = globalReceiver
                    receivers[globalReceiver] = CopyOnWriteArrayList()
                    UtilsApp.getApp().registerReceiver(globalReceiver, IntentFilter(action))
                    L.i("Registered global receiver $globalReceiver for action $action .")
                }
                actions[receiver] = action
                receivers[globalReceiver]?.add(receiver)
            }
        } else {
            LocalBroadcastManager.getInstance(UtilsApp.getApp())
                .registerReceiver(receiver, IntentFilter(action))
        }
        processes[receiver] = multiProcess
    }

    /** 取消注册广播 */
    fun unregisterReceiver(receiver: BroadcastReceiver) {
        when (processes[receiver]) {
            true -> {
                synchronized(lock) {
                    val action = actions.remove(receiver)
                    if (action != null) {
                        val globalReceiver = globals[action]
                        if (globalReceiver != null) {
                            receivers[globalReceiver]?.remove(receiver)
                            L.i("Receiver [$receiver] unregistered!")
                            // 如果所有子广播已经全部取消，取消全局广播监听
                            if (receivers[globalReceiver]?.isEmpty() == true) {
                                globals.remove(action)
                                receivers.remove(globalReceiver)
                                UtilsApp.getApp().unregisterReceiver(globalReceiver)
                                L.i("Global receiver [$globalReceiver] unregistered! " +
                                        "Left: actions[${actions.size}], global[${globals.size}].")
                            }
                        } else {
                            L.e("Failed to unregister receiver [$receiver]: " +
                                    "global receiver not found! WARN: This might lead to memory leak!")
                        }
                    } else {
                        L.e("Failed to unregister receiver [$receiver]: " +
                                "action not found! WARN: This might lead to memory leak!")
                    }
                }
            }
            false -> {
                LocalBroadcastManager.getInstance(UtilsApp.getApp())
                    .unregisterReceiver(receiver)
            }
            else -> {
                L.e("Failed to unregister receiver[$receiver]: " +
                        "process not found! WARN: This might lead to memory leak!")
            }
        }
        processes.remove(receiver)
    }

    /** 获取用于分发子广播的全局广播 */
    private fun generateGlobalBroadcastReceiver(): BroadcastReceiver {
        return object: BroadcastReceiver () {
            override fun onReceive(context: Context?, intent: Intent?) {
                receivers[this]?.forEach {
                    it.onReceive(context, intent?.clone() as? Intent)
                }
            }
        }
    }

}
