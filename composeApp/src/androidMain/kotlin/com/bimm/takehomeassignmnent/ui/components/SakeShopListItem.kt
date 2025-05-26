package com.bimm.takehomeassignmnent.ui.components

import RatingDisplay
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bimm.takehomeassignmnent.domain.SakeShop
import com.bimm.takehomeassignmnent.R

/**
 * Displays a single sake shop entry in a horizontal row.
 *
 * Shows the shop’s circular image on the left, its name, and a star rating.
 * The entire row is clickable, invoking [onClick] when tapped.
 *
 * @param shop the [SakeShop] data to display
 * @param onClick callback invoked when the row is clicked
 */
@Composable
fun SakeShopListItem(
    shop: SakeShop,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(true, onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(0.7f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(shop.picture)
                    .crossfade(true)
                    .placeholder(R.drawable.sake)
                    .error(R.drawable.sake)
                    .build(),
                contentDescription = shop.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .shadow(
                        elevation = 6.dp,
                        shape = CircleShape,
                        clip = false
                    )
                    .clip(CircleShape)
                    .border(2.dp, Color.Red, CircleShape)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = shop.name,
                style = MaterialTheme.typography.titleMedium
            )
        }
        RatingDisplay(shop.rating)
    }
}