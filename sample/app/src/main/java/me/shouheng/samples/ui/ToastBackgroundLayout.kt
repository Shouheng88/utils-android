package me.shouheng.samples.ui

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.widget.FrameLayout
import me.shouheng.samples.R
import kotlin.math.abs

class ToastBackgroundLayout : FrameLayout {

    var mShadowColor: Int = 0
    var mShadowRadius: Float = 0.toFloat()
    var mCornerRadius: Float = 0.toFloat()
    var mDx: Float = 0.toFloat()
    var mDy: Float = 0.toFloat()
    var mBackgroundColor: Int = 0

    private var mInvalidateShadowOnSizeChanged = true
    private var mForceInvalidateShadow = false

    constructor(context: Context) : super(context) {
        initView(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(context, attrs)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (w > 0 && h > 0 && (background == null || mInvalidateShadowOnSizeChanged || mForceInvalidateShadow)) {
            mForceInvalidateShadow = false
            setBackgroundCompat(w, h)
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (mForceInvalidateShadow) {
            mForceInvalidateShadow = false
            setBackgroundCompat(right - left, bottom - top)
        }
    }

    fun setInvalidateShadowOnSizeChanged(invalidateShadowOnSizeChanged: Boolean) {
        mInvalidateShadowOnSizeChanged = invalidateShadowOnSizeChanged
    }

    fun invalidateShadow() {
        mForceInvalidateShadow = true
        background = null
        requestLayout()
        invalidate()
    }

    private fun initView(context: Context, attrs: AttributeSet?) {
        initAttributes(context, attrs)
        refreshPadding()
    }

    private fun refreshPadding() {
        val xPadding = (mShadowRadius + abs(mDx)).toInt()
        val yPadding = (mShadowRadius + abs(mDy)).toInt()
        setPadding(xPadding, yPadding, xPadding, yPadding)
    }

    private fun setBackgroundCompat(w: Int, h: Int) {
        if (w > 0 && h > 0) {
            val bitmap = createShadowBitmap(w, h, mCornerRadius, mShadowRadius, mDx, mDy, mShadowColor, Color.TRANSPARENT)
            val drawable = BitmapDrawable(resources, bitmap)
            background = drawable
        }
    }

    private fun initAttributes(context: Context, attrs: AttributeSet?) {
        val attr = getTypedArray(context, attrs, R.styleable.ShadowLayout) ?: return
        try {
            mCornerRadius = attr.getDimension(R.styleable.ShadowLayout_shadow_layout_radius, 0f)
            mShadowRadius = attr.getDimension(R.styleable.ShadowLayout_shadow_layout_blur, 0f)
            mDx = attr.getDimension(R.styleable.ShadowLayout_shadow_layout_offsetX, 0f)
            mDy = attr.getDimension(R.styleable.ShadowLayout_shadow_layout_offsetY, 0f)
            mShadowColor = attr.getColor(R.styleable.ShadowLayout_shadow_layout_color, 0x4BFFFFFF)
            mBackgroundColor = attr.getColor(R.styleable.ShadowLayout_shadow_layout_background_color, Integer.MIN_VALUE)
        } finally {
            attr.recycle()
        }
    }

    private fun getTypedArray(context: Context, attributeSet: AttributeSet?, attr: IntArray): TypedArray? {
        return context.obtainStyledAttributes(attributeSet, attr, 0, 0)
    }

    private fun createShadowBitmap(shadowWidth: Int, shadowHeight: Int, cornerRadius: Float, shadowRadius: Float,
                                   dx: Float, dy: Float, shadowColor: Int, fillColor: Int): Bitmap {
        val output: Bitmap = Bitmap.createBitmap(shadowWidth, shadowHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)

        val shadowRect = RectF(shadowRadius, shadowRadius, shadowWidth - shadowRadius, shadowHeight - shadowRadius)
        if (dy > 0) {
            shadowRect.top += dy
            shadowRect.bottom -= dy
        } else if (dy < 0) {
            shadowRect.top += abs(dy)
            shadowRect.bottom -= abs(dy)
        }

        if (dx > 0) {
            shadowRect.left += dx
            shadowRect.right -= dx
        } else if (dx < 0) {
            shadowRect.left += abs(dx)
            shadowRect.right -= abs(dx)
        }

        val paint = Paint()
        paint.isAntiAlias = true
        paint.color = fillColor
        paint.style = Paint.Style.FILL

        if (!isInEditMode) {
            paint.setShadowLayer(shadowRadius, dx, dy, shadowColor)
        }
        canvas.drawRoundRect(shadowRect, cornerRadius, cornerRadius, paint)
        if (mBackgroundColor != Integer.MIN_VALUE) {
            paint.clearShadowLayer()
            paint.color = mBackgroundColor
            val backgroundRect = RectF(paddingLeft.toFloat(), paddingTop.toFloat(),
                (width - paddingRight).toFloat(), (height - paddingBottom).toFloat())
            canvas.drawRoundRect(backgroundRect, cornerRadius, cornerRadius, paint)
        }

        return output
    }
}