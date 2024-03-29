package me.shouheng.utils.ktx

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import androidx.annotation.ColorInt
import me.shouheng.utils.data.RegexUtils

fun CharSequence.isMatch(regex: String): Boolean = RegexUtils.isMatch(regex, this)

fun CharSequence.isMobileSimple(): Boolean = RegexUtils.isMobileSimple(this)

fun CharSequence.isMobileExact(): Boolean = RegexUtils.isMobileExact(this)

fun CharSequence.isEmail(): Boolean = RegexUtils.isEmail(this)

fun CharSequence.isValidChineseIdCard(): Boolean = RegexUtils.isValidChineseIdCard(this)

fun CharSequence.isBankCard(): Boolean = RegexUtils.isBankCard(this)

fun CharSequence.isNumber(): Boolean = RegexUtils.isNumber(this)

/** Get colored text from text. */
fun CharSequence.colored(@ColorInt color: Int): CharSequence {
    val sb = SpannableStringBuilder(this)
    sb.setSpan(ForegroundColorSpan(color), 0, this.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return sb
}

/** Set text underlined. */
fun CharSequence.underlined(): CharSequence {
    val sb = SpannableStringBuilder(this)
    sb.setSpan(UnderlineSpan(), 0, this.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return sb
}
