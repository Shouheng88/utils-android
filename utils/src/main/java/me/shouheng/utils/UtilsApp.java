package me.shouheng.utils;

import android.app.Application;

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
    }
}
