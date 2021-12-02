package com.krp.zipcodeapi.zipcodebyradius.repository

import android.util.Log
import com.krp.zipcodeapi.api.ApiService
import com.krp.zipcodeapi.api.ResponseStatus
import com.krp.zipcodeapi.zipcodebyradius.model.ZipcodeResponse
import java.lang.Exception

/**
 * Created by Rakesh Praneeth.
 * Implementation class for the [ZipcodeByRadiusRepository]. Makes the API call, checks for success
 *  or failure.
 */
// Enhancement: Can use Dagger to inject the ApiService
class ZipcodeByRadiusRepositoryImpl(
    private val apiService: ApiService
) : ZipcodeByRadiusRepository {

    // Enhancement: We can use failure code to show a related message to the user.
    /**
     * Gets the [ZipcodeResponse] that contains list of zipcodes for provided values.
     * @param zipcode for which user want to search.
     * @param radius in which want to see the zipcodes.
     * @return the [ResponseStatus] with [ZipcodeResponse]
     */
    override suspend fun getZipcodesByRadius(
        zipcode: String,
        radius: String
    ): ResponseStatus<ZipcodeResponse> {
        return try {
            val response = apiService.getNearbyZipCodes(zipcode, radius)
            Log.i(TAG,"response is $response")
            val responseBody = response.body()
            if (response.isSuccessful && responseBody != null) {
                // successfully obtained response.
                ResponseStatus.Success(responseBody)
            } else {
                // If the response is not successful or response is null.
                ResponseStatus.Failure
            }
        } catch (exception: Exception) {
            // JsonException or any other.
            Log.e(TAG, "exception is ${exception.localizedMessage}")
            ResponseStatus.Failure
        }
    }

    companion object {
        private const val TAG = "ZipcodeByRadiusRepositoryImpl"
    }
}