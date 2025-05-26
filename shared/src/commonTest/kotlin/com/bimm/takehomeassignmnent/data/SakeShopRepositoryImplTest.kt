package com.bimm.takehomeassignmnent.data

import com.bimm.takehomeassignmnent.data.SakeShopRepositoryImpl
import com.bimm.takehomeassignmnent.domain.SakeShop
import com.bimm.takehomeassignmnent.domain.SakeShopDatasource
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class SakeShopRepositoryImplTest {

    @Test
    fun `given datasource returns data when getSakeShops called then return same list`() = runTest {
        // Given
        val fakeData = listOf(
            SakeShop(
                name = "Test Sake",
                description = "Test description",
                picture = null,
                rating = 4.8,
                address = "Test address",
                latitude = 1.0,
                longitude = 2.0,
                googleMapsLink = "http://maps.example.com",
                website = "http://example.com"
            )
        )
        val fakeDatasource = object : SakeShopDatasource {
            override suspend fun getSakeShops(): List<SakeShop> = fakeData
        }

        val repository = SakeShopRepositoryImpl(fakeDatasource)

        // When
        val result = repository.getSakeShops()

        // Then
        assertEquals(fakeData, result)
    }
}