package il.co.amikashkash.aquariumtoolkit.data

import kotlinx.coroutines.flow.Flow

class AdditiveRepository(private val additiveDao: AdditiveDao) {

    suspend fun addAdditive(additive: Additive) {
        additiveDao.addAdditive(additive)
    }

    fun getAdditives(): Flow<List<Additive>> = additiveDao.getAllAdditives()

    fun getAdditiveById(additiveId: Long) : Flow<Additive> {
        return additiveDao.getAdditiveById(additiveId)

    }

    suspend fun updateAdditive(additive: Additive) {
        additiveDao.updateAdditive(additive)
    }

    suspend fun deleteAdditive(additive: Additive) {
        additiveDao.deleteAdditive(additive)
    }


}