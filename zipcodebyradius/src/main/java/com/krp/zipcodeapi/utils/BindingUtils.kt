package com.krp.zipcodeapi.utils

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

/**
 * Created by Rakesh Praneeth.
 */
object BindingUtils {

    /**
     * Makes the view either visible or gone depending on the value provided. If null, we hide the view.
     * @param boolean either true/false or null.
     */
    @JvmStatic
    @BindingAdapter("showIf")
    fun View.setVisibility(boolean: Boolean?) {
        isVisible = boolean ?: false
    }

    /**
     * Sets the text and make the text view visible if not null or not empty. Otherwise, will hide
     * the view.
     * @param input text that needs to be shown.
     */
    @JvmStatic
    @BindingAdapter("setTextAndShow")
    fun AppCompatTextView.setTextAndShow(input: String?) {
        if (input.isNullOrEmpty().not()) {
            text = input
            isVisible = true
        } else {
            isVisible = false
        }
    }
}
