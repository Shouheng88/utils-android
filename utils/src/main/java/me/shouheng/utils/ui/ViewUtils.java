package me.shouheng.utils.ui;

import android.os.Build;
import android.view.View;
import android.view.animation.AlphaAnimation;

/**
 * @author WngShhng (shouheng2015@gmail.com)
 * @version 2019/5/9 22:38
 */
public final class ViewUtils {

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
