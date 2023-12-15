package me.shouheng.utils.ktx

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import me.shouheng.utils.ui.ImageUtils
import me.shouheng.utils.ui.ViewUtils


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

/** Set view height. */
fun View.setHeight(height: Int) {
    val layoutParams = this.layoutParams
    layoutParams?.height = height
    this.layoutParams = layoutParams
}

/** Set view width. */
fun View.setWidth(width: Int) {
    val layoutParams = this.layoutParams
    layoutParams?.width = width
    this.layoutParams = layoutParams
}

fun View.setBackgroundCompact(drawable: Drawable) { ViewUtils.setBackground(this, drawable) }

fun View.toBitmap() { ImageUtils.view2Bitmap(this) }

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