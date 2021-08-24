package me.shouheng.samples.ui

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import me.shouheng.samples.R
import me.shouheng.utils.app.ResUtils
import me.shouheng.utils.ktx.*
import me.shouheng.utils.ui.ImageUtils
import me.shouheng.utils.ui.ViewUtils

class TestImageUtilsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_image_utils)
        val src = ImageUtils.getBitmap(R.drawable.img_lena)
        val watermark = ImageUtils.getBitmap(R.drawable.ic_widgets_black_24dp)
        val width = src.width
        val height = src.height
        val ll = findViewById<LinearLayout>(R.id.ll)
        addImage(ll, src, "Original")
        addImage(ll, ImageUtils.drawColor(src, Color.parseColor("#8000FF00")), "Add Color")
        addImage(ll, src.scale(width / 2, height / 2), "Scale")
        addImage(ll, src.clip(0, 0, width / 2, height / 2), "Clip")
        addImage(ll, ImageUtils.skew(src, 0.2f, 0.1f), "Skew")
        addImage(ll, ImageUtils.rotate(src, 90, width / 2.toFloat(), height / 2.toFloat()), "Rotate")
        addImage(ll, src.toRound(), "Round")
        addImage(ll, ImageUtils.toRound(src, 16, Color.GREEN), "Round")
        addImage(ll, ImageUtils.toRoundCorner(src, 80f), "Round Corner")
        addImage(ll, ImageUtils.toRoundCorner(src, 80f, 16, Color.GREEN), "Round Corner")
        addImage(ll, ImageUtils.addCornerBorder(src, 16, Color.GREEN, 0f), "Corner Border")
        addImage(ll, ImageUtils.addTextWatermark(src, "Watermark", 40, Color.GREEN, 0f, 0f), "Text Watermark")
        addImage(ll, ImageUtils.addImageWatermark(src, watermark, 0, 0, 0x88), "Image Watermark")
        addImage(ll, src.toGray(), "Grey")
        addImage(ll, ImageUtils.fastBlur(src, 0.1f, 5f), "Fast Blur")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            addImage(ll, ImageUtils.renderScriptBlur(src, 10f), "Render Script Blur")
        }
        addImage(ll, ImageUtils.stackBlur(src, 10), "Stack Blur")
        addImage(ll, src.compressByScale(0.5f, 0.5f), "Compress By Scale")
        addImage(ll, src.compressByQuality(50), "Compress By Quality")
        addImage(ll, src.compressByQuality(10L * 1024), "Compress By Quality") // 10Kb
        addImage(ll, src.compressBySampleSize(2), "Compress By Sample Size")
        addImage(ll, ResUtils.getDrawable(R.drawable.ic_widgets_black_24dp).tint(Color.BLUE), "Tint Drawable")
        addImage(ll, ImageUtils.getDrawable(Color.BLUE, ViewUtils.dp2px(120f).toFloat()), "Generated Drawable By Code")
        addImage(ll, ImageUtils.getDrawable(Color.BLUE, ViewUtils.dp2px(120f).toFloat(), ViewUtils.dp2px(10f), Color.YELLOW), "Generated Drawable By Code With Stroke")
    }

    private fun addImage(container: LinearLayout, bitmap: Bitmap, title: String) {
        val iv = addImage(container, title)
        iv.setImageBitmap(bitmap)
    }

    private fun addImage(container: LinearLayout, drawable: Drawable, title: String) {
        val iv = addImage(container, title)
        iv.setImageDrawable(drawable)
    }

    private fun addImage(container: LinearLayout, title: String): ImageView {
        val ll = LinearLayout(this)
        ll.orientation = LinearLayout.VERTICAL
        ll.gravity = Gravity.CENTER_HORIZONTAL
        val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        params.bottomMargin = ViewUtils.dp2px(5f)
        params.topMargin = ViewUtils.dp2px(5f)
        ll.layoutParams = params
        val iv = ImageView(this)
        val dp200 = ViewUtils.dp2px(200f)
        iv.layoutParams = LinearLayout.LayoutParams(dp200, dp200)
        val tv = TextView(this)
        tv.text = title
        val tvParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        tvParams.gravity = Gravity.CENTER
        tv.layoutParams = tvParams
        ll.addView(iv)
        ll.addView(tv)
        container.addView(ll)
        return iv
    }
}