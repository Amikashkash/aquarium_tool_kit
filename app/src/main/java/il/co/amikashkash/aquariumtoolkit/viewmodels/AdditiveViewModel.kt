package il.co.amikashkash.aquariumtoolkit.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import il.co.amikashkash.aquariumtoolkit.data.Additive
import il.co.amikashkash.aquariumtoolkit.data.AdditiveRepository
import il.co.amikashkash.aquariumtoolkit.elements.Graph
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AdditiveViewModel(
    private val additiveRepository: AdditiveRepository = Graph.additiveRepository
) : ViewModel() {

    var additiveName by mutableStateOf("")
        private set
    var dosagePerQuantity by mutableStateOf("")
        private set
    var quantity by mutableStateOf("")
        private set

    

    fun onNameChange(newName: String) {
        additiveName = newName
    }

    fun onDosageChange(newDosage: String) {
        dosagePerQuantity = newDosage
    }

    fun onQuantityChange(newQuantity: String) {
        quantity = newQuantity
    }

    lateinit var getAllAdditives: Flow<List<Additive>>

    init {
        viewModelScope.launch {
            getAllAdditives = additiveRepository.getAdditives()
        }
    }

    fun addAdditive(additive: Additive) {
        viewModelScope.launch(Dispatchers.IO) {
            additiveRepository.addAdditive(additive = additive)
        }
    }

    fun getAdditiveById(additiveId: Long) : Flow<Additive> {
        return additiveRepository.getAdditiveById(additiveId)

    }

    fun updateAdditive(additive: Additive) {
        viewModelScope.launch(Dispatchers.IO) {
            additiveRepository.updateAdditive(additive = additive)
        }
    }

    fun deleteAdditive(additive: Additive) {
        viewModelScope.launch(Dispatchers.IO) {
            additiveRepository.deleteAdditive(additive = additive)
        }
    }



}