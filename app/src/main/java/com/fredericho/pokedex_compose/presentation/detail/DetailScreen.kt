package com.fredericho.pokedex_compose.presentation.detail

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fredericho.pokedex_compose.data.entity.Pokemon
import com.fredericho.pokedex_compose.data.entity.TypePokemon
import com.fredericho.pokedex_compose.data.entity.color
import com.fredericho.pokedex_compose.data.entity.pokemons
import com.fredericho.pokedex_compose.presentation.detail.components.DetailTopBar
import com.fredericho.pokedex_compose.ui.theme.PokedexComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    id: String,
    onNavigationBack: () -> Unit,
) {
    val pokemon = pokemons.find { pokemon -> pokemon.id == id }

    Scaffold(
        topBar = {
            DetailTopBar(
                onNavigationBack = { onNavigationBack() },
                title = pokemon?.name.toString(),
                id = pokemon?.id.toString(),
                color = pokemon?.color()!!.toInt()
            )
        },
        containerColor = colorResource(id = pokemon!!.color()),
        modifier = Modifier

    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            CardContent(
                pokemon = pokemon,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .fillMaxHeight(0.81f)
                    .padding(4.dp),
            )
            Image(
                painter = painterResource(id = pokemon.image!!.toInt()),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.TopCenter),
            )
        }
    }
}

@Composable
fun CardContent(
    pokemon: Pokemon,
    modifier: Modifier = Modifier,
) {
    Box(Modifier.fillMaxSize()) {
        Card(
            modifier = modifier
                .padding(top = 28.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                ) {
                    pokemon.typeOfPokemon?.forEach { type ->
                        ChipType(typePokemon = type)
                        Spacer(modifier = Modifier.padding(end = 8.dp))
                    }
                }

                //description
                DescriptionPokemon(
                    description = pokemon.description.toString(),
                    color = pokemon.color()
                )
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                BaseStatsPokemon(pokemon = pokemon)
            }
        }
    }
}

@Composable
fun BaseStatsPokemon(
    pokemon: Pokemon
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Base Stats",
            modifier = Modifier
                .padding(vertical = 12.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = pokemon.color())
        )
        //status item
        StatsItem(title = "HP", value = pokemon.hp!!.toInt(), color = pokemon.color())
        StatsItem(title = "ATK", value = pokemon.attack!!.toInt(), color = pokemon.color())
        StatsItem(title = "DEF", value = pokemon.defense!!.toInt(), color = pokemon.color())
        StatsItem(title = "SATK", value = pokemon.specialAttack!!.toInt(), color = pokemon.color())
        StatsItem(title = "SDEF", value = pokemon.specialDefense!!.toInt(), color = pokemon.color())
        StatsItem(title = "SPD", value = pokemon.speed!!.toInt(), color = pokemon.color())
    }
}

@Composable
fun StatsItem(
    title : String,
    value : Int,
    color : Int,
) {
    val progress by remember {
        mutableFloatStateOf(value.toFloat() / 100)
    }
    val animatedProgress = animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
        label = title,
    ).value

    Row(
        modifier = Modifier
            .wrapContentWidth()
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        Text(
            text = title,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = color),
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth(0.1f)
        )
        Text(
            text = "0$value",
            fontSize = 10.sp,
            modifier = Modifier
                .fillMaxWidth(0.15f)
                .padding(horizontal = 8.dp),
            textAlign = TextAlign.End,
        )
        LinearProgressIndicator(
            progress = animatedProgress,
            modifier = Modifier
                .wrapContentWidth()
                .fillMaxWidth(),
            color = colorResource(id = color),
            trackColor = colorResource(id = color).copy(alpha = 0.2f),
        )
    }
}

@Composable
fun DescriptionPokemon(
    description : String,
    color : Int,
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "About",
            modifier = Modifier
                .padding(vertical = 12.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = color)
        )
        Text(
            text = description,
            fontSize = 10.sp,
            textAlign = TextAlign.Justify,
            lineHeight = 15.sp,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChipType(
    typePokemon: TypePokemon
) {
    AssistChip(
        onClick = { },
        label = {
            Text(
                text = typePokemon.name.toString(),
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 10.sp
            )
        },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = colorResource(id = typePokemon.color!!.toInt())
        )
    )
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    PokedexComposeTheme {
        DetailScreen(id = "#001") {

        }
    }
}