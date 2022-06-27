package dev.havlicektomas.dogsapp.presentation.detail

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
class DogsDetailViewModel @Inject constructor(
    private val repository: DogsRepository
): ViewModel() {

    var state by mutableStateOf(DogsDetailState())

    fun getDogBreedByName(breedName: String) {
        viewModelScope.launch {
            repository.getDogsBreedByName(breedName).collect { result ->
                if (result is Resource.Error) {
                    state = state.copy(error = result.message ?: "")
                } else {
                    result.data?.let { dogsBreed ->
                        state = state.copy(
                            dogsBreed = dogsBreed,
                            error = ""
                        )
                    }
                }
            }
        }
    }

    data class DogsDetailState(
        val dogsBreed: DogsBreed? = null,
        val error: String = ""
    )
}