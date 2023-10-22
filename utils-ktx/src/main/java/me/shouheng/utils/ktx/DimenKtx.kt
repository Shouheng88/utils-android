package me.shouheng.utils.ktx

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
