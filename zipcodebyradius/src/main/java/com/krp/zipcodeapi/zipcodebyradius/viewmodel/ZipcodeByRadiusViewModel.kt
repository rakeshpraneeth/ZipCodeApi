package com.krp.zipcodeapi.zipcodebyradius.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krp.zipcodeapi.api.ResponseStatus
import com.krp.zipcodeapi.zipcodebyradius.R
import com.krp.zipcodeapi.zipcodebyradius.adapter.ZipcodeListItem
import com.krp.zipcodeapi.zipcodebyradius.model.ZipcodeResponse
import com.krp.zipcodeapi.zipcodebyradius.repository.ZipcodeByRadiusRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Rakesh Praneeth.
 */
// Enhancement: Can use dagger to inject the repository
class ZipcodeByRadiusViewModel(
    private val repository: ZipcodeByRadiusRepository
) : ViewModel() {

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
     * Notifies if the progress bar to be shown to the user.
     */
    private val _buttonEnabled = MutableLiveData<Boolean>()
    val buttonEnabled: LiveData<Boolean> = _buttonEnabled


    /**
     * Notifies if the progress bar to be shown to the user.
     */
    private val _progressBar = MutableLiveData<Boolean>()
    val progressBar: LiveData<Boolean> = _progressBar

    /**
     * Notifies if a which message to be shown to the user.
     */
    private val _message = MutableLiveData<Int>()
    val message: LiveData<Int> = _message

    /**
     * Notifies the list of items if the response is successful.
     */
    private val _listItems = MutableLiveData<List<ZipcodeListItem>>()
    val listItems: LiveData<List<ZipcodeListItem>> = _listItems

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
        viewModelScope.launch(Dispatchers.IO) {
            val zipCodeValue = zipCode.get() ?: ""
            val radiusValue = radius.get() ?: ""
            _progressBar.postValue(true)
            val response = repository.getZipcodesByRadius(zipCodeValue, radiusValue)
            if (response is ResponseStatus.Success) {
                onResponseObtained(zipCodeValue, response.data)
            } else if (response is ResponseStatus.Failure) {
                _message.postValue(R.string.generic_error_message)
            }
            _progressBar.postValue(false)
        }
    }

    /**
     * Called when the response is successful.
     */
    private fun onResponseObtained(userProvidedZipCode: String, zipcodeResponse: ZipcodeResponse) {
        zipcodeResponse.zipcodes?.let { zipcodes ->
            if (zipcodes.isEmpty().not()) {
                _listItems.postValue(zipcodes.filter {
                    userProvidedZipCode != it.zipcode
                }.map { ZipcodeListItem(it) })
            } else {
                _message.postValue(R.string.no_zipcodes_available_nearby)
            }
        }
    }
}