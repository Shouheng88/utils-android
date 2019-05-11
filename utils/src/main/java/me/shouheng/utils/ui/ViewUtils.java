package me.shouheng.utils.ui;

import android.os.Build;
import android.view.View;
import android.view.animation.AlphaAnimation;

import me.shouheng.utils.UtilsApp;

/**
 * @author WngShhng (shouheng2015@gmail.com)
 * @version 2019/5/9 22:38
 */
public final class ViewUtils {

    public static int dp2px(final float dpValue) {
        final float scale = UtilsApp.getApp().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dp(final float pxValue) {
        final float scale = UtilsApp.getApp().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int sp2px(final float spValue) {
        final float fontScale = UtilsApp.getApp().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int px2sp(final float pxValue) {
        final float fontScale = UtilsApp.getApp().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static void setAlpha(View v, float alpha) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            final AlphaAnimation animation = new AlphaAnimation(1F, alpha);
            animation.setFillAfter(true);
            v.startAnimation(animation);
        } else {
            v.setAlpha(alpha);
        }
    }

    /*----------------------------------inner methods--------------------------------------*/

    private ViewUtils() {
        throw new UnsupportedOperationException("u can't initialize me!");
    }
}
