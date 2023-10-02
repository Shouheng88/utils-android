package me.shouheng.samples.permission

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import me.shouheng.samples.R
import me.shouheng.samples.databinding.ActivityPermissionTestBinding
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

    private var binding: ActivityPermissionTestBinding? = null

    private var onGetPermissionCallback: OnGetPermissionCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPermissionTestBinding.inflate(LayoutInflater.from(this))
        setContentView(binding!!.root)
        binding?.btnStorage?.onDebouncedClick {
            checkStoragePermission { toast(R.string.permission_get_storage_permission) }
        }
        binding?.btnCamera?.onDebouncedClick {
            checkCameraPermission { toast(R.string.permission_get_camera_permission) }
        }
        binding?.btnLocation?.onDebouncedClick {
            checkLocationPermission { toast(R.string.permission_get_location_permission) }
//            PermissionUtils.checkLocationPermission(this, {  })
        }
        binding?.btnGroup?.onDebouncedClick {
            checkPermissions({ toast(R.string.permission_get_permissions)},
                    Permission.CAMERA, Permission.LOCATION, Permission.STORAGE)
        }
        binding?.btnImages?.onDebouncedClick {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                checkPermissions({
                    toast("On get READ_MEDIA_IMAGES")
                }, Manifest.permission.READ_MEDIA_IMAGES)
            }
        }
        binding?.btnMedia?.onDebouncedClick {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                checkPermissions({
                    toast("On get READ_MEDIA_IMAGES, READ_MEDIA_AUDIO and READ_MEDIA_VIDEO")
                }, Manifest.permission.READ_MEDIA_IMAGES, Manifest.permission.READ_MEDIA_AUDIO, Manifest.permission.READ_MEDIA_VIDEO)
            }
        }
        binding?.btnImagesCompact?.onDebouncedClick {
            checkPermissions({
                toast("On get MEDIA_IMAGES")
            }, Permission.MEDIA_IMAGES)
        }
        binding?.btnMediaCompact?.onDebouncedClick {
            checkPermissions({
                toast("On get MEDIA_AUDIO, MEDIA_VIDEO and MEDIA_IMAGES")
            }, Permission.MEDIA_AUDIO, Permission.MEDIA_VIDEO, Permission.MEDIA_IMAGES)
        }
        binding?.btnOpenAlbum?.onDebouncedClick {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                val i =  Intent(MediaStore.ACTION_PICK_IMAGES)
                startActivityForResult(i, 0x01111)
            }
        }
        binding?.btnPostNotification?.onDebouncedClick {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                checkPermissions({
                    toast("Got post notification")
                }, Manifest.permission.POST_NOTIFICATIONS)
            }
        }

        // Update permission info.
        updatePermissions()
    }

    private fun updatePermissions() {
        val texts = ArrayList<String>()
        texts.add("CAMERA:${hasPermissions(Permission.CAMERA)}")
        texts.add("LOCATION:${hasPermissions(Permission.LOCATION)}")
        texts.add("STORAGE:${hasPermissions(Permission.STORAGE)}")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            texts.add("READ_MEDIA_IMAGES:${hasPermissions(Manifest.permission.READ_MEDIA_IMAGES)}")
            texts.add("READ_MEDIA_AUDIO:${hasPermissions(Manifest.permission.READ_MEDIA_AUDIO)}")
            texts.add("READ_MEDIA_VIDEO:${hasPermissions(Manifest.permission.READ_MEDIA_VIDEO)}")
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            texts.add("POST_NOTIFICATIONS:${hasPermissions(Manifest.permission.POST_NOTIFICATIONS)}")
        }
        binding?.tv?.text = "\n".join(texts)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // call permission result handler
        PermissionResultHandler.handlePermissionsResult(this, requestCode, permissions,
                grantResults, PermissionResultCallbackImpl(this, onGetPermissionCallback))
        updatePermissions()
    }

    override fun setOnGetPermissionCallback(onGetPermissionCallback: OnGetPermissionCallback) {
        this.onGetPermissionCallback = onGetPermissionCallback
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}