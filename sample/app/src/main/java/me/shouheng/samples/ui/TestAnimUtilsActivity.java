package me.shouheng.samples.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import me.shouheng.samples.R;
import me.shouheng.utils.ui.AnimUtils;
import me.shouheng.utils.ui.ViewUtils;

/**
 * Anim utils test
 *
 * @author <a href="mailto:shouheng2015@gmail.com">WngShhng</a>
 * @version 2020-05-24 12:53
 */
public class TestAnimUtilsActivity extends AppCompatActivity {

    private View obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_utils);
        obj = findViewById(R.id.v);
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

    public void shake(View view) {
        AnimUtils.shake(obj);
    }

    public void changeColor(View view) {
        AnimUtils.changeColor(Color.RED, Color.BLUE, 3000, new AnimUtils.OnColorChangeListener() {
            @Override
            public void onColorChanged(int color) {
                obj.setBackgroundColor(color);
            }
        });
    }

    public void onZoomIn(View view) {
        AnimUtils.zoomIn(obj, .5f, 0f, 300);
    }

    public void onZoomOut(View view) {
        AnimUtils.zoomOut(obj, .5f, 300);
    }

    public void scaleUpDown(View view) {
        AnimUtils.scaleUpDown(obj, 1200);
    }

    public void animateHeight(View view) {
        AnimUtils.animateHeight(obj, 0, ViewUtils.dp2px(100f));
    }

    public void popupIn(View view) {
        AnimUtils.popupIn(obj, 1000);
    }

    public void popupOut(View view) {
        AnimUtils.popupOut(obj, 1000, null);
    }
}
