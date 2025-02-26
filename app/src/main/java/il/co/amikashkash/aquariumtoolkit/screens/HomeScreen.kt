package il.co.amikashkash.aquariumtoolkit.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import il.co.amikashkash.aquariumtoolkit.R
import il.co.amikashkash.aquariumtoolkit.elements.AppBar
import il.co.amikashkash.aquariumtoolkit.elements.CustomButton
import il.co.amikashkash.aquariumtoolkit.navigation.Screen

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = { AppBar(navController, title = "Aquarium Tool Kit") },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.homescreen_fish),
                contentDescription = "Aquarium calculation image",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            CustomButton(
                onClick = { navController.navigate(Screen.VOLUME_INPUT.route) },
                text = "Volume Calculation",
                modifier = Modifier.padding(bottom = 8.dp,top = 8.dp)
            )

            CustomButton(
                onClick = { navController.navigate(Screen.DOSAGE_AQUA.createDosageAquaRoute(""))
                          },
                text = "Dosage Calculator",
                modifier = Modifier.padding(bottom = 8.dp,top = 8.dp)
                )
            
            CustomButton(
                onClick = { navController.navigate(Screen.ADDITIVES.route)
                          },
                text = "Additives List",
                modifier = Modifier.padding(bottom = 8.dp,top = 8.dp)
            )

        }
    }

}

