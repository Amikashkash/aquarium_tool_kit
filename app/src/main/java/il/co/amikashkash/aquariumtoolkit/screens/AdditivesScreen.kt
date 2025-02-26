package il.co.amikashkash.aquariumtoolkit.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import il.co.amikashkash.aquariumtoolkit.data.Additive
import il.co.amikashkash.aquariumtoolkit.data.DummyList
import il.co.amikashkash.aquariumtoolkit.elements.AppBar
import il.co.amikashkash.aquariumtoolkit.navigation.Screen
import il.co.amikashkash.aquariumtoolkit.viewmodels.AdditiveViewModel

@Composable
fun AdditivesScreen(navController: NavController,
                    additiveViewModel: AdditiveViewModel) {
    Scaffold(
        topBar = { AppBar(navController, title = "Additives") },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(all = 20.dp),
                contentColor = MaterialTheme.colorScheme.onPrimary,
                containerColor = MaterialTheme.colorScheme.primary,
                onClick = {
                    navController.navigate(Screen.ADD_ADDITIVE.route)
                })
            {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(DummyList.additives){
                additive -> AdditiveItem(additive = additive, onClick = {})
            }
            
        }
    }
}

@Composable
fun AdditiveItem(additive: Additive, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .clickable { onClick() },
        elevation = cardElevation(
            defaultElevation = 10.dp // Set the default elevation here
        )

    )
    { Column(modifier = Modifier.padding(16.dp)) {
        Row        {
            Text(text = "Additive Name: ")
            Text(text = additive.name, fontWeight = FontWeight.ExtraBold)

        }
        Row {

            Text("Additive Dose:  ")
            Text("${additive.addingDose} ml", fontWeight = FontWeight.ExtraBold)
        }
        Row {
            Text("Dose for volume:  ")
            Text("${additive.forVolumeDose} litters", fontWeight = FontWeight.ExtraBold)

    }

        
    }

    }
}