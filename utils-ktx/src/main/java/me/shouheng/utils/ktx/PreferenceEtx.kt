package me.shouheng.utils.ktx

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.preference.Preference

abstract class PreferenceNoDoubleClickListener : Preference.OnPreferenceClickListener {

    private var lastClickTime: Long = 0

    override fun onPreferenceClick(preference: Preference): Boolean {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime
            return onNoDoubleClick(preference)
        }
        return true
    }

    protected abstract fun onNoDoubleClick(preference: Preference): Boolean

    companion object {
        var MIN_CLICK_DELAY_TIME                       = 500L
    }
}

/** Tint [Preference.getIcon] if exists. */
fun Preference.tint(@ColorInt color: Int) = apply {
    icon = icon?.tint(color)
}

/** Set [Preference] icon with resource id and color. */
fun Preference.setIcon(@DrawableRes drawableRes: Int, @ColorInt color: Int) = apply {
    icon = drawableOf(drawableRes).tint(color)
}

/** Set [Preference] click event listener. */
fun Preference.onClick(click: () -> Boolean) = apply {
    this.setOnPreferenceClickListener {
        return@setOnPreferenceClickListener click.invoke()
    }
}

/** Set [Preference] click event listener. */
fun Preference.onDebouncedClick(click: (preference: Preference) -> Boolean) = apply {
    this.onPreferenceClickListener = object: PreferenceNoDoubleClickListener() {
        override fun onNoDoubleClick(preference: Preference): Boolean {
            return click.invoke(preference)
        }
    }
}

/** Set [Preference] change event listener.] */
fun Preference.onChange(
    onChange: (preference: Preference, newValue: Any) -> Boolean
) = apply {
    this.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { preference, newValue ->
        return@OnPreferenceChangeListener onChange.invoke(preference, newValue)
    }
}
