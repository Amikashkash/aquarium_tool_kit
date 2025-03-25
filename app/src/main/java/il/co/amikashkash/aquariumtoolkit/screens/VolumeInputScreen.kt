package il.co.amikashkash.aquariumtoolkit.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import il.co.amikashkash.aquariumtoolkit.R
import il.co.amikashkash.aquariumtoolkit.elements.AppBar
import il.co.amikashkash.aquariumtoolkit.elements.CustomButton
import il.co.amikashkash.aquariumtoolkit.navigation.Screen
import il.co.amikashkash.aquariumtoolkit.viewmodels.VolumeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VolumeInputScreen(
    navController: NavController,
    volumeViewModel: VolumeViewModel = viewModel()
) {

    val length by volumeViewModel.lengthState
    val width by volumeViewModel.widthState
    val height by volumeViewModel.heightState
    val volume by volumeViewModel.volumeState

    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = { AppBar(navController,  title = stringResource(R.string.volume_calculator))},
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item{
                Image(
                    painter = painterResource(id = R.drawable.aquarium_calculation),
                    contentDescription = "Aquarium calculation image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
            }
            item{
                OutlinedTextField(
                    value = length,
                    onValueChange = volumeViewModel::onLengthChange,
                    label = { Text((stringResource(R.string.length_cm))) },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
            item{ Spacer(modifier = Modifier.height(16.dp)) }
            item{
                OutlinedTextField(
                    value = width,
                    onValueChange = volumeViewModel::onWidthChange,
                    label = { Text(stringResource(R.string.width_cm)) },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
            item{ Spacer(modifier = Modifier.height(16.dp)) }
            item{
                OutlinedTextField(
                    value = height,
                    onValueChange = volumeViewModel::onHeightChange,
                    label = { Text(stringResource(R.string.height_cm)) },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
            item{ Spacer(modifier = Modifier.height(32.dp)) }
            item{
                CustomButton(
                    onClick = {
                        volumeViewModel.calculateVolume()
                        focusManager.clearFocus()
                    },
                    text = stringResource(R.string.calculate_volume)
                )
            }
            item{ Spacer(modifier = Modifier.height(16.dp)) }
            item{
                Text(
                    stringResource(R.string.volume_liters, String.format("%.2f", volume)),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            item{ Spacer(modifier = Modifier.height(24.dp)) }
            item{
                CustomButton(onClick = {
                    volumeViewModel.calculateVolume()
                    navController.navigate(Screen.DOSAGE_AQUA.createDosageAquaRoute(volume.toString()))
                }, text = stringResource(R.string.dosage_calculator))
            }
            item { Spacer(modifier = Modifier.height(72.dp)) }

        }


    }

}

