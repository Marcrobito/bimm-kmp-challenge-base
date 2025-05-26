package com.bimm.takehomeassignmnent.di

import com.bimm.takehomeassignmnent.domain.JsonProvider
import com.bimm.takehomeassignmnent.domain.SakeShopsRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SharedFactoryTest {

    private val fakeJson = """
        [
            {
                "name": "Test Shop",
                "description": "A great place for sake",
                "picture": null,
                "rating": 4.5,
                "address": "123 Sake Street",
                "coordinates": [35.6895, 139.6917],
                "google_maps_link": "https://maps.test",
                "website": "https://sake.test"
            }
        ]
    """.trimIndent()

    private val fakeJsonProvider = object : JsonProvider {
        override fun loadJson(): String = fakeJson
    }

    @Test
    fun `given fakeJsonProvider when provideSakeShopsRepository then returns expected data`() = runTest {
        // Given
        val repository: SakeShopsRepository = SharedFactory.provideSakeShopsRepository(fakeJsonProvider)

        // When
        val result = repository.getSakeShops()

        // Then
        assertEquals(1, result.size)
        assertEquals("Test Shop", result.first().name)
        assertTrue(result.first().rating > 4)
    }
}