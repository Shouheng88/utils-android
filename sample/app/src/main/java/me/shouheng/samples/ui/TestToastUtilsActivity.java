package me.shouheng.samples.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import me.shouheng.samples.R;
import me.shouheng.utils.UtilsApp;
import me.shouheng.utils.ui.ImageUtils;
import me.shouheng.utils.ui.ToastUtils;
import me.shouheng.utils.ui.ViewUtils;

public class TestToastUtilsActivity extends AppCompatActivity {

    private int count = 0;
    private @ToastUtils.ToastStyle int style = ToastUtils.ToastStyle.NORMAL;
    private String text = "普通样式|可自定义";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toast_utils);
    }

    public void showToast(View view) {
        ToastUtils.showShort("Toast_"  + count++);
    }

    public void showCustomMsgToast(View view) {
        ToastUtils.setMsgColor(Color.GREEN);
        ToastUtils.setMsgTextSize(20);
        ToastUtils.showShort("Toast_"  + count++);
    }

    public void showCenterToast(View view) {
        ToastUtils.setGravity(Gravity.CENTER, 0, 0);
        ToastUtils.showShort("Toast_"  + count++);
    }

    public void cancelToast(View view) {
        ToastUtils.cancel();
        ToastUtils.setToastViewCallback(null);
    }

    public void customToast(View view) {
        if (ToastUtils.getToastViewCallback() == null) {
            ToastUtils.setToastViewCallback(new ToastUtils.ToastViewCallback() {
                @Override
                public View getView(CharSequence text, int style) {
                    LayoutInflater inflater = (LayoutInflater) UtilsApp.getApp().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    ToastBackgroundLayout layout = (ToastBackgroundLayout) inflater.inflate(R.layout.layout_custom_toast, null);
                    TextView textView = layout.findViewById(R.id.toastText);
                    switch (style) {
                        case ToastUtils.ToastStyle.NORMAL:
                            textView.setText(text);
                            break;
                        case ToastUtils.ToastStyle.INFO: {
                            Drawable drawable = ImageUtils.tintDrawable(R.drawable.ic_baseline_info_24, Color.BLACK);
                            drawable.setBounds(0, 0, ViewUtils.dp2px(14f), ViewUtils.dp2px(14f));
                            SpannableStringBuilder builder = new SpannableStringBuilder("  " + text);
                            builder.setSpan(new CenterVerticalImageSpan(drawable), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            textView.setText(builder);
                            break;
                        }
                        case ToastUtils.ToastStyle.WARN: {
                            Drawable drawable = ImageUtils.tintDrawable(R.drawable.ic_baseline_error_outline_24, 0xFFFF9800);
                            drawable.setBounds(0, 0, ViewUtils.dp2px(14f), ViewUtils.dp2px(14f));
                            SpannableStringBuilder builder = new SpannableStringBuilder("  " + text);
                            builder.setSpan(new CenterVerticalImageSpan(drawable), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            textView.setText(builder);
                            textView.setTextColor(0xFFFF9800);
                            break;
                        }
                        case ToastUtils.ToastStyle.ERROR: {
                            Drawable drawable = ImageUtils.tintDrawable(R.drawable.ic_baseline_cancel_24, Color.RED);
                            drawable.setBounds(0, 0, ViewUtils.dp2px(14f), ViewUtils.dp2px(14f));
                            SpannableStringBuilder builder = new SpannableStringBuilder("  " + text);
                            builder.setSpan(new CenterVerticalImageSpan(drawable), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            textView.setText(builder);
                            textView.setTextColor(Color.RED);
                            break;
                        }
                    }
                    return layout;
                }
            });
        }
        ToastUtils.showShort(text, style);
        switch (style) {
            case ToastUtils.ToastStyle.NORMAL:
                style = ToastUtils.ToastStyle.INFO;
                text = "提示样式|可自定义";
                break;
            case ToastUtils.ToastStyle.INFO:
                style = ToastUtils.ToastStyle.WARN;
                text = "警告样式|可自定义";
                break;
            case ToastUtils.ToastStyle.WARN:
                style = ToastUtils.ToastStyle.ERROR;
                text = "错误样式|可自定义";
                break;
            case ToastUtils.ToastStyle.ERROR:
                style = ToastUtils.ToastStyle.NORMAL;
                text = "普通样式|可自定义";
                break;
        }
    }
}
