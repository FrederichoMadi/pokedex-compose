package com.fredericho.pokedex_compose.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fredericho.pokedex_compose.R
import com.fredericho.pokedex_compose.data.entity.pokemons
import com.fredericho.pokedex_compose.presentation.home.components.CardPokemon
import com.fredericho.pokedex_compose.presentation.home.components.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(),
    navigateToDetail : (String) -> Unit,
) {
    val state by homeViewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopBar(
                title = "Pokedex",
                icon = R.drawable.ic_pokemon,
                expanded = state.isExpanded,
                field = state.field,
                onValueChange = { newValue ->
                    homeViewModel.changeValueField(newValue)
                },
                onChangeExpanded = {
                    homeViewModel.changeExpanded(!state.isExpanded)
                },
                onDismissExpanded = {
                    homeViewModel.dismissExpanded()
                }
            )
        },
        modifier = Modifier.nestedScroll(TopAppBarDefaults.pinnedScrollBehavior().nestedScrollConnection)
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            LazyColumn(
                modifier = Modifier
                    .padding(12.dp)
            ) {
                items(
                    items = pokemons,
                    key = {pokemon -> pokemon.id.toString() }
                ) { pokemon ->
                    CardPokemon(pokemon = pokemon, onDetailPokemon = { navigateToDetail(it) })
                }
            }
        }

    }
}