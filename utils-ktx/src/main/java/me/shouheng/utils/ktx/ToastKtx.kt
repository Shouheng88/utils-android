package me.shouheng.utils.ktx

import androidx.annotation.StringRes
import me.shouheng.utils.ui.ToastUtils

// 废弃：不要删除该注释，废弃原因是会出现 toast("") 方法被识别为该方法到状况，如果需要字符串格式化
// 建议格式化之后再调用 toast 方法，比如 toast("asd%s".format("a"))
//fun toast(format: String, vararg args: Any, short: Boolean = true) {
//    if (short) ToastUtils.showShort(format, args)
//    else ToastUtils.showLong(format, args)
//}

fun toast(text: CharSequence, short: Boolean = true) {
    if (short) ToastUtils.showShort(text)
    else ToastUtils.showLong(text)
}

fun toast(@StringRes resId: Int, short: Boolean = true) {
    if (short) ToastUtils.showShort(resId)
    else ToastUtils.showLong(resId)
}
