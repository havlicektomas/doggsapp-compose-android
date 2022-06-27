package dev.havlicektomas.dogsapp.presentation.list.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.havlicektomas.dogsapp.domain.DogsBreed
import dev.havlicektomas.dogsapp.ui.theme.DogsAppTheme

@ExperimentalMaterial3Api
@Composable
fun DogsBreedGridItem(
    modifier: Modifier = Modifier,
    breed: DogsBreed,
    onFavouriteTapped: (DogsBreed) -> Unit = {},
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        shape = MaterialTheme.shapes.large
    ) {
        AsyncImage(
            model = breed.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(1f)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = breed.name,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun DogsBreedGridItemPreview() {
    DogsAppTheme {
        DogsBreedGridItem(breed =
            DogsBreed(
                name = "Breed",
                imageUrl = ""
            )
        )
    }
}