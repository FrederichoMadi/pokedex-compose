package com.fredericho.pokedex_compose.navigation

import androidx.annotation.DrawableRes
import com.fredericho.pokedex_compose.R

enum class NavDestination(
    val route: String,
) {
    HOME(
      route = "home",
    ),
    DETAIL_POKEMON(
        route = "detail_pokemon",
    )
}