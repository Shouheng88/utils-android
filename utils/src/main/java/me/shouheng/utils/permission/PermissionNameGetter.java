package me.shouheng.utils.permission;

/**
 * The permission name getter interface.
 *
 * @author <a href="mailto:shouheng2020@gmail.com">Shouheng Wang</a>
 * @version 2019-12-21 10:29
 */
public interface PermissionNameGetter {

    /**
     * Get custom permission name.
     *
     * @param permission permission from {@link android.Manifest}
     * @return           permission name
     */
    String getName(String permission);
}
