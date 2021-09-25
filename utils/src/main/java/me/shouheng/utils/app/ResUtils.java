package me.shouheng.utils.app;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION_CODES;
import androidx.annotation.ArrayRes;
import androidx.annotation.BoolRes;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.FontRes;
import androidx.annotation.IntegerRes;
import androidx.annotation.PluralsRes;
import androidx.annotation.RawRes;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;
import androidx.appcompat.content.res.AppCompatResources;
import android.util.TypedValue;

import java.io.InputStream;

import me.shouheng.utils.UtilsApp;

/**
 * @author Shouheng Wang 2019-05-08 20:20
 */
public final class ResUtils {

    public static float getAttrFloatValue(Context context, int attrRes){
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attrRes, typedValue, true);
        return typedValue.getFloat();
    }

    public static int getAttrColor(Context context, int attrRes){
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attrRes, typedValue, true);
        return typedValue.data;
    }

    public static ColorStateList getAttrColorStateList(Context context, int attrRes){
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attrRes, typedValue, true);
        return ContextCompat.getColorStateList(context, typedValue.resourceId);
    }

    public static Drawable getAttrDrawable(Context context, int attrRes){
        int[] attrs = new int[] { attrRes };
        TypedArray ta = context.obtainStyledAttributes(attrs);
        Drawable drawable = getAttrDrawable(context, ta, 0);
        ta.recycle();
        return drawable;
    }

    public static Drawable getAttrDrawable(Context context, TypedArray typedArray, int index){
        TypedValue value = typedArray.peekValue(index);
        if (value != null) {
            if (value.type != TypedValue.TYPE_ATTRIBUTE && value.resourceId != 0) {
                return AppCompatResources.getDrawable(context, value.resourceId);
            }
        }
        return null;
    }

    public static int getAttrDimen(Context context, int attrRes){
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attrRes, typedValue, true);
        return TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
    }

    /*----------------------------------wrapper methods--------------------------------------*/

    public static int[] getIntArray(@ArrayRes int id) {
        return UtilsApp.getApp().getResources().getIntArray(id);
    }

    public static CharSequence[] getTextArray(@ArrayRes int id) {
        return UtilsApp.getApp().getResources().getTextArray(id);
    }

    public static String[] getStringArray(@ArrayRes int id) {
        return UtilsApp.getApp().getResources().getStringArray(id);
    }

    @ColorInt
    public static int getColor(@ColorRes int id) {
        return UtilsApp.getApp().getResources().getColor(id);
    }

    public static String getString(@StringRes int id) {
        return UtilsApp.getApp().getResources().getString(id);
    }

    public static String getString(@StringRes int id, Object... formatArgs) {
        return UtilsApp.getApp().getResources().getString(id, formatArgs);
    }

    public static CharSequence getText(@StringRes int id) {
        return UtilsApp.getApp().getResources().getText(id);
    }

    public static CharSequence getQuantityText(@PluralsRes int id, int quantity) {
        return UtilsApp.getApp().getResources().getQuantityText(id, quantity);
    }

    public static String getQuantityString(@PluralsRes int id, int quantity) {
        return UtilsApp.getApp().getResources().getQuantityString(id, quantity);
    }

    public static String getQuantityString(@PluralsRes int id, int quantity, Object... formatArgs) {
        return UtilsApp.getApp().getResources().getQuantityString(id, quantity, formatArgs);
    }

    public static Drawable getDrawable(@DrawableRes int id) {
        return UtilsApp.getApp().getResources().getDrawable(id);
    }

    @TargetApi(VERSION_CODES.O)
    public static Typeface getFont(@FontRes int id) {
        return UtilsApp.getApp().getResources().getFont(id);
    }

    public static float getDimension(@DimenRes int id) {
        return UtilsApp.getApp().getResources().getDimension(id);
    }

    public static boolean getBoolean(@BoolRes int id) {
        return UtilsApp.getApp().getResources().getBoolean(id);
    }

    public static int getInteger(@IntegerRes int id) {
        return UtilsApp.getApp().getResources().getInteger(id);
    }

    public static InputStream openRawResource(@RawRes int id) {
        return UtilsApp.getApp().getResources().openRawResource(id);
    }

    public static AssetFileDescriptor openRawResourceFd(@RawRes int id) {
        return UtilsApp.getApp().getResources().openRawResourceFd(id);
    }

    public static AssetManager getAssets() {
        return UtilsApp.getApp().getResources().getAssets();
    }

    /*----------------------------------inner methods--------------------------------------*/

    private ResUtils() {
        throw new UnsupportedOperationException("u can't initialize me!");
    }

}
