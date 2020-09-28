package me.shouheng.samples.permission

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import me.shouheng.samples.R
import me.shouheng.utils.ktx.*
import me.shouheng.utils.permission.Permission
import me.shouheng.utils.permission.PermissionResultHandler
import me.shouheng.utils.permission.PermissionResultResolver
import me.shouheng.utils.permission.PermissionUtils
import me.shouheng.utils.permission.callback.OnGetPermissionCallback
import me.shouheng.utils.permission.callback.PermissionResultCallbackImpl

/**
 * To use [PermissionUtils] to request permissions, you need:
 * 1. Let your activity implement [PermissionResultResolver]. It's suggested that
 * you implement this interface in your base activity.
 * 2. Override [.onRequestPermissionsResult] and call
 * [PermissionResultHandler.handlePermissionsResult].
 * 3. Call [PermissionUtils.checkPermission]
 * to request given permissions.
 *
 * @author shouh
 * @version $Id: TestPermissionActivity, v 0.1 2018/11/22 12:13 shouh Exp$
 */
class TestPermissionActivity : AppCompatActivity(), PermissionResultResolver {
    private var onGetPermissionCallback: OnGetPermissionCallback? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission_test)
        findViewById<View>(R.id.btn_storage).setOnClickListener {
            checkStoragePermission { toast(R.string.permission_get_storage_permission) }
        }
        findViewById<View>(R.id.btn_camera).setOnClickListener {
            checkCameraPermission { toast(R.string.permission_get_camera_permission) }
        }
        findViewById<View>(R.id.btn_location).setOnClickListener {
            checkLocationPermission { toast(R.string.permission_get_location_permission) }
//            PermissionUtils.checkLocationPermission(this, {  })
        }
        findViewById<View>(R.id.btn_group).setOnClickListener {
            checkPermissions({ toast(R.string.permission_get_permissions)},
                    Permission.CAMERA, Permission.LOCATION, Permission.STORAGE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // call permission result handler
        PermissionResultHandler.handlePermissionsResult(this, requestCode, permissions,
                grantResults, PermissionResultCallbackImpl(this, onGetPermissionCallback))
    }

    override fun setOnGetPermissionCallback(onGetPermissionCallback: OnGetPermissionCallback) {
        this.onGetPermissionCallback = onGetPermissionCallback
    }
}