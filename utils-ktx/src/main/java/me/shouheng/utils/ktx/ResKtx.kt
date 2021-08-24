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
import androidx.annotation.*
import me.shouheng.utils.app.ResUtils
import java.io.InputStream

fun Context.attrFloatOf(attrRes: Int): Float = ResUtils.getAttrFloatValue(this, attrRes)

fun Context.attrColorOf(attrRes: Int): Int = ResUtils.getAttrColor(this, attrRes)

fun Context.attrColorStateListOf(attrRes: Int): ColorStateList? = ResUtils.getAttrColorStateList(this, attrRes)

fun Context.attrDrawableOf(attrRes: Int): Drawable? = ResUtils.getAttrDrawable(this, attrRes)

fun Context.attrDrawableOf(typedArray: TypedArray, index: Int): Drawable? = ResUtils.getAttrDrawable(this, typedArray, index)

fun Context.attrDimenOf(attrRes: Int): Int = ResUtils.getAttrDimen(this, attrRes)

fun intArrayOf(@ArrayRes id: Int): IntArray = ResUtils.getIntArray(id)

fun textArrayOf(@ArrayRes id: Int): Array<CharSequence> = ResUtils.getTextArray(id)

fun stringArrayOf(@ArrayRes id: Int): Array<String> = ResUtils.getStringArray(id)

@ColorInt fun colorOf(@ColorRes id: Int): Int = ResUtils.getColor(id)

fun stringOf(@StringRes id: Int): String = ResUtils.getString(id)

fun stringOf(@StringRes id: Int, vararg formatArgs: Any): String = ResUtils.getString(id, *formatArgs)

fun textOf(@StringRes id: Int): CharSequence = ResUtils.getText(id)

fun quantityTextOf(@PluralsRes id: Int, quantity: Int): CharSequence = ResUtils.getQuantityText(id, quantity)

fun quantityStringOf(@PluralsRes id: Int, quantity: Int): String = ResUtils.getQuantityString(id, quantity)

fun quantityStringOf(@PluralsRes id: Int, quantity: Int, vararg formatArgs: Any): String = ResUtils.getQuantityString(id, quantity, *formatArgs)

fun drawableOf(@DrawableRes id: Int): Drawable = ResUtils.getDrawable(id)

@TargetApi(VERSION_CODES.O)
fun fontOf(@FontRes id: Int): Typeface = ResUtils.getFont(id)

fun dimenOf(@DimenRes id: Int): Float = ResUtils.getDimension(id)

fun booleanOf(@BoolRes id: Int): Boolean = ResUtils.getBoolean(id)

fun intOf(@IntegerRes id: Int): Int = ResUtils.getInteger(id)

fun openRawResourceOf(@RawRes id: Int): InputStream = ResUtils.openRawResource(id)

fun openRawResourceFdOf(@RawRes id: Int): AssetFileDescriptor = ResUtils.openRawResourceFd(id)

fun assetsOf(): AssetManager = ResUtils.getAssets()
