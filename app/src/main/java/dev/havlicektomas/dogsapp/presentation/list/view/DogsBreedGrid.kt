package dev.havlicektomas.dogsapp.presentation.list.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.havlicektomas.dogsapp.domain.DogsBreed
import dev.havlicektomas.dogsapp.ui.theme.DogsAppTheme

@ExperimentalMaterial3Api
@Composable
fun DogsBreedGrid(
    breeds: List<DogsBreed>,
    onFavouriteTapped: (DogsBreed) -> Unit = {},
    onGridItemTapped: (String) -> Unit = {}
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(breeds) {
            DogsBreedGridItem(
                modifier = Modifier.clickable {
                    onGridItemTapped(it.name)
                },
                breed = it,
                onFavouriteTapped = onFavouriteTapped,
            )
        }
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun DogsBreedGridPreview() {
    DogsAppTheme {
        DogsBreedGrid(breeds = (0 until 10).map {
            DogsBreed(
                name = "Breed $it",
                imageUrl = ""
            )
        })
    }
}