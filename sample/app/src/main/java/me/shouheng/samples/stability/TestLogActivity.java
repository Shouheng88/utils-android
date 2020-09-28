package me.shouheng.samples.stability;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import me.shouheng.samples.R;
import me.shouheng.samples.common.BaseActivity;
import me.shouheng.utils.stability.L;

import static me.shouheng.utils.ktx.LogKtxKt.loga;
import static me.shouheng.utils.ktx.LogKtxKt.logd;
import static me.shouheng.utils.ktx.LogKtxKt.loge;
import static me.shouheng.utils.ktx.LogKtxKt.logi;
import static me.shouheng.utils.ktx.LogKtxKt.logv;
import static me.shouheng.utils.ktx.LogKtxKt.logw;

public class TestLogActivity extends BaseActivity {

    private int cnt = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_test);
        EditText et = findViewById(R.id.et);
        findViewById(R.id.btn_log).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                cnt++;
                switch (cnt % 6) {
                    case 0:
                        logv("Logging v");
                        break;
                    case 1:
                        logd("Logging d");
                        break;
                    case 2:
                        logi("Logging i");
                        break;
                    case 3:
                        logw("Logging w");
                        break;
                    case 4:
                        loge("Logging e");
                        break;
                    case 5:
                        loga("Logging a");
                        break;
                    default:
                        // do nothing
                }
            }
        });
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // empty
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // empty
            }

            @Override
            public void afterTextChanged(Editable s) {
                L.d(s);
            }
        });
    }
}
