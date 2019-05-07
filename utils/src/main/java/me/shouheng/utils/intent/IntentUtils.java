package me.shouheng.utils.intent;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;

import me.shouheng.utils.UtilsApp;

/**
 * @author WngShhng (shouheng2015@gmail.com)
 * @version 2019/5/7 23:09
 */
public final class IntentUtils {

    public static Intent getLaunchAppIntent(final String pkgName, final boolean isNewTask) {
        PackageManager pm = UtilsApp.getApp().getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage(pkgName);
        if (intent == null) return null;
        return isNewTask ? intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) : intent;
    }

    public static Intent getLaunchSettingIntent(final String pkgName) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + pkgName));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    private IntentUtils() {
        throw new UnsupportedOperationException("u can't initialize me!");
    }
}
