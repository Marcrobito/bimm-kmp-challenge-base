//
//  SakeShopListItem.swift
//  iosApp
//
//  Created by Marco Antonio Martinez Gutierrez on 25/05/25.
//

import SwiftUI
import Shared

/// A single row item view representing a sake shop in the list.
///
/// Displays a circular image, the shop name, and its star rating.
/// Tapping the row should trigger navigation to the detail view.
struct SakeShopListItem: View {
    /// The sake shop data to display.
    let shop: SakeShop

    var body: some View {
        HStack(alignment: .center, spacing: 12) {
            /// Shop image displayed as a circular thumbnail.
            CircularImageView(imageUrl: shop.picture ?? "")

            /// The shop's name, limited to two lines.
            Text(shop.name)
                .font(.headline)
                .lineLimit(2)

            Spacer()

            /// The star rating display for this shop.
            RatingDisplay(rating: shop.rating)
        }
        .padding(.vertical, 8)
        .padding(.horizontal, 16)
        .background(Color.white)
    }
}
