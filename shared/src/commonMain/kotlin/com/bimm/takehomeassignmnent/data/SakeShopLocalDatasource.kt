package com.bimm.takehomeassignmnent.data

import com.bimm.takehomeassignmnent.domain.JsonProvider
import com.bimm.takehomeassignmnent.domain.SakeShop
import com.bimm.takehomeassignmnent.domain.SakeShopDatasource
import kotlinx.serialization.json.Json

/**
 * Local data source implementation that loads sake shop data from
 * a JSON file via a [JsonProvider].
 *
 * Uses kotlinx.serialization to decode the raw JSON into DTOs,
 * then maps them to the domain [SakeShop] model.
 *
 * @property jsonProvider Platform-specific provider to read the JSON content.
 */
class SakeShopLocalDatasource(
    private val jsonProvider: JsonProvider
) : SakeShopDatasource {
    /**
     * Loads and parses the JSON resource, mapping it into domain entities.
     *
     * @return List of [SakeShop] parsed from JSON.
     */
    override suspend fun getSakeShops(): List<SakeShop> {
        val json = jsonProvider.loadJson()
        val dtoList = Json.decodeFromString<List<SakeShopDto>>(json)
        return dtoList.map { it.toDomain() }
    }
}
