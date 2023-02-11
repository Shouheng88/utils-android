package me.shouheng.utils.ktx

import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.*
import android.view.animation.Animation.AnimationListener
import androidx.annotation.ColorInt
import me.shouheng.utils.ui.AnimUtils
import me.shouheng.utils.ui.AnimUtils.UIDirection

fun View.fadeIn(
    duration: Int,
    listener: AnimationListener?,
    isNeedAnimation: Boolean
): AlphaAnimation {
    return AnimUtils.fadeIn(this, duration, listener, isNeedAnimation)
}

fun View.fadeOut(
    duration: Int,
    listener: AnimationListener?,
    isNeedAnimation: Boolean
): AlphaAnimation {
    return AnimUtils.fadeOut(this, duration, listener, isNeedAnimation)
}

fun View.slideIn(
    duration: Int,
    listener: AnimationListener?,
    isNeedAnimation: Boolean,
    @UIDirection direction: Int
): TranslateAnimation? {
    return AnimUtils.slideIn(this, duration, listener, isNeedAnimation, direction)
}

fun View.slideOut(
    duration: Int,
    listener: AnimationListener?,
    isNeedAnimation: Boolean,
    @UIDirection direction: Int
): TranslateAnimation? {
    return AnimUtils.slideOut(this, duration, listener, isNeedAnimation, direction)
}

fun View.shining(
    duration: Int,
    repeatCount: Int,
    vararg values: Float
): ObjectAnimator {
    return AnimUtils.shining(this, duration, repeatCount, *values)
}

fun changeColor(
    @ColorInt beforeColor: Int,
    @ColorInt afterColor: Int,
    duration: Long,
    listener: (color: Int) -> Unit
): ValueAnimator {
    return AnimUtils.changeColor(beforeColor, afterColor, duration, listener)
}

fun View.changeBackgroundColor(
    @ColorInt beforeColor: Int,
    @ColorInt afterColor: Int,
    duration: Long
) {
    AnimUtils.changeBackgroundColor(this, beforeColor, afterColor, duration)
}

fun View.shake(): TranslateAnimation = AnimUtils.shake(this)

fun View.shake(@UIDirection direction: Int): TranslateAnimation = AnimUtils.shake(this, direction)

fun View.shake(
    delta: Float,
    cycles: Float,
    duration: Int,
    @UIDirection direction: Int
): TranslateAnimation = AnimUtils.shake(this, delta, cycles, duration, direction)

fun View.zoomIn(
    scale: Float,
    dist: Float,
    duration: Long
): AnimatorSet = AnimUtils.zoomIn(this, scale, dist, duration)

fun View.zoomOut(
    scale: Float,
    duration: Long
): AnimatorSet = AnimUtils.zoomOut(this, scale, duration)

fun View.scaleUpDown(duration: Long): ScaleAnimation = AnimUtils.scaleUpDown(this, duration)

fun View.animateHeight(start: Int, end: Int): ValueAnimator = AnimUtils.animateHeight(this, start, end)

fun View.popupIn(duration: Long): ObjectAnimator = AnimUtils.popupIn(this, duration)

fun View.popupOut(
    duration: Long,
    animatorListenerAdapter: AnimatorListenerAdapter?
): ObjectAnimator {
    return AnimUtils.popupOut(this, duration, animatorListenerAdapter)
}

fun View.rotate(
    duration: Long,
    repeatCount: Int = Animation.INFINITE,
    fromDegrees: Float = .0f,
    toDegrees: Float = 360f
): RotateAnimation {
    return AnimUtils.rotate(this, duration, repeatCount, fromDegrees, toDegrees)
}

fun View.scales(
    duration: Long,
    vararg scales: Float
): AnimationSet = AnimUtils.scales(this, duration, *scales)


fun View.scales(
    duration: Long,
    pivot: Float,
    pivotType: Int,
    vararg scales: Float
): AnimationSet = AnimUtils.scales(this, duration, pivot, pivotType, *scales)
