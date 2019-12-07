package me.shouheng.utils.ui;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;

/**
 * 颜色相关
 *
 * @author <a href="mailto:shouheng2015@gmail.com">WngShhng</a>
 * @version 2019-10-27 15:27
 */
public final class ColorUtils {

    public static int setColorAlpha(@ColorInt int color, @FloatRange(from = 0, to = 1) float alpha){
        return setColorAlpha(color, alpha, true);
    }

    /**
     * 设置颜色的 alpha 值
     *
     * @param color    需要被设置的颜色值
     * @param alpha    取值为 [0,1]，0 表示全透明，1 表示不透明
     * @param override 覆盖原本的 alpha
     * @return         返回改变了 alpha 值的颜色值
     */
    public static int setColorAlpha(@ColorInt int color, @FloatRange(from = 0, to = 1) float alpha, boolean override) {
        int origin = override ? 0xff : (color >> 24) & 0xff;
        return color & 0x00ffffff | (int) (alpha * origin) << 24;
    }

    /**
     * 根据比例，在两个 color 值之间计算出一个 color 值
     * <b>注意该方法是 ARGB 通道分开计算比例的</b>
     *
     * @param fromColor 开始的 color 值
     * @param toColor   最终的 color 值
     * @param fraction  比例，取值为 [0,1]，为 0 时返回 fromColor， 为 1 时返回 toColor
     * @return          计算出的 color 值
     */
    public static int computeColor(@ColorInt int fromColor, @ColorInt int toColor, @FloatRange(from = 0, to = 1) float fraction) {
        fraction = Math.max(Math.min(fraction, 1), 0);

        int minColorA = Color.alpha(fromColor);
        int maxColorA = Color.alpha(toColor);
        int resultA = (int) ((maxColorA - minColorA) * fraction) + minColorA;

        int minColorR = Color.red(fromColor);
        int maxColorR = Color.red(toColor);
        int resultR = (int) ((maxColorR - minColorR) * fraction) + minColorR;

        int minColorG = Color.green(fromColor);
        int maxColorG = Color.green(toColor);
        int resultG = (int) ((maxColorG - minColorG) * fraction) + minColorG;

        int minColorB = Color.blue(fromColor);
        int maxColorB = Color.blue(toColor);
        int resultB = (int) ((maxColorB - minColorB) * fraction) + minColorB;

        return Color.argb(resultA, resultR, resultG, resultB);
    }

    /**
     * 将 color 颜色值转换为十六进制字符串
     *
     * @param color 颜色值
     * @return      转换后的字符串
     */
    public static String colorToString(@ColorInt int color) {
        return String.format("#%08X", color);
    }

    private ColorUtils() {
        throw new UnsupportedOperationException();
    }
}
