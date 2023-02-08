package me.shouheng.utils.ktx

/**
 * 安全的 for each 便利拓展方法
 *
 * @param eachSafe true 时表示一个 action 失败遍历仍然会继续，false 时表示一个 action 失败整个遍历都会结束
 * @param action 要执行的逻辑
 */
inline fun <T> Iterable<T>.safeForEach(
    eachSafe: Boolean = false,
    action: (T) -> Unit,
) {
    if (BuildConfig.DEBUG) {
        for (element in this) action(element)
    } else {
        if (eachSafe) {
            for (element in this) {
                try {
                    action(element)
                } catch (ex: Throwable) {
                    ex.printStackTrace()
                }
            }
        } else {
            try {
                for (element in this) action(element)
            } catch (ex: Throwable) {
                ex.printStackTrace()
            }
        }
    }
}
