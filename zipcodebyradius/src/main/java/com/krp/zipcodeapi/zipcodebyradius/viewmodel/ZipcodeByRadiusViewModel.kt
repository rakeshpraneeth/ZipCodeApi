package com.krp.zipcodeapi.zipcodebyradius.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Rakesh Praneeth.
 */
class ZipcodeByRadiusViewModel : ViewModel() {

    /**
     * Observable to listen and notify the zipcode value provided by user and to show on UI.
     * It calls the method [checkIfZipCodeAndRadiusProvided] to check the button state.
     */
    val zipCode: ObservableField<String> = object : ObservableField<String>() {
        override fun set(value: String?) {
            super.set(value)
            checkIfZipCodeAndRadiusProvided()
        }
    }

    /**
     * Observable to listen and notify the radius value provided by user and to show on UI.
     * It calls the method [checkIfZipCodeAndRadiusProvided] to check the button state.
     */
    val radius: ObservableField<String> = object : ObservableField<String>() {
        override fun set(value: String?) {
            super.set(value)
            checkIfZipCodeAndRadiusProvided()
        }
    }

    /**
     * Notifies if the button is enabled or not.
     */
    private val _buttonEnabled = MutableLiveData<Boolean>()
    val buttonEnabled: LiveData<Boolean> = _buttonEnabled

    /**
     * Called when zipcode or radius input value is changed. If both are not null or empty, button
     * should be enabled. Otherwise, button should be disabled.
     */
    private fun checkIfZipCodeAndRadiusProvided() {
        _buttonEnabled.value = zipCode.get().isNullOrEmpty().not()
                && radius.get().isNullOrEmpty().not()
    }

    /**
     * Uses the zipcode and radius to search for the nearby zipcodes.
     */
    fun onSearch() {
        Log.i(TAG, "zipcode: ${zipCode.get()} and radius: ${radius.get()}")
    }

    companion object {
        private const val TAG = "ZipcodeByRadiusViewModel"
    }
}