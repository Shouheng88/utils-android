package me.shouheng.utils.store;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.preference.PreferenceManager;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;

import com.tencent.mmkv.MMKV;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import me.shouheng.utils.Platform;
import me.shouheng.utils.UtilsApp;
import me.shouheng.utils.data.StringUtils;

/**
 * An Android Key-value typed data storage utils. This utils allow you to switch
 * inner storage between {@link SharedPreferences} and {@link MMKV}.
 *
 * To learn how to use MMKV, see <a href="https://github.com/Tencent/MMKV">MMKV homepage</a>.
 *
 * @author Shouheng Wang 2019-05-08 21:30
 */
public final class KV {

    @IntDef({Storage.SHARED_PREFERENCES, Storage.MMKV})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Storage {
        int SHARED_PREFERENCES      = 1;
        int MMKV                    = 2;
    }

    private static int DEFAULT_STORAGE_TYPE = Storage.SHARED_PREFERENCES;
    private static final Map<String, KV> KV_MAP = new ConcurrentHashMap<>();
    private SharedPreferences sp;

    /** Set default storage type. */
    public static void setDefaultStorageType(@Storage int storage) {
        DEFAULT_STORAGE_TYPE = storage;
    }

    /*-------------------------------------get instance----------------------------------------*/

    public static KV get() {
        return get("", Context.MODE_PRIVATE, DEFAULT_STORAGE_TYPE);
    }

    public static KV get(final int mode) {
        return get("", mode, DEFAULT_STORAGE_TYPE);
    }

    public static KV get(String spName) {
        return get(spName, Context.MODE_PRIVATE, DEFAULT_STORAGE_TYPE);
    }

    public static KV of(final @Storage int storage) {
        return get("", Context.MODE_PRIVATE, storage);
    }

    public static KV of(final int mode, final @Storage int storage) {
        return get("", mode, storage);
    }

    public static KV of(String spName, final @Storage int storage) {
        return get(spName, Context.MODE_PRIVATE, storage);
    }

    /** Get an {@link KV} instance from {@link #KV_MAP} by spName and storage type. */
    public static KV get(String spName, final int mode, final @Storage int storage) {
        if (StringUtils.isSpace(spName)) {
            spName = getDefaultSharedPreferencesName();
        }
        String key = getMapKeyName(spName, storage);
        KV kv = KV_MAP.get(key);
        if (kv == null) {
            kv = new KV(spName, mode, storage);
            KV_MAP.put(key, kv);
        }
        return kv;
    }

    /*-------------------------------------instance methods----------------------------------------*/

    public void put(@NonNull final String key, final String value) {
        put(key, value, false);
    }

    public void put(@NonNull final String key, final String value, final boolean isCommit) {
        if (isCommit) {
            sp.edit().putString(key, value).commit();
        } else {
            sp.edit().putString(key, value).apply();
        }
    }

    public String getString(@NonNull final String key) {
        return getString(key, "");
    }

    public String getString(@NonNull final String key, final String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    public void put(@NonNull final String key, final int value) {
        put(key, value, false);
    }

    public void put(@NonNull final String key, final int value, final boolean isCommit) {
        if (isCommit) {
            sp.edit().putInt(key, value).commit();
        } else {
            sp.edit().putInt(key, value).apply();
        }
    }

    public int getInt(@NonNull final String key) {
        return getInt(key, -1);
    }

    public int getInt(@NonNull final String key, final int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    public void put(@NonNull final String key, final long value) {
        put(key, value, false);
    }

    public void put(@NonNull final String key, final long value, final boolean isCommit) {
        if (isCommit) {
            sp.edit().putLong(key, value).commit();
        } else {
            sp.edit().putLong(key, value).apply();
        }
    }

    public long getLong(@NonNull final String key) {
        return getLong(key, -1L);
    }

    public long getLong(@NonNull final String key, final long defaultValue) {
        return sp.getLong(key, defaultValue);
    }

    public void put(@NonNull final String key, final float value) {
        put(key, value, false);
    }

    public void put(@NonNull final String key, final float value, final boolean isCommit) {
        if (isCommit) {
            sp.edit().putFloat(key, value).commit();
        } else {
            sp.edit().putFloat(key, value).apply();
        }
    }

    public float getFloat(@NonNull final String key) {
        return getFloat(key, -1f);
    }

    public float getFloat(@NonNull final String key, final float defaultValue) {
        return sp.getFloat(key, defaultValue);
    }

    public void put(@NonNull final String key, final boolean value) {
        put(key, value, false);
    }

    public void put(@NonNull final String key, final boolean value, final boolean isCommit) {
        if (isCommit) {
            sp.edit().putBoolean(key, value).commit();
        } else {
            sp.edit().putBoolean(key, value).apply();
        }
    }

    public boolean getBoolean(@NonNull final String key) {
        return getBoolean(key, false);
    }

    public boolean getBoolean(@NonNull final String key, final boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    public void put(@NonNull final String key, final Set<String> value) {
        put(key, value, false);
    }

    public void put(@NonNull final String key, final Set<String> value, final boolean isCommit) {
        if (isCommit) {
            sp.edit().putStringSet(key, value).commit();
        } else {
            sp.edit().putStringSet(key, value).apply();
        }
    }

    public Set<String> getStringSet(@NonNull final String key) {
        return getStringSet(key, Collections.<String>emptySet());
    }

    public Set<String> getStringSet(@NonNull final String key, final Set<String> defaultValue) {
        return sp.getStringSet(key, defaultValue);
    }

    public Map<String, ?> getAll() {
        return sp.getAll();
    }

    public boolean contains(@NonNull final String key) {
        return sp.contains(key);
    }

    public void remove(@NonNull final String key) {
        remove(key, false);
    }

    public void remove(@NonNull final String key, final boolean isCommit) {
        if (isCommit) {
            sp.edit().remove(key).commit();
        } else {
            sp.edit().remove(key).apply();
        }
    }

    public void clear() {
        clear(false);
    }

    public void clear(final boolean isCommit) {
        if (isCommit) {
            sp.edit().clear().commit();
        } else {
            sp.edit().clear().apply();
        }
    }

    public void setOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
        sp.registerOnSharedPreferenceChangeListener(listener);
    }

    public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
        sp.unregisterOnSharedPreferenceChangeListener(listener);
    }

    /*-------------------------------------inner methods----------------------------------------*/

    private KV(final String spName, final int mode, final @Storage int storage) {
        if (Platform.DEPENDENCY_MMKV_ANALYTICS && storage == Storage.MMKV) {
            sp = MMKV.mmkvWithID(spName, mode);
        } else {
            sp = UtilsApp.getApp().getSharedPreferences(spName, mode);
        }
    }

    private static String getDefaultSharedPreferencesName() {
        if (VERSION.SDK_INT >= VERSION_CODES.N) {
            return PreferenceManager.getDefaultSharedPreferencesName(UtilsApp.getApp());
        } else {
            return UtilsApp.getApp().getPackageName() + "_preferences";
        }
    }

    /** Get key for {@link #KV_MAP}. */
    private static String getMapKeyName(String spName, final @Storage int storage) {
        return spName + "_" + storage;
    }
}
