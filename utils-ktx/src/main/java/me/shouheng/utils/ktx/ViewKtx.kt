package me.shouheng.utils.ktx

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import me.shouheng.utils.ui.ImageUtils
import me.shouheng.utils.ui.ViewUtils

fun Float.dp2px(): Int = ViewUtils.dp2px(this)

/** Get pixels of given dp */
fun Float.dp(): Int = ViewUtils.dp2px(this)

/** Get pixels of given dp */
fun Float.dpF(): Float = ViewUtils.dp2px(this).toFloat()

fun Float.px2dp(): Int = ViewUtils.px2dp(this)

fun Float.sp2px(): Int = ViewUtils.sp2px(this)

/** Get pixels of given sp */
fun Float.sp(): Int = ViewUtils.sp2px(this)

fun Float.spF(): Float = ViewUtils.sp2px(this).toFloat()

fun Float.px2sp(): Int = ViewUtils.px2sp(this)

fun View.alpha(alpha: Float) { ViewUtils.setAlpha(this, alpha) }

fun View.gone() { ViewUtils.setGone(this) }

/** Gone if match [goneIf] else visible. */
fun View.gone(goneIf: Boolean) { if (goneIf) ViewUtils.setGone(this) else ViewUtils.setVisible(this) }

fun View.invisible() { ViewUtils.setInvisible(this) }

fun View.visible() { ViewUtils.setVisible(this) }

/** Toggle visibility in [View.VISIBLE] and [View.GONE] of current view. */
fun View.toggle() {
    if (this.visibility == View.VISIBLE) {
        this.visibility = View.GONE
    } else {
        this.visibility = View.VISIBLE
    }
}

fun View.setBackgroundCompact(drawable: Drawable) { ViewUtils.setBackground(this, drawable) }

fun View.toBitmap() { ImageUtils.view2Bitmap(this) }

/** Find view by id quick method. */
fun <T: View> ViewGroup.f(@IdRes id: Int): T? {
    return findViewById(id)
}

/** Calls the [scroll] callback when the receiving RecyclerView's scroll position is changed. */
fun RecyclerView.onScroll(scroll: (Int) -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(
            recyclerView: RecyclerView,
            dx: Int,
            dy: Int
        ) {
            super.onScrolled(recyclerView, dx, dy)
            scroll(computeVerticalScrollOffset())
        }
    })
}

/** Calls the [cb] callback when the receiving SeekBar's value is changed. */
fun SeekBar.onProgressChanged(cb: (Int) -> Unit) {
    setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(
            seekBar: SeekBar?,
            progress: Int,
            fromUser: Boolean
        ) = cb(progress)

        override fun onStartTrackingTouch(seekBar: SeekBar?) = Unit

        override fun onStopTrackingTouch(seekBar: SeekBar?) = Unit
    })
}

/** Set top drawable. */
fun TextView.setDrawableTop(@DrawableRes redId: Int) {
    this.setDrawableTop(drawableOf(redId))
}

fun TextView.setDrawableTop(drawable: Drawable) {
    this.setCompoundDrawablesWithIntrinsicBounds(
        null, drawable, null, null)
}

/** Set left drawable. */
fun TextView.setDrawableLeft(@DrawableRes redId: Int) {
    this.setDrawableLeft(drawableOf(redId))
}

fun TextView.setDrawableLeft(drawable: Drawable) {
    this.setCompoundDrawablesWithIntrinsicBounds(
        drawable, null, null, null)
}

/** Set right drawable. */
fun TextView.setDrawableRight(@DrawableRes redId: Int) {
    this.setDrawableRight(drawableOf(redId))
}

fun TextView.setDrawableRight(drawable: Drawable) {
    this.setCompoundDrawablesWithIntrinsicBounds(
        null, null, drawable, null)
}

/** Set bottom drawable. */
fun TextView.setDrawableBottom(@DrawableRes redId: Int) {
    this.setDrawableBottom(drawableOf(redId))
}

fun TextView.setDrawableBottom(drawable: Drawable) {
    this.setCompoundDrawablesWithIntrinsicBounds(
        null, null, null, drawable)
}

