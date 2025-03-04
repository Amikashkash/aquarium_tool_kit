package il.co.amikashkash.aquariumtoolkit.viewmodels

import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class DosingViewModel: ViewModel() {


    var aquaVolume = mutableStateOf("")
        private set
    var materialDose = mutableStateOf("")
        private set
    var perQuantityDose = mutableStateOf("")
        private set
    var dosage = mutableDoubleStateOf(0.0)



    fun onAquaVolumeChange(newAquaVolume: String) {
        if(newAquaVolume.toIntOrNull() != null){
            aquaVolume.value = newAquaVolume
        }
        }
    fun onMaterialDoseChange(newMaterialDose: String) {
        if(newMaterialDose.toIntOrNull() != null){
            materialDose.value = newMaterialDose
        }
    }
    fun onPerQuantityDoseChange(newPerQuantityDose: String) {
        if(newPerQuantityDose.toIntOrNull() != null){
            perQuantityDose.value = newPerQuantityDose
        }
    }
    fun calculateDosage() {
        val aqv = aquaVolume.value.toDoubleOrNull() ?: 0.0
        val md = materialDose.value.toDoubleOrNull() ?: 0.0
        val pqd = perQuantityDose.value.toDoubleOrNull() ?: 0.0
        dosage.doubleValue = if (pqd != 0.0) (aqv / pqd) * md else 0.0
    }

    fun setAquaVolume(newAquaVolume: String) {
        aquaVolume.value = newAquaVolume
    }


}