package com.bimm.takehomeassignmnent.data

import com.bimm.takehomeassignmnent.domain.SakeShop
import com.bimm.takehomeassignmnent.domain.SakeShopDatasource
import com.bimm.takehomeassignmnent.domain.SakeShopsRepository

/**
 * Repository implementation that provides sake shop data by delegating
 * to a [SakeShopDatasource].
 *
 * Acts as a simple pass-through, allowing higher layers to depend on
 * the domain-level [SakeShopsRepository] interface.
 *
 * @property sakeShopDatasource The data source used to load sake shops.
 */
class SakeShopRepositoryImpl(
    private val sakeShopDatasource: SakeShopDatasource
) : SakeShopsRepository {

    /**
     * Fetches a list of sake shops from the data source.
     *
     * @return A list of [SakeShop] domain entities.
     */
    override suspend fun getSakeShops(): List<SakeShop> =
        sakeShopDatasource.getSakeShops()
}
