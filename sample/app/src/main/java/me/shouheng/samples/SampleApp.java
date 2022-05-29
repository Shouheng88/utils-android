package me.shouheng.samples;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.tencent.mmkv.MMKV;

import me.shouheng.utils.UtilsApp;
import me.shouheng.utils.stability.L;
import me.shouheng.utils.store.KV;

public class SampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        // initialize the utils library
        UtilsApp.init(this);
        String rootDir = MMKV.initialize(this);
        L.d("mmkv root: " + rootDir);
        KV.setDefaultStorageType(KV.Storage.MMKV);
    }
}
