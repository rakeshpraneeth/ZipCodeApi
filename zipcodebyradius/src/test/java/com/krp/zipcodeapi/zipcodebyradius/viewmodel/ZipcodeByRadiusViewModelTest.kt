package com.krp.zipcodeapi.zipcodebyradius.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.krp.zipcodeapi.api.ResponseStatus
import com.krp.zipcodeapi.zipcodebyradius.repository.ZipcodeByRadiusRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
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
    fun `should notify progress and message to null if previous notified to show message`() {
        runBlockingTest {
            coEvery {
                mockRepository.getZipcodesByRadius(
                    any(),
                    any()
                )
            } returns ResponseStatus.Failure andThen ResponseStatus.Loading
            viewModel.onSearch()
            viewModel.message.observeForever { assertTrue(it) }
            viewModel.onSearch()
            viewModel.message.observeForever { assertNull(it) }
        }
    }

    companion object {
        private const val ZIPCODE = "12345"
        private const val RADIUS = "10"
    }
}
