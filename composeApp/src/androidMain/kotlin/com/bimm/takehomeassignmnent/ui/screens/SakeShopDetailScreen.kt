package com.bimm.takehomeassignmnent.ui.screens

import RatingDisplay
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bimm.takehomeassignmnent.R
import com.bimm.takehomeassignmnent.domain.SakeShop
import androidx.core.net.toUri

@OptIn(ExperimentalMaterial3Api::class)
@Composable
        /**
         * Renders the detail screen for a given SakeShop, including its image, address,
         * rating, description and action links for website and directions.
         *
         * @param shop The SakeShop domain model containing all information to display.
         * @param navController The NavController used to navigate back to the previous screen.
         */
fun SakeShopDetailScreen(
    shop: SakeShop,
    navController: NavController
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Text(shop.name)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(vertical = 16.dp)
                .verticalScroll(rememberScrollState())
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
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(modifier = Modifier.fillMaxWidth(0.7F)) {
                        Text(
                            text = "📍 ${shop.address}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    RatingDisplay(shop.rating)
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = shop.description, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "🔗 Website: ${shop.website}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Blue,
                    modifier = Modifier.clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(shop.website))
                        context.startActivity(intent)
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "📌 Directions",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Blue,
                    modifier = Modifier.clickable {
                        val intent = Intent(Intent.ACTION_VIEW, shop.googleMapsLink.toUri())
                        context.startActivity(intent)
                    }
                )
            }
        }
    }
}