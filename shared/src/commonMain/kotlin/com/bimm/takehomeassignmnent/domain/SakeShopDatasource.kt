package com.bimm.takehomeassignmnent.domain

/**
 * Defines the contract for fetching a list of sake shops.
 * Implementations may load data from local resources, network, etc.
 */
interface SakeShopDatasource {
    /**
     * Retrieves the list of all available sake shops.
     *
     * @return A list of [SakeShop] entities.
     */
    suspend fun getSakeShops(): List<SakeShop>
}