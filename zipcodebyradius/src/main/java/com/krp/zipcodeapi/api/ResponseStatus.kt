package com.krp.zipcodeapi.api

/**
 * Created by Rakesh Praneeth.
 * States of the API calls with values.
 */
sealed class ResponseStatus<out T : Any> {
    /**
     * Notifies that the data is loading.
     */
    object Loading : ResponseStatus<Nothing>()

    /**
     * Notifies success with the response object being expected.
     */
    class Success<out T : Any>(val data: T) : ResponseStatus<T>()

    /**
     * Notifies the Error. Can pass details like error code, message and other values.
     */
    object Failure : ResponseStatus<Nothing>()
}
