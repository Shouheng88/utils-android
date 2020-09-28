package me.shouheng.utils.ktx

import android.annotation.TargetApi
import android.content.Context
import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build.VERSION_CODES
import android.support.annotation.*
import me.shouheng.utils.app.ResUtils
import java.io.InputStream

fun Context.getAttrFloatValue(attrRes: Int): Float = ResUtils.getAttrFloatValue(this, attrRes)

fun Context.getAttrColor(attrRes: Int): Int = ResUtils.getAttrColor(this, attrRes)

fun Context.getAttrColorStateList(attrRes: Int): ColorStateList? = ResUtils.getAttrColorStateList(this, attrRes)

fun Context.getAttrDrawable(attrRes: Int): Drawable? = ResUtils.getAttrDrawable(this, attrRes)

fun Context.getAttrDrawable(typedArray: TypedArray, index: Int): Drawable? = ResUtils.getAttrDrawable(this, typedArray, index)

fun Context.getAttrDimen(attrRes: Int): Int = ResUtils.getAttrDimen(this, attrRes)

fun getIntArray(@ArrayRes id: Int): IntArray? = ResUtils.getIntArray(id)

fun getTextArray(@ArrayRes id: Int): Array<CharSequence?>? = ResUtils.getTextArray(id)

fun getStringArray(@ArrayRes id: Int): Array<String?>? = ResUtils.getStringArray(id)

@ColorInt fun getColor(@ColorRes id: Int): Int = ResUtils.getColor(id)

fun getString(@StringRes id: Int): String? = ResUtils.getString(id)

fun getString(@StringRes id: Int, vararg formatArgs: Any?): String? = ResUtils.getString(id, *formatArgs)

fun getText(@StringRes id: Int): CharSequence? = ResUtils.getText(id)

fun getQuantityText(@PluralsRes id: Int, quantity: Int): CharSequence? = ResUtils.getQuantityText(id, quantity)

fun getQuantityString(@PluralsRes id: Int, quantity: Int): String? = ResUtils.getQuantityString(id, quantity)

fun getQuantityString(@PluralsRes id: Int, quantity: Int, vararg formatArgs: Any?): String? = ResUtils.getQuantityString(id, quantity, *formatArgs)

fun getDrawable(@DrawableRes id: Int): Drawable? = ResUtils.getDrawable(id)

@TargetApi(VERSION_CODES.O)
fun getFont(@FontRes id: Int): Typeface? = ResUtils.getFont(id)

fun getDimension(@DimenRes id: Int): Float = ResUtils.getDimension(id)

fun getBoolean(@BoolRes id: Int): Boolean = ResUtils.getBoolean(id)

fun getInteger(@IntegerRes id: Int): Int = ResUtils.getInteger(id)

fun openRawResource(@RawRes id: Int): InputStream? = ResUtils.openRawResource(id)

fun openRawResourceFd(@RawRes id: Int): AssetFileDescriptor? = ResUtils.openRawResourceFd(id)

fun getAssets(): AssetManager? = ResUtils.getAssets()
