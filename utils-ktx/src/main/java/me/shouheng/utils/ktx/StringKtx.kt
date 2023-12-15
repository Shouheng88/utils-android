package me.shouheng.utils.ktx

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.annotation.ColorInt
import me.shouheng.utils.data.*
import me.shouheng.utils.data.function.StringFunction
import java.lang.NumberFormatException
import java.util.*

fun String.isSpace(): Boolean = StringUtils.isSpace(this)

fun String.isEmpty(): Boolean = StringUtils.isEmpty(this)

fun String.isTrimEmpty(): Boolean = StringUtils.isTrimEmpty(this)

fun String.upperFirstLetter(): String = StringUtils.upperFirstLetter(this)

fun String.lowerFirstLetter(): String = StringUtils.lowerFirstLetter(this)

fun String.asHtml(): Spanned = StringUtils.fromHtml(this)

fun String.md2(): String = EncryptUtils.md2(this)

fun String.md5(): String = EncryptUtils.md5(this)

fun String.md5(salt: String): String = EncryptUtils.md5(this, salt)

fun String.sha1(): String = EncryptUtils.sha1(this)

fun String.sha224(): String = EncryptUtils.sha224(this)

fun String.sha256(): String = EncryptUtils.sha256(this)

fun String.sha384(): String = EncryptUtils.sha384(this)

fun String.sha512(): String = EncryptUtils.sha512(this)

fun String.urlEncode(): String = EncodeUtils.urlEncode(this)

fun String.urlEncode(charset: String): String = EncodeUtils.urlEncode(this, charset)

fun String.urlDecode(): String = EncodeUtils.urlDecode(this)

fun String.urlDecode(charset: String): String = EncodeUtils.urlDecode(this, charset)

fun String.base64(): ByteArray = EncodeUtils.base64Encode(this)

fun String.base64Decode(): ByteArray = EncodeUtils.base64Decode(this)

/** Get partial of given string from [start] to [end] to avoid index out of bound exception. */
fun String.partial(start: Int, end: Int): String {
    var s = start; var e = end
    if (e > length) e = length
    if (s < 0) s = 0
    return substring(s, e)
}

/** Transform string to given type */
fun String.toDate(): Date = TimeUtils.toDate(this)

fun String.safeToBoolean(def: Boolean = false): Boolean = try { this.toBoolean() } catch (e: NumberFormatException) { def }

fun String.safeToInt(def: Int = 0): Int = try { this.toInt() } catch (e: NumberFormatException) { def }

fun String.safeToLong(def: Long = 0): Long = try { this.toLong() } catch (e: NumberFormatException) { def }

fun String.safeToFloat(def: Float = 0f): Float = try { this.toFloat() } catch (e: NumberFormatException) { def }

fun String.safeToDouble(def: Double = .0): Double = try { this.toDouble() } catch (e: NumberFormatException) { def }

/** Transform string to color. */
@ColorInt fun String.toColor(): Int = Color.parseColor(this)

/** Join string by current string as python, for example, ','.join([1,2,3]) is '1,2,3'. */
fun <E> String.join(c: Collection<E>): String = StringUtils.connect(c, this)

fun <E> String.join(c: Collection<E>, function: StringFunction<E>): String = StringUtils.connect(c, this, function)

fun String.colored(@ColorInt color: Int): CharSequence {
    val length = length
    return SpannableString(this).apply {
        this.setSpan(ForegroundColorSpan(color), 0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
}

