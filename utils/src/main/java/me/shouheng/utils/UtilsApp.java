package me.shouheng.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import me.shouheng.utils.app.AppUtils;

/**
 * The utils configuration, used to initialize the library.
 *
 * o      o              .oPYo. 8      8
 * 8      8              8      8      8
 * 8      8 odYo. .oPYo. `Yooo. 8oPYo. 8oPYo. odYo. .oPYo.
 * 8  db  8 8' `8 8    8     `8 8    8 8    8 8' `8 8    8
 * `b.PY.d' 8   8 8    8      8 8    8 8    8 8   8 8    8
 *  `8  8'  8   8 `YooP8 `YooP' 8    8 8    8 8   8 `YooP8
 * ::..:..::..::..:....8 :.....:..:::....:::....::..:....8
 * :::::::::::::::::ooP'.:::::::::::::::::::::::::::::ooP'.
 * :::::::::::::::::...:::::::::::::::::::::::::::::::...::
 *
 * @author WngShhng 2019-05-7 12:13
 */
public final class UtilsApp {

    private static Application app;

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
        app.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                AppUtils.attachActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                AppUtils.attachForeActivity(activity);
            }

            @Override
            public void onActivityResumed(Activity activity) {
                // no-op
            }

            @Override
            public void onActivityPaused(Activity activity) {
                // no-op
            }

            @Override
            public void onActivityStopped(Activity activity) {
                AppUtils.detachForeActivity(activity);
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                // no-op
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                AppUtils.detachActivity(activity);
            }
        });
    }
}
