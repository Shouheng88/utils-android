package me.shouheng.utils.ktx

import me.shouheng.utils.stability.L

fun logv(vararg contents: Any) {
    L.v(contents)
}

fun logd(vararg contents: Any) {
    L.d(contents)
}

fun logi(vararg contents: Any) {
    L.i(contents)
}

fun logw(vararg contents: Any) {
    L.w(contents)
}

fun loge(vararg contents: Any) {
    L.e(contents)
}

fun loga(vararg contents: Any) {
    L.a(contents)
}