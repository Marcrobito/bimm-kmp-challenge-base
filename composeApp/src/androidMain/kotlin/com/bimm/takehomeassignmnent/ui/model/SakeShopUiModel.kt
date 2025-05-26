package com.bimm.takehomeassignmnent.ui.model

import android.os.Parcelable
import com.bimm.takehomeassignmnent.domain.SakeShop
import kotlinx.parcelize.Parcelize

/**
 * UI model representing a sake shop for passing between Compose screens or through navigation.
 *
 * @property name the name of the sake shop
 * @property description a brief description of the shop
 * @property picture an optional URL to the shop’s image
 * @property rating the shop’s rating from 0.0 to 5.0
 * @property address the shop’s physical address
 * @property latitude the shop’s latitude coordinate
 * @property longitude the shop’s longitude coordinate
 * @property googleMapsLink a URL to open the shop location in Google Maps
 * @property website a URL to the shop’s website
 */
@Parcelize
data class SakeShopUiModel(
    val name: String,
    val description: String,
    val picture: String?,
    val rating: Double,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val googleMapsLink: String,
    val website: String
) : Parcelable

/**
 * Maps a domain [SakeShop] to its corresponding UI model.
 *
 * @receiver the domain model to convert
 * @return a [SakeShopUiModel] with identical property values
 */
fun SakeShop.toUiModel(): SakeShopUiModel = SakeShopUiModel(
    name = name,
    description = description,
    picture = picture,
    rating = rating,
    address = address,
    latitude = latitude,
    longitude = longitude,
    googleMapsLink = googleMapsLink,
    website = website
)

/**
 * Maps a UI [SakeShopUiModel] back to the domain [SakeShop].
 *
 * @receiver the UI model to convert
 * @return a [SakeShop] with identical property values
 */
fun SakeShopUiModel.toDomain(): SakeShop = SakeShop(
    name = name,
    description = description,
    picture = picture,
    rating = rating,
    address = address,
    latitude = latitude,
    longitude = longitude,
    googleMapsLink = googleMapsLink,
    website = website
)