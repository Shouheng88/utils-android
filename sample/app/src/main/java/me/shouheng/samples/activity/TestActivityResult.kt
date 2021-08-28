package me.shouheng.samples.activity

import android.app.Activity
import android.content.Intent
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import me.shouheng.samples.R
import me.shouheng.samples.activity.TestActivityResult
import me.shouheng.utils.app.ActivityUtils
import me.shouheng.utils.app.ResUtils
import me.shouheng.utils.ktx.attrColorOf
import me.shouheng.utils.ktx.colorOf
import java.io.Serializable

/**
 * @author shouh
 * @version $Id: TestActivityResult, v 0.1 2018/11/22 12:51 shouh Exp$
 */
class TestActivityResult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_helper_result)
        val request = intent.getSerializableExtra(REQUEST_EXTRA_KEY_DATA) as? Request
        val tvRequest = findViewById<TextView>(R.id.tv_request)
        if (request != null) {
            tvRequest.text = request.toString()
        }
        val intent = Intent()
        intent.putExtra(RESULT_EXTRA_KEY_DATA, Result("Result-name", "Result-value"))
        setResult(Activity.RESULT_OK, intent)
        val btnFinish = findViewById<View>(R.id.btn_finish)
        if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
            btnFinish.transitionName = "SHARED_ELEMENT"
        }
        btnFinish.setOnClickListener { ActivityUtils.finishActivity(this@TestActivityResult) }
        findViewById<View>(R.id.iv_prop).setBackgroundColor(attrColorOf(R.attr.custom_prop))
        colorOf(R.color.colorAccent)
    }

    class Request internal constructor(val name: String, val value: String) : Serializable {
        override fun toString(): String {
            return "Request: " +
                    "name='" + name + '\'' +
                    ", value='" + value
        }

    }

    class Result internal constructor(val name: String, val value: String) : Serializable {
        override fun toString(): String {
            return "Result: " +
                    "name='" + name + '\'' +
                    ", value='" + value
        }

    }

    companion object {
        const val REQUEST_EXTRA_KEY_DATA = "__request_extra_key_data"
        const val RESULT_EXTRA_KEY_DATA = "__result_extra_key_data"
    }
}