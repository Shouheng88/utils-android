package me.shouheng.utils.permission;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Permission constants
 *
 * @author <a href="mailto:shouheng2020@gmail.com">Shouheng Wang</a>
 * @version 2020-03-09 12:47
 */
@IntDef({
        Permission.STORAGE,
        Permission.PHONE_STATE,
        Permission.LOCATION,
        Permission.MICROPHONE,
        Permission.SMS,
        Permission.SENSORS,
        Permission.CONTACTS,
        Permission.CAMERA,
        Permission.CALENDAR
})
@Retention(RetentionPolicy.SOURCE)
public @interface Permission {
    int STORAGE             = 0xFF01;
    int PHONE_STATE         = 0xFF02;
    int LOCATION            = 0xFF03;
    int MICROPHONE          = 0xFF04;
    int SMS                 = 0xFF05;
    int SENSORS             = 0xFF06;
    int CONTACTS            = 0xFF07;
    int CAMERA              = 0xFF08;
    int CALENDAR            = 0xFF09;
}
