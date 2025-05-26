package com.bimm.takehomeassignmnent.di

import com.bimm.takehomeassignmnent.data.SakeShopLocalDatasource
import com.bimm.takehomeassignmnent.data.SakeShopRepositoryImpl
import com.bimm.takehomeassignmnent.domain.JsonProvider
import com.bimm.takehomeassignmnent.domain.SakeShopDatasource
import com.bimm.takehomeassignmnent.domain.SakeShopsRepository
import org.koin.dsl.module

/**
 * Provides the Koin module for shared DI across Android and iOS.
 *
 * @param jsonProvider   a platform-specific implementation to load the JSON data.
 *
 * This module registers:
 *  - [SakeShopDatasource] backed by [SakeShopLocalDatasource], which reads from the provided JSON.
 *  - [SakeShopsRepository] backed by [SakeShopRepositoryImpl] that delegates to the datasource.
 *
 * Note: On iOS, this module is currently not used, and manual provisioning via [SharedFactory]
 * was used instead due to Koin integration limitations in the iOS environment.
 *
 * @note Currently this module injects a single LocalDatasource into the repository.
 *       In the future, consider supplying a map of different DataSource implementations (e.g., Local, Network, Database)
 *       so that the repository can select or combine sources dynamically based on business requirements.
 */
fun sharedModule(jsonProvider: JsonProvider) = module {
    single<SakeShopDatasource> { SakeShopLocalDatasource(jsonProvider) }
    single<SakeShopsRepository> { SakeShopRepositoryImpl(get()) }
}
