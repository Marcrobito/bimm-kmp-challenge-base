package com.bimm.takehomeassignmnent.data

import kotlin.test.Test
import kotlin.test.assertEquals

class SakeShopDtoTest {

    @Test
    fun `given valid dto when mapped to domain then fields are correctly copied`() {
        val dto = SakeShopDto(
            name = "Shop",
            description = "A sake shop",
            picture = "http://example.com/image.png",
            rating = 4.2,
            address = "123 Sake St.",
            coordinates = listOf(35.0, 139.0),
            googleMapsLink = "http://maps.example.com",
            website = "http://shop.example.com"
        )

        val domain = dto.toDomain()

        assertEquals("Shop", domain.name)
        assertEquals("A sake shop", domain.description)
        assertEquals("http://example.com/image.png", domain.picture)
        assertEquals(4.2, domain.rating)
        assertEquals("123 Sake St.", domain.address)
        assertEquals(35.0, domain.latitude)
        assertEquals(139.0, domain.longitude)
        assertEquals("http://maps.example.com", domain.googleMapsLink)
        assertEquals("http://shop.example.com", domain.website)
    }

    @Test
    fun `given dto without coordinates when mapped to domain then fallback to 0_0`() {
        val dto = SakeShopDto(
            name = "MissingCoords",
            description = "No coords",
            picture = null,
            rating = 3.0,
            address = "No Address",
            coordinates = emptyList(),
            googleMapsLink = "",
            website = ""
        )

        val domain = dto.toDomain()

        assertEquals(0.0, domain.latitude)
        assertEquals(0.0, domain.longitude)
    }
}