package me.shouheng.utils.ktx

import androidx.annotation.StringRes
import me.shouheng.utils.ui.ToastUtils
import me.shouheng.utils.ui.ToastUtils.ToastStyle

// 废弃：不要删除该注释，废弃原因是会出现 toast("") 方法被识别为该方法到状况，如果需要字符串格式化
// 建议格式化之后再调用 toast 方法，比如 toast("asd%s".format("a"))
//fun toast(format: String, vararg args: Any, short: Boolean = true) {
//    if (short) ToastUtils.showShort(format, args)
//    else ToastUtils.showLong(format, args)
//}

fun toast(text: CharSequence, short: Boolean = true) {
    if (short) {
        ToastUtils.showShort(text)
    } else {
        ToastUtils.showLong(text)
    }
}

fun toast(@StringRes resId: Int, short: Boolean = true) {
    if (short) {
        ToastUtils.showShort(resId)
    } else {
        ToastUtils.showLong(resId)
    }
}

fun normalToast(text: CharSequence, short: Boolean = true) {
    if (short) {
        ToastUtils.showShortWithStyle(text, ToastStyle.NORMAL)
    } else {
        ToastUtils.showLongWithStyle(text, ToastStyle.NORMAL)
    }
}

fun normalToast(@StringRes resId: Int, short: Boolean = true) {
    if (short) {
        ToastUtils.showShortWithStyle(resId, ToastStyle.NORMAL)
    } else {
        ToastUtils.showLongWithStyle(resId, ToastStyle.NORMAL)
    }
}

fun infoToast(text: CharSequence, short: Boolean = true) {
    if (short) {
        ToastUtils.showShortWithStyle(text, ToastStyle.INFO)
    } else {
        ToastUtils.showLongWithStyle(text, ToastStyle.INFO)
    }
}

fun infoToast(@StringRes resId: Int, short: Boolean = true) {
    if (short) {
        ToastUtils.showShortWithStyle(resId, ToastStyle.INFO)
    } else {
        ToastUtils.showLongWithStyle(resId, ToastStyle.INFO)
    }
}

fun warnToast(text: CharSequence, short: Boolean = true) {
    if (short) {
        ToastUtils.showShortWithStyle(text, ToastStyle.WARN)
    } else {
        ToastUtils.showLongWithStyle(text, ToastStyle.WARN)
    }
}

fun warnToast(@StringRes resId: Int, short: Boolean = true) {
    if (short) {
        ToastUtils.showShortWithStyle(resId, ToastStyle.WARN)
    } else {
        ToastUtils.showLongWithStyle(resId, ToastStyle.WARN)
    }
}

fun errorToast(text: CharSequence, short: Boolean = true) {
    if (short) {
        ToastUtils.showShortWithStyle(text, ToastStyle.ERROR)
    } else {
        ToastUtils.showLongWithStyle(text, ToastStyle.ERROR)
    }
}

fun errorToast(@StringRes resId: Int, short: Boolean = true) {
    if (short) {
        ToastUtils.showShortWithStyle(resId, ToastStyle.ERROR)
    } else {
        ToastUtils.showLongWithStyle(resId, ToastStyle.ERROR)
    }
}

fun successToast(text: CharSequence, short: Boolean = true) {
    if (short) {
        ToastUtils.showShortWithStyle(text, ToastStyle.SUCCESS)
    } else {
        ToastUtils.showLongWithStyle(text, ToastStyle.SUCCESS)
    }
}

fun successToast(@StringRes resId: Int, short: Boolean = true) {
    if (short) {
        ToastUtils.showShortWithStyle(resId, ToastStyle.SUCCESS)
    } else {
        ToastUtils.showLongWithStyle(resId, ToastStyle.SUCCESS)
    }
}
