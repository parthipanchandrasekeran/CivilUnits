package com.civilunits.canada.ui.favorites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.civilunits.canada.data.model.FavoriteConversion

@Composable
fun FavoritesScreen(
    onFavoriteClick: (String) -> Unit,
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    val favorites by viewModel.favorites.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 600.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Favorites",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(top = 16.dp, bottom = 12.dp)
            )

            if (favorites.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No favorites yet",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(
                        items = favorites,
                        key = { it.id }
                    ) { favorite ->
                        FavoriteItem(
                            favorite = favorite,
                            viewModel = viewModel,
                            onClick = { onFavoriteClick(favorite.categoryId) },
                            onDelete = { viewModel.remove(favorite) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun FavoriteItem(
    favorite: FavoriteConversion,
    viewModel: FavoritesViewModel,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    val fromName = viewModel.getUnitDisplayName(favorite.categoryId, favorite.fromUnitId)
    val toName = viewModel.getUnitDisplayName(favorite.categoryId, favorite.toUnitId)
    val categoryName = favorite.categoryId.replaceFirstChar { it.uppercase() }

    ListItem(
        modifier = Modifier.clickable(onClick = onClick),
        overlineContent = {
            Text(
                text = categoryName,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary
            )
        },
        headlineContent = {
            Text(text = "$fromName \u2192 $toName")
        },
        trailingContent = {
            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Remove favorite",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    )
}
