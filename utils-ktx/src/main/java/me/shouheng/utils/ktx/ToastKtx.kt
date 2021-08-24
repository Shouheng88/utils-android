package me.shouheng.utils.ktx

import androidx.annotation.StringRes
import me.shouheng.utils.ui.ToastUtils

fun toast(text: CharSequence, short: Boolean = true) {
    if (short) ToastUtils.showShort(text)
    else ToastUtils.showLong(text)
}

fun toast(@StringRes resId: Int, short: Boolean = true) {
    if (short) ToastUtils.showShort(resId)
    else ToastUtils.showLong(resId)
}

fun toast(format: String, vararg args: Any, short: Boolean = true) {
    if (short) ToastUtils.showShort(format, args)
    else ToastUtils.showLong(format, args)
}
