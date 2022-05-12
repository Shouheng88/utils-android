package me.shouheng.utils.ktx

import android.graphics.Color
import android.text.Spanned
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

fun String.safeToInt(str: String, def: Int = 0): Int = try { str.toInt() } catch (e: NumberFormatException) { def }

fun String.safeToLong(str: String, def: Long = 0): Long = try { str.toLong() } catch (e: NumberFormatException) { def }

fun String.safeToFloat(str: String, def: Float = 0f): Float = try { str.toFloat() } catch (e: NumberFormatException) { def }

fun String.safeToDouble(str: String, def: Double = .0): Double = try { str.toDouble() } catch (e: NumberFormatException) { def }

/** Transform string to color. */
@ColorInt fun String.toColor(): Int = Color.parseColor(this)

/** Join string by current string as python, for example, ','.join([1,2,3]) is '1,2,3'. */
fun <E> String.join(c: Collection<E>): String = StringUtils.connect(c, this)

fun <E> String.join(c: Collection<E>, function: StringFunction<E>): String = StringUtils.connect(c, this, function)

fun CharSequence.equalsIgnoreCase(s: CharSequence): Boolean = StringUtils.equals(this, s)

fun CharSequence.isMatch(regex: String): Boolean = RegexUtils.isMatch(regex, this)

fun CharSequence.isMobileSimple(): Boolean = RegexUtils.isMobileSimple(this)

fun CharSequence.isMobileExact(): Boolean = RegexUtils.isMobileExact(this)

fun CharSequence.isEmail(): Boolean = RegexUtils.isEmail(this)

fun CharSequence.isValidChineseIdCard(): Boolean = RegexUtils.isValidChineseIdCard(this)

fun CharSequence.isBankCard(): Boolean = RegexUtils.isBankCard(this)

fun CharSequence.isNumber(): Boolean = RegexUtils.isNumber(this)
