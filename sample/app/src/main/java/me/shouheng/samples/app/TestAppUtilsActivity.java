package me.shouheng.samples.app;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import me.shouheng.samples.R;
import me.shouheng.samples.common.BaseActivity;
import me.shouheng.utils.app.AppUtils;

public class TestAppUtilsActivity extends BaseActivity {

    public static final String WEIBO_APP_PACKAGE = "com.sina.weibo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_app_utils);

        TextView tv1 = findViewById(R.id.tv);
        ImageView iv1 = findViewById(R.id.iv1);
        TextView tv2 = findViewById(R.id.tv2);
        ImageView iv2 = findViewById(R.id.iv2);

        String sb = "Current App: " + "\n" +
                "Is app rooted : " + AppUtils.isAppRoot() + "\n" +
                "Is app debug : " + AppUtils.isAppDebug() + "\n" +
                "Is app system : " + AppUtils.isAppSystem() + "\n" +
                "Package name : " + AppUtils.getPackageName() + "\n" +
                "App name : " + AppUtils.getAppName() + "\n" +
                "App path : " + AppUtils.getAppPath() + "\n" +
                "App version name : " + AppUtils.getAppVersionName() + "\n" +
                "App version code : " + AppUtils.getAppVersionCode() + "\n" +
                "App signature SHA1 : " + AppUtils.getAppSignatureSHA1() + "\n" +
                "App signature SHA256 : " + AppUtils.getAppSignatureSHA256() + "\n" +
                "App signature MD5 : " + AppUtils.getAppSignatureMD5() + "\n" +
                "\n" + "\n";
        tv1.setText(sb);
        iv1.setImageDrawable(AppUtils.getAppIcon());

        String sb2 = "Weibo App: " + "\n" +
                "Is app debug : " + AppUtils.isAppDebug(WEIBO_APP_PACKAGE) + "\n" +
                "Is app system : " + AppUtils.isAppSystem(WEIBO_APP_PACKAGE) + "\n" +
                "Package name : " + WEIBO_APP_PACKAGE + "\n" +
                "App name : " + AppUtils.getAppName(WEIBO_APP_PACKAGE) + "\n" +
                "App path : " + AppUtils.getAppPath(WEIBO_APP_PACKAGE) + "\n" +
                "App version name : " + AppUtils.getAppVersionName(WEIBO_APP_PACKAGE) + "\n" +
                "App version code : " + AppUtils.getAppVersionCode(WEIBO_APP_PACKAGE) + "\n" +
                "App signature SHA1 : " + AppUtils.getAppSignatureSHA1(WEIBO_APP_PACKAGE) + "\n" +
                "App signature SHA256 : " + AppUtils.getAppSignatureSHA256(WEIBO_APP_PACKAGE) + "\n" +
                "App signature MD5 : " + AppUtils.getAppSignatureMD5(WEIBO_APP_PACKAGE) + "\n";
        tv2.setText(sb2);
        iv2.setImageDrawable(AppUtils.getAppIcon(WEIBO_APP_PACKAGE));
    }
}
