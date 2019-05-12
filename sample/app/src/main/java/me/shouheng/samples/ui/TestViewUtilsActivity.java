package me.shouheng.samples.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import me.shouheng.samples.R;
import me.shouheng.utils.ui.ViewUtils;

public class TestViewUtilsActivity extends AppCompatActivity {

    private boolean isAlpha = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view_utils);
        final TextView tvMeasure = findViewById(R.id.tv_measure_result);
        ViewUtils.forceGetViewSize(tvMeasure, new ViewUtils.onGetSizeListener() {
            @Override
            public void onGetSize(View view) {
                tvMeasure.setText("W:" + view.getWidth() + ", H:" + view.getHeight());
            }
        });
    }

    public void showSortInput(View view) {
        ViewUtils.showSoftInput(view);
    }

    public void hideSortInput(View view) {
        ViewUtils.hideSoftInput(view);
    }

    public void toggleSortInput(View view) {
        ViewUtils.toggleSoftInput();
    }

    public void toggleAlpha(View view) {
        isAlpha = !isAlpha;
        ViewUtils.setAlpha(view, isAlpha ? 0.5f : 1f);
    }
}
