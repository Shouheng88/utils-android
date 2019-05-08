package me.shouheng.samples.app;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
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

        StringBuilder sb = new StringBuilder();
        sb.append("Current App: ").append("\n");
        sb.append("Is app rooted : ").append(AppUtils.isAppRoot()).append("\n");
        sb.append("Is app debug : ").append(AppUtils.isAppDebug()).append("\n");
        sb.append("Is app system : ").append(AppUtils.isAppSystem()).append("\n");
        sb.append("Package name : ").append(AppUtils.getPackageName()).append("\n");
        sb.append("App name : ").append(AppUtils.getAppName()).append("\n");
        sb.append("App path : ").append(AppUtils.getAppPath()).append("\n");
        sb.append("App version name : ").append(AppUtils.getAppVersionName()).append("\n");
        sb.append("App version code : ").append(AppUtils.getAppVersionCode()).append("\n");
        sb.append("App signature SHA1 : ").append(AppUtils.getAppSignatureSHA1()).append("\n");
        sb.append("App signature SHA256 : ").append(AppUtils.getAppSignatureSHA256()).append("\n");
        sb.append("App signature MD5 : ").append(AppUtils.getAppSignatureMD5()).append("\n");
        sb.append("\n").append("\n");

        StringBuilder sb2 = new StringBuilder();
        sb2.append("Weibo App: ").append("\n");
        sb2.append("Is app debug : ").append(AppUtils.isAppDebug(WEIBO_APP_PACKAGE)).append("\n");
        sb2.append("Is app system : ").append(AppUtils.isAppSystem(WEIBO_APP_PACKAGE)).append("\n");
        sb2.append("Package name : ").append(WEIBO_APP_PACKAGE).append("\n");
        sb2.append("App name : ").append(AppUtils.getAppName(WEIBO_APP_PACKAGE)).append("\n");
        sb2.append("App path : ").append(AppUtils.getAppPath(WEIBO_APP_PACKAGE)).append("\n");
        sb2.append("App version name : ").append(AppUtils.getAppVersionName(WEIBO_APP_PACKAGE)).append("\n");
        sb2.append("App version code : ").append(AppUtils.getAppVersionCode(WEIBO_APP_PACKAGE)).append("\n");
        sb2.append("App signature SHA1 : ").append(AppUtils.getAppSignatureSHA1(WEIBO_APP_PACKAGE)).append("\n");
        sb2.append("App signature SHA256 : ").append(AppUtils.getAppSignatureSHA256(WEIBO_APP_PACKAGE)).append("\n");
        sb2.append("App signature MD5 : ").append(AppUtils.getAppSignatureMD5(WEIBO_APP_PACKAGE)).append("\n");

        tv1.setText(sb.toString());
        iv1.setImageDrawable(AppUtils.getAppIcon());
        tv2.setText(sb2.toString());
        iv2.setImageDrawable(AppUtils.getAppIcon(WEIBO_APP_PACKAGE));
    }
}
