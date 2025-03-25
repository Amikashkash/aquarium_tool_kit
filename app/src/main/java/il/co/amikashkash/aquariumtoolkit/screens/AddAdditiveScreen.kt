package il.co.amikashkash.aquariumtoolkit.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import il.co.amikashkash.aquariumtoolkit.R
import il.co.amikashkash.aquariumtoolkit.data.Additive
import il.co.amikashkash.aquariumtoolkit.elements.AppBar
import il.co.amikashkash.aquariumtoolkit.elements.CustomButton
import il.co.amikashkash.aquariumtoolkit.viewmodels.AdditiveViewModel
import kotlinx.coroutines.launch

@Composable
fun AddAdditivesScreen(
    additiveId: Long,
    additiveViewModel: AdditiveViewModel,
    navController: NavController
) {
    val additiveName = additiveViewModel.additiveName
    val dosagePerQuantity = additiveViewModel.dosagePerQuantity
    val quantity = additiveViewModel.quantity
    val snackMessage = remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()


    LaunchedEffect(key1 = additiveId) {
        if (additiveId != 0L) {
            additiveViewModel.getAdditiveById(additiveId).collect { additive ->
                additiveViewModel.onNameChange(additive.name)
                additiveViewModel.onDosageChange(additive.addingDose)
                additiveViewModel.onQuantityChange(additive.forVolumeDose)
            }
        } else {
            additiveViewModel.onNameChange("")
            additiveViewModel.onDosageChange("")
            additiveViewModel.onQuantityChange("")
        }
    }


    Scaffold(

        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                navController, title =
                if (additiveId != 0L) {
                    stringResource(id = R.string.update_additive)
                } else {
                    stringResource(id = R.string.add_additive)

                }
            )
        }, backgroundColor = MaterialTheme.colorScheme.background

    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            item {
                Image(
                    painter = painterResource(id = R.drawable.fish_additive),
                    contentDescription = stringResource(R.string.aquarium_calculation_image),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
            }
            item {
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                OutlinedTextField(
                    value = additiveName,
                    onValueChange = additiveViewModel::onNameChange,
                    label = { Text(stringResource(R.string.name)) },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)

                )
            }
            item {
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                OutlinedTextField(
                    value = dosagePerQuantity,
                    onValueChange = additiveViewModel::onDosageChange,
                    label = { Text(stringResource(R.string.dosage_per_quantity)) },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
            }
            item {
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                OutlinedTextField(
                    value = quantity,
                    onValueChange = additiveViewModel::onQuantityChange,
                    label = { Text(stringResource(R.string.quantity)) },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
            }
            item {//
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                CustomButton(
                    text = if (additiveId != 0L) stringResource(id = R.string.update_additive)
                    else stringResource(id = R.string.add_additive),
                    onClick = {
                        if (additiveName.isNotEmpty() && dosagePerQuantity.isNotEmpty() && quantity.isNotEmpty()) {
                            if (additiveId != 0L) {
                                additiveViewModel.updateAdditive(
                                    Additive(
                                        additiveId = additiveId,
                                        name = additiveViewModel.additiveName.trim(),
                                        addingDose = additiveViewModel.dosagePerQuantity,
                                        forVolumeDose = additiveViewModel.quantity
                                    )
                                )

                            } else {
                                // add the additive
                                additiveViewModel.addAdditive(
                                    Additive(
                                        name = additiveViewModel.additiveName.trim(),
                                        addingDose = additiveViewModel.dosagePerQuantity,
                                        forVolumeDose = additiveViewModel.quantity
                                    )
                                )
                                snackMessage.value = "Additive Added"
                            }
                        } else {
                            // add the additive
                            snackMessage.value = "Please fill in all fields"
                        }
                        scope.launch {
                            //scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)

                            navController.navigateUp()
                        }
                    }
                )
            }
            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
            item {
                Image(
                    painter = painterResource(id = R.drawable.fish_additive),
                    contentDescription = stringResource(R.string.aquarium_calculation_image),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
            }


        }
    }

}






