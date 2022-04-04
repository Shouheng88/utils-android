package me.shouheng.utils;

/**
 * The platform information.
 *
 * @author Shouheng Wang
 */
public class Platform {
    public static final boolean DEPENDENCY_MMKV_ANALYTICS;

    static {
        DEPENDENCY_MMKV_ANALYTICS    = findClassByClassName("com.tencent.mmkv.MMKV");
    }

    private static boolean findClassByClassName(String className) {
        boolean hasDependency;
        try {
            Class.forName(className);
            hasDependency = true;
        } catch (ClassNotFoundException e) {
            hasDependency = false;
        }
        return hasDependency;
    }
}
