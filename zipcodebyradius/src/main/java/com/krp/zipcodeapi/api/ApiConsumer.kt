package com.krp.zipcodeapi.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Enhancement : can create a network or core module to maintain the Retrofit related items.
/**
 * Created by Rakesh Praneeth.
 * Creates and maintains the Retrofit configurations.
 */
class ApiConsumer {

    /**
     * maintains the [Retrofit] instance.
     */
    private var sRetrofit: Retrofit? = null

    /**
     * Creates a retrofit instance with the Base url and [GsonConverterFactory] if not created.
     * @return instance of [Retrofit]
     */
    private fun getRetrofit(): Retrofit {

        if (sRetrofit == null) {
            sRetrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return sRetrofit!!
    }

    /**
     * gives the API service that contains the list of api calls.
     */
    fun getApiService(): ApiService {
        return getRetrofit().create(ApiService::class.java)
    }

    companion object {
        /**
         * Base url that is common for all the APIs.
         */
        private const val BASE_URL = " https://www.zipcodeapi.com/"

        /**
         * Maintains the [ApiConsumer] instance.
         */
        private var sInstance: ApiConsumer? = null

        /**
         * @return a singleton instance of the [ApiConsumer].
         */
        @JvmStatic
        fun getInstance(): ApiConsumer {
            if (sInstance == null) {
                sInstance = ApiConsumer()
            }
            return sInstance!!
        }
    }
}
