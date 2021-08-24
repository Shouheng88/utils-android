package me.shouheng.samples.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import me.shouheng.samples.R
import me.shouheng.utils.constant.ActivityDirection
import me.shouheng.utils.ktx.attrColorOf
import me.shouheng.utils.ktx.open
import me.shouheng.utils.ktx.start
import me.shouheng.utils.ktx.toast
import me.shouheng.utils.stability.L

/**
 * @author shouh
 * @version $Id: TestActivityHelper, v 0.1 2018/11/22 12:40 shouh Exp$
 */
class TestActivityHelper : AppCompatActivity() {
    private var currentAnimationIndex = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_helper_test)
        findViewById<View>(R.id.btn_view).setOnClickListener {
            open().setAction(Intent.ACTION_VIEW)
                    .setData(Uri.parse("http://www.baidu.com"))
                    .launch(this@TestActivityHelper)
        }
        findViewById<View>(R.id.btn_request_code).setOnClickListener {
            open(TestActivityResult::class.java)
                    .put(TestActivityResult.REQUEST_EXTRA_KEY_DATA,
                            TestActivityResult.Request("Request-name", "Request-value"))
                    .withDirection(ActivityDirection.ANIMATE_FORWARD)
                    .launch(this@TestActivityHelper, REQUEST_RESULT)
        }
        val btnShared = findViewById<View>(R.id.btn_shared_activity)
        if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
            btnShared.transitionName = "SHARED_ELEMENT"
        }
        btnShared.setOnClickListener {
            open(TestActivityResult::class.java)
                    .wishSharedElements(arrayOf(btnShared))
                    .launch(this@TestActivityHelper)
        }
        findViewById<View>(R.id.btn_animation).setOnClickListener {
            val index = currentAnimationIndex++ % DIRECTION_ANIMATION_ARRAY.size
            toast(DIRECTION_ANIMATION_NAME_ARRAY[index])
            start(TestActivityResult::class.java, 0, DIRECTION_ANIMATION_ARRAY[index])
        }
        findViewById<View>(R.id.iv_prop).setBackgroundColor(attrColorOf(R.attr.custom_prop))
    }

    override fun onStart() {
        super.onStart()
        L.d("START HELPER")
    }

    override fun onResume() {
        super.onResume()
        L.d("RESUME HELPER")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_RESULT && resultCode == Activity.RESULT_OK) {
            assert(data != null)
            val result = data!!.getSerializableExtra(TestActivityResult.RESULT_EXTRA_KEY_DATA) as TestActivityResult.Result
            toast(result.toString())
        }
    }

    companion object {
        private const val REQUEST_RESULT = 1
        private val DIRECTION_ANIMATION_ARRAY = intArrayOf(
                ActivityDirection.ANIMATE_NONE,
                ActivityDirection.ANIMATE_FORWARD,
                ActivityDirection.ANIMATE_EASE_IN_OUT,
                ActivityDirection.ANIMATE_SLIDE_TOP_FROM_BOTTOM,
                ActivityDirection.ANIMATE_SLIDE_BOTTOM_FROM_TOP,
                ActivityDirection.ANIMATE_SCALE_IN,
                ActivityDirection.ANIMATE_SCALE_OUT
        )
        private val DIRECTION_ANIMATION_NAME_ARRAY = arrayOf(
                "ANIMATE_NONE",
                "ANIMATE_FORWARD",
                "ANIMATE_EASE_IN_OUT",
                "ANIMATE_SLIDE_TOP_FROM_BOTTOM",
                "ANIMATE_SLIDE_BOTTOM_FROM_TOP",
                "ANIMATE_SCALE_IN",
                "ANIMATE_SCALE_OUT"
        )
    }
}