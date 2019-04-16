package me.shouheng.samples;

import android.Manifest.permission;
import android.app.Application;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import me.shouheng.utils.crash.CrashHelper;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (ActivityCompat.checkSelfPermission(this, permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            CrashHelper.init(this);
        }
    }
}
