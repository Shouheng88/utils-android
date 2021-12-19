package me.shouheng.samples.intent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AlertDialog;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.List;

import me.shouheng.samples.R;
import me.shouheng.samples.common.BaseActivity;
import me.shouheng.utils.app.AppUtils;
import me.shouheng.utils.app.IntentUtils;
import me.shouheng.utils.permission.Permission;
import me.shouheng.utils.permission.PermissionUtils;
import me.shouheng.utils.permission.callback.OnGetPermissionCallback;
import me.shouheng.utils.store.PathUtils;

public class TestIntentActivity extends BaseActivity {

    private static final int REQUEST_CODE_CAPTURE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView textView = findViewById(R.id.tv_notification_status);
        textView.setText("Notification status : " + (AppUtils.areNotificationEnabled() ? "on" : "off"));
    }

    public void testLaunchApp(View v) {
        startActivity(IntentUtils.getLaunchAppIntent(getPackageName()));
    }

    public void testLaunchSetting(View v) {
        startActivity(IntentUtils.getLaunchSettingIntent(getPackageName()));
    }

    public void testShareText(View v) {
        startActivity(IntentUtils.getShareTextIntent("Shared Text"));
    }

    public void testDial(View v) {
        startActivity(IntentUtils.getDialIntent("10086"));
    }

    public void testSendSms(View v) {
        startActivity(IntentUtils.getSendSmsIntent("10086", "SMS Content"));
    }

    public void testSendEmail(View v) {
        Intent i = IntentUtils.getSendEmailIntent("shouheng2020@gmail.com", "Subject", "Email body");
        if (!IntentUtils.isIntentAvailable(i)) {
            Toast.makeText(this, "Failed to send email : application not found!", Toast.LENGTH_SHORT).show();
        } else {
            startActivity(i);
        }
    }

    public void testOpenWeb(View v) {
        startActivity(IntentUtils.getOpenWebIntent("http://www.baidu.com"));
    }

    public void testOpenMarket(View v) {
        startActivity(IntentUtils.getLaunchMarketIntent(getPackageName()));
    }

    public void testCapture(View v) {
        PermissionUtils.checkPermissions(this, new OnGetPermissionCallback() {
            @Override
            public void onGetPermission() {
                File file = new File(PathUtils.getExternalAppFilesPath() + File.separator + "capture.jpeg");
                Uri data;
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                    data = Uri.fromFile(file);
                } else {
                    String authority = getPackageName() + ".fileProvider";
                    data = FileProvider.getUriForFile(TestIntentActivity.this, authority, file);
                }
                startActivityForResult(IntentUtils.getCaptureIntent(data), REQUEST_CODE_CAPTURE);
            }
        }, Permission.CAMERA, Permission.STORAGE);
    }

    public void testGetAppInfo(View v) {
        Intent i = IntentUtils.getShareTextIntent("SHARED TEXT");
        List<IntentUtils.AppInfo> appInfos = IntentUtils.getMatchAppInfos(i);
        StringBuilder sb = new StringBuilder();
        for (IntentUtils.AppInfo appInfo : appInfos) {
            sb.append(appInfo.toString());
            sb.append("\n");
        }
        new AlertDialog.Builder(this)
                .setTitle("Matched Apps")
                .setMessage(sb.toString())
                .setPositiveButton("OK", null)
                .create().show();
    }

    public void openNotification(View v) {
        Intent intent = IntentUtils.getNotificationSettingIntent(true);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_CAPTURE:
                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(this, "Got capture image!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
