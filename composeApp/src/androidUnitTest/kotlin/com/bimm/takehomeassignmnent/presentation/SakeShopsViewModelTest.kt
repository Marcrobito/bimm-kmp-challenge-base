package com.bimm.takehomeassignmnent.presentation

import app.cash.turbine.test
import com.bimm.takehomeassignmnent.domain.SakeShop
import com.bimm.takehomeassignmnent.domain.SakeShopsRepository
import com.bimm.takehomeassignmnent.state.SakeShopState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class SakeShopsViewModelTest {

    private lateinit var repository: FakeSakeShopsRepository
    private lateinit var viewModel: SakeShopsViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = FakeSakeShopsRepository()
        viewModel = SakeShopsViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `given initial state, when loading succeeds, then emits loading and success`() = runTest {
        viewModel.state.test {
            assertEquals(SakeShopState.NotInitialized, awaitItem())

            viewModel.loadData()
            advanceUntilIdle()

            assertEquals(SakeShopState.Loading, awaitItem())
            assertEquals(SakeShopState.Success(repository.sampleData), awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `given repository throws, when loading starts, then emits loading and error`() = runTest {
        repository.shouldFail = true
        viewModel = SakeShopsViewModel(repository)

        viewModel.state.test {
            assertEquals(SakeShopState.NotInitialized, awaitItem())

            viewModel.loadData()
            advanceUntilIdle()

            assertEquals(SakeShopState.Loading, awaitItem())
            val errorState = awaitItem() as SakeShopState.Error
            assertEquals("Failed to load", errorState.error)

            cancelAndIgnoreRemainingEvents()
        }
    }

    private class FakeSakeShopsRepository : SakeShopsRepository {
        var shouldFail = false

        val sampleData = listOf(
            SakeShop(
                name = "Mock Shop",
                description = "Test Description",
                picture = null,
                rating = 4.0,
                address = "123 Sake Lane",
                latitude = 0.0,
                longitude = 0.0,
                googleMapsLink = "",
                website = ""
            )
        )

        override suspend fun getSakeShops(): List<SakeShop> {
            if (shouldFail) throw RuntimeException("Failed to load")
            return sampleData
        }
    }
}