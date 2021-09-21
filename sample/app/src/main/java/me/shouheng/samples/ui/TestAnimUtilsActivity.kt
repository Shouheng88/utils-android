package me.shouheng.samples.ui

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import me.shouheng.samples.R
import me.shouheng.utils.ktx.*
import me.shouheng.utils.ui.AnimUtils
import me.shouheng.utils.ui.ViewUtils

/**
 * Anim utils test
 *
 * @author [WngShhng](mailto:shouheng2015@gmail.com)
 * @version 2020-05-24 12:53
 */
class TestAnimUtilsActivity : AppCompatActivity() {
    private var obj: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim_utils)
        obj = findViewById(R.id.v)
        findViewById<View>(R.id.btn_fade_in).setOnClickListener { obj?.fadeIn(2000, null, true) }
        findViewById<View>(R.id.btn_fade_out).setOnClickListener { obj?.fadeOut(2000, null, true) }
        findViewById<View>(R.id.btn_slide_in).setOnClickListener { obj?.slideIn(2000, null, true, AnimUtils.UIDirection.RIGHT_TO_LEFT) }
        findViewById<View>(R.id.btn_slide_out).setOnClickListener { obj?.slideOut(2000, null, true, AnimUtils.UIDirection.LEFT_TO_RIGHT) }
        findViewById<View>(R.id.btn_shinning).setOnClickListener { obj?.shining(2888, 6, 0f, 0.66f, 1.0f, 0f) }
    }

    fun shake(view: View?) {
        obj?.shake()
    }

    fun changeColor(view: View?) {
        changeColor(Color.RED, Color.BLUE, 3000) { obj!!.setBackgroundColor(it) }
//        AnimUtils.changeColor(Color.RED, Color.BLUE, 3000) {  }
    }

    fun onZoomIn(view: View?) {
        obj?.zoomIn(.5f, 0f, 300)
    }

    fun onZoomOut(view: View?) {
        obj?.zoomOut(.5f, 300)
    }

    fun scaleUpDown(view: View?) {
        obj?.scaleUpDown(1200)
    }

    fun animateHeight(view: View?) {
        obj?.animateHeight(0, ViewUtils.dp2px(100f))
    }

    fun popupIn(view: View?) {
        obj?.popupIn(1000)
    }

    fun popupOut(view: View?) {
        obj?.popupOut(1000, null)
    }

    fun rotate(view: View) {
        obj?.rotate(2_000)
    }
}