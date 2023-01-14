package me.shouheng.utils.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.LinkedList;
import java.util.List;

import me.shouheng.utils.UtilsApp;
import me.shouheng.utils.permission.callback.OnGetPermissionCallback;
import me.shouheng.utils.permission.callback.PermissionResultCallback;

/**
 * The utils to get runtime permission.
 *
 * To use this utils, you should:
 * 1. Make your activity implement {@link PermissionResultResolver} and,
 * 2. call {@link PermissionResultHandler#handlePermissionsResult(Activity, int, String[], int[],
 * PermissionResultCallback)} in its {@link Activity#onRequestPermissionsResult(int, String[], int[])}.
 *
 * Sample as below. Follow the step to handle your own logic,
 * <code>
 * // Step 1: implement PermissionResultResolver
 * public class TestPermissionActivity extends AppCompatActivity implements PermissionResultResolver {
 *
 *     // Step 2: define a OnGetPermissionCallback instance
 *     private OnGetPermissionCallback onGetPermissionCallback;
 *
 *     @Override
 *     protected void onCreate(@Nullable Bundle savedInstanceState) {
 *         super.onCreate(savedInstanceState);
 *         setContentView(R.layout.activity_permission_test);
 *
 *         findViewById(R.id.btn_storage).setOnClickListener(new View.OnClickListener() {
 *             @Override
 *             public void onClick(View v) {
 *                 // Step 6: do check permission
 *                 PermissionUtils.checkStoragePermission(TestPermissionActivity.this,
 *                         new OnGetPermissionCallback() {
 *                             @Override
 *                             public void onGetPermission() {
 *                                 // Step 7: you logic when get permission
 *                                 Toast.makeText(TestPermissionActivity.this,
 *                                         R.string.permission_get_storage_permission,
 *                                         Toast.LENGTH_SHORT).show();
 *                             }
 *                         });
 *             }
 *         });
 *     }
 *
 *     // Step 5: Call PermissionResultHandler.handlePermissionsResult() in onRequestPermissionsResult method
 *     @Override
 *     public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
 *         super.onRequestPermissionsResult(requestCode, permissions, grantResults);
 *         // call permission result handler
 *         PermissionResultHandler.handlePermissionsResult(this, requestCode, permissions,
 *                 grantResults, new PermissionResultCallbackImpl(this, onGetPermissionCallback));
 *     }
 *
 *     // Step 4: set value to OnGetPermissionCallback instance
 *     @Override
 *     public void setOnGetPermissionCallback(OnGetPermissionCallback onGetPermissionCallback) {
 *         this.onGetPermissionCallback = onGetPermissionCallback;
 *     }
 * }
 * </code>
 *
 * Created by Shouheng Wang on 2017/12/5.
 */
public final class PermissionUtils {

    private static PermissionNameGetter permissionNameGetter;

    private PermissionUtils() {
        throw new UnsupportedOperationException("U can't initialize me!");
    }

    /**
     * 请求存储空间权限
     *
     * @param activity 对应的 Activity
     * @param callback 获取到权限时的回调
     * @param <T> 需要同时实现 {@link PermissionResultResolver}
     */
    public static <T extends Activity & PermissionResultResolver> void checkStoragePermission(
            @NonNull T activity,
            OnGetPermissionCallback callback
    ) {
        checkPermissions(activity, callback, Permission.STORAGE);
    }

    /**
     * 请求手机状态权限
     *
     * @param activity 对应的 Activity
     * @param callback 获取到权限时的回调
     * @param <T> 需要同时实现 {@link PermissionResultResolver}
     */
    public static <T extends Activity & PermissionResultResolver> void checkPhonePermission(
            @NonNull T activity,
            OnGetPermissionCallback callback
    ) {
        checkPermissions(activity, callback, Permission.PHONE_STATE);
    }

    /**
     * 请求位置权限
     *
     * @param activity 对应的 Activity
     * @param callback 获取到权限时的回调
     * @param <T> 需要同时实现 {@link PermissionResultResolver}
     */
    public static <T extends Activity & PermissionResultResolver> void checkLocationPermission(
            @NonNull T activity,
            OnGetPermissionCallback callback
    ) {
        checkPermissions(activity, callback, Permission.LOCATION);
    }

    /**
     * 请求录音权限
     *
     * @param activity 对应的 Activity
     * @param callback 获取到权限时的回调
     * @param <T> 需要同时实现 {@link PermissionResultResolver}
     */
    public static <T extends Activity & PermissionResultResolver> void checkRecordPermission(
            @NonNull T activity,
            OnGetPermissionCallback callback
    ) {
        checkPermissions(activity, callback, Permission.MICROPHONE);
    }

    /**
     * 请求短信权限
     *
     * @param activity 对应的 Activity
     * @param callback 获取到权限时的回调
     * @param <T> 需要同时实现 {@link PermissionResultResolver}
     */
    public static <T extends Activity & PermissionResultResolver> void checkSmsPermission(
            @NonNull T activity,
            OnGetPermissionCallback callback
    ) {
        checkPermissions(activity, callback, Permission.SMS);
    }

    /**
     * 请求传感器权限
     *
     * @param activity 对应的 Activity
     * @param callback 获取到权限时的回调
     * @param <T> 需要同时实现 {@link PermissionResultResolver}
     */
    @TargetApi(Build.VERSION_CODES.KITKAT_WATCH)
    public static <T extends Activity & PermissionResultResolver> void checkSensorsPermission(
            @NonNull T activity,
            OnGetPermissionCallback callback
    ) {
        checkPermissions(activity, callback, Permission.SENSORS);
    }

    /**
     * 请求联系人权限
     *
     * @param activity 对应的 Activity
     * @param callback 获取到权限时的回调
     * @param <T> 需要同时实现 {@link PermissionResultResolver}
     */
    public static <T extends Activity & PermissionResultResolver> void checkContactsPermission(
            @NonNull T activity,
            OnGetPermissionCallback callback
    ) {
        checkPermissions(activity, callback, Permission.CONTACTS);
    }

    /**
     * 请求相机权限
     *
     * @param activity 对应的 Activity
     * @param callback 获取到权限时的回调
     * @param <T> 需要同时实现 {@link PermissionResultResolver}
     */
    public static <T extends Activity & PermissionResultResolver> void checkCameraPermission(
            @NonNull T activity,
            OnGetPermissionCallback callback
    ) {
        checkPermissions(activity, callback, Permission.CAMERA);
    }

    /**
     * 请求日历权限
     *
     * @param activity 对应的 Activity
     * @param callback 获取到权限时的回调
     * @param <T> 需要同时实现 {@link PermissionResultResolver}
     */
    public static <T extends Activity & PermissionResultResolver> void checkCalendarPermission(
            @NonNull T activity,
            OnGetPermissionCallback callback
    ) {
        checkPermissions(activity, callback, Permission.CALENDAR);
    }

    /**
     * 同时请求多组权限
     *
     * @param activity    对应的 Activity
     * @param callback    获取到权限时的回调
     * @param permissions 要获取的权限
     * @param <T> 需要同时实现 {@link PermissionResultResolver}
     */
    public static <T extends Activity & PermissionResultResolver> void checkPermissions(
            @NonNull T activity,
            OnGetPermissionCallback callback,
            @Permission int ...permissions
    ) {
        activity.setOnGetPermissionCallback(callback);
        // map permission code
        int length = permissions.length;
        String[] standardPermissions = new String[length];
        for (int i=0; i<length; i++) {
            standardPermissions[i] = PermissionHelper.map(permissions[i]);
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
                    notGranted.toArray(new String[0]), PermissionHelper.REQUEST_PERMISSIONS);
        }
    }

    /**
     * 检查是否具有指定的全部权限
     *
     * @param permissions 权限列表
     * @return            全部具备
     */
    public static boolean hasPermissions(@Permission int ...permissions) {
        return hasPermissions(UtilsApp.getApp(), permissions);
    }

    /**
     * 检查是否具有指定的全部权限
     *
     * @param context     ctx
     * @param permissions 权限列表
     * @return            全部具备
     */
    public static boolean hasPermissions(Context context, @Permission int ...permissions) {
        // map permission code
        int length = permissions.length;
        String[] standardPermissions = new String[length];
        for (int i=0; i<length; i++) {
            standardPermissions[i] = PermissionHelper.map(permissions[i]);
        }
        // check permissions
        int notGrantedCount = 0;
        for (int i=0; i<length; i++) {
            if (ContextCompat.checkSelfPermission(context, standardPermissions[i])
                    != PackageManager.PERMISSION_GRANTED) {
                notGrantedCount++;
            }
        }
        return notGrantedCount == 0;
    }

    /**
     * 去设置权限
     *
     * @param context ctx
     */
    public static void toSetPermission(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", UtilsApp.getApp().getPackageName(), null);
        intent.setData(uri);
        context.startActivity(intent);
    }

    /**
     * Set getter to get permission name
     *
     * @param permissionNameGetter permission name getter
     */
    public static void setPermissionNameGetter(PermissionNameGetter permissionNameGetter) {
        PermissionUtils.permissionNameGetter = permissionNameGetter;
    }

    public static PermissionNameGetter getPermissionNameGetter() {
        return permissionNameGetter;
    }
}