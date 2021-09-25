package me.shouheng.samples.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

/**
 * Make the image span of textview center vertical.
 *
 * @Author wangshouheng
 * @Time 2021/9/9
 */
public class CenterVerticalImageSpan extends ImageSpan {

    public CenterVerticalImageSpan(Drawable d) {
        super(d);
    }

    public CenterVerticalImageSpan(Context context, int resourceId) {
        super(context, resourceId);
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        Drawable b = getDrawable();
        canvas.save();
        int transY = (y + paint.getFontMetricsInt().descent + y + paint.getFontMetricsInt().ascent) / 2 - b.getBounds().bottom / 2; // why?
        canvas.translate(x, transY);
        b.draw(canvas);
        canvas.restore();
    }
}

