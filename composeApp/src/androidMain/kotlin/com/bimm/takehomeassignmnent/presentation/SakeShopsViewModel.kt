package com.bimm.takehomeassignmnent.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bimm.takehomeassignmnent.domain.SakeShopsRepository
import com.bimm.takehomeassignmnent.state.SakeShopState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * ViewModel responsible for loading and exposing the list of sake shops.
 *
 * Holds a [SakeShopState] that represents the current UI state:
 * - [SakeShopState.NotInitialized]: nothing has been loaded yet.
 * - [SakeShopState.Loading]: a load is in progress.
 * - [SakeShopState.Success]: data loaded successfully.
 * - [SakeShopState.Error]: an error occurred during load.
 *
 * @property sakeShopsRepository repository used to fetch the sake shops
 */
class SakeShopsViewModel(
    private val sakeShopsRepository: SakeShopsRepository
) : ViewModel() {

    private val _state = MutableStateFlow<SakeShopState>(SakeShopState.NotInitialized)
    /** Publicly exposed UI state flow for observing from the UI. */
    val state: StateFlow<SakeShopState> get() = _state

    /**
     * Initiates loading of sake shops.
     *
     * Emits [SakeShopState.Loading] after a brief delay to simulate network latency,
     * then performs the fetch on [Dispatchers.IO].
     * On success, emits [SakeShopState.Success] with the fetched list.
     * On failure, emits [SakeShopState.Error] with the error message.
     */
    fun loadData() {
        _state.value = SakeShopState.Loading

        viewModelScope.launch {
            delay(1500) // Simulate network delay
            withContext(Dispatchers.IO) {
                try {
                    val shops = sakeShopsRepository.getSakeShops()
                    _state.value = SakeShopState.Success(shops)
                } catch (e: Exception) {
                    _state.value = SakeShopState.Error(e.message ?: "Unknown error")
                }
            }
        }
    }
}