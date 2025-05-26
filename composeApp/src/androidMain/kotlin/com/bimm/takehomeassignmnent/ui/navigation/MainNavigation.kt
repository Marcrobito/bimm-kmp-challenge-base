package com.bimm.takehomeassignmnent.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bimm.takehomeassignmnent.ui.model.SakeShopUiModel
import com.bimm.takehomeassignmnent.ui.model.toDomain
import com.bimm.takehomeassignmnent.ui.model.toUiModel
import com.bimm.takehomeassignmnent.ui.screens.SakeShopDetailScreen
import com.bimm.takehomeassignmnent.ui.screens.SakeShopsScreen

/**
 * Hosts the app’s navigation graph, wiring up:
 *  - the list screen at [Routes.SHOPS]
 *  - the detail screen at [Routes.DETAIL]
 *
 * When an item is clicked on the list screen, we:
 *  1. Store its [SakeShopUiModel] in the SavedStateHandle
 *  2. Navigate to the detail route
 *
 * On the detail screen we:
 * 1. Retrieve the [SakeShopUiModel] from the previous back stack entry
 * 2. Convert it back to domain via [toDomain] and pass it to [SakeShopDetailScreen]
 */
@Composable
fun MainNavigation() {
    // Create the NavController for our navigation graph
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.SHOPS
    ) {
        // List screen destination
        composable(Routes.SHOPS) {
            SakeShopsScreen(onItemClicked = { shop ->
                // Store the selected shop as a UiModel so it can be passed safely
                navController.currentBackStackEntry
                    ?.savedStateHandle
                    ?.set("sakeShop", shop.toUiModel())
                // Navigate to the detail screen
                navController.navigate(Routes.DETAIL)
            })
        }

        // Detail screen destination
        composable(Routes.DETAIL) {
            // Retrieve the UiModel from the previous back stack entry
            val shopUiModel = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<SakeShopUiModel>("sakeShop")

            // If present, convert back to domain and show detail screen
            shopUiModel?.let {
                SakeShopDetailScreen(
                    shop = it.toDomain(),
                    navController = navController
                )
            }
        }
    }
}