//
//  SakeShopDetailView.swift
//  iosApp
//
//  Created by Marco Antonio Martinez Gutierrez on 25/05/25.
//

import SwiftUI
import Shared

/// A view that displays the full details of a single `SakeShop`.
///
/// Shows a header image (or placeholder), address with map icon,
/// star rating, description text, and buttons to open the website or directions.
struct SakeShopDetailView: View {
    /// The shop whose details will be displayed.
    let shop: SakeShop

    /// Environment value used to open external URLs.
    @Environment(\.openURL) private var openURL

    var body: some View {
        ScrollView {
            // Header image section
            AsyncImage(url: URL(string: shop.picture ?? "")) { phase in
                switch phase {
                case .success(let image):
                    image
                        .resizable()
                        .aspectRatio(contentMode: .fill)
                        .frame(height: 200)
                        .clipped()
                default:
                    // Placeholder image shown while loading or on failure
                    Image("sake")
                        .resizable()
                        .aspectRatio(contentMode: .fill)
                        .frame(height: 200)
                        .clipped()
                }
            }

            // Shop details section
            VStack(alignment: .leading, spacing: 12) {
                HStack(alignment: .top) {
                    // Address with map pin icon
                    Image(systemName: "mappin.and.ellipse")
                    Text(shop.address)
                        .font(.body)

                    Spacer()

                    // Star rating display
                    RatingDisplay(rating: shop.rating)
                }

                // Shop description text
                Text(shop.description_)

                // Website link button
                Button {
                    if let url = URL(string: shop.website) {
                        openURL(url)
                    }
                } label: {
                    Label("Website", systemImage: "link")
                }

                // Directions link button
                Button {
                    if let url = URL(string: shop.googleMapsLink) {
                        openURL(url)
                    }
                } label: {
                    Label("Directions", systemImage: "map")
                }
            }
            .padding()
        }
        // Navigation bar title shows the shop name
        .navigationTitle(shop.name)
        .navigationBarTitleDisplayMode(.inline)
    }
}
