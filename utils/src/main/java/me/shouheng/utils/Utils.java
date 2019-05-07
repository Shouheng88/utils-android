package me.shouheng.utils;

import android.app.Application;

/**
 * The utils configuration, used to initialize the library.
 *
 * @author WngShhng 2019-05-7 12:13
 */
public final class Utils {

    private static Application app;

    private Utils() {
        throw new UnsupportedOperationException("u can't initialize me!");
    }

    public static Application getApp() {
        if (app == null) {
            throw new IllegalStateException("Sorry, you must call Utils#init() method at first!");
        }
        return app;
    }

    /**
     * Initialize with given application, the app is usually used to get the app context.
     *
     * @param app the app used to initialize the utils library.
     */
    public static void init(Application app) {
        Utils.app = app;
    }
}
