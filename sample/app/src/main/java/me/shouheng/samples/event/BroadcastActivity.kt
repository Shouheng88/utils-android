package me.shouheng.samples.event

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import me.shouheng.samples.R
import me.shouheng.utils.ktx.GlobalBroadcast
import java.io.Serializable

const val GLOBAL_BROADCAST_ACTION   = "me.shouheng.samples.event.GLOBAL_BROADCAST"

class GlobalEvent(val data: String): Serializable
class GlobalEvent2(val data: String): Serializable

class BroadcastActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast)
    }

    fun testGlobalBroadcast(v: View) {
        GlobalBroadcast.sendBroadcast(Intent(GLOBAL_BROADCAST_ACTION).putExtra("__extra", "Hello!"))
    }

    fun testGlobalBroadcastEvent(v: View) {
        GlobalBroadcast.sendEvent(GlobalEvent("Hello world!"))
    }

    fun testGlobalBroadcastWithMultiProcesses(v: View) {
        startService(Intent(this, EventService::class.java))
    }
}
