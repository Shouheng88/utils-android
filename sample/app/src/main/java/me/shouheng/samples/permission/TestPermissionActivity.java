package me.shouheng.samples.permission;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import me.shouheng.samples.R;
import me.shouheng.utils.permission.PermissionActivity;
import me.shouheng.utils.permission.PermissionUtils;

/**
 * @author shouh
 * @version $Id: TestPermissionActivity, v 0.1 2018/11/22 12:13 shouh Exp$
 */
public class TestPermissionActivity extends PermissionActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_test);

        findViewById(R.id.btn_storage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionUtils.checkStoragePermission(TestPermissionActivity.this,
                        new PermissionUtils.OnGetPermissionCallback() {
                            @Override
                            public void onGetPermission() {
                                Toast.makeText(TestPermissionActivity.this,
                                        "Get storage permission!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        findViewById(R.id.btn_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionUtils.checkCameraPermission(TestPermissionActivity.this,
                        new PermissionUtils.OnGetPermissionCallback() {
                            @Override
                            public void onGetPermission() {
                                Toast.makeText(TestPermissionActivity.this,
                                        "Get camera permission!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        findViewById(R.id.btn_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionUtils.checkLocationPermission(TestPermissionActivity.this,
                        new PermissionUtils.OnGetPermissionCallback() {
                            @Override
                            public void onGetPermission() {
                                Toast.makeText(TestPermissionActivity.this,
                                        "Get location permission!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        findViewById(R.id.btn_group).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionUtils.checkPermissions(TestPermissionActivity.this,
                        new PermissionUtils.OnGetPermissionCallback() {
                            @Override
                            public void onGetPermission() {
                                Toast.makeText(TestPermissionActivity.this,
                                        "Get group permissions!", Toast.LENGTH_SHORT).show();
                            }
                        }, PermissionUtils.Permission.CAMERA,
                        PermissionUtils.Permission.LOCATION,
                        PermissionUtils.Permission.STORAGE);
            }
        });
    }
}
