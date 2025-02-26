package il.co.amikashkash.aquariumtoolkit.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Additive::class],
    version = 1,
    exportSchema = false
)
abstract class AdditiveDatabase : RoomDatabase() {

    abstract fun additiveDao() : AdditiveDao


}