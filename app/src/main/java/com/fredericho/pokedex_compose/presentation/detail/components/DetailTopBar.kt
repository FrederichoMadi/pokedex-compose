package com.fredericho.pokedex_compose.presentation.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fredericho.pokedex_compose.R
import com.fredericho.pokedex_compose.ui.theme.PokedexComposeTheme

@Composable
fun DetailTopBar(
    onNavigationBack : () -> Unit,
    title : String,
    id : String,
    color : Int,
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .background(colorResource(id = color)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onNavigationBack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
            )
        }

        Text(
            text = id,
            style = MaterialTheme.typography.labelSmall,
            color = Color.White,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailTopBarPreview(){
    PokedexComposeTheme {
        DetailTopBar(
            onNavigationBack = {},
            title = "Bulbasaur",
            id = "#001",
            color = R.color.poke_green
        )
    }
}