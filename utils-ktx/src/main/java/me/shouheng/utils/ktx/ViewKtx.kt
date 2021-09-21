package me.shouheng.utils.ktx

import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import me.shouheng.utils.ui.ImageUtils
import me.shouheng.utils.ui.ViewUtils

fun Float.dp2px(): Int = ViewUtils.dp2px(this)

/** Get pixels of given dp */
fun Float.dp(): Int = ViewUtils.dp2px(this)

fun Float.px2dp(): Int = ViewUtils.px2dp(this)

fun Float.sp2px(): Int = ViewUtils.sp2px(this)

/** Get pixels of given sp */
fun Float.sp(): Int = ViewUtils.sp2px(this)

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
