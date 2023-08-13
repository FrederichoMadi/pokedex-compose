import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fredericho.pokedex_compose.navigation.NavDestination
import com.fredericho.pokedex_compose.presentation.detail.DetailScreen
import com.fredericho.pokedex_compose.presentation.home.HomeScreen

@Composable
fun PokedexApp(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = NavDestination.HOME.route,
    ) {
        composable(NavDestination.HOME.route){
            HomeScreen(
                navigateToDetail = { id ->
                    navController.navigate("${NavDestination.DETAIL_POKEMON.route}/$id")
                }
            )
        }
        composable("${NavDestination.DETAIL_POKEMON.route}/{id}",
            arguments = listOf(navArgument("id") {type = NavType.StringType})
        ){
            val id = it.arguments?.getString("id") ?: ""

            DetailScreen(id = id, onNavigationBack = { navController.popBackStack()})
        }
    }
}