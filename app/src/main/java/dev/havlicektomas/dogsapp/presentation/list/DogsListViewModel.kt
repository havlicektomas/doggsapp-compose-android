package dev.havlicektomas.dogsapp.presentation.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.havlicektomas.dogsapp.data.repository.DogsRepository
import dev.havlicektomas.dogsapp.domain.DogsBreed
import dev.havlicektomas.dogsapp.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogsListViewModel @Inject constructor(
    private val repository: DogsRepository
): ViewModel() {

    var state by mutableStateOf(DogsListState())

    init {
        getDogsBreeds()
    }

    fun onEvent(event: DogsListEvent) {
        when (event) {
            is DogsListEvent.Refresh -> {
                getDogsBreeds(fetchFromRemote = true)
            }
        }
    }

    private fun getDogsBreeds(fetchFromRemote: Boolean = false) {
        viewModelScope.launch {
            repository.getDogsBreeds(fetchFromRemote).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { dogsBreeds ->
                            state = state.copy(
                                dogsBreeds = dogsBreeds,
                                error = ""
                            )
                        }
                    }
                    is Resource.Error -> {
                        state = state.copy(error = result.message ?: "")
                    }
                    is Resource.Loading -> {
                        state = state.copy(isLoading = result.isLoading)
                    }
                }
            }
        }
    }
}

data class DogsListState(
    val dogsBreeds: List<DogsBreed> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)

sealed class DogsListEvent {
    object Refresh: DogsListEvent()
}