package me.shouheng.samples.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import me.shouheng.samples.R;
import me.shouheng.utils.ui.AnimUtils;

/**
 * Anim utils test
 *
 * @author <a href="mailto:shouheng2015@gmail.com">WngShhng</a>
 * @version 2020-05-24 12:53
 */
public class TestAnimUtilsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_utils);
        final View obj = findViewById(R.id.v);
        findViewById(R.id.btn_fade_in).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimUtils.fadeIn(obj, 2000, null, true);
            }
        });
        findViewById(R.id.btn_fade_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimUtils.fadeOut(obj, 2000, null, true);
            }
        });
        findViewById(R.id.btn_slide_in).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimUtils.slideIn(obj, 2000, null, true, AnimUtils.UIDirection.RIGHT_TO_LEFT);
            }
        });
        findViewById(R.id.btn_slide_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimUtils.slideOut(obj, 2000, null, true, AnimUtils.UIDirection.LEFT_TO_RIGHT);
            }
        });
        findViewById(R.id.btn_shinning).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimUtils.shining(obj, 2888, 6, 0, 0.66f, 1.0f, 0);
            }
        });
    }
}
