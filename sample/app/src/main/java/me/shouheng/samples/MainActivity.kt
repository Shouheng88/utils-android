package me.shouheng.samples

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import me.shouheng.samples.activity.TestActivityHelper
import me.shouheng.samples.app.TestAppUtilsActivity
import me.shouheng.samples.common.BaseActivity
import me.shouheng.samples.common.FileUtils
import me.shouheng.samples.data.TestEncryptUtilsActivity
import me.shouheng.samples.data.TestTimeUtilsActivity
import me.shouheng.samples.device.TestDeviceUtilsActivity
import me.shouheng.samples.device.TestNetworkUtilsActivity
import me.shouheng.samples.device.TestShellActivity
import me.shouheng.samples.event.*
import me.shouheng.samples.intent.TestIntentActivity
import me.shouheng.samples.permission.TestPermissionActivity
import me.shouheng.samples.stability.TestCrashActivity
import me.shouheng.samples.stability.TestLogActivity
import me.shouheng.samples.store.StorageActivity
import me.shouheng.samples.store.TestPathUtilsActivity
import me.shouheng.samples.store.TestSpUtilsActivity
import me.shouheng.samples.ui.TestAnimUtilsActivity
import me.shouheng.samples.ui.TestImageUtilsActivity
import me.shouheng.samples.ui.TestToastUtilsActivity
import me.shouheng.samples.ui.TestViewUtilsActivity
import me.shouheng.utils.ktx.*
import me.shouheng.utils.permission.PermissionUtils
import me.shouheng.utils.stability.CrashHelper
import me.shouheng.utils.stability.L

/**
 * @author shouh
 * @version $Id: MainActivity, v 0.1 2018/11/22 12:10 shouh Exp$
 */
class MainActivity : BaseActivity() {

    private val liveData = MutableLiveData<MutableList<String>>()
    private val list = mutableListOf("A", "B", "C")

    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            L.d("[Internal] Received broadcast ${intent?.action} with extras: " + intent?.getStringExtra("__extra"))
        }
    }
    private val eventReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            L.d("[Internal] Received event ${intent?.action} ${GlobalBroadcast.getEvent<GlobalEvent>(intent)?.data}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.btn_permission).setOnClickListener { start(TestPermissionActivity::class.java) }
        findViewById<View>(R.id.btn_activity_helper).setOnClickListener { start(TestActivityHelper::class.java) }
        findViewById<View>(R.id.btn_crash).setOnClickListener { start(TestCrashActivity::class.java) }
        findViewById<View>(R.id.btn_log).setOnClickListener { start(TestLogActivity::class.java) }
        PermissionUtils.checkStoragePermission(this) {
            CrashHelper.init(
                application,
                FileUtils.getExternalStoragePublicCrashDir()
            ) { crashInfo, e -> L.e(crashInfo) }
            L.getConfig().setLog2FileSwitch(true)
                .setDir(FileUtils.getExternalStoragePublicLogDir())
                .setFileFilter(L.W)
        }
        stringOf(R.string.activity_helper_activity_result)
        intOf(R.integer.ease_in)
        drawableOf(R.drawable.ic_launcher_background)
        observeBroadcasts()
    }

    override fun onStop() {
        super.onStop()
        L.d("STOP MAIN")
    }

    override fun onPause() {
        super.onPause()
        L.d("PAUSE MAIN")
    }

    override fun onDestroy() {
        super.onDestroy()
        GlobalBroadcast.unregisterReceiver(receiver)
        GlobalBroadcast.unregisterReceiver(eventReceiver)
    }

    private fun observeBroadcasts() {
        observeBroadcast(GLOBAL_BROADCAST_ACTION, false) { _, intent ->
            L.d("[DSL] Received broadcast ${intent?.action} with extras: " + intent?.getStringExtra("__extra"))
        }
        observeBroadcast(GLOBAL_BROADCAST_ACTION, false, object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                L.d("[LiveData] Received broadcast ${intent?.action} with extras: " + intent?.getStringExtra("__extra"))
            }
        })
        observeBroadcast(ACTION_MULTI_PROCESS_BROADCAST, true) { _, intent ->
            L.d("[DSL] Received broadcast ${intent?.action} with extras: " + intent?.getStringExtra("__extra"))
        }
        observeBroadcast(ACTION_PROCESS_BROADCAST, true) { _, intent ->
            L.d("[DSL] Received broadcast ${intent?.action} with extras: " + intent?.getStringExtra("__extra"))
        }
        observeEvent(GlobalEvent::class.java) {
            L.d("[DSL] Received GlobalEvent: ${it?.data}")
        }
        observeEvent(GlobalEvent2::class.java) {
            L.d("[DSL] Received GlobalEvent2: ${it?.data}")
        }
        GlobalBroadcast.registerReceiver(receiver, GLOBAL_BROADCAST_ACTION)
        GlobalBroadcast.registerReceiver(eventReceiver, GlobalEvent::class.java)

        liveData.observe(this) {
            L.d("llllllllllivedata: ${";".join(it)}")
        }
    }

    fun testIntentUtils(view: View?) { start(TestIntentActivity::class.java) }

    fun testShellUtils(view: View?) { start(TestShellActivity::class.java) }

    fun testAppUtils(v: View?) { start(TestAppUtilsActivity::class.java) }

    fun testSPUtils(v: View?) { start(TestSpUtilsActivity::class.java) }

    fun testStorageUtils(v: View?) { start(StorageActivity::class.java) }

    fun testDeviceUtils(v: View?) { start(TestDeviceUtilsActivity::class.java) }

    fun testNetworkUtils(v: View?) { start(TestNetworkUtilsActivity::class.java) }

    fun testPathUtils(v: View?) { start(TestPathUtilsActivity::class.java) }

    fun testImageUtils(v: View?) { start(TestImageUtilsActivity::class.java) }

    fun testViewUtils(view: View?) { start(TestViewUtilsActivity::class.java) }

    fun testEncryptUtils(view: View?) { start(TestEncryptUtilsActivity::class.java) }

    fun testTimeUtils(view: View?) { start(TestTimeUtilsActivity::class.java) }

    fun testToastUtils(view: View?) { start(TestToastUtilsActivity::class.java) }

    fun testAnimUtils(view: View?) { start(TestAnimUtilsActivity::class.java) }

    fun testBroadcast(view: View?) { start(BroadcastActivity::class.java) }

    fun testLiveData(view: View?) {
        list.add(nowString())
        liveData.value = list
    }
}