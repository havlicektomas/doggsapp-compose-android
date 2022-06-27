package dev.havlicektomas.dogsapp.presentation.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dev.havlicektomas.dogsapp.presentation.destinations.DogsDetailScreenDestination
import dev.havlicektomas.dogsapp.presentation.list.view.DogsBreedGrid

@ExperimentalMaterial3Api
@RootNavGraph(start = true) // sets this as the start destination of the default nav graph
@Destination
@Composable
fun DogsListScreen(
    navigator: DestinationsNavigator,
    viewModel: DogsListViewModel = hiltViewModel()
) {
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.isLoading
    )
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(text = "Dogs App")
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    ) { values ->
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { viewModel.onEvent(DogsListEvent.Refresh) },
            indicatorPadding = values
        ) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(values)
            ) {
                when {
                    viewModel.state.error.isNotEmpty() -> {
                        LaunchedEffect(key1 = viewModel.state) {
                            snackbarHostState.showSnackbar(
                                message = viewModel.state.error
                            )
                        }
                    }
                    viewModel.state.dogsBreeds.isNotEmpty() -> {
                        DogsBreedGrid(
                            breeds = viewModel.state.dogsBreeds,
                            onGridItemTapped = {
                                navigator.navigate(DogsDetailScreenDestination(it))
                            }
                        )
                    }
                }
            }
        }
    }
}
