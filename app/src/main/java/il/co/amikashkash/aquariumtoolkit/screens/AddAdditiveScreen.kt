package il.co.amikashkash.aquariumtoolkit.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import il.co.amikashkash.aquariumtoolkit.R
import il.co.amikashkash.aquariumtoolkit.elements.AppBar
import il.co.amikashkash.aquariumtoolkit.elements.CustomButton
import il.co.amikashkash.aquariumtoolkit.viewmodels.AdditiveViewModel

@Composable
fun AddAdditivesScreen(
    additiveId:Long,
    additiveViewModel: AdditiveViewModel,
    navController: NavController
    ) {
    val additiveName = additiveViewModel.additiveName
    val dosagePerQuantity = additiveViewModel.dosagePerQuantity
    val quantity = additiveViewModel.quantity

    Scaffold(
        topBar = {AppBar(navController,title =
            if(  additiveId != 0L ){
                stringResource(id = R.string.update_additive)
            } else {
                stringResource(id = R.string.add_additive)

            })},containerColor = MaterialTheme.colorScheme.background
    ) {
        Column (modifier = Modifier
            .padding(it)
            .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
            ){
             Spacer(modifier = Modifier.height(10.dp))
             AddTextField(
                 value = additiveName,
                 onValueChange = additiveViewModel::onNameChange,
                 label = "Name"
             )
            AddTextField(
                value = dosagePerQuantity,
                onValueChange = additiveViewModel::onDosageChange,
                label = "Dosage Per Quantity"
            )
            AddTextField(
                value = quantity,
                onValueChange = additiveViewModel::onQuantityChange,
                label = "Quntity"
            )
            Spacer(modifier = Modifier.height(10.dp))
            CustomButton(
                text = if( additiveId != 0L) stringResource(id = R.string.update_additive)
                else stringResource(id = R.string.add_additive),
                onClick = {
                     if( additiveName.isNotEmpty() && dosagePerQuantity.isNotEmpty() && quantity.isNotEmpty()  ){
                         // ToDo update the additive
                     }else{
                         // ToDo add the additive
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
        )

    )
}

@Preview
@Composable
fun AddTextFieldPreview() {
    AddTextField(value = "text", onValueChange = {}, label = "Name")
}



