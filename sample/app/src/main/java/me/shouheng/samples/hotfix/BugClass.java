package me.shouheng.samples.hotfix;

import android.util.Log;

public class BugClass {

    private static final String TAG = "BugClass";

    public String bug() {
        String str = null;
//        Log.e(TAG, "length" + str.length());
        return "This is a bug";
    }
}
