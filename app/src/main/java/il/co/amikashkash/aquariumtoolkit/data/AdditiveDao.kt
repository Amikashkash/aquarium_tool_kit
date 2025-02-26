package il.co.amikashkash.aquariumtoolkit.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
abstract class AdditiveDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addAdditive(additiveEntity: Additive)

    @Query("Select * from `additives_table`")
    abstract fun getAllAdditives(): Flow<List<Additive>>

    @Update
    abstract suspend fun updateAdditive(additiveEntity: Additive)

    @Delete
    abstract suspend fun deleteAdditive(additiveEntity: Additive)

    @Query("Select * from `additives_table` where additiveId = :additiveId")
    abstract fun getAdditiveById(additiveId: Long): Flow<Additive>

}