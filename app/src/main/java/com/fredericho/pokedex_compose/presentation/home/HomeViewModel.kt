package com.fredericho.pokedex_compose.presentation.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {

    private val homeState = MutableStateFlow(HomeState())
    val state = homeState.asStateFlow()

    fun changeValueField(newValue : String) {
        homeState.update { data ->
            data.copy(
                field = newValue
            )
        }
    }

    fun changeExpanded(newValue : Boolean) {
        homeState.update { data ->
            data.copy(
                isExpanded = newValue
            )
        }
    }

    fun dismissExpanded(){
        homeState.update {
            it.copy(isExpanded = false)
        }
    }

}

data class HomeState(
    val field : String = "",
    val isExpanded : Boolean = false,
)