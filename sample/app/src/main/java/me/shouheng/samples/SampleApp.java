package me.shouheng.samples;

import android.app.Application;

import me.shouheng.utils.Utils;

public class SampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // initialize the utils library
        Utils.init(this);
    }
}
