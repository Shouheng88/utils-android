package me.shouheng.utils.ktx

import me.shouheng.utils.data.TimeUtils
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/** Format given date with [pattern]. */
fun Date.format(pattern: String) = SimpleDateFormat(pattern, Locale.getDefault()).format(this)

fun Date.format(format: DateFormat) = TimeUtils.toString(this, format)

/** Get current timestamp, date string etc. */
fun now(): Long = TimeUtils.now()

fun nowDate(): Date = TimeUtils.nowDate()

fun nowString(): String = TimeUtils.nowString()

fun nowString(format: DateFormat): String = TimeUtils.nowString(format)

/** Calculate time cost from [from] until now. */
fun cost(from: Long): Long = now() - from
