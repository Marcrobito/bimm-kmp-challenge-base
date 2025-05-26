package com.bimm.takehomeassignmnent.di

import com.bimm.takehomeassignmnent.data.SakeShopLocalDatasource
import com.bimm.takehomeassignmnent.data.SakeShopRepositoryImpl
import com.bimm.takehomeassignmnent.domain.JsonProvider
import com.bimm.takehomeassignmnent.domain.SakeShopsRepository

/**
 * Factory for creating shared implementations of core components without
 * full DI frameworks. Used primarily by iOS to bypass Koin integration
 * challenges encountered on native side.
 */
object SharedFactory {
    /**
     * Provides a [SakeShopsRepository] using a local JSON datasource.
     * This manual factory replaces automated DI on iOS due to time constraints
     * and Koin compatibility issues.
     *
     * @param jsonProvider platform-specific JSON loader implementation
     * @return configured repository instance
     */
    fun provideSakeShopsRepository(jsonProvider: JsonProvider): SakeShopsRepository {
        val datasource = SakeShopLocalDatasource(jsonProvider)
        return SakeShopRepositoryImpl(datasource)
    }
}