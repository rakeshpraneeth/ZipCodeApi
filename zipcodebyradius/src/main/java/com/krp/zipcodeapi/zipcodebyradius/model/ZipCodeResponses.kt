package com.krp.zipcodeapi.zipcodebyradius.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Rakesh Praneeth.
 */

/**
 * @property [zipcodes] list of zipcodes in the response.
 */
data class ZipcodeResponse(
    @SerializedName("zip_codes") val zipcodes: List<Zipcode>? = emptyList()
)

/**
 * @param [zipcode] of the item
 * @param [distance] from the user provided zipcode
 * @param [city] city
 * @param [state] state for the city.
 */
data class Zipcode(
    @SerializedName("zip_code") val zipcode: String? = null,
    @SerializedName("distance") val distance: Double? = null,
    @SerializedName("city") val city: String? = null,
    @SerializedName("state") val state: String? = null
)
