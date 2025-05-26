package com.bimm.takehomeassignmnent.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bimm.takehomeassignmnent.domain.SakeShop
import com.bimm.takehomeassignmnent.presentation.SakeShopsViewModel
import com.bimm.takehomeassignmnent.state.SakeShopState
import com.bimm.takehomeassignmnent.ui.components.SakeShopListItem
import org.koin.androidx.compose.koinViewModel

/**
 * Displays the list of sake shops, handling loading, success, and error states.
 *
 * This composable observes the [viewModel.state] and:
 *  - Triggers the initial load when in [SakeShopState.NotInitialized].
 *  - Shows a centered spinner while in [SakeShopState.Loading].
 *  - Renders a [LazyColumn] of [SakeShopListItem] when in [SakeShopState.Success].
 *  - Displays an error message with a retry button when in [SakeShopState.Error].
 *
 * @param viewModel the [SakeShopsViewModel] providing the current [SakeShopState]
 * @param onItemClicked lambda to invoke when a shop item is clicked
 */
@Composable
fun SakeShopsScreen(
    viewModel: SakeShopsViewModel = koinViewModel(),
    onItemClicked: (SakeShop) -> Unit
) {
    val state by viewModel.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when (state) {
            SakeShopState.NotInitialized -> {
                LaunchedEffect(Unit) { viewModel.loadData() }
            }

            SakeShopState.Loading -> {
                // Centered spinner
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            is SakeShopState.Success -> {
                // Show list of shops
                val shops = (state as SakeShopState.Success).data
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(shops) { shop ->
                        SakeShopListItem(shop, onClick = { onItemClicked(shop) })
                    }
                }
            }

            is SakeShopState.Error -> {
                // Show error + retry
                val message = (state as SakeShopState.Error).error
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Error: $message", color = Color.Red)
                    Spacer(Modifier.height(8.dp))
                    androidx.compose.material3.Button(onClick = { viewModel.loadData() }) {
                        Text("Retry")
                    }
                }
            }
        }
    }
}