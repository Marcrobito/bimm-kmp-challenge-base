//
//  RatingDisplay.swift
//  iosApp
//
//  Created by Marco Antonio Martinez Gutierrez on 25/05/25.
//

import SwiftUI

/// A view that displays a numeric rating alongside a row of star icons.
///
/// The rating is shown with one decimal place, and up to 5 stars are rendered:
/// - A filled star for each whole number in the rating.
/// - A half-filled star for a .5 increment.
/// - An empty star for the remainder.
///
/// Example:
/// ```swift
/// RatingDisplay(rating: 4.5)
/// ```
///
/// - Note: Uses SF Symbols "star.fill", "star.leadinghalf.filled", and "star".
struct RatingDisplay: View {
    /// The rating value to display. Expected range: 0.0 to 5.0.
    let rating: Double

    var body: some View {
        VStack(alignment: .center, spacing: 4) {
            // Display the numeric value of the rating
            Text(String(format: "%.1f", rating))
                .font(.title3)
                .bold()

            // Display stars based on the rating
            HStack(spacing: 2) {
                ForEach(0..<5) { index in
                    let threshold = Double(index) + 1.0
                    if rating >= threshold {
                        // Full star for each whole number
                        Image(systemName: "star.fill")
                            .foregroundColor(.yellow)
                    } else if rating >= threshold - 0.5 {
                        // Half star for a 0.5 increment
                        Image(systemName: "star.leadinghalf.filled")
                            .foregroundColor(.yellow)
                    } else {
                        // Empty star otherwise
                        Image(systemName: "star")
                            .foregroundColor(.yellow)
                    }
                }
            }
        }
    }
}
