package me.shouheng.utils.ktx

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.annotation.AnimRes
import android.support.v4.app.Fragment
import me.shouheng.utils.app.ActivityUtils
import me.shouheng.utils.constant.ActivityDirection

fun Context.start(activity: Class<out Activity?>) {
    ActivityUtils.start(this, activity)
}

fun Context.start(activity: Class<out Activity?>, @ActivityDirection direction: Int) {
    ActivityUtils.start(this, activity, direction)
}

fun Context.startHomeActivity() {
    val i = Intent(Intent.ACTION_MAIN)
    i.addCategory(Intent.CATEGORY_HOME)
    this.startActivity(i)
}

fun Activity.start(activityClass: Class<out Activity?>, requestCode: Int) {
    ActivityUtils.start(this, activityClass, requestCode)
}

fun Activity.start(activityClass: Class<out Activity?>, requestCode: Int, @ActivityDirection direction: Int) {
    ActivityUtils.start(this, activityClass, requestCode, direction)
}

fun Activity.start(activityClass: Class<out Activity?>, requestCode: Int, @ActivityDirection direction: Int, finishLast: Boolean) {
    ActivityUtils.start(this, activityClass, requestCode, direction, finishLast)
}

fun Fragment.start(activityClass: Class<out Activity?>, requestCode: Int) {
    ActivityUtils.start(this, activityClass, requestCode)
}

fun Fragment.start(activityClass: Class<out Activity?>, requestCode: Int, @ActivityDirection direction: Int) {
    ActivityUtils.start(this, activityClass, requestCode, direction)
}

fun Activity.overridePendingTransition(@ActivityDirection direction: Int) {
    when (direction) {
        ActivityDirection.ANIMATE_FORWARD -> { this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left) }
        ActivityDirection.ANIMATE_BACK -> { this.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right) }
        ActivityDirection.ANIMATE_EASE_IN_OUT -> { this.overridePendingTransition(R.anim.ease_in, R.anim.ease_out) }
        ActivityDirection.ANIMATE_SLIDE_TOP_FROM_BOTTOM -> { this.overridePendingTransition(R.anim.slide_bottom_to_top, R.anim.slide_none_medium_time) }
        ActivityDirection.ANIMATE_SLIDE_BOTTOM_FROM_TOP -> { this.overridePendingTransition(R.anim.slide_none_medium_time, R.anim.slide_top_to_bottom) }
        ActivityDirection.ANIMATE_SCALE_IN -> { this.overridePendingTransition(R.anim.popup_scale_in, R.anim.slide_none) }
        ActivityDirection.ANIMATE_SCALE_OUT -> { this.overridePendingTransition(R.anim.slide_none, R.anim.popup_scale_out) }
        ActivityDirection.ANIMATE_NONE -> {/*do nothing*/ }
        else -> { this.overridePendingTransition(R.anim.magnify_fade_in, R.anim.fade_out) }
    }
}


fun Activity.finish(@ActivityDirection direction: Int) {
    this.finish()
    this.overridePendingTransition(direction)
}

fun Activity.finish(@AnimRes enterAnim: Int, @AnimRes exitAnim: Int) {
    this.finish()
    this.overridePendingTransition(enterAnim, exitAnim)
}

fun <T : Activity?> open(activity: Class<T>): ActivityUtils.Builder<T> = ActivityUtils.open(activity)

fun open(): ActivityUtils.Builder<*> = ActivityUtils.open()