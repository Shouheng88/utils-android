package me.shouheng.utils.ktx

import me.shouheng.utils.stability.L

/** A safer way to print log. The 'block' block won't even executed if the log switch is off. */
inline fun logv(block: () -> Any) {
    if (L.isLogSwitchOff()) return
    L.v(block.invoke())
}

inline fun logd(block: () -> Any) {
    if (L.isLogSwitchOff()) return
    L.d(block.invoke())
}

inline fun logi(block: () -> Any) {
    if (L.isLogSwitchOff()) return
    L.i(block.invoke())
}

inline fun logw(block: () -> Any) {
    if (L.isLogSwitchOff()) return
    L.w(block.invoke())
}

inline fun loge(block: () -> Any) {
    if (L.isLogSwitchOff()) return
    L.e(block.invoke())
}

inline fun loga(block: () -> Any) {
    if (L.isLogSwitchOff()) return
    L.a(block.invoke())
}

inline fun logfile(block: () -> Any) {
    if (L.isLogSwitchOff()) return
    L.file(block.invoke())
}

inline fun logjson(block: () -> String) {
    if (L.isLogSwitchOff()) return
    L.json(block.invoke())
}

inline fun logxml(block: () -> String) {
    if (L.isLogSwitchOff()) return
    L.xml(block.invoke())
}
