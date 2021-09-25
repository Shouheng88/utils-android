package me.shouheng.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import me.shouheng.utils.app.AppUtils;

/**
 * =================================================================================================
 *
 *                                 _           _     _   _    _ _   _ _
 *                 /\             | |         (_)   | | | |  | | | (_) |
 *                /  \   _ __   __| |_ __ ___  _  __| | | |  | | |_ _| |___
 *               / /\ \ | '_ \ / _` | '__/ _ \| |/ _` | | |  | | __| | / __|
 *              / ____ \| | | | (_| | | | (_) | | (_| | | |__| | |_| | \__ \
 *             /_/    \_\_| |_|\__,_|_|  \___/|_|\__,_|  \____/ \__|_|_|___/
 *
 *
 *                                    == Shouheng Wang ==
 *
 *                         AN COLLECTION OF USEFUL UTILS IN ANDROID
 *
 * =================================================================================================
 *
 * To initialize this library in your project, you should simply call
 * {@link UtilsApp#init(Application)} in your application:
 *
 * <code>
 * public class SampleApp extends Application {
 *
 *     public void onCreate() {
 *         super.onCreate();
 *         // initialize the utils library
 *         UtilsApp.init(this);
 *     }
 * }
 * </code>
 *
 * @author Shouheng Wang shouheng2020@gmail.com
 * @version 2019-05-07 12:13
 */
public final class UtilsApp {

    private static final String TAG = "UtilsApp";

    private static Application app;

    private static boolean isForeGround = false;

    private static List<OnForegroundChangeListener> onForegroundChangeListeners = new ArrayList<>();

    private static final Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = new Application.ActivityLifecycleCallbacks() {

        private int activityCount = 0;

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            AppUtils.attachActivity(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {
            activityCount++;
            AppUtils.attachForeActivity(activity);
        }

        @Override
        public void onActivityResumed(Activity activity) {
            if (!isForeGround) {
                isForeGround = true;
                notifyForegroundChange(true);
            }
        }

        @Override
        public void onActivityPaused(Activity activity) {
            // no-op
        }

        @Override
        public void onActivityStopped(Activity activity) {
            AppUtils.detachForeActivity(activity);
            activityCount--;
            if (activityCount == 0) {
                isForeGround = false;
                notifyForegroundChange(false);
                Log.i(TAG, "Activity foreground: " + System.currentTimeMillis());
            }
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            // no-op
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            AppUtils.detachActivity(activity);
        }
    };

    /** Used to mark the register status, to avoid multiple register actions. */
    private static volatile boolean registered = false;

    private UtilsApp() {
        throw new UnsupportedOperationException("u can't initialize me!");
    }

    /**
     * Get the application
     *
     * @return the application
     */
    public static Application getApp() {
        if (app == null) {
            throw new IllegalStateException("Sorry, you must call UtilsApp#init() method at first!");
        }
        return app;
    }

    /**
     * Initialize with given application, the app is usually used to get the app context.
     *
     * @param app the app used to initialize the utils library.
     */
    public static void init(Application app) {
        UtilsApp.app = app;
        if (!registered) {
            app.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
            registered = true;
        }
    }

    /**
     * Is current app foreground
     *
     * @return is foreground
     */
    public static boolean isAppForeGround() {
        return isForeGround;
    }

    /**
     * Register app foreground state change listener.
     *
     * @param l listener
     */
    public static void registerForegroundChangeListener(OnForegroundChangeListener l) {
        if (!onForegroundChangeListeners.contains(l)) {
            onForegroundChangeListeners.add(l);
        }
    }

    /**
     * Unregister app foreground state change listener.
     *
     * @param l listener
     */
    public static void unRegisterForegroundChangeListener(OnForegroundChangeListener l) {
        onForegroundChangeListeners.remove(l);
    }

    private static void notifyForegroundChange(boolean isForeGround) {
        for (OnForegroundChangeListener l : onForegroundChangeListeners) {
            l.onForegroundChange(isForeGround);
        }
    }

    /** On foreground state change callback. */
    public interface OnForegroundChangeListener {

        void onForegroundChange(boolean isForeground);
    }
}
