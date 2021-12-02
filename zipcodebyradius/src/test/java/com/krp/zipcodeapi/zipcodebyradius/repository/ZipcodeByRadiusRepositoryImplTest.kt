package com.krp.zipcodeapi.zipcodebyradius.repository

import com.krp.zipcodeapi.api.ApiService
import com.krp.zipcodeapi.api.ResponseStatus
import com.krp.zipcodeapi.zipcodebyradius.model.ZipcodeResponse
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import java.io.IOException

/**
 * Created by Rakesh Praneeth.
 *
 * Tests for [ZipcodeByRadiusRepositoryImpl]
 */
class ZipcodeByRadiusRepositoryImplTest {

    private val mockApiService = mockk<ApiService>()
    private val mockResponse = mockk<Response<ZipcodeResponse>>()
    private lateinit var repository: ZipcodeByRadiusRepository

    @Before
    fun setup() {
        repository = ZipcodeByRadiusRepositoryImpl(mockApiService)
    }

    @Test
    fun `should notify failure if obtained exception`() {
        runBlockingTest {
            coEvery { mockApiService.getNearbyZipCodes(any(), any()) } throws IOException()
            val response = repository.getZipcodesByRadius(ZIPCODE, RADIUS)
            assertNotNull(response)
            assertTrue(response is ResponseStatus.Failure)
        }
    }

    @Test
    fun `should notify failure if response obtained is not successful`() {
        runBlockingTest {
            every { mockResponse.isSuccessful } returns false
            coEvery { mockApiService.getNearbyZipCodes(any(), any()) } returns mockResponse
            val response = repository.getZipcodesByRadius(ZIPCODE, RADIUS)
            assertNotNull(response)
            assertTrue(response is ResponseStatus.Failure)
        }
    }

    @Test
    fun `should notify failure if response obtained is successful but list is null`() {
        runBlockingTest {
            every { mockResponse.isSuccessful } returns true
            every { mockResponse.body() } returns null
            coEvery { mockApiService.getNearbyZipCodes(any(), any()) } returns mockResponse
            val response = repository.getZipcodesByRadius(ZIPCODE, RADIUS)
            assertNotNull(response)
            assertTrue(response is ResponseStatus.Failure)
        }
    }

    @Test
    fun `should notify success if response obtained is successful and contains list`() {
        val mockZipcodeResponse = mockk<ZipcodeResponse>()
        runBlockingTest {
            every { mockResponse.isSuccessful } returns true
            every { mockResponse.body() } returns mockZipcodeResponse
            coEvery { mockApiService.getNearbyZipCodes(any(), any()) } returns mockResponse
            val response = repository.getZipcodesByRadius(ZIPCODE, RADIUS)
            assertNotNull(response)
            val successResponse = response as ResponseStatus.Success
            assertNotNull(successResponse)
            assertEquals(mockZipcodeResponse, successResponse.data)
        }
    }

    companion object {
        private const val ZIPCODE = "12345"
        private const val RADIUS = "10"
    }
}
