package me.shouheng.utils.ui;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Build;
import androidx.annotation.ColorInt;
import androidx.annotation.IntDef;
import androidx.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static me.shouheng.utils.ui.AnimUtils.UIDirection.BOTTOM_TO_TOP;
import static me.shouheng.utils.ui.AnimUtils.UIDirection.LEFT_TO_RIGHT;
import static me.shouheng.utils.ui.AnimUtils.UIDirection.RIGHT_TO_LEFT;
import static me.shouheng.utils.ui.AnimUtils.UIDirection.TOP_TO_BOTTOM;

/**
 * 动画相关
 *
 * @author <a href="mailto:shouheng2020@gmail.com">Shouheng Wang</a>
 * @version 2019-10-27 16:00
 */
public final class AnimUtils {

    /**
     * Rotate given view.
     *
     * @param view the view to rotate
     * @param duration the duration
     * @param repeatCount the repeat count, might be {@link Animation#INFINITE} to be infinite.
     * @param fromDegrees might be 0
     * @param toDegrees might be 360
     * @return the animation object.
     */
    public static RotateAnimation rotate(View view,
                                         long duration,
                                         int repeatCount,
                                         float fromDegrees,
                                         float toDegrees) {
        if (view == null) {
            return null;
        }
        RotateAnimation animRotate = new RotateAnimation(fromDegrees, toDegrees,
                RELATIVE_TO_SELF, 0.5f,
                RELATIVE_TO_SELF, 0.5f);
        animRotate.setDuration(duration);
        animRotate.setInterpolator(new LinearInterpolator());
        animRotate.setRepeatCount(repeatCount);
        view.startAnimation(animRotate);
        return animRotate;
    }

    /**
     * <p>对 View 做透明度变化的进场动画。</p>
     * <p>相关方法 {@link #fadeOut(View, int, Animation.AnimationListener, boolean)}</p>
     *
     * @param view            做动画的 View
     * @param duration        动画时长(毫秒)
     * @param listener        动画回调
     * @param isNeedAnimation 是否需要动画
     */
    public static AlphaAnimation fadeIn(View view,
                                        int duration,
                                        Animation.AnimationListener listener,
                                        boolean isNeedAnimation) {
        if (view == null) {
            return null;
        }
        if (isNeedAnimation) {
            view.setVisibility(View.VISIBLE);
            AlphaAnimation alpha = new AlphaAnimation(0, 1);
            alpha.setInterpolator(new DecelerateInterpolator());
            alpha.setDuration(duration);
            alpha.setFillAfter(true);
            if (listener != null) {
                alpha.setAnimationListener(listener);
            }
            view.startAnimation(alpha);
            return alpha;
        } else {
            view.setAlpha(1);
            view.setVisibility(View.VISIBLE);
            return null;
        }
    }

    /**
     * <p>对 View 做透明度变化的退场动画</p>
     * <p>相关方法 {@link #fadeIn(View, int, Animation.AnimationListener, boolean)}</p>
     *
     * @param view            做动画的 View
     * @param duration        动画时长(毫秒)
     * @param listener        动画回调
     * @param isNeedAnimation 是否需要动画
     */
    public static AlphaAnimation fadeOut(final View view,
                                         int duration,
                                         final Animation.AnimationListener listener,
                                         boolean isNeedAnimation) {
        if (view == null) {
            return null;
        }
        if (isNeedAnimation) {
            AlphaAnimation alpha = new AlphaAnimation(1, 0);
            alpha.setInterpolator(new DecelerateInterpolator());
            alpha.setDuration(duration);
            alpha.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    if (listener != null) {
                        listener.onAnimationStart(animation);
                    }
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    view.setVisibility(View.GONE);
                    if (listener != null) {
                        listener.onAnimationEnd(animation);
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    if (listener != null) {
                        listener.onAnimationRepeat(animation);
                    }
                }
            });
            view.startAnimation(alpha);
            return alpha;
        } else {
            view.setVisibility(View.GONE);
            return null;
        }
    }

    public static void clearValueAnimator(Animator animator) {
        if (animator != null) {
            animator.removeAllListeners();
            if (animator instanceof ValueAnimator) {
                ((ValueAnimator) animator).removeAllUpdateListeners();
            }

            if (Build.VERSION.SDK_INT >= 19) {
                animator.pause();
            }
            animator.cancel();
        }
    }

    /**
     * <p>对 View 做上下位移的进场动画</p>
     * <p>相关方法 {@link #slideOut(View, int, Animation.AnimationListener, boolean, int)}</p>
     *
     * @param view            做动画的 View
     * @param duration        动画时长(毫秒)
     * @param listener        动画回调
     * @param isNeedAnimation 是否需要动画
     * @param direction       进场动画的方向
     * @return                动画对应的 Animator 对象, 注意无动画时返回 null
     */
    @Nullable
    public static TranslateAnimation slideIn(final View view,
                                             int duration,
                                             final Animation.AnimationListener listener,
                                             boolean isNeedAnimation,
                                             @UIDirection int direction) {
        if (view == null) {
            return null;
        }
        if (isNeedAnimation) {
            TranslateAnimation translate = null;
            switch (direction) {
                case LEFT_TO_RIGHT:
                    translate = new TranslateAnimation(
                            RELATIVE_TO_SELF, -1f, RELATIVE_TO_SELF, 0f,
                            RELATIVE_TO_SELF, 0f, RELATIVE_TO_SELF, 0f
                    );
                    break;
                case TOP_TO_BOTTOM:
                    translate = new TranslateAnimation(
                            RELATIVE_TO_SELF, 0f, RELATIVE_TO_SELF, 0f,
                            RELATIVE_TO_SELF, -1f, RELATIVE_TO_SELF, 0f
                    );
                    break;
                case RIGHT_TO_LEFT:
                    translate = new TranslateAnimation(
                            RELATIVE_TO_SELF, 1f, RELATIVE_TO_SELF, 0f,
                            RELATIVE_TO_SELF, 0f, RELATIVE_TO_SELF, 0f
                    );
                    break;
                case BOTTOM_TO_TOP:
                    translate = new TranslateAnimation(
                            RELATIVE_TO_SELF, 0f, RELATIVE_TO_SELF, 0f,
                            RELATIVE_TO_SELF, 1f, RELATIVE_TO_SELF, 0f
                    );
                    break;
            }
            translate.setInterpolator(new DecelerateInterpolator());
            translate.setDuration(duration);
            translate.setFillAfter(true);
            if (listener != null) {
                translate.setAnimationListener(listener);
            }
            view.setVisibility(View.VISIBLE);
            view.startAnimation(translate);
            return translate;
        } else {
            view.clearAnimation();
            view.setVisibility(View.VISIBLE);

            return null;
        }
    }

    /**
     * <p>对 View 做上下位移的退场动画</p>
     * <p>相关方法 {@link #slideIn(View, int, Animation.AnimationListener, boolean, int)}</p>
     *
     * @param view            做动画的 View
     * @param duration        动画时长(毫秒)
     * @param listener        动画回调
     * @param isNeedAnimation 是否需要动画
     * @param direction       进场动画的方向
     * @return                动画对应的 Animator 对象, 注意无动画时返回 null
     */
    @Nullable
    public static TranslateAnimation slideOut(final View view,
                                              int duration,
                                              final Animation.AnimationListener listener,
                                              boolean isNeedAnimation,
                                              @UIDirection int direction) {
        if (view == null) {
            return null;
        }
        if (isNeedAnimation) {
            TranslateAnimation translate = null;
            switch (direction) {
                case LEFT_TO_RIGHT:
                    translate = new TranslateAnimation(
                            RELATIVE_TO_SELF, 0f, RELATIVE_TO_SELF, 1f,
                            RELATIVE_TO_SELF, 0f, RELATIVE_TO_SELF, 0f
                    );
                    break;
                case TOP_TO_BOTTOM:
                    translate = new TranslateAnimation(
                            RELATIVE_TO_SELF, 0f, RELATIVE_TO_SELF, 0f,
                            RELATIVE_TO_SELF, 0f, RELATIVE_TO_SELF, 1f
                    );
                    break;
                case RIGHT_TO_LEFT:
                    translate = new TranslateAnimation(
                            RELATIVE_TO_SELF, 0f, RELATIVE_TO_SELF, -1f,
                            RELATIVE_TO_SELF, 0f, RELATIVE_TO_SELF, 0f
                    );
                    break;
                case BOTTOM_TO_TOP:
                    translate = new TranslateAnimation(
                            RELATIVE_TO_SELF, 0f, RELATIVE_TO_SELF, 0f,
                            RELATIVE_TO_SELF, 0f, RELATIVE_TO_SELF, -1f
                    );
                    break;
            }
            translate.setInterpolator(new DecelerateInterpolator());
            translate.setDuration(duration);
            translate.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    if (listener != null) {
                        listener.onAnimationStart(animation);
                    }
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    view.setVisibility(View.GONE);
                    if (listener != null) {
                        listener.onAnimationEnd(animation);
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    if (listener != null) {
                        listener.onAnimationRepeat(animation);
                    }
                }
            });
            view.startAnimation(translate);
            return translate;
        } else {
            view.clearAnimation();
            view.setVisibility(View.GONE);
            return null;
        }
    }

    /**
     * 指定的控件闪烁，用在提示等各种场景中
     *
     * @param view        做动画的 View
     * @param duration    动画时长(毫秒)，参考 2888
     * @param repeatCount 动画重复的次数，参考 6
     * @param values      透明度改变的值，参考 0, 0.66f, 1.0f, 0
     */
    public static ObjectAnimator shining(View view, int duration, int repeatCount, float ...values) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", values);
        animator.setDuration(duration);
        animator.setRepeatCount(repeatCount);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.start();
        return animator;
    }

    /** Make given view shake */
    public static TranslateAnimation shake(View view) {
        return shake(view, UIDirection.LEFT_TO_RIGHT);
    }

    /** Make given view shake */
    public static TranslateAnimation shake(View view, @UIDirection int direction) {
        return shake(view, 15f, 4, 700, direction);
    }

    public static TranslateAnimation shake(View view,
                                           float delta,
                                           float cycles,
                                           int duration,
                                           @UIDirection int direction) {
        TranslateAnimation animation;
        if (direction == LEFT_TO_RIGHT || direction == RIGHT_TO_LEFT) {
            animation = new TranslateAnimation(0f, delta, 0f, 0f);
        } else {
            animation = new TranslateAnimation(0f, 0f, delta, 0f);
        }
        animation.setDuration(duration);
        Interpolator interpolator = new CycleInterpolator(cycles);
        animation.setInterpolator(interpolator);
        view.startAnimation(animation);
        return animation;
    }

    /**
     * Scale from one to another continuously.
     *
     * @see #scales(View, long, float, int, float...)
     */
    public static AnimationSet scales(View view, long duration, float ...scales) {
        return scales(view, duration, .5f, RELATIVE_TO_SELF, scales);
    }

    /**
     * Scale from one to another continuously.
     *
     * @param scales For example, to scale from 1f->0.5f->1.5f->0.5f->1f,
     *               you should pass scales as 1f, 0.5f, 1.5f, 0.5f, 1f.
     *               The first 1f here is the start scale point.
     */
    public static AnimationSet scales(View view, long duration, float pivot, int pivotType, float ...scales) {
        AnimationSet animationSet = new AnimationSet(true);
        ScaleAnimation animation;
        float current = 1f;
        for (int i=1, len=scales.length; i<len; i++) {
            float from = i == 1 ? scales[0] : 1f;
            float to = scales[i]/current;
            current = scales[i];
            animation = new ScaleAnimation(from, to, from, to, pivotType, pivot, pivotType, pivot);
            animation.setDuration(duration);
            animation.setStartOffset(duration * (i-1));
            animationSet.addAnimation(animation);
        }
        view.startAnimation(animationSet);
        return animationSet;
    }

    /**
     * Change color from color to color.
     *
     * @param beforeColor color before
     * @param afterColor  color after
     * @param duration    duration, such as 3000
     * @param listener    the value change callback
     * @return            the animator
     */
    public static ValueAnimator changeColor(
            @ColorInt int beforeColor,
            @ColorInt int afterColor,
            long duration,
            final OnColorChangeListener listener
    ) {
        ArgbEvaluator argbEvaluator = new ArgbEvaluator();
        ValueAnimator valueAnimator = ValueAnimator.ofObject(argbEvaluator, beforeColor, afterColor).setDuration(duration);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (listener != null) {
                    listener.onColorChanged((Integer) animation.getAnimatedValue());
                }
            }
        });
        valueAnimator.start();
        return valueAnimator;
    }

    /** Change background color from 'beforeColor' to 'afterColor'. */
    public static ValueAnimator changeBackgroundColor(final View view,
                                                      @ColorInt int beforeColor,
                                                      @ColorInt int afterColor,
                                                      long duration) {
        return changeColor(beforeColor, afterColor, duration, new OnColorChangeListener() {
            @Override
            public void onColorChanged(int color) {
                view.setBackgroundColor(color);
            }
        });
    }

    /**
     * Make given view zoom in
     *
     * @param view     the view
     * @param scale    scale
     * @param dist     the dist to move
     * @param duration the duration
     * @return         the animator set
     */
    public static AnimatorSet zoomIn(View view, float scale, float dist, long duration) {
        view.setPivotX(view.getWidth()*.5f);
        view.setPivotY(view.getHeight());
        AnimatorSet animationSet = new AnimatorSet();
        ObjectAnimator mAnimatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, scale);
        ObjectAnimator mAnimatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, scale);
        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(view, "translationY", 0.0f, -dist);
        animationSet.play(mAnimatorTranslateY).with(mAnimatorScaleX);
        animationSet.play(mAnimatorScaleX).with(mAnimatorScaleY);
        animationSet.setDuration(duration);
        animationSet.start();
        return animationSet;
    }

    /**
     * Make given view zoom in
     *
     * @param view     the view
     * @param scale    scale
     * @param duration the duration
     * @return         the animator set
     */
    public static AnimatorSet zoomOut(View view, float scale, long duration) {
        view.setPivotX(view.getWidth()*.5f);
        view.setPivotY(view.getHeight());
        AnimatorSet animationSet = new AnimatorSet();
        ObjectAnimator mAnimatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", scale, 1.0f);
        ObjectAnimator mAnimatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", scale, 1.0f);
        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(view, "translationY", view.getTranslationY(), 0f);
        animationSet.play(mAnimatorTranslateY).with(mAnimatorScaleX);
        animationSet.play(mAnimatorScaleX).with(mAnimatorScaleY);
        animationSet.setDuration(duration);
        animationSet.start();
        return animationSet;
    }

    /**
     * Scale up down
     *
     * @param view     the view
     * @param duration the duration
     * @return         the scale animation
     */
    public static ScaleAnimation scaleUpDown(View view, long duration) {
        ScaleAnimation animation = new ScaleAnimation(1.0f, 1.0f, 0.0f, 1.0f);
        animation.setRepeatCount(-1);
        animation.setRepeatMode(Animation.RESTART);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(duration);
        view.startAnimation(animation);
        return animation;
    }

    /**
     * Animate the height of given view.
     *
     * @param view  the view to animate
     * @param start the start height
     * @param end   the end height
     * @return      the value animator
     */
    public static ValueAnimator animateHeight(final View view, int start, int end) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(start, end);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);
            }
        });
        valueAnimator.start();
        return valueAnimator;
    }

    /**
     * Popup in
     *
     * @param view     the view
     * @param duration the duration
     * @return         the animator
     */
    public static ObjectAnimator popupIn(View view, long duration) {
        view.setAlpha(0f);
        view.setVisibility(View.VISIBLE);
        ObjectAnimator popupIn = ObjectAnimator.ofPropertyValuesHolder(view,
                PropertyValuesHolder.ofFloat("alpha", 0f, 1f),
                PropertyValuesHolder.ofFloat("scaleX", 0f, 1f),
                PropertyValuesHolder.ofFloat("scaleY", 0f, 1f));
        popupIn.setDuration(duration);
        popupIn.setInterpolator(new OvershootInterpolator());
        popupIn.start();
        return popupIn;
    }

    /**
     * Popup out
     *
     * @param view                    the view
     * @param duration                the duration
     * @param animatorListenerAdapter the animator adapter
     * @return                        the animator
     */
    public static ObjectAnimator popupOut(final View view,
                                          long duration,
                                          final AnimatorListenerAdapter animatorListenerAdapter) {
        ObjectAnimator popupOut = ObjectAnimator.ofPropertyValuesHolder(view,
                PropertyValuesHolder.ofFloat("alpha", 1f, 0f),
                PropertyValuesHolder.ofFloat("scaleX", 1f, 0f),
                PropertyValuesHolder.ofFloat("scaleY", 1f, 0f));
        popupOut.setDuration(duration);
        popupOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
                if (animatorListenerAdapter != null) {
                    animatorListenerAdapter.onAnimationEnd(animation);
                }
            }
        });
        popupOut.setInterpolator(new AnticipateOvershootInterpolator());
        popupOut.start();
        return popupOut;
    }

    /** Listener for color change event. */
    public interface OnColorChangeListener {

        /** On color changed callback, */
        void onColorChanged(@ColorInt  int color);
    }

    @IntDef(value = {LEFT_TO_RIGHT, TOP_TO_BOTTOM, RIGHT_TO_LEFT, BOTTOM_TO_TOP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface UIDirection {
        int LEFT_TO_RIGHT = 0;
        int TOP_TO_BOTTOM = 1;
        int RIGHT_TO_LEFT = 2;
        int BOTTOM_TO_TOP = 3;
    }

    private AnimUtils() {
        throw new UnsupportedOperationException();
    }
}
