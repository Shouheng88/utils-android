package me.shouheng.utils.permission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.text.TextUtils;

import me.shouheng.utils.R;
import me.shouheng.utils.UtilsApp;

public class PermissionHelper {

    static final int REQUEST_PERMISSIONS        = 0xFF00;

    private PermissionHelper() {
        throw new UnsupportedOperationException("U can't initialize me!");
    }

    /**
     * Map from permission code of {@link PermissionHelper} to string in {@link Manifest}.
     *
     * @param permission permission code
     * @return permission name
     */
    public static String map(@Permission int permission) {
        switch (permission) {
            case Permission.STORAGE:     return Manifest.permission.WRITE_EXTERNAL_STORAGE;
            case Permission.PHONE_STATE: return Manifest.permission.READ_PHONE_STATE;
            case Permission.LOCATION:    return Manifest.permission.ACCESS_FINE_LOCATION;
            case Permission.MICROPHONE:  return Manifest.permission.RECORD_AUDIO;
            case Permission.SMS:         return Manifest.permission.SEND_SMS;
            case Permission.SENSORS:
                if (VERSION.SDK_INT >= VERSION_CODES.KITKAT_WATCH) {
                    return Manifest.permission.BODY_SENSORS;
                }
                return "android.permission.BODY_SENSORS";
            case Permission.CONTACTS:    return Manifest.permission.READ_CONTACTS;
            case Permission.CAMERA:      return Manifest.permission.CAMERA;
            case Permission.CALENDAR:    return Manifest.permission.READ_CALENDAR;
            case Permission.MEDIA_AUDIO:
                if (VERSION.SDK_INT >= VERSION_CODES.TIRAMISU) {
                    return Manifest.permission.READ_MEDIA_AUDIO;
                } else {
                    return Manifest.permission.WRITE_EXTERNAL_STORAGE;
                }
            case Permission.MEDIA_VIDEO:
                if (VERSION.SDK_INT >= VERSION_CODES.TIRAMISU) {
                    return Manifest.permission.READ_MEDIA_VIDEO;
                } else {
                    return Manifest.permission.WRITE_EXTERNAL_STORAGE;
                }
            case Permission.MEDIA_IMAGES:
                if (VERSION.SDK_INT >= VERSION_CODES.TIRAMISU) {
                    return Manifest.permission.READ_MEDIA_IMAGES;
                } else {
                    return Manifest.permission.WRITE_EXTERNAL_STORAGE;
                }
            default:
                throw new IllegalArgumentException("Unrecognized permission code " + permission);
        }
    }

    /**
     * Get translated permission name. Use {@link PermissionUtils#setPermissionNameGetter(PermissionNameGetter)}
     * to add custom permission name, otherwise the default permission name will be used.
     *
     * @param permission the permission from {@link Manifest.permission}
     * @return the translated permission
     */
    public static String name(String permission) {
        // Get permission name from getter first
        PermissionNameGetter getter = PermissionUtils.getPermissionNameGetter();
        if (getter != null) {
            String name = getter.getName(permission);
            if (name != null) {
                return name;
            }
        }
        // If not get permission name from getter, use default permission name.
        int resName = 0;
        switch (permission) {
            case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                resName = R.string.permission_storage_permission;
                break;
            case Manifest.permission.READ_PHONE_STATE:
                resName = R.string.permission_phone_permission;
                break;
            case Manifest.permission.ACCESS_FINE_LOCATION:
                resName = R.string.permission_location_permission;
                break;
            case Manifest.permission.RECORD_AUDIO:
                resName = R.string.permission_microphone_permission;
                break;
            case Manifest.permission.SEND_SMS:
                resName = R.string.permission_sms_permission;
                break;
            case Manifest.permission.BODY_SENSORS:
                resName = R.string.permission_sensor_permission;
                break;
            case Manifest.permission.READ_CONTACTS:
                resName = R.string.permission_contacts_permission;
                break;
            case Manifest.permission.CAMERA:
                resName = R.string.permission_camera_permission;
                break;
            case Manifest.permission.READ_CALENDAR:
                resName = R.string.permission_calendar_permission;
                break;
        }

        // 到 API 33 查询对应的多语言
        if (resName == 0 && VERSION.SDK_INT >= VERSION_CODES.TIRAMISU) {
            switch (permission) {
                case Manifest.permission.READ_MEDIA_IMAGES:
                    resName = R.string.permission_media_images_permission;
                    break;
                case Manifest.permission.READ_MEDIA_VIDEO:
                    resName = R.string.permission_media_video_permission;
                    break;
                case Manifest.permission.READ_MEDIA_AUDIO:
                    resName = R.string.permission_media_audio_permission;
                    break;
            }
        }
        return resName != 0 ? UtilsApp.getApp().getString(resName) : null;
    }

    /**
     * Map multiple permission names to one single string used to display in toast and dialog.
     *
     * @param permissions the permission names
     * @return the single string of permission names connected by ','
     */
    public static String names(String[] permissions) {
        PackageManager pm = UtilsApp.getApp().getPackageManager();
        StringBuilder names = new StringBuilder();
        for (int i=0, length=permissions.length; i<length; i++) {
            try {
                // 优先使用自己翻译的名称
                String name = PermissionHelper.name(permissions[i]);
                if (name == null) {
                    // 先用权限本身的名称
                    PermissionInfo info = pm.getPermissionInfo(permissions[i], 0);
                    if (info != null) {
                        name = info.loadLabel(pm).toString();
                    }
                    // 再用权限组的名称
                    if (info != null && info.group != null) {
                        PermissionGroupInfo groupInfo = pm.getPermissionGroupInfo(info.group, 0);
                        String groupName = groupInfo.loadLabel(pm).toString();
                        if (!groupName.endsWith("UNDEFINED")) {
                            name = groupName;
                        }
                    }
                    // 取最后一个单词
                    if (!TextUtils.isEmpty(name)) {
                        int index = name.lastIndexOf('.');
                        if (index != -1) {
                            name = name.substring(index+1);
                        }
                    }
                }
                // 最后直接使用原始名称
                if (name == null) {
                    name = permissions[i];
                }
                names.append(name);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if (i != length - 1) {
                names.append(",");
            }
        }
        return names.toString();
    }
}