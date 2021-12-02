package com.krp.zipcodeapi.zipcodebyradius.repository

import com.krp.zipcodeapi.api.ResponseStatus
import com.krp.zipcodeapi.zipcodebyradius.model.ZipcodeResponse

/**
 * Created by Rakesh Praneeth.
 * Interface to maintain the API calls.
 */
interface ZipcodeByRadiusRepository {

    /**
     * Gets the [ZipcodeResponse] that contains list of zipcodes for provided values.
     * @param zipcode for which user want to search.
     * @param radius in which want to see the zipcodes.
     * @return the [ResponseStatus] with [ZipcodeResponse]
     */
    suspend fun getZipcodesByRadius(zipcode: String, radius: String): ResponseStatus<ZipcodeResponse>
}
