package com.bimm.takehomeassignmnent.domain

/**
 * Represents a Sake shop with its details.
 *
 * @property name The name of the sake shop.
 * @property description A brief description of the shop.
 * @property picture Optional URL of the shop's picture.
 * @property rating Shop's rating between 0.0 and 5.0.
 * @property address Full address of the shop.
 * @property latitude Geographic latitude coordinate.
 * @property longitude Geographic longitude coordinate.
 * @property googleMapsLink URL to open the location in Google Maps.
 * @property website URL of the shop's official website.
 */
data class SakeShop(
    val name: String,
    val description: String,
    val picture: String?,
    val rating: Double,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val googleMapsLink: String,
    val website: String
)