package me.shouheng.utils.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;

import me.shouheng.utils.UtilsApp;

/**
 * Toast utils. Except basic displaying message by a bubble styled system toast.
 * You are also allowed to custom the view of toast by calling
 * {@link #setToastViewCallback(ToastViewCallback)} method.
 *
 * @author Shouheng Wang (shouheng2020@gmail.com)
 * @version 2019/5/12 18:19
 */
public final class ToastUtils {

    private static final int     COLOR_DEFAULT = 0xFEFFFFFF;
    private static final Handler HANDLER       = new Handler(Looper.getMainLooper());
    private static final String  NULL          = "null";

    private static Toast sToast;
    private static int sGravity     = -1;
    private static int sXOffset     = -1;
    private static int sYOffset     = -1;
    private static int sMsgColor    = COLOR_DEFAULT;
    private static int sMsgTextSize = -1;

    private static ToastViewCallback toastViewCallback;

    public static void showShort(final CharSequence text) {
        show(text == null ? NULL : text, Toast.LENGTH_SHORT, ToastStyle.NORMAL, null);
    }

    public static void showShort(final CharSequence text, @ToastStyle int style) {
        show(text == null ? NULL : text, Toast.LENGTH_SHORT, style, null);
    }

    public static void showShort(final CharSequence text, ToastViewGetter getter) {
        show(text == null ? NULL : text, Toast.LENGTH_SHORT, ToastStyle.NORMAL, getter);
    }

    public static void showShort(@StringRes final int resId) {
        show(resId, Toast.LENGTH_SHORT, ToastStyle.NORMAL, null);
    }

    public static void showShort(@StringRes final int resId, @ToastStyle int style) {
        show(resId, Toast.LENGTH_SHORT, style, null);
    }

    public static void showShort(@StringRes final int resId, ToastViewGetter getter) {
        show(resId, Toast.LENGTH_SHORT, ToastStyle.NORMAL, getter);
    }

    public static void showShort(@StringRes final int resId, final Object... args) {
        show(resId, Toast.LENGTH_SHORT, ToastStyle.NORMAL, null, args);
    }

    public static void showShort(final String format, final Object... args) {
        show(format, Toast.LENGTH_SHORT, ToastStyle.NORMAL, null, args);
    }

    public static void showLong(final CharSequence text) {
        show(text == null ? NULL : text, Toast.LENGTH_LONG, ToastStyle.NORMAL, null);
    }

    public static void showLong(final CharSequence text, @ToastStyle int style) {
        show(text == null ? NULL : text, Toast.LENGTH_LONG, style, null);
    }

    public static void showLong(final CharSequence text, ToastViewGetter getter) {
        show(text == null ? NULL : text, Toast.LENGTH_LONG, ToastStyle.NORMAL, getter);
    }

    public static void showLong(@StringRes final int resId) {
        show(resId, Toast.LENGTH_LONG, ToastStyle.NORMAL, null);
    }

    public static void showLong(@StringRes final int resId, @ToastStyle int style) {
        show(resId, Toast.LENGTH_LONG, style, null);
    }

    public static void showLong(@StringRes final int resId, ToastViewGetter getter) {
        show(resId, Toast.LENGTH_LONG, ToastStyle.NORMAL, getter);
    }

    public static void showLong(@StringRes final int resId, final Object... args) {
        show(resId, Toast.LENGTH_LONG, ToastStyle.NORMAL, null, args);
    }

    public static void showLong(final String format, final Object... args) {
        show(format, Toast.LENGTH_LONG, ToastStyle.NORMAL, null, args);
    }

    public static void setGravity(final int gravity, final int xOffset, final int yOffset) {
        sGravity = gravity;
        sXOffset = xOffset;
        sYOffset = yOffset;
    }

    public static void setMsgColor(@ColorInt final int msgColor) {
        sMsgColor = msgColor;
    }

    public static void setMsgTextSize(final int textSize) {
        sMsgTextSize = textSize;
    }

    public static void setToastViewCallback(ToastViewCallback toastViewCallback) {
        ToastUtils.toastViewCallback = toastViewCallback;
    }

    public static ToastViewCallback getToastViewCallback() {
        return toastViewCallback;
    }

    public static void cancel() {
        if (sToast != null) {
            sToast.cancel();
        }
    }

    /*---------------------------------- 内部方法 --------------------------------------*/

    private static void show(
            @StringRes final int resId,
            final int duration,
            @ToastStyle final int style,
            ToastViewGetter getter
    ) {
        show(UtilsApp.getApp().getResources().getText(resId).toString(), duration, style, getter);
    }

    private static void show(
            @StringRes final int resId,
            final int duration,
            @ToastStyle final int style,
            ToastViewGetter getter,
            final Object... args
    ) {
        show(String.format(UtilsApp.getApp().getResources().getString(resId), args), duration, style, getter);
    }

    private static void show(
            final String format,
            final int duration,
            @ToastStyle final int style,
            ToastViewGetter getter,
            final Object... args
    ) {
        String text;
        if (format == null) {
            text = NULL;
        } else {
            text = String.format(format, args);
            if (text == null) {
                text = NULL;
            }
        }
        show(text, duration, style, getter);
    }

    private static void show(
            final CharSequence text,
            final int duration,
            @ToastStyle final int style,
            final ToastViewGetter getter
    ) {
        HANDLER.post(new Runnable() {
            @SuppressLint("ShowToast")
            @Override
            public void run() {
                cancel();
                sToast = Toast.makeText(UtilsApp.getApp(), text, duration);
                View toastView = sToast.getView();
                TextView tvMessage = null;
                if (toastView != null) {
                    tvMessage = toastView.findViewById(android.R.id.message);
                }/* else {
                    tvMessage = new AppCompatTextView(UtilsApp.getApp());
                    tvMessage.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    tvMessage.setText(text);
                    sToast.setView(tvMessage);
                }*/
                if (tvMessage != null) {
                    if (sMsgColor != COLOR_DEFAULT) {
                        tvMessage.setTextColor(sMsgColor);
                    }
                    if (sMsgTextSize != -1) {
                        tvMessage.setTextSize(sMsgTextSize);
                    }
                }
                if (sGravity != -1 || sXOffset != -1 || sYOffset != -1) {
                    sToast.setGravity(sGravity, sXOffset, sYOffset);
                }
                // View from getter is prior then global toastViewCallback.
                if (getter != null) {
                    sToast.setView(getter.getView(text));
                } else {
                    View view;
                    if (toastViewCallback != null && (view = toastViewCallback.getView(text, style)) != null) {
                        sToast.setView(view);
                    }
                }
                showToast();
            }
        });
    }

    private static void showToast() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N_MR1) {
            try {
                // noinspection JavaReflectionMemberAccess
                Field field = View.class.getDeclaredField("mContext");
                field.setAccessible(true);
                field.set(sToast.getView(), new ApplicationContextWrapperForApi25());
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        sToast.show();
    }

    private static final class ApplicationContextWrapperForApi25 extends ContextWrapper {

        ApplicationContextWrapperForApi25() {
            super(UtilsApp.getApp());
        }

        @Override
        public Context getApplicationContext() {
            return this;
        }

        @Override
        public Object getSystemService(@NonNull String name) {
            if (Context.WINDOW_SERVICE.equals(name)) {
                // noinspection ConstantConditions
                return new WindowManagerWrapper(
                        (WindowManager) getBaseContext().getSystemService(name)
                );
            }
            return super.getSystemService(name);
        }

        private static final class WindowManagerWrapper implements WindowManager {

            private final WindowManager base;

            private WindowManagerWrapper(@NonNull WindowManager base) {
                this.base = base;
            }

            @Override
            public Display getDefaultDisplay() {
                return base.getDefaultDisplay();
            }

            @Override
            public void removeViewImmediate(View view) {
                base.removeViewImmediate(view);
            }

            @Override
            public void addView(View view, ViewGroup.LayoutParams params) {
                try {
                    base.addView(view, params);
                } catch (BadTokenException e) {
                    Log.e("WindowManagerWrapper", e.getMessage());
                } catch (Throwable throwable) {
                    Log.e("WindowManagerWrapper", "[addView]", throwable);
                }
            }

            @Override
            public void updateViewLayout(View view, ViewGroup.LayoutParams params) {
                base.updateViewLayout(view, params);
            }

            @Override
            public void removeView(View view) {
                base.removeView(view);
            }
        }
    }

    /** The custom toast view factory method. */
    public interface ToastViewCallback {
        /** Get normal styled toast view. */
        View getView(CharSequence text, @ToastStyle int style);
    }

    /** The custom toast view getter. */
    public interface ToastViewGetter {
        View getView(CharSequence text);
    }

    @IntDef({ToastStyle.NORMAL, ToastStyle.INFO, ToastStyle.WARN, ToastStyle.ERROR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ToastStyle {
        int NORMAL      = 0;
        int INFO        = 1;
        int WARN        = 2;
        int ERROR       = 3;
    }

    private ToastUtils() {
        throw new UnsupportedOperationException("u can't initialize me!");
    }
}
