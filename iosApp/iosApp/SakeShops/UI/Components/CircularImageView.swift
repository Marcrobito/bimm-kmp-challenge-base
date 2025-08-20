//
//  CircularImageView.swift
//  iosApp
//
//  Created by Marco Antonio Martinez Gutierrez on 25/05/25.
//

import SwiftUI

/// A view that displays an image loaded from a URL inside a circular frame,
/// with a placeholder and error fallback.
///
/// If the image fails to load or is still loading, a default placeholder image
/// named "sake" from the asset catalog is shown. The loaded image is scaled
/// to fill the circle, and any overflowing content is clipped.
///
/// - Note: You must include an image named "sake" in your asset catalog for the
///         placeholder and error states.
struct CircularImageView: View {
    /// The URL string of the image to load.
    let imageUrl: String

    var body: some View {
        AsyncImage(url: URL(string: imageUrl)) { phase in
            switch phase {
            case .empty, .failure:
                // Placeholder or error: show default "sake" asset
                Image("sake")
                    .resizable()
                    .scaledToFill()

            case .success(let image):
                // Successfully loaded image
                image
                    .resizable()
                    .scaledToFill()

            @unknown default:
                // Fallback for any future cases
                Image("sake")
                    .resizable()
                    .scaledToFill()
            }
        }
        .frame(width: 64, height: 64)
        .clipShape(Circle())
        .overlay(
            Circle()
                .stroke(Color.red, lineWidth: 2)
        )
        .contentShape(Circle())
        .clipped()
    }
}
