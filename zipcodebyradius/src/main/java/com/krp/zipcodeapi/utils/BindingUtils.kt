package com.krp.zipcodeapi.utils

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

/**
 * Created by Rakesh Praneeth.
 */
object BindingUtils {

    @JvmStatic
    @BindingAdapter("showIf")
    fun View.setVisibility(boolean: Boolean?) {
        isVisible = boolean ?: false
    }
}
