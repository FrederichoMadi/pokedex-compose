package com.fredericho.pokedex_compose

import PokedexApp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.fredericho.pokedex_compose.ui.theme.PokedexComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexComposeTheme {
                // A surface container using the 'background' color from the theme
                PokedexApp()
            }
        }
    }
}