package me.shouheng.utils.ktx

import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes

/** Find view by id quick method. */
fun <T: View> ViewGroup.f(@IdRes id: Int): T? {
    return findViewById(id)
}
