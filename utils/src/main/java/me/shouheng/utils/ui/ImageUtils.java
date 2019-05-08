package me.shouheng.utils.ui;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.v4.graphics.drawable.DrawableCompat;

import me.shouheng.utils.app.ResUtils;

/**
 * 用来处理
 * 1. {@link Drawable}
 * 2. {@link android.graphics.Bitmap}
 * 的工具类
 *
 * @author WngShhng 2019-05-08 21:30
 */
public final class ImageUtils {

    public static Drawable tintDrawable(@DrawableRes int drawableRes, @ColorInt int color) {
        Drawable drawable = ResUtils.getDrawable(drawableRes);
        return tintDrawable(drawable, color);
    }

    /**
     * 对 Drawable 进行着色
     *
     * @param drawable 要着色的 Drawable
     * @param color 颜色
     * @return 着色之后的 Drawable
     */
    public static Drawable tintDrawable(Drawable drawable, @ColorInt int color) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable.mutate());
        DrawableCompat.setTintList(wrappedDrawable, ColorStateList.valueOf(color));
        return wrappedDrawable;
    }

    private ImageUtils() {
        throw new UnsupportedOperationException("u can't initialize me!");
    }

}
