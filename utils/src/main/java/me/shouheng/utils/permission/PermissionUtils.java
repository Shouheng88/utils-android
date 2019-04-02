package me.shouheng.utils.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.LinkedList;
import java.util.List;

import me.shouheng.utils.permission.Permission.PermissionCode;
import me.shouheng.utils.permission.callback.OnGetPermissionCallback;
import me.shouheng.utils.permission.callback.PermissionResultCallback;

/**
 * The wrapped utils class to request for permission in runtime. The activity must
 * implement {@link PermissionResultResolver} and call {@link PermissionResultHandler#handlePermissionsResult(
 * Activity, int, String[], int[], PermissionResultCallback)} in its
 * {@link Activity#onRequestPermissionsResult(int, String[], int[])} method.
 *
 * Created by wang shouheng on 2017/12/5.*/
public final class PermissionUtils {

    private PermissionUtils() {
        throw new UnsupportedOperationException("U can't initialize me!");
    }

    /**
     * Check storage permission.
     *
     * @param activity the base activity
     * @param callback the callback of checking result
     * @param <T> the activity type, must implement {@link PermissionResultResolver}
     */
    public static <T extends Activity & PermissionResultResolver> void checkStoragePermission(
            @NonNull T activity, OnGetPermissionCallback callback) {
        checkPermission(activity, Permission.STORAGE, callback);
    }

    /**
     * Check phone permission.
     *
     * @param activity the base activity
     * @param callback the callback of checking result
     * @param <T> the activity type, must implement {@link PermissionResultResolver}
     */
    public static <T extends Activity & PermissionResultResolver> void checkPhonePermission(
            @NonNull T activity, OnGetPermissionCallback callback) {
        checkPermission(activity, Permission.PHONE_STATE, callback);
    }

    /**
     * Check location permission.
     *
     * @param activity the base activity
     * @param callback the callback of checking result
     * @param <T> the activity type, must implement {@link PermissionResultResolver}
     */
    public static <T extends Activity & PermissionResultResolver> void checkLocationPermission(
            @NonNull T activity, OnGetPermissionCallback callback) {
        checkPermission(activity, Permission.LOCATION, callback);
    }

    /**
     * Check record permission.
     *
     * @param activity the base activity
     * @param callback the callback of checking result
     * @param <T> the activity type, must implement {@link PermissionResultResolver}
     */
    public static <T extends Activity & PermissionResultResolver> void checkRecordPermission(
            @NonNull T activity, OnGetPermissionCallback callback) {
        checkPermission(activity, Permission.MICROPHONE, callback);
    }

    /**
     * Check sms permission.
     *
     * @param activity the base activity
     * @param callback the callback of checking result
     * @param <T> the activity type, must implement {@link PermissionResultResolver}
     */
    public static <T extends Activity & PermissionResultResolver> void checkSmsPermission(
            @NonNull T activity, OnGetPermissionCallback callback) {
        checkPermission(activity, Permission.SMS, callback);
    }

    /**
     * Check sensors permission.
     *
     * @param activity the base activity
     * @param callback the callback of checking result
     * @param <T> the activity type, must implement {@link PermissionResultResolver}
     */
    @TargetApi(Build.VERSION_CODES.KITKAT_WATCH)
    public static <T extends Activity & PermissionResultResolver> void checkSensorsPermission(
            @NonNull T activity, OnGetPermissionCallback callback) {
        checkPermission(activity, Permission.SENSORS, callback);
    }

    /**
     * Check contacts permission.
     *
     * @param activity the base activity
     * @param callback the callback of checking result
     * @param <T> the activity type, must implement {@link PermissionResultResolver}
     */
    public static <T extends Activity & PermissionResultResolver> void checkContactsPermission(
            @NonNull T activity, OnGetPermissionCallback callback) {
        checkPermission(activity, Permission.CONTACTS, callback);
    }

    /**
     * Check camera permission.
     *
     * @param activity the base activity
     * @param callback the callback of checking result
     * @param <T> the activity type, must implement {@link PermissionResultResolver}
     */
    public static <T extends Activity & PermissionResultResolver> void checkCameraPermission(
            @NonNull T activity, OnGetPermissionCallback callback) {
        checkPermission(activity, Permission.CAMERA, callback);
    }

    /**
     * Check calendar permission.
     *
     * @param activity the base activity
     * @param callback the callback of checking result
     * @param <T> the activity type, must implement {@link PermissionResultResolver}
     */
    public static <T extends Activity & PermissionResultResolver> void checkCalendarPermission(
            @NonNull T activity, OnGetPermissionCallback callback) {
        checkPermission(activity, Permission.CALENDAR, callback);
    }

    /**
     * The permission check method used to check one permission one time.
     *
     * @param activity the activity
     * @param permission the permission to check
     * @param callback the callback listener
     * @param <T> the activity type, must implement {@link PermissionResultResolver}
     */
    public static <T extends Activity & PermissionResultResolver> void checkPermission(
            @NonNull T activity, @PermissionCode int permission, OnGetPermissionCallback callback) {
        activity.setOnGetPermissionCallback(callback);
        if (ContextCompat.checkSelfPermission(activity, Permission.map(permission))
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Permission.map(permission)}, permission);
        } else {
            if (callback != null) {
                callback.onGetPermission();
            }
        }
    }

    /**
     * Check multiple permissions at the same time.
     *
     * @param activity activity
     * @param permissions permissions to check, use fields from {@link Permission}
     * @param callback callback of permission result
     * @param <T> the activity type
     */
    public static <T extends Activity & PermissionResultResolver> void checkPermissions(
            @NonNull T activity, @PermissionCode int[] permissions, OnGetPermissionCallback callback) {
        activity.setOnGetPermissionCallback(callback);
        // map permission code
        int length = permissions.length;
        String[] standardPermissions = new String[length];
        for (int i=0; i<length; i++) {
            standardPermissions[i] = Permission.map(permissions[i]);
        }
        // check permissions
        int notGrantedCount = 0;
        List<String> notGranted = new LinkedList<>();
        for (int i=0; i<length; i++) {
            if (ContextCompat.checkSelfPermission(activity, standardPermissions[i])
                    != PackageManager.PERMISSION_GRANTED) {
                notGrantedCount++;
                notGranted.add(standardPermissions[i]);
            }
        }
        if (notGrantedCount == 0) {
            // all permission granted
            if (callback != null) {
                callback.onGetPermission();
            }
        } else {
            ActivityCompat.requestPermissions(activity,
                    notGranted.toArray(new String[0]), Permission.REQUEST_PERMISSIONS);
        }
    }

    /**
     * Navigate to setting page of this app to grant permissions.
     *
     * @param context the context
     */
    public static void toSetPermission(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(context), null);
        intent.setData(uri);
        context.startActivity(intent);
    }

    /**
     * Get the package name
     *
     * @param context context to get package name
     * @return the package name
     */
    private static String getPackageName(Context context) {
        return context.getApplicationContext().getPackageName();
    }

}