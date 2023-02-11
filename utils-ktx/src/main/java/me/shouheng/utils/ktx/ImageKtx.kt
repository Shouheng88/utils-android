package me.shouheng.utils.ktx

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import androidx.annotation.ColorInt
import androidx.annotation.IntRange
import androidx.annotation.RequiresApi
import me.shouheng.utils.ui.ImageUtils
import java.io.File

fun Bitmap.toBytes(format: Bitmap.CompressFormat): ByteArray = ImageUtils.bitmap2Bytes(this, format)

fun Bitmap.toDrawable(): Drawable = ImageUtils.bitmap2Drawable(this)

fun Bitmap.scale(width: Int, height: Int): Bitmap = ImageUtils.scale(this, width, height)

fun Bitmap.scale(width: Int, height: Int, recycle: Boolean): Bitmap = ImageUtils.scale(this, width, height, recycle)

fun Bitmap.scale(scaleWidth: Float, scaleHeight: Float): Bitmap = ImageUtils.scale(this, scaleWidth, scaleHeight)

fun Bitmap.scale(scaleWidth: Float, scaleHeight: Float, recycle: Boolean): Bitmap = ImageUtils.scale(this, scaleWidth, scaleHeight, recycle)

fun Bitmap.clip(x: Int, y: Int, width: Int, height: Int): Bitmap = ImageUtils.clip(this, x, y, width, height)

fun Bitmap.clip(x: Int, y: Int, width: Int, height: Int, recycle: Boolean): Bitmap = ImageUtils.clip(this, x, y, width, height, recycle)

fun Bitmap.rotate(degree: Int, px: Float, py: Float): Bitmap = ImageUtils.rotate(this, degree, px, py)

fun Bitmap.rotate(degree: Int, px: Float, py: Float, recycle: Boolean): Bitmap = ImageUtils.rotate(this, degree, px, py, recycle)

fun Bitmap.toRound(): Bitmap = ImageUtils.toRound(this)

fun Bitmap.toRound(recycle: Boolean): Bitmap = ImageUtils.toRound(this, recycle)

fun Bitmap.toAlpha(): Bitmap = ImageUtils.toAlpha(this)

fun Bitmap.toAlpha(recycle: Boolean): Bitmap = ImageUtils.toAlpha(this, recycle)

fun Bitmap.toGray(): Bitmap = ImageUtils.toGray(this)

fun Bitmap.toGray(recycle: Boolean): Bitmap = ImageUtils.toGray(this, recycle)

fun Bitmap.save(filePath: String, format: Bitmap.CompressFormat): Boolean = ImageUtils.save(this, filePath, format)

fun Bitmap.save(file: File, format: Bitmap.CompressFormat): Boolean = ImageUtils.save(this, file, format)

fun Bitmap.save(filePath: String, format: Bitmap.CompressFormat, recycle: Boolean): Boolean = ImageUtils.save(this, filePath, format, recycle)

fun Bitmap.save(file: File, format: Bitmap.CompressFormat, recycle: Boolean): Boolean = ImageUtils.save(this, file, format, recycle)

fun Bitmap.compressByScale(newWidth: Int, newHeight: Int): Bitmap = ImageUtils.compressByScale(this, newWidth, newHeight)

fun Bitmap.compressByScale(newWidth: Int, newHeight: Int, recycle: Boolean): Bitmap = ImageUtils.compressByScale(this, newWidth, newHeight, recycle)

fun Bitmap.compressByScale(scaleWidth: Float, scaleHeight: Float): Bitmap = ImageUtils.compressByScale(this, scaleWidth, scaleHeight)

fun Bitmap.compressByScale(scaleWidth: Float, scaleHeight: Float, recycle: Boolean): Bitmap = ImageUtils.compressByScale(this, scaleWidth, scaleHeight, recycle)

fun Bitmap.compressByQuality(@IntRange(from = 0, to = 100) quality: Int): Bitmap = ImageUtils.compressByQuality(this, quality)

fun Bitmap.compressByQuality(@IntRange(from = 0, to = 100) quality: Int, recycle: Boolean): Bitmap = ImageUtils.compressByQuality(this, quality, recycle)

fun Bitmap.compressByQuality(maxByteSize: Long): Bitmap = ImageUtils.compressByQuality(this, maxByteSize)

fun Bitmap.compressByQuality(maxByteSize: Long, recycle: Boolean): Bitmap = ImageUtils.compressByQuality(this, maxByteSize, recycle)

fun Bitmap.compressBySampleSize(sampleSize: Int): Bitmap = ImageUtils.compressBySampleSize(this, sampleSize)

fun Bitmap.compressBySampleSize(sampleSize: Int, recycle: Boolean): Bitmap = ImageUtils.compressBySampleSize(this, sampleSize, recycle)

fun Bitmap.compressBySampleSize(maxWidth: Int, maxHeight: Int): Bitmap = ImageUtils.compressBySampleSize(this, maxWidth, maxHeight)

fun Bitmap.compressBySampleSize(maxWidth: Int, maxHeight: Int, recycle: Boolean): Bitmap = ImageUtils.compressBySampleSize(this, maxWidth, maxHeight, recycle)

fun Drawable.toBitmap(): Bitmap = ImageUtils.drawable2Bitmap(this)

fun Drawable.tint(@ColorInt color: Int): Drawable = ImageUtils.tintDrawable(this, color)

/** A gradient drawable builder. */
class GradientDrawableBuilder {

    private val gradientDrawable = GradientDrawable()

    fun withShape(shape: Int) = apply {
        gradientDrawable.shape = shape
    }

    fun withColor(@ColorInt color: Int) = apply {
        gradientDrawable.setColor(color)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    fun withColors(@ColorInt colors: IntArray) = apply {
        gradientDrawable.colors = colors
    }

    fun withStroke(strokeWidth: Int, @ColorInt strokeColor: Int) = apply {
        gradientDrawable.setStroke(strokeWidth, strokeColor)
    }

    fun withRadius(radius: Float) = apply {
        gradientDrawable.cornerRadius = radius
    }

    fun withRadius(topLeftRadius: Float,
                   topRightRadius: Float,
                   bottomLeftRadius: Float,
                   bottomRightRadius: Float) = apply {
        gradientDrawable.cornerRadii = floatArrayOf(
            topLeftRadius, topLeftRadius,
            topRightRadius, topRightRadius,
            bottomRightRadius, bottomRightRadius,
            bottomLeftRadius, bottomLeftRadius
        )
    }

    fun withGradientType(gradientType: Int) = apply {
        gradientDrawable.gradientType = gradientType
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    fun withOrientation(orientation: GradientDrawable.Orientation) = apply {
        gradientDrawable.orientation = orientation
    }

    fun withGradientRadius(gradientRadius: Float) = apply {
        gradientDrawable.gradientRadius = gradientRadius
    }

    fun build(): GradientDrawable = gradientDrawable
}
