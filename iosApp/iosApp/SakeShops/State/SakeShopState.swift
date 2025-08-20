//
//  SakeShopState.swift
//  iosApp
//
//  Created by Marco Antonio Martinez Gutierrez on 25/05/25.
//

import Shared

/// Represents the various loading states for fetching Sake Shops data.
///
/// - notInitialized: Initial state before any action has been taken.
/// - loading: Data fetch is in progress.
/// - success: Data fetch completed successfully with an array of `SakeShop`.
/// - error: Data fetch failed with an associated error message.
enum SakeShopState: Equatable {
    /// Initial state before triggering data load.
    case notInitialized

    /// Indicates that the view model is currently loading data.
    case loading

    /// Indicates that data was loaded successfully.
    /// - Parameter shops: The list of fetched `SakeShop` items.
    case success([SakeShop])

    /// Indicates that an error occurred during data loading.
    /// - Parameter message: A human-readable error description.
    case error(String)
}
