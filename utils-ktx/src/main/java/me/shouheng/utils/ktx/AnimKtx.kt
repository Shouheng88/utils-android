package me.shouheng.utils.ktx

import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import androidx.annotation.ColorInt
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation.AnimationListener
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import me.shouheng.utils.ui.AnimUtils
import me.shouheng.utils.ui.AnimUtils.UIDirection

fun View.fadeIn(duration: Int, listener: AnimationListener?, isNeedAnimation: Boolean): AlphaAnimation {
    return AnimUtils.fadeIn(this, duration, listener, isNeedAnimation)
}

fun View.fadeOut(duration: Int, listener: AnimationListener?, isNeedAnimation: Boolean): AlphaAnimation {
    return AnimUtils.fadeOut(this, duration, listener, isNeedAnimation)
}

fun View.slideIn(duration: Int, listener: AnimationListener?, isNeedAnimation: Boolean, @UIDirection direction: Int): TranslateAnimation? {
    return AnimUtils.slideIn(this, duration, listener, isNeedAnimation, direction)
}

fun View.slideOut(duration: Int, listener: AnimationListener?, isNeedAnimation: Boolean, @UIDirection direction: Int): TranslateAnimation? {
    return AnimUtils.slideOut(this, duration, listener, isNeedAnimation, direction)
}

fun View.shining(duration: Int, repeatCount: Int, vararg values: Float): ObjectAnimator {
    return AnimUtils.shining(this, duration, repeatCount, *values)
}

fun changeColor(@ColorInt beforeColor: Int, @ColorInt afterColor: Int, duration: Long, listener: (color: Int) -> Unit): ValueAnimator {
    return AnimUtils.changeColor(beforeColor, afterColor, duration, listener)
}

fun View.shake(): TranslateAnimation = AnimUtils.shake(this)

fun View.zoomIn(scale: Float, dist: Float, duration: Long): AnimatorSet = AnimUtils.zoomIn(this, scale, dist, duration)

fun View.zoomOut(scale: Float, duration: Long): AnimatorSet = AnimUtils.zoomOut(this, scale, duration)

fun View.scaleUpDown(duration: Long): ScaleAnimation = AnimUtils.scaleUpDown(this, duration)

fun View.animateHeight(start: Int, end: Int): ValueAnimator = AnimUtils.animateHeight(this, start, end)

fun View.popupIn(duration: Long): ObjectAnimator = AnimUtils.popupIn(this, duration)

fun View.popupOut(duration: Long, animatorListenerAdapter: AnimatorListenerAdapter?): ObjectAnimator {
    return AnimUtils.popupOut(this, duration, animatorListenerAdapter)
}

