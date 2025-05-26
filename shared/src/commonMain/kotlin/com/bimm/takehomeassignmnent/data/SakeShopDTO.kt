package com.bimm.takehomeassignmnent.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data Transfer Object representing the JSON structure for a sake shop.
 *
 * This DTO is used by kotlinx.serialization to parse raw JSON into
 * Kotlin objects. Later, it's converted to the domain model [SakeShop]
 * via an extension mapping function.
 *
 * @property name The name of the sake shop.
 * @property description A brief description of the shop.
 * @property picture Optional URL to the shop's image.
 * @property rating Star rating (e.g., 4.5).
 * @property address Physical address of the shop.
 * @property coordinates List containing latitude and longitude values.
 * @property googleMapsLink URL to view the shop location on Google Maps.
 * @property website URL of the shop's website.
 */
@Serializable
data class SakeShopDto(
    val name: String,
    val description: String,
    val picture: String? = null,
    val rating: Double,
    val address: String,
    val coordinates: List<Double>,
    @SerialName("google_maps_link")
    val googleMapsLink: String,
    val website: String
)
