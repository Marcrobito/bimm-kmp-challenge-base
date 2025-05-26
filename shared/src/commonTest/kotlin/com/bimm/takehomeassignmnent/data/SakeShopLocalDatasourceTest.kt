package com.bimm.takehomeassignmnent.data

import com.bimm.takehomeassignmnent.data.SakeShopLocalDatasource
import com.bimm.takehomeassignmnent.domain.JsonProvider
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class SakeShopLocalDatasourceTest {

    @Test
    fun `given valid json when getSakeShops called then returns list of domain models`() = runTest {
        // Given
        val fakeJson = """
            [
                {
                    "name": "Shop A",
                    "description": "A description",
                    "picture": null,
                    "rating": 4.5,
                    "address": "123 Street",
                    "coordinates": [10.0, 20.0],
                    "google_maps_link": "https://maps.example.com",
                    "website": "https://shop.example.com"
                }
            ]
        """.trimIndent()

        val fakeProvider = JsonProvider { fakeJson }
        val datasource = SakeShopLocalDatasource(fakeProvider)

        // When
        val result = datasource.getSakeShops()

        // Then
        assertEquals(1, result.size)
        val shop = result[0]
        assertEquals("Shop A", shop.name)
        assertEquals("A description", shop.description)
        assertEquals(null, shop.picture)
        assertEquals(4.5, shop.rating)
        assertEquals("123 Street", shop.address)
        assertEquals(10.0, shop.latitude)
        assertEquals(20.0, shop.longitude)
        assertEquals("https://maps.example.com", shop.googleMapsLink)
        assertEquals("https://shop.example.com", shop.website)
    }
}