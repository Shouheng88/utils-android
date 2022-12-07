package me.shouheng.utils.ktx

import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import androidx.annotation.IntRange
import me.shouheng.utils.ui.ColorUtils

// The kotlin extensions for basic data types.

/** A group of constants of alpha from percent to dex. */
const val ALPHA_15_PERCENT = 0x26 // 15%
const val ALPHA_20_PERCENT = 0x33 // 20%
const val ALPHA_30_PERCENT = 0x4D // 30%
const val ALPHA_45_PERCENT = 0x66 // 45%
const val ALPHA_50_PERCENT = 0x80 // 50%
const val ALPHA_60_PERCENT = 0x99 // 60%
const val ALPHA_90_PERCENT = 0xE5 // 90%

/** Get color with alpha from given color. */
@ColorInt fun Int.withAlpha(@FloatRange(from = .0, to = 1.0) alpha: Float, override: Boolean = true): Int {
    return ColorUtils.setColorAlpha(this, alpha, override)
}

/** Get color with alpha from given color. */
@ColorInt fun Int.withAlpha(@IntRange(from = 0, to = 255) alpha: Int, override: Boolean = true): Int {
    return ColorUtils.setColorAlpha(this, alpha/255.0f, override)
}

/** Save way to get sublist from list. */
fun <T> List<T>.partial(start: Int, end: Int): List<T> {
    val realStart = maxOf(start, 0)
    val realEnd = minOf(end, this.size)
    if (realStart > realEnd) return emptyList()
    return subList(realStart, realEnd)
}
