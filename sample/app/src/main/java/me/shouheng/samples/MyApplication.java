package me.shouheng.samples;

import android.Manifest.permission;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

import me.shouheng.utils.crash.CrashHelper;

public class MyApplication extends TinkerApplication {

    /**
     * 需要空的构造方法
     */
    public MyApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "me.shouheng.samples.SampleApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (ActivityCompat.checkSelfPermission(this, permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            CrashHelper.init(this);
        }
        /*
        第三个参数为SDK调试模式开关，调试模式的行为特性如下：
        - 输出详细的Bugly SDK的Log；
        - 每一条Crash都会被立即上报；
        - 自定义日志将会在Logcat中输出。
        - 建议在测试阶段建议设置成true，发布时设置为false。
         */
//        CrashReport.initCrashReport(getApplicationContext(), "3c71fe5de3", BuildConfig.DEBUG);

        // 如果使用了升级功能，那么只保留下面的配置，注释掉上面的配置即可
//        Bugly.init(getApplicationContext(), "3c71fe5de3",  BuildConfig.DEBUG);
    }
}
