package com.bimm.takehomeassignmnent.data

import com.bimm.takehomeassignmnent.domain.SakeShop

/**
 * Maps a [SakeShopDto] to the domain model [SakeShop].
 *
 * Coordinates list is expected to contain latitude at index 0 and longitude
 * at index 1. If absent, defaults to 0.0 for missing values.
 *
 * @receiver SakeShopDto instance to convert.
 * @return Mapped [SakeShop] domain object.
 */
fun SakeShopDto.toDomain(): SakeShop {
    return SakeShop(
        name = name,
        description = description,
        picture = picture,
        rating = rating,
        address = address,
        latitude = coordinates.getOrNull(0) ?: 0.0,
        longitude = coordinates.getOrNull(1) ?: 0.0,
        googleMapsLink = googleMapsLink,
        website = website
    )
}
