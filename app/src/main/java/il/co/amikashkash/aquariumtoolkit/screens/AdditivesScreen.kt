package il.co.amikashkash.aquariumtoolkit.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import il.co.amikashkash.aquariumtoolkit.elements.AdditiveItem
import il.co.amikashkash.aquariumtoolkit.elements.AppBar
import il.co.amikashkash.aquariumtoolkit.navigation.Screen
import il.co.amikashkash.aquariumtoolkit.viewmodels.AdditiveViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AdditivesScreen(
    navController: NavController,
    additiveViewModel: AdditiveViewModel
) {
    Scaffold(
        topBar = { AppBar(navController, title = "Additives") },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(all = 20.dp),
                contentColor = MaterialTheme.colorScheme.onPrimary,
                containerColor = MaterialTheme.colorScheme.primary,
                onClick = {
                    navController.navigate(Screen.ADD_ADDITIVE.route + "/0L")
                })
            {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        val additivesList = additiveViewModel.getAllAdditives.collectAsState(initial = listOf())
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(additivesList.value, key = { additive -> additive.additiveId }) { additive ->
                val dismissState = rememberDismissState(
                    confirmStateChange = {
                        if (it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart) {
                            additiveViewModel.deleteAdditive(additive)
                            true
                        } else {
                            false
                        }

                    }
                )
                SwipeToDismiss(
                    state = dismissState,
                    background = {
                        val color = when (dismissState.dismissDirection) {
                            DismissDirection.StartToEnd -> Color.Green
                            DismissDirection.EndToStart -> Color.Red
                            null -> Color.Transparent
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color)
                                .padding(16.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )
                        }
                    },
                    dismissContent = {
                        AdditiveItem(additive = additive) {
                            val id = additive.additiveId
                            navController.navigate(Screen.ADD_ADDITIVE.route + "/$id")
                        }
                    },
                    directions = setOf(DismissDirection.EndToStart)
                )


            }

        }
    }
}

