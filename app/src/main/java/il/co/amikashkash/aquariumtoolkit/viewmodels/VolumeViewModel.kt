package il.co.amikashkash.aquariumtoolkit.viewmodels

import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class VolumeViewModel: ViewModel(){
    var lengthState = mutableStateOf("")
        private set
    var widthState = mutableStateOf("")
        private set
    var heightState = mutableStateOf("")
        private set
    var volumeState = mutableDoubleStateOf(0.0)
        private set



    // setters
    fun onLengthChange(newLength: String) {
        if(newLength.toIntOrNull() != null)
        { lengthState.value = newLength
        } else{
            lengthState.value = ""
        }
    }

    fun onWidthChange(newWidth: String) {
        if(newWidth.toIntOrNull() != null ) {
            widthState.value = newWidth
        } else{
            widthState.value = ""
        }

    }

    fun onHeightChange(newHeight: String) {
        if (newHeight.toIntOrNull() != null)
        { heightState.value = newHeight
        }else{
            heightState.value = ""
        }
    }

    fun calculateVolume() {
        val l = lengthState.value.toDoubleOrNull() ?: 0.0
        val w = widthState.value.toDoubleOrNull() ?: 0.0
        val h = heightState.value.toDoubleOrNull() ?: 0.0
        volumeState.doubleValue = (l * w * h) / 1000
    }

}