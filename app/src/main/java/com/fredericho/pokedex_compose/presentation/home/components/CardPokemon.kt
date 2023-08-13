package com.fredericho.pokedex_compose.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fredericho.pokedex_compose.data.entity.Pokemon
import com.fredericho.pokedex_compose.data.entity.color
import com.fredericho.pokedex_compose.data.entity.pokemons
import com.fredericho.pokedex_compose.ui.theme.PokedexComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardPokemon(
    pokemon: Pokemon,
    onDetailPokemon : (String) -> Unit = {},
) {

    Card(
        colors = CardDefaults.elevatedCardColors(
            containerColor = colorResource(id = pokemon.color()).copy(alpha = 0.2f)
        ),
        modifier = Modifier
            .padding(vertical = 4.dp)
            .clickable {
                onDetailPokemon(pokemon.id.toString())
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier
                .padding(start = 8.dp)) {
                Text(
                    text = pokemon.id.toString(),
                    style = MaterialTheme.typography.labelSmall,
                )
                Text(
                    text = pokemon.name.toString(),
                    style = MaterialTheme.typography.titleLarge,
                )
                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                ) {
                    pokemon.typeOfPokemon?.forEach { type ->
                        ChipTypePokemon(name = type.name.toString(), color = type.color)
                    }
                }

            }
            CompositionLocalProvider(
                LocalMinimumTouchTargetEnforcement provides false
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp))
                        .background(color = colorResource(id = pokemon.color()))
                        ,
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Image(
                        painter = painterResource(id = pokemon.image!!.toInt()),
                        contentDescription = "${pokemon.name}",
                        modifier = Modifier
                            .padding(8.dp),
                    )
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChipTypePokemon(
    name: String,
    color: Int?,
) {
    if (color != null) {
        AssistChip(
            onClick = { },
            label = { Text(text = name, style = MaterialTheme.typography.labelSmall) },
            colors = AssistChipDefaults.assistChipColors(
                containerColor = colorResource(id = color.toInt()).copy(alpha = 0.6f)
            ),
            modifier = Modifier.padding(end = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CardPokemonPreview() {
    PokedexComposeTheme {
        Surface(

        ) {
            CardPokemon(pokemon = pokemons[0])
        }
    }
}