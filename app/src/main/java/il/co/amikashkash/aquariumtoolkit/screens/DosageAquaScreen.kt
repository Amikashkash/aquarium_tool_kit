package il.co.amikashkash.aquariumtoolkit.screens


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import il.co.amikashkash.aquariumtoolkit.R
import il.co.amikashkash.aquariumtoolkit.data.Additive
import il.co.amikashkash.aquariumtoolkit.elements.AppBar
import il.co.amikashkash.aquariumtoolkit.elements.CustomButton
import il.co.amikashkash.aquariumtoolkit.viewmodels.AdditiveViewModel
import il.co.amikashkash.aquariumtoolkit.viewmodels.DosingViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DosageAquaScreen(
    navController: NavController,
    additiveId: Long,
    additiveViewModel: AdditiveViewModel,
    volume: String?,
    dosingViewModel: DosingViewModel = viewModel()

) {


    val focusManager = LocalFocusManager.current
    val additive = remember { mutableStateOf<Additive?>(null) }
    val currentAdditiveId by rememberUpdatedState(newValue = additiveId)

    LaunchedEffect(currentAdditiveId) {
        dosingViewModel.resetState()
        if (currentAdditiveId != 0L) {
            additiveViewModel.getAdditiveById(currentAdditiveId).collectLatest {
                additive.value = it
            }
        }
    }
        val aquaVolume by dosingViewModel.aquaVolume
        val materialDose by dosingViewModel.materialDose
        val perQuantityDose by dosingViewModel.perQuantityDose
        val dosage by dosingViewModel.dosage



        LaunchedEffect(key1 = additive.value, volume) {
           // Log.d("Ami", "additiveId =${additiveId}, volume = $volume")
            if (volume != null) {
                dosingViewModel.setAquaVolume(volume)
            }
            if (additive.value != null) {

                    dosingViewModel.onMaterialDoseChange(additive.value!!.addingDose)
                    dosingViewModel.onPerQuantityDoseChange(additive.value!!.forVolumeDose)

            }
        }

        Scaffold(
            topBar = { AppBar(navController, title = stringResource(R.string.dosage_calculator)) },
            containerColor = MaterialTheme.colorScheme.background
        ) { innerPadding ->
            LazyColumn(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Image(
                        painter = painterResource(id = R.drawable.sience_fish),
                        contentDescription = "Aquarium calculation image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )
                }
                item {
                    OutlinedTextField(
                        value = aquaVolume,
                        onValueChange = dosingViewModel::onAquaVolumeChange,
                        label = { Text(stringResource(R.string.aquarium_volume_litters)) },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                    )
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
                item {
                    OutlinedTextField(
                        value = materialDose,
                        onValueChange = dosingViewModel::onMaterialDoseChange,
                        label = { Text(stringResource(R.string.dosage_by_instruction_ml)) },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                    )
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
                item {
                    OutlinedTextField(
                        value = perQuantityDose,
                        onValueChange = dosingViewModel::onPerQuantityDoseChange,
                        label = { Text(stringResource(R.string.dosage_per_quantity_litters)) },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                    )
                }
                item { Spacer(modifier = Modifier.height(32.dp)) }
                item {
                    CustomButton(
                        onClick = {
                            dosingViewModel.calculateDosage()
                            focusManager.clearFocus()
                        },
                        text = stringResource(R.string.calculate_dosage),
                    )
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
                item {
                    Text(
                        stringResource(R.string.dosage_milliliter, String.format("%.2f", dosage)),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp)

                    )
                }
                item {
                    Text(
                        stringResource(R.string.of_solution_for_this_aquarium),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp)

                    )
                }
                item { Spacer(modifier = Modifier.height(104.dp)) }
            }

        }


    }
