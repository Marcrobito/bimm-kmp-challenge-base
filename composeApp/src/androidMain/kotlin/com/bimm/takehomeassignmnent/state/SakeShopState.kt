package com.bimm.takehomeassignmnent.state

import com.bimm.takehomeassignmnent.domain.SakeShop

/**
 * Represents the possible UI states for the Sake Shops screen.
 */
sealed class SakeShopState {

    /**
     * The initial state before any loading has begun.
     */
    object NotInitialized : SakeShopState()

    /**
     * Indicates that data is currently being loaded.
     */
    object Loading : SakeShopState()

    /**
     * Indicates that data was successfully loaded.
     *
     * @property data the list of loaded [SakeShop] items
     */
    data class Success(val data: List<SakeShop>) : SakeShopState()

    /**
     * Indicates that an error occurred while loading data.
     *
     * @property error a human-readable error message
     */
    data class Error(val error: String) : SakeShopState()
}