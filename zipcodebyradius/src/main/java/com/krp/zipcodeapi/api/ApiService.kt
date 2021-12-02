package com.krp.zipcodeapi.api

import com.krp.zipcodeapi.zipcodebyradius.model.ZipcodeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Rakesh Praneeth.
 */
private const val DEMO_KEY = "DemoOnly00FYmXGfSayL4sJYuQTxCsapPvfWTrMeMQlkqNQKRYjzcqxMFqoFzRN3"

interface ApiService {

    @GET("/rest/$DEMO_KEY/radius.json/{zipcode}/{radius}/km")
    suspend fun getNearbyZipCodes(
        @Path("zipcode") zipcode: String,
        @Path("radius") radius: String
    ): Response<ZipcodeResponse>
}
