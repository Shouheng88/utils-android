package me.shouheng.samples.event

import android.app.Service
import android.content.Intent
import android.os.IBinder
import me.shouheng.utils.ktx.GlobalBroadcast

const val ACTION_MULTI_PROCESS_BROADCAST = "me.shouheng.samples.event.GLOBAL_MULTI_PROCESS_BROADCAST"
const val ACTION_PROCESS_BROADCAST = "me.shouheng.samples.event.GLOBAL_PROCESS_BROADCAST"

class EventService : Service() {

    override fun onBind(intent: Intent): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        GlobalBroadcast.sendBroadcast(ACTION_PROCESS_BROADCAST, true)
        GlobalBroadcast.sendBroadcast(ACTION_MULTI_PROCESS_BROADCAST)
        return super.onStartCommand(intent, flags, startId)
    }
}
