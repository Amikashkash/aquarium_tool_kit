package il.co.amikashkash.aquariumtoolkit.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parceler
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

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    companion object : Parceler<Additive> {

        override fun Additive.write(parcel: Parcel, flags: Int) {
            parcel.writeLong(additiveId)
            parcel.writeString(name)
            parcel.writeString(addingDose)
            parcel.writeString(forVolumeDose)
        }

        override fun create(parcel: Parcel): Additive {
            return Additive(parcel)
        }
    }
}

object DummyList{
    val additives = listOf(
        Additive(name = "Formalin", addingDose = "3", forVolumeDose = "110"),
        Additive(name = "Chlorine Remover", addingDose = "5", forVolumeDose = "38"),
        Additive(name = "Voogle", addingDose = "10", forVolumeDose = "60"),
        
    )
}