/** Set drawable with size. */
@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
fun TextView.setDrawableTop(@DrawableRes redId: Int, width: Int, height: Int) {
    this.setDrawableTop(drawableOf(redId), width, height)
}

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
fun TextView.setDrawableTop(drawable: Drawable, width: Int, height: Int) {
    drawable.setBounds(0, 0, width, height)
    this.setCompoundDrawablesRelative(null, drawable, null, null)
}

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
fun TextView.setDrawableBottom(@DrawableRes redId: Int, width: Int, height: Int) {
    this.setDrawableBottom(drawableOf(redId), width, height)
}

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
fun TextView.setDrawableBottom(drawable: Drawable, width: Int, height: Int) {
    drawable.setBounds(0, 0, width, height)
    this.setCompoundDrawablesRelative(null, null, null, drawable)
}

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
fun TextView.setDrawableLeft(@DrawableRes redId: Int, width: Int, height: Int) {
    this.setDrawableLeft(drawableOf(redId), width, height)
}

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
fun TextView.setDrawableLeft(drawable: Drawable, width: Int, height: Int) {
    drawable.setBounds(0, 0, width, height)
    this.setCompoundDrawablesRelative(drawable, null, null, null)
}

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
fun TextView.setDrawableRight(@DrawableRes redId: Int, width: Int, height: Int) {
    this.setDrawableRight(drawableOf(redId), width, height)
}

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
fun TextView.setDrawableRight(drawable: Drawable, width: Int, height: Int) {
    drawable.setBounds(0, 0, width, height)
    this.setCompoundDrawablesRelative(null, null, drawable, null)
}

/** Set view padding. */
fun View.setPaddingLeft(padding: Int) {
    this.setPadding(padding, paddingTop, paddingRight, paddingBottom)
}

fun View.setPaddingRight(padding: Int) {
    this.setPadding(paddingLeft, paddingTop, padding, paddingBottom)
}

fun View.setPaddingTop(padding: Int) {
    this.setPadding(paddingLeft, padding, paddingRight, paddingBottom)
}

fun View.setPaddingBottom(padding: Int) {
    this.setPadding(paddingLeft, paddingTop, paddingRight, padding)
}

/** Set view margin. Valid only when the layout params of view is [ViewGroup.MarginLayoutParams]. */
fun View.setMarginTop(margin: Int) {
    (this.layoutParams as? ViewGroup.MarginLayoutParams)?.apply {
        this.topMargin = margin
        layoutParams = this
    }
}

fun View.setMarginBottom(margin: Int) {
    (this.layoutParams as? ViewGroup.MarginLayoutParams)?.apply {
        this.bottomMargin = margin
        layoutParams = this
    }
}

fun View.setMarginLeft(margin: Int) {
    (this.layoutParams as? ViewGroup.MarginLayoutParams)?.apply {
        this.leftMargin = margin
        layoutParams = this
    }
}

fun View.setMarginRight(margin: Int) {
      (this.layoutParams as? ViewGroup.MarginLayoutParams)?.apply {
        this.rightMargin = margin
        layoutParams = this
    }
}

fun View.setMarginStart(margin: Int) {
    (this.layoutParams as? ViewGroup.MarginLayoutParams)?.apply {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            this.marginStart = margin
        } else {
            this.leftMargin = margin
        }
        layoutParams = this
    }
}

fun View.setMarginEnd(margin: Int) {
    (this.layoutParams as? ViewGroup.MarginLayoutParams)?.apply {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            this.marginEnd = margin
        } else {
            this.rightMargin = margin
        }
        layoutParams = this
    }
}

/** Scale when pressed. */
@SuppressLint("ClickableViewAccessibility")
fun View.pressScale(scaleFactor: Float = 0.96f, alphaFactor: Float? = null, duration: Long = 50) {
    val originAlpha = alpha.takeIf { it != 0F } ?: 1F
    setOnTouchListener { _, event ->
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                animate().setDuration(duration)
                    .scaleX(scaleFactor)
                    .scaleY(scaleFactor).apply {
                        if (alphaFactor != null) {
                            this.alpha(originAlpha * alphaFactor)
                        }
                    }
                    .start()
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                animate().setDuration(duration)
                    .scaleX(1f)
                    .scaleY(1f).apply {
                        if (alphaFactor != null) {
                            this.alpha(originAlpha)
                        }
                    }
                    .start()
            }
            else -> {
                // noop
            }
        }
        false
    }
}

@SuppressLint("ClickableViewAccessibility")
fun View.clearPressScale() {
    setOnTouchListener { _, _ ->
        false
    }
}