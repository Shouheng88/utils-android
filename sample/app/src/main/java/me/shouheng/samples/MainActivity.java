package me.shouheng.samples;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import java.io.File;

import me.shouheng.samples.activity.TestActivityHelper;
import me.shouheng.samples.permission.TestPermissionActivity;
import me.shouheng.utils.activity.ActivityHelper;
import me.shouheng.utils.crash.CrashHelper;

/**
 * @author shouh
 * @version $Id: MainActivity, v 0.1 2018/11/22 12:10 shouh Exp$
 */
public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

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
                for (File file : CrashHelper.listCrashFiles()) {
                    Log.d(TAG, file.getAbsolutePath());
                }
                throw new IllegalStateException("Throw crash exception ...");
            }
        });
    }
}
