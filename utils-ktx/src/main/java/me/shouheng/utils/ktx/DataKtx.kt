package me.shouheng.utils.ktx

import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import androidx.annotation.IntRange
import me.shouheng.utils.ui.ColorUtils

/** Get color with alpha from given color. */
@ColorInt fun Int.withAlpha(@FloatRange(from = .0, to = 1.0) alpha: Float, override: Boolean = true): Int {
    return ColorUtils.setColorAlpha(this, alpha, override)
}

/** Get color with alpha from given color. */
@ColorInt fun Int.withAlpha(@IntRange(from = 0, to = 255) alpha: Int, override: Boolean = true): Int {
    return ColorUtils.setColorAlpha(this, alpha/255.0f, override)
}
