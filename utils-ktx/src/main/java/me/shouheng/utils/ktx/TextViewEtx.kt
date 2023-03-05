package me.shouheng.utils.ktx

import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.os.Build
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi

/** Set top drawable. */
fun TextView.setDrawableTop(@DrawableRes redId: Int) {
    this.setDrawableTop(drawableOf(redId))
}

fun TextView.setDrawableTop(drawable: Drawable) {
    this.setCompoundDrawablesWithIntrinsicBounds(
        null, drawable, null, null)
}

/** Set left drawable. */
fun TextView.setDrawableLeft(@DrawableRes redId: Int) {
    this.setDrawableLeft(drawableOf(redId))
}

fun TextView.setDrawableLeft(drawable: Drawable) {
    this.setCompoundDrawablesWithIntrinsicBounds(
        drawable, null, null, null)
}

/** Set right drawable. */
fun TextView.setDrawableRight(@DrawableRes redId: Int) {
    this.setDrawableRight(drawableOf(redId))
}

fun TextView.setDrawableRight(drawable: Drawable) {
    this.setCompoundDrawablesWithIntrinsicBounds(
        null, null, drawable, null)
}

/** Set bottom drawable. */
fun TextView.setDrawableBottom(@DrawableRes redId: Int) {
    this.setDrawableBottom(drawableOf(redId))
}

fun TextView.setDrawableBottom(drawable: Drawable) {
    this.setCompoundDrawablesWithIntrinsicBounds(
        null, null, null, drawable)
}

/** Set drawable with size. */
@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
fun TextView.setDrawableTop(@DrawableRes redId: Int, width: Int, height: Int) {
    this.setDrawableTop(drawableOf(redId), width, height)
}

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
fun TextView.setDrawableTop(drawable: Drawable, width: Int, height: Int) {
    drawable.setBounds(0, 0, width, height)
    this.setCompoundDrawablesRelative(null, drawable, null, null)
}

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
fun TextView.setDrawableBottom(@DrawableRes redId: Int, width: Int, height: Int) {
    this.setDrawableBottom(drawableOf(redId), width, height)
}

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
fun TextView.setDrawableBottom(drawable: Drawable, width: Int, height: Int) {
    drawable.setBounds(0, 0, width, height)
    this.setCompoundDrawablesRelative(null, null, null, drawable)
}

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
fun TextView.setDrawableLeft(@DrawableRes redId: Int, width: Int, height: Int) {
    this.setDrawableLeft(drawableOf(redId), width, height)
}

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
fun TextView.setDrawableLeft(drawable: Drawable, width: Int, height: Int) {
    drawable.setBounds(0, 0, width, height)
    this.setCompoundDrawablesRelative(drawable, null, null, null)
}

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
fun TextView.setDrawableRight(@DrawableRes redId: Int, width: Int, height: Int) {
    this.setDrawableRight(drawableOf(redId), width, height)
}

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
fun TextView.setDrawableRight(drawable: Drawable, width: Int, height: Int) {
    drawable.setBounds(0, 0, width, height)
    this.setCompoundDrawablesRelative(null, null, drawable, null)
}

/** Set under line. */
fun TextView.setUnderline() {
    this.paint.flags = Paint.UNDERLINE_TEXT_FLAG
}