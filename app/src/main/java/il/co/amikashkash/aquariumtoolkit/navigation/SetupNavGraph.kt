package il.co.amikashkash.aquariumtoolkit.navigation


import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import il.co.amikashkash.aquariumtoolkit.screens.AddAdditivesScreen
import il.co.amikashkash.aquariumtoolkit.screens.AdditivesScreen
import il.co.amikashkash.aquariumtoolkit.screens.DosageAquaScreen
import il.co.amikashkash.aquariumtoolkit.screens.HomeScreen
import il.co.amikashkash.aquariumtoolkit.screens.VolumeInputScreen
import il.co.amikashkash.aquariumtoolkit.viewmodels.AdditiveViewModel
import il.co.amikashkash.aquariumtoolkit.viewmodels.DosingViewModel

enum class Screen(
    val route: String, val arguments: List<NamedNavArgument> = emptyList()
) {
    HOME("homescreen"),
    ADDITIVES("additivesscreen"),

    VOLUME_INPUT("volumeinputscreen"),
    DOSAGE_AQUA("dosageaquascreen?volume={volume}&additiveId={additiveId}", listOf(
        navArgument("volume") {
            type = NavType.StringType
            nullable = true
            defaultValue = ""
        },
        navArgument("additiveId") {
            type = NavType.LongType
            nullable = false
            defaultValue = 0L
        }
    )),
    ADD_ADDITIVE("addadditivescreen");

    fun createDosageAquaRoute(volume: String): String {
        return "dosageaquascreen?volume=$volume"
    }

    fun createDosageAquaRouteWithAdditive(additiveId: Long): String {
        return "dosageaquascreen?volume=&additiveId=$additiveId"
    }
}

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController(), additiveViewModel: AdditiveViewModel, dosingViewModel: DosingViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screen.HOME.route
    ) {
        composable(route = Screen.HOME.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.VOLUME_INPUT.route) {
            VolumeInputScreen(navController = navController)
        }
        composable(
            route = Screen.DOSAGE_AQUA.route,
            arguments = Screen.DOSAGE_AQUA.arguments
        ) {
            val volume = it.arguments?.getString("volume")
            val additiveId = it.arguments?.getLong("additiveId")
            DosageAquaScreen(
                navController = navController,
                volume = volume ?: "",
                additiveId = additiveId ?: 0L,
                additiveViewModel = additiveViewModel,
                dosingViewModel = dosingViewModel
            )
        }
        composable(route = Screen.ADDITIVES.route) {
             AdditivesScreen(navController = navController, additiveViewModel = additiveViewModel)
        }
        composable(route = Screen.ADD_ADDITIVE.route + "/{additiveId}",
            arguments = listOf(navArgument(
                "additiveId",){
                type = NavType.LongType
                defaultValue = 0L
                nullable = false
            }
            )
        ){entry ->
            val additiveId = if(entry.arguments != null)  entry.arguments!!.getLong("additiveId") else 0L
            AddAdditivesScreen(
                additiveId = additiveId,
                additiveViewModel = viewModel(),
                navController = navController
            )
        }
    }
}