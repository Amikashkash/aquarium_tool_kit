package il.co.amikashkash.aquariumtoolkit.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "additives_table")
@Parcelize
data class Additive(
    @PrimaryKey(autoGenerate = true)
    val additiveId: Long = 0L,
    @ColumnInfo(name = "additive_name")
    val name:String ="",
    @ColumnInfo(name = "dose")
    val addingDose:String="",
    @ColumnInfo(name = "for_volume")
    val forVolumeDose:String="",

): Parcelable

object DummyList{
    val additives = listOf(
        Additive(name = "Formalin", addingDose = "3", forVolumeDose = "110"),
        Additive(name = "Chlorine Remover", addingDose = "5", forVolumeDose = "38"),
        Additive(name = "Voogle", addingDose = "10", forVolumeDose = "60"),
        
    )
}
