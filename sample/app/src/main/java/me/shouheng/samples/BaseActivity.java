package me.shouheng.samples;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import me.shouheng.utils.permission.PermissionResultHandler;
import me.shouheng.utils.permission.PermissionResultResolver;
import me.shouheng.utils.permission.callback.OnGetPermissionCallback;
import me.shouheng.utils.permission.callback.PermissionResultCallbackImpl;

public abstract class BaseActivity extends AppCompatActivity implements PermissionResultResolver {

    private OnGetPermissionCallback onGetPermissionCallback;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionResultHandler.handlePermissionsResult(this, requestCode, permissions, grantResults,
                new PermissionResultCallbackImpl(this, onGetPermissionCallback));
    }

    @Override
    public void setOnGetPermissionCallback(OnGetPermissionCallback onGetPermissionCallback) {
        this.onGetPermissionCallback = onGetPermissionCallback;
    }
}
