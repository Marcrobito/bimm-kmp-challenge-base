package com.bimm.takehomeassignmnent.domain

/**
 * Provides application-level operations for sake shops.
 * Acts as a boundary between domain layer and data sources.
 */
interface SakeShopsRepository {
    /**
     * Fetches the complete list of sake shops.
     *
     * @return A list of [SakeShop] domain entities.
     */
    suspend fun getSakeShops(): List<SakeShop>
}