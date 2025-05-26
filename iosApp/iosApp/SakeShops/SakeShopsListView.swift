//
//  SakeShopsListView.swift
//  iosApp
//
//  Created by Marco Antonio Martinez Gutierrez on 25/05/25.
//

import SwiftUI
import Shared

/// Displays a list of sake shops and handles loading/error states.
///
/// Uses the injected `SakeShopsViewModel` to drive UI updates.
/// On success, presents each shop as a navigation link to its detail view.
struct SakeShopsListView: View {
    /// The view model responsible for fetching and holding shop data.
    @StateObject var viewModel: SakeShopsViewModel

    /// The main content of the view, switching between loading,
    /// success, and error states.
    @ViewBuilder
    private var content: some View {
        switch viewModel.state {
        case .notInitialized:
            // Trigger initial load as soon as this view appears.
            Color.clear
                .onAppear { viewModel.loadData() }

        case .loading:
            // Show an indeterminate progress indicator.
            ProgressView()

        case .success(let shops):
            // Display the list of shops; tapping navigates to detail.
            List(shops, id: \.name) { shop in
                NavigationLink(destination: SakeShopDetailView(shop: shop)) {
                    SakeShopListItem(shop: shop)
                }
            }
            .listStyle(.plain)
            .scrollContentBackground(.hidden)

        case .error(let message):
            // Show error message with retry button.
            VStack(spacing: 16) {
                Text("Error: \(message)")
                    .foregroundColor(.red)
                Button("Retry") {
                    viewModel.loadData()
                }
            }
        }
    }

    var body: some View {
        NavigationView {
            content
                .navigationBarHidden(true)
        }
        .navigationViewStyle(.stack)
    }
}

private extension SakeShopsViewModel {
    /// Extracts the list of shops if the state is `.success`, otherwise returns empty.
    var stateShops: [SakeShop] {
        if case .success(let shops) = state {
            return shops
        }
        return []
    }
}
