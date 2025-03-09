package il.co.amikashkash.aquariumtoolkit.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults

import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
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
    additiveId:Long,
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


    Scaffold(scaffoldState = scaffoldState,
        topBar = {AppBar(navController,title =
            if(  additiveId != 0L ){
                stringResource(id = R.string.update_additive)
            } else {
                stringResource(id = R.string.add_additive)

            })}, backgroundColor = MaterialTheme.colorScheme.background

    ) {
        Column (modifier = Modifier
            .padding(it)
            .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
            ){
            Image(
                painter = painterResource(id = R.drawable.fish_additive),
                contentDescription = "Aquarium calculation image",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
             Spacer(modifier = Modifier.height(10.dp))
             AddTextField(
                 value = additiveName,
                 onValueChange = additiveViewModel::onNameChange,
                 label = "Name",
                 keyboardType = KeyboardType.Text
             )
            AddTextField(
                value = dosagePerQuantity,
                onValueChange = additiveViewModel::onDosageChange,
                label = "Dosage Per Quantity",
                keyboardType = KeyboardType.Number
            )
            AddTextField(
                value = quantity,
                onValueChange = additiveViewModel::onQuantityChange,
                label = "Quantity",
                keyboardType = KeyboardType.Number
            )
            Spacer(modifier = Modifier.height(10.dp))
            CustomButton(
                text = if( additiveId != 0L) stringResource(id = R.string.update_additive)
                else stringResource(id = R.string.add_additive),
                onClick = {
                     if( additiveName.isNotEmpty() && dosagePerQuantity.isNotEmpty() && quantity.isNotEmpty()  ){
                         if(additiveId != 0L){
                             additiveViewModel.updateAdditive(
                                 Additive(
                                     additiveId = additiveId,
                                     name = additiveViewModel.additiveName.trim(),
                                     addingDose = additiveViewModel.dosagePerQuantity,
                                     forVolumeDose = additiveViewModel.quantity
                             )
                             )
                             
                         }else{
                             // add the additive
                             additiveViewModel.addAdditive(
                                 Additive(
                                     name = additiveViewModel.additiveName.trim(),
                                     addingDose = additiveViewModel.dosagePerQuantity,
                                     forVolumeDose = additiveViewModel.quantity
                                 ))
                             snackMessage.value = "Additive Added"
                         }
                     }else{
                         // add the additive
                         snackMessage.value = "Please fill in all fields"
                     }
                     scope.launch {
                         //scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)

                         navController.navigateUp()
                     }
                },




            )

        }
        }

}

@Composable
fun AddTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType
) {

    OutlinedTextField(
        value= value,
        onValueChange = onValueChange,
        label = {Text(text = label, color = Color.Black)},
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.secondary,
            unfocusedBorderColor = MaterialTheme.colorScheme.tertiary,
            focusedLabelColor = MaterialTheme.colorScheme.secondary,
            unfocusedLabelColor = MaterialTheme.colorScheme.tertiary,
            cursorColor = MaterialTheme.colorScheme.primary
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)

    )
}

@Preview
@Composable
fun AddTextFieldPreview() {
    AddTextField(value = "text", onValueChange = {}, label = "Name", keyboardType = KeyboardType.Text)
}



