package com.krp.zipcodeapi.zipcodebyradius.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.krp.zipcodeapi.api.ResponseStatus
import com.krp.zipcodeapi.zipcodebyradius.R
import com.krp.zipcodeapi.zipcodebyradius.model.Zipcode
import com.krp.zipcodeapi.zipcodebyradius.model.ZipcodeResponse
import com.krp.zipcodeapi.zipcodebyradius.repository.ZipcodeByRadiusRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Rakesh Praneeth.
 * Tests for [ZipcodeByRadiusViewModel]
 */
class ZipcodeByRadiusViewModelTest {

    @get:Rule
    val instantExecutionRule = InstantTaskExecutorRule()

    private val mockRepository = mockk<ZipcodeByRadiusRepository>(relaxed = true)

    private lateinit var viewModel: ZipcodeByRadiusViewModel

    @Before
    fun setUp() {
        viewModel = ZipcodeByRadiusViewModel(mockRepository)
        viewModel.zipCode.set(ZIPCODE)
        viewModel.radius.set(RADIUS)
    }

    @Test
    fun `should not enable the button if provided zipcode only`() {
        viewModel.zipCode.set(ZIPCODE)
        viewModel.radius.set(null)
        viewModel.buttonEnabled.observeForever { assertFalse(it) }
    }

    @Test
    fun `should not enable the button if provided radius only`() {
        viewModel.radius.set(RADIUS)
        viewModel.zipCode.set(null)
        viewModel.buttonEnabled.observeForever { assertFalse(it) }
    }

    @Test
    fun `should enable the button if provided zipcode and radius`() {
        viewModel.buttonEnabled.observeForever { assertTrue(it) }
    }

    @Test
    fun `should notify to show message with proper resource id if failed`() {
        runBlockingTest {
            coEvery {
                mockRepository.getZipcodesByRadius(
                    any(),
                    any()
                )
            } returns ResponseStatus.Failure
            viewModel.onSearch()
            viewModel.message.observeForever { assertEquals(R.string.generic_error_message, it) }
        }
    }

    @Test
    fun `should notify to show message with proper resource id if success but data is empty`() {
        runBlockingTest {
            coEvery {
                mockRepository.getZipcodesByRadius(
                    any(),
                    any()
                )
            } returns ResponseStatus.Success(ZipcodeResponse())
            viewModel.onSearch()
            viewModel.message.observeForever {
                assertEquals(
                    R.string.no_zipcodes_available_nearby,
                    it
                )
            }
        }
    }

    @Test
    fun `should notify to show list of data if success with items`() {
        runBlockingTest {
            val data = ZipcodeResponse(
                listOf(
                    getZipcode("10000"),
                    getZipcode("20000"),
                    getZipcode(ZIPCODE)
                )
            )
            coEvery {
                mockRepository.getZipcodesByRadius(
                    any(),
                    any()
                )
            } returns ResponseStatus.Failure andThen ResponseStatus.Success(ZipcodeResponse())
            viewModel.onSearch()
            viewModel.listItems.observeForever {
                assertTrue(it.isNotEmpty())
                assertEquals(2, it.size)
            }
        }
    }

    private fun getZipcode(zipcodeValue: String) = Zipcode(
        zipcode = zipcodeValue,
        distance = 10.0,
        city = "jersey",
        state = "nj"
    )

    companion object {
        private const val ZIPCODE = "12345"
        private const val RADIUS = "10"
    }
}
