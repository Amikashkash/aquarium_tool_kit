package il.co.amikashkash.aquariumtoolkit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import il.co.amikashkash.aquariumtoolkit.navigation.SetupNavGraph
import il.co.amikashkash.aquariumtoolkit.ui.theme.AquariumToolKitTheme
import il.co.amikashkash.aquariumtoolkit.viewmodels.AdditiveViewModel
import il.co.amikashkash.aquariumtoolkit.viewmodels.DosingViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            AquariumToolKitTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    SetupNavGraph(
                        navController = navController,
                        additiveViewModel = AdditiveViewModel(),
                        dosingViewModel = DosingViewModel(),
                    )
                }
            }
            }
        }
    }



