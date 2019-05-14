package me.shouheng.utils.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.io.File;

import me.shouheng.utils.UtilsApp;
import me.shouheng.utils.data.EncryptUtils;
import me.shouheng.utils.data.StringUtils;
import me.shouheng.utils.device.ShellUtils;

/**
 * Utils for App level.
 *
 * @author WngShhng 2019-05-07 19:20
 */
public final class AppUtils {

    /*--------------------------------install and uninstall----------------------------------*/

    public static boolean isInstall(String pkgName) {
        PackageManager pm = UtilsApp.getApp().getPackageManager();
        try {
            pm.getPackageInfo(pkgName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /*-------------------------------------get app info-------------------------------------*/

    /**
     * API 17
     *
     * @return true if above API 17
     */
    public static boolean isJellyBeanMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1;
    }

    /**
     * API 18
     *
     * @return true if above API 18
     */
    public static boolean isJellyBeanMR2() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2;
    }

    /**
     * API 19
     *
     * @return true if above API 18
     */
    public static boolean isKitKat(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    /**
     * API 21
     *
     * @return true if above API 21
     */
    public static boolean isLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    /**
     * API 23
     *
     * @return true if above API 23
     */
    public static boolean isMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    /**
     * Get whether this app got the root permission
     *
     * @return true if rooted
     */
    public static boolean isAppRoot() {
        ShellUtils.CommandResult result = ShellUtils.execCmd("echo root", true);
        if (result.result == 0) return true;
        if (result.errorMsg != null) {
            Log.d("AppUtils", "isAppRoot() called" + result.errorMsg);
        }
        return false;
    }

    public static boolean isAppDebug() {
        return isAppDebug(UtilsApp.getApp().getPackageName());
    }

    /**
     * Get whether given app is a debug app.
     *
     * @param pkgName the package name
     * @return true if it is
     */
    public static boolean isAppDebug(final String pkgName) {
        if (StringUtils.isSpace(pkgName)) return false;
        try {
            PackageManager pm = UtilsApp.getApp().getPackageManager();
            ApplicationInfo ai = pm.getApplicationInfo(pkgName, 0);
            return ai != null && (ai.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isAppSystem() {
        return isAppSystem(UtilsApp.getApp().getPackageName());
    }

    /**
     * Get whether given app is a system app.
     *
     * @param pkgName the package name
     * @return true if it is
     */
    public static boolean isAppSystem(final String pkgName) {
        if (StringUtils.isSpace(pkgName)) return false;
        try {
            PackageManager pm = UtilsApp.getApp().getPackageManager();
            ApplicationInfo ai = pm.getApplicationInfo(pkgName, 0);
            return ai != null && (ai.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Drawable getAppIcon() {
        return getAppIcon(UtilsApp.getApp().getPackageName());
    }

    /**
     * Get icon of given app
     *
     * @param pkgName the package name of given app
     * @return the icon drawable
     */
    public static Drawable getAppIcon(final String pkgName) {
        if (StringUtils.isSpace(pkgName)) return null;
        try {
            PackageManager pm = UtilsApp.getApp().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(pkgName, 0);
            return pi == null ? null : pi.applicationInfo.loadIcon(pm);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getPackageName() {
        return UtilsApp.getApp().getPackageName();
    }

    public static String getAppName() {
        return getAppName(UtilsApp.getApp().getPackageName());
    }

    /**
     * Get app name of given app
     *
     * @param pkgName package name of given app
     * @return the app name
     */
    public static String getAppName(final String pkgName) {
        if (StringUtils.isSpace(pkgName)) return "";
        try {
            PackageManager pm = UtilsApp.getApp().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(pkgName, 0);
            return pi == null ? null : pi.applicationInfo.loadLabel(pm).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int getAppUid() {
        return getAppUid(UtilsApp.getApp().getPackageName());
    }

    public static int getAppUid(String packageName) {
        try {
            PackageManager pm = UtilsApp.getApp().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi != null && pi.applicationInfo != null ? pi.applicationInfo.uid : 0;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getAppVersionName() {
        return getAppVersionName(UtilsApp.getApp().getPackageName());
    }

    public static String getAppVersionName(final String packageName) {
        if (StringUtils.isSpace(packageName)) return "";
        try {
            PackageManager pm = UtilsApp.getApp().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi == null ? null : pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int getAppVersionCode() {
        return getAppVersionCode(UtilsApp.getApp().getPackageName());
    }

    public static int getAppVersionCode(final String packageName) {
        if (StringUtils.isSpace(packageName)) return -1;
        try {
            PackageManager pm = UtilsApp.getApp().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi == null ? -1 : pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int getAppTargetSdkVersion() {
        return getAppTargetSdkVersion(UtilsApp.getApp().getPackageName());
    }

    public static int getAppTargetSdkVersion(String packageName) {
        try {
            PackageManager pm = UtilsApp.getApp().getPackageManager();
            ApplicationInfo ai = pm.getApplicationInfo(packageName, 0);
            return ai == null ? 0 : ai.targetSdkVersion;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static int getAppMinSdkVersion() {
        return getAppMinSdkVersion(UtilsApp.getApp().getPackageName());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static int getAppMinSdkVersion(String packageName) {
        try {
            PackageManager pm = UtilsApp.getApp().getPackageManager();
            ApplicationInfo ai = pm.getApplicationInfo(packageName, 0);
            return ai == null ? 0 : ai.minSdkVersion;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getMetaData(String key) {
        return getMetaData(UtilsApp.getApp().getPackageName(), key);
    }

    public static String getMetaData(final String packageName, String key) {
        try {
            PackageManager pm = UtilsApp.getApp().getPackageManager();
            ApplicationInfo ai = pm.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
            return ai != null && ai.metaData != null ? ai.metaData.getString(key) : null;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Signature[] getAppSignature() {
        return getAppSignature(UtilsApp.getApp().getPackageName());
    }

    public static Signature[] getAppSignature(final String packageName) {
        if (StringUtils.isSpace(packageName)) return null;
        try {
            PackageManager pm = UtilsApp.getApp().getPackageManager();
            @SuppressLint("PackageManagerGetSignatures")
            PackageInfo pi = pm.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            return pi == null ? null : pi.signatures;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getAppSignatureSHA1() {
        return getAppSignatureSHA1(UtilsApp.getApp().getPackageName());
    }

    public static String getAppSignatureSHA1(final String packageName) {
        return getAppSignatureHash(packageName, "SHA1");
    }

    public static String getAppSignatureSHA256() {
        return getAppSignatureSHA256(UtilsApp.getApp().getPackageName());
    }

    public static String getAppSignatureSHA256(final String packageName) {
        return getAppSignatureHash(packageName, "SHA256");
    }

    public static String getAppSignatureMD5() {
        return getAppSignatureMD5(UtilsApp.getApp().getPackageName());
    }

    public static String getAppSignatureMD5(final String packageName) {
        return getAppSignatureHash(packageName, "MD5");
    }

    private static String getAppSignatureHash(final String packageName, final String algorithm) {
        if (StringUtils.isSpace(packageName)) return "";
        Signature[] signature = getAppSignature(packageName);
        if (signature == null || signature.length <= 0) return "";
        return StringUtils.bytes2HexString(EncryptUtils.hashTemplate(signature[0].toByteArray(), algorithm))
                .replaceAll("(?<=[0-9A-F]{2})[0-9A-F]{2}", ":$0");
    }

    public static long getAppFirstInstallTime() {
        return getAppFirstInstallTime(UtilsApp.getApp().getPackageName());
    }

    public static long getAppFirstInstallTime(String packageName) {
        if (StringUtils.isSpace(packageName)) return -1;
        try {
            PackageManager pm = UtilsApp.getApp().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi == null ? -1 : pi.firstInstallTime;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static long getAppLastUpdateTime() {
        return getAppLastUpdateTime(UtilsApp.getApp().getPackageName());
    }

    public static long getAppLastUpdateTime(String packageName) {
        if (StringUtils.isSpace(packageName)) return -1;
        try {
            PackageManager pm = UtilsApp.getApp().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi == null ? -1 : pi.lastUpdateTime;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static long getAppSize() {
        return getAppSize(UtilsApp.getApp().getPackageName());
    }

    public static long getAppSize(String packageName) {
        try {
            PackageManager pm = UtilsApp.getApp().getPackageManager();
            ApplicationInfo ai = pm.getApplicationInfo(packageName, 0);
            return ai == null ? 0 : new File(ai.sourceDir).length();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getAppSourceDir() {
        return getAppSourceDir(UtilsApp.getApp().getPackageName());
    }

    public static String getAppSourceDir(String packageName) {
        try {
            PackageManager pm = UtilsApp.getApp().getPackageManager();
            ApplicationInfo ai = pm.getApplicationInfo(packageName, 0);
            return ai == null ? null : ai.sourceDir;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*-------------------------------------launch and exit-------------------------------------*/

    /**
     * Launch app of given package name
     *
     * @param pkgName package name
     */
    public static void launchApp(final String pkgName) {
        if (StringUtils.isSpace(pkgName)) return;
        UtilsApp.getApp().startActivity(IntentUtils.getLaunchAppIntent(pkgName, true));
    }

    /**
     * Launch app of given package name and get result from it
     *
     * @param activity the activity to receive the result
     * @param pkgName the package name of given app
     * @param requestCode the request code
     */
    public static void launchApp(final Activity activity, final String pkgName, final int requestCode) {
        if (StringUtils.isSpace(pkgName)) return;
        activity.startActivityForResult(IntentUtils.getLaunchAppIntent(pkgName, false), requestCode);
    }

    /**
     * Relaunch current app
     */
    public static void relaunchApp() {
        Intent i = IntentUtils.getLaunchAppIntent(UtilsApp.getApp().getPackageName(), false);
        if (i == null) return;
        ComponentName cn = i.getComponent();
        Intent ii = Intent.makeRestartActivityTask(cn);
        UtilsApp.getApp().startActivity(ii);
        System.exit(0);
    }

    public static void launchAppSettings() {
        launchAppSettings(UtilsApp.getApp().getPackageName());
    }

    /**
     * Launch setting page of given app
     *
     * @param pkgName the package name of given app
     */
    public static void launchAppSettings(final String pkgName) {
        if (StringUtils.isSpace(pkgName)) return;
        UtilsApp.getApp().startActivity(IntentUtils.getLaunchSettingIntent(pkgName, true));
    }

    /*-------------------------------------inner methods----------------------------------------*/

    private AppUtils() {
        throw new UnsupportedOperationException("u can't initialize me!");
    }
}
