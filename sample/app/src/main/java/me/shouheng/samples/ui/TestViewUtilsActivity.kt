package me.shouheng.samples.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import me.shouheng.samples.R
import me.shouheng.utils.ktx.colorOf
import me.shouheng.utils.ui.ColorUtils
import me.shouheng.utils.ui.ViewUtils

class TestViewUtilsActivity : AppCompatActivity() {
    private var isAlpha = false
    private var ivCapture: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_view_utils)
        val tvMeasure = findViewById<TextView>(R.id.tv_measure_result)
        ivCapture = findViewById(R.id.iv)
        ViewUtils.forceGetViewSize(tvMeasure) { view -> tvMeasure.text = "W:" + view.width + ", H:" + view.height }
        val tv = findViewById<TextView>(R.id.tv)
        tv.text = "颜色转换：#458CF7 -> " + ColorUtils.colorToString(colorOf(R.color.colorPrimary))
    }

    fun showSortInput(view: View?) {
        ViewUtils.showSoftInput(view)
    }

    fun hideSortInput(view: View?) {
        ViewUtils.hideSoftInput(view)
    }

    fun toggleSortInput(view: View?) {
        ViewUtils.toggleSoftInput()
    }

    fun toggleAlpha(view: View?) {
        isAlpha = !isAlpha
        ViewUtils.setAlpha(view!!, if (isAlpha) 0.5f else 1f)
    }

    fun captureScreen(view: View?) {
        val result = ViewUtils.captureScreenWithoutStatusBar(this)
        ivCapture!!.setImageBitmap(result)
    }
}