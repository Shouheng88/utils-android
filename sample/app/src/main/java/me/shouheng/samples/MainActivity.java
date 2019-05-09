package me.shouheng.samples;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;

import me.shouheng.samples.activity.TestActivityHelper;
import me.shouheng.samples.app.TestAppUtilsActivity;
import me.shouheng.samples.common.BaseActivity;
import me.shouheng.samples.crash.TestCrashActivity;
import me.shouheng.samples.intent.TestIntentActivity;
import me.shouheng.samples.log.TestLogActivity;
import me.shouheng.samples.permission.TestPermissionActivity;
import me.shouheng.samples.shell.TestShellActivity;
import me.shouheng.samples.common.FileUtils;
import me.shouheng.samples.store.TestSpUtilsActivity;
import me.shouheng.utils.app.ActivityHelper;
import me.shouheng.utils.stability.CrashHelper;
import me.shouheng.utils.stability.CrashHelper.OnCrashListener;
import me.shouheng.utils.stability.LogUtils;
import me.shouheng.utils.permission.PermissionUtils;
import me.shouheng.utils.permission.callback.OnGetPermissionCallback;

/**
 * @author shouh
 * @version $Id: MainActivity, v 0.1 2018/11/22 12:10 shouh Exp$
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_permission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityHelper.start(MainActivity.this, TestPermissionActivity.class);
            }
        });
        findViewById(R.id.btn_activity_helper).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityHelper.start(MainActivity.this, TestActivityHelper.class);
            }
        });
        findViewById(R.id.btn_crash).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityHelper.start(MainActivity.this, TestCrashActivity.class);
            }
        });
        findViewById(R.id.btn_log).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityHelper.start(MainActivity.this, TestLogActivity.class);
            }
        });

        PermissionUtils.checkStoragePermission(this, new OnGetPermissionCallback() {
            @SuppressLint("MissingPermission")
            @Override
            public void onGetPermission() {
                CrashHelper.init(getApplication(), FileUtils.getExternalStoragePublicCrashDir(), new OnCrashListener() {
                    @Override
                    public void onCrash(String crashInfo, Throwable e) {
                        LogUtils.e(crashInfo);
                    }
                });
                LogUtils.getConfig()
                        .setLog2FileSwitch(true)
                        .setDir(FileUtils.getExternalStoragePublicLogDir())
                        .setFileFilter(LogUtils.W);
            }
        });
    }

    public void testIntentUtils(View view) {
        ActivityHelper.start(this, TestIntentActivity.class);
    }

    public void testShellUtils(View view) {
        ActivityHelper.start(this, TestShellActivity.class);
    }

    public void testAppUtils(View v) {
        ActivityHelper.start(this, TestAppUtilsActivity.class);
    }

    public void testSPUtils(View v) {
        ActivityHelper.start(this, TestSpUtilsActivity.class);
    }
}
