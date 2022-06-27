package dev.havlicektomas.dogsapp.presentation.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dev.havlicektomas.dogsapp.presentation.detail.view.DogsBreedDetail

@ExperimentalMaterial3Api
@Destination
@Composable
fun DogsDetailScreen(
    navigator: DestinationsNavigator,
    breedName: String,
    viewModel: DogsDetailViewModel = hiltViewModel()
) {
    viewModel.getDogBreedByName(breedName)

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(text = breedName)
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Up navigation button",
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .clickable {
                                navigator.navigateUp()
                            }
                    )
                },
            )
        }
    ) { values ->
        Column(
            modifier = Modifier
                .padding(values)
                .verticalScroll(scrollState)
        ) {
            DogsBreedDetail(
                name = viewModel.state.dogsBreed?.name ?: "",
                imageUrl = viewModel.state.dogsBreed?.imageUrl ?: "",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}