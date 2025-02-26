package il.co.amikashkash.aquariumtoolkit.elements

import android.content.Context
import androidx.room.Room
import il.co.amikashkash.aquariumtoolkit.data.AdditiveDatabase
import il.co.amikashkash.aquariumtoolkit.data.AdditiveRepository

object Graph {
    lateinit var database: AdditiveDatabase

    val additiveRepository by lazy {
        AdditiveRepository(
            additiveDao = database.additiveDao()
        )

    }

    fun provide(context: Context) {
        database = Room.databaseBuilder(context, AdditiveDatabase::class.java, "additive.db").build()
    }

}