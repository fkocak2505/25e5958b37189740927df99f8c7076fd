package com.fkocak.spacedelivery.component.favoriteButton

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.fkocak.spacedelivery.ui.theme.BACKGROUND_HALF_WHITE
import com.fkocak.spacedelivery.ui.theme.LOGO_LIGHT_BLUE

@Composable
fun SDFavoriteButton(
    modifier: Modifier,
    color: Color = LOGO_LIGHT_BLUE
) {

    var isFavorite by remember { mutableStateOf(false) }

    val iconModifier = Modifier.padding(8.dp)

    Surface(
        shape = CircleShape,
        modifier = modifier,
        color = BACKGROUND_HALF_WHITE
    ) {
        IconToggleButton(
            checked = isFavorite,
            onCheckedChange = {
                isFavorite = !isFavorite
            }
        ) {
            Icon(
                tint = color,
                modifier = iconModifier.graphicsLayer {
                    scaleX = 1.3f
                    scaleY = 1.3f
                },
                imageVector = if (isFavorite) {
                    Icons.Filled.Favorite
                } else {
                    Icons.Default.FavoriteBorder
                },
                contentDescription = null
            )
        }
    }
}