//
//  SakeShopsViewModel.swift
//  iosApp
//
//  Created by Marco Antonio Martinez Gutierrez on 25/05/25.
//

import Foundation
@preconcurrency import Shared

/// ViewModel responsible for loading and exposing the list of sake shops.
///
/// Uses an injected `SakeShopsRepository` to fetch data asynchronously
/// and publishes its loading, success, or error state to the UI.
@MainActor
class SakeShopsViewModel: ObservableObject {
    /// Represents the current loading state of the view.
    @Published private(set) var state: SakeShopState = .notInitialized

    /// The repository used to fetch sake shops data.
    private let repository: SakeShopsRepository

    /// Initializes the ViewModel with the provided repository.
    ///
    /// - Parameter repository: The repository responsible for fetching shop data.
    init(repository: SakeShopsRepository) {
        self.repository = repository
    }

    /// Loads the sake shops, simulating a network delay,
    /// and updates the `state` property accordingly.
    ///
    /// Transitions through:
    /// 1. `.loading` when the load begins.
    /// 2. `.success` with the list of shops on successful fetch.
    /// 3. `.error` with an error message if fetching fails.
    func loadData() {
        state = .loading

        Task {
            do {
                // Simulate network latency
                try await Task.sleep(nanoseconds: 1_500_000_000)

                // Fetch shops from repository
                let shops = try await repository.getSakeShops()
                self.state = .success(shops)
            } catch {
                // Publish error state with localized message
                self.state = .error(error.localizedDescription)
            }
        }
    }
}
