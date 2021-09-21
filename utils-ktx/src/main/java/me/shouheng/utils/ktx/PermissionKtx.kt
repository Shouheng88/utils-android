package me.shouheng.utils.ktx

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.os.Build
import me.shouheng.utils.permission.Permission
import me.shouheng.utils.permission.PermissionResultResolver
import me.shouheng.utils.permission.PermissionUtils
import me.shouheng.utils.permission.callback.OnGetPermissionCallback

fun <T> T.checkStoragePermission(callback: () -> Unit) where T : Activity, T : PermissionResultResolver {
    PermissionUtils.checkPermissions(this, OnGetPermissionCallback { callback() }, Permission.STORAGE)
}

fun <T> T.checkPhonePermission(callback: () -> Unit) where T : Activity, T : PermissionResultResolver {
    PermissionUtils.checkPermissions(this, OnGetPermissionCallback { callback() }, Permission.PHONE_STATE)
}

fun <T> T.checkLocationPermission(callback: () -> Unit) where T : Activity, T : PermissionResultResolver {
    PermissionUtils.checkPermissions(this, OnGetPermissionCallback { callback() }, Permission.LOCATION)
}
fun <T> T.checkRecordPermission(callback: () -> Unit) where T : Activity, T : PermissionResultResolver {
    PermissionUtils.checkPermissions(this, OnGetPermissionCallback { callback() }, Permission.MICROPHONE)
}

fun <T> T.checkSmsPermission(callback: () -> Unit) where T : Activity, T : PermissionResultResolver {
    PermissionUtils.checkPermissions(this, OnGetPermissionCallback { callback() }, Permission.SMS)
}

@TargetApi(Build.VERSION_CODES.KITKAT_WATCH)
fun <T> T.checkSensorsPermission(callback: () -> Unit) where T : Activity, T : PermissionResultResolver {
    PermissionUtils.checkPermissions(this, OnGetPermissionCallback { callback() }, Permission.SENSORS)
}
fun <T> T.checkContactsPermission(callback: () -> Unit) where T : Activity, T : PermissionResultResolver {
    PermissionUtils.checkPermissions(this, OnGetPermissionCallback { callback() }, Permission.CONTACTS)
}

fun <T> T.checkCameraPermission(callback: () -> Unit) where T : Activity, T : PermissionResultResolver {
    PermissionUtils.checkPermissions(this, OnGetPermissionCallback{ callback() }, Permission.CAMERA)
}

fun <T> T.checkCalendarPermission(callback: () -> Unit) where T : Activity, T : PermissionResultResolver {
    PermissionUtils.checkPermissions(this, OnGetPermissionCallback { callback() }, Permission.CALENDAR)
}

fun <T> T.checkPermissions(callback: () -> Unit, @Permission vararg permissions: Int) where T : Activity, T : PermissionResultResolver {
    PermissionUtils.checkPermissions(this, OnGetPermissionCallback { callback() }, *permissions)
}

fun hasPermissions(@Permission vararg permissions: Int) = PermissionUtils.hasPermissions(*permissions)

fun hasPermissions(context: Context, @Permission vararg permissions: Int) = PermissionUtils.hasPermissions(context, *permissions)
