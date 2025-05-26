import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.StarHalf
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Displays a numeric rating alongside star icons.
 *
 * The numeric value is shown above the stars, and the stars
 * reflect the rating from 0.0 to 5.0, supporting half-star increments.
 *
 * @param rating the rating value between 0.0 and 5.0
 * @param modifier optional [Modifier] for styling, layout, and touch handling
 */
@Composable
fun RatingDisplay(
    rating: Double,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(start = 8.dp)
    ) {
        // Display the numeric rating
        Text(
            text = String.format("%.1f", rating),
            style = MaterialTheme.typography.headlineSmall
        )
        // Display star icons with minimal spacing
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(2.dp),
            modifier = Modifier.padding(top = 2.dp)
        ) {
            val fullStars = rating.toInt()
            val hasHalfStar = (rating - fullStars) in 0.25..0.749
            val emptyStars = 5 - fullStars - if (hasHalfStar) 1 else 0

            // Full stars
            repeat(fullStars) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Full star",
                    tint = Color(0xFFFFD700),
                    modifier = Modifier.size(16.dp)
                )
            }
            // Half star, if needed
            if (hasHalfStar) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.StarHalf,
                    contentDescription = "Half star",
                    tint = Color(0xFFFFD700),
                    modifier = Modifier.size(16.dp)
                )
            }
            // Empty stars
            repeat(emptyStars) {
                Icon(
                    imageVector = Icons.Outlined.StarOutline,
                    contentDescription = "Empty star",
                    tint = Color(0xFFFFD700),
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}