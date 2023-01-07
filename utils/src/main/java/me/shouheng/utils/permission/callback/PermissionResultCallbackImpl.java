package me.shouheng.utils.permission.callback;

import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import android.text.Html;

import me.shouheng.utils.R;
import me.shouheng.utils.permission.PermissionHelper;
import me.shouheng.utils.permission.PermissionUtils;
import me.shouheng.utils.ui.ToastUtils;

/**
 * {@link PermissionResultCallback} 的默认实现
 *
 * @author Shouheng Wang 2019-04-02 12:40
 */
public class PermissionResultCallbackImpl implements PermissionResultCallback {

    private final Context context;

    private final OnGetPermissionCallback onGetPermissionCallback;

    public PermissionResultCallbackImpl(Context context, OnGetPermissionCallback onGetPermissionCallback) {
        this.context = context;
        this.onGetPermissionCallback = onGetPermissionCallback;
    }

    @Override
    public void onGetAllPermissions() {
        if (onGetPermissionCallback != null) {
            onGetPermissionCallback.onGetPermission();
        }
    }

    @Override
    public void showPermissionsRationale(String ...permissions) {
        String names = PermissionHelper.names(permissions);
        String message = context.getResources().getQuantityString(
                R.plurals.permission_set_in_settings_message, permissions.length, names);
        new AlertDialog.Builder(context)
                .setTitle(R.string.permission_set_permission)
                .setMessage(Html.fromHtml(message))
                .setPositiveButton(R.string.permission_to_set, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PermissionUtils.toSetPermission(context);
                    }
                })
                .setNegativeButton(R.string.permission_cancel, null)
                .create().show();
    }

    @Override
    public void onPermissionNotGranted(String... permissions) {
        String names = PermissionHelper.names(permissions);
        String message = context.getResources().getQuantityString(
                R.plurals.permission_denied_message, permissions.length, names);
        ToastUtils.showShort(Html.fromHtml(message));
    }
}
