package com.krp.zipcodeapi.zipcodebyradius.model

import org.junit.Assert.*
import org.junit.Test

/**
 * Created by Rakesh Praneeth.
 */
class ZipcodeTest {

    @Test
    fun `test default values`() {
        val zipcodesResponse = ZipcodeResponse()
        assertTrue(zipcodesResponse.zipcodes?.isEmpty() ?: false)
        val zipcodeModel = Zipcode()
        with(zipcodeModel) {
            assertNull(zipcode)
            assertNull(distance)
            assertNull(city)
            assertNull(state)
        }
    }

    @Test
    fun `test provided values`() {
        val zipcodeValue = "07306"
        val distanceValue = 123.45
        val cityValue = "Jersey"
        val stateValue = "NJ"

        val zipcode = Zipcode(
            zipcode = zipcodeValue,
            distance = distanceValue,
            city = cityValue,
            state = stateValue
        )
        val zipcodeResponse = ZipcodeResponse(listOf(zipcode, zipcode, zipcode))
        with(zipcodeResponse) {
            assertTrue(zipcodes.isNullOrEmpty().not())
            assertEquals(3, zipcodes?.size)
            assertNotNull(zipcodes?.get(0))
            with(zipcodes?.get(0)) {
                assertEquals(zipcodeValue, this?.zipcode)
                assertEquals(distanceValue, this?.distance)
                assertEquals(cityValue, this?.city)
                assertEquals(stateValue, this?.state)
            }
        }
    }
}
