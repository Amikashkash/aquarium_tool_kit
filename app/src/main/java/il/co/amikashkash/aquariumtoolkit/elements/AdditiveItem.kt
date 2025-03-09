package il.co.amikashkash.aquariumtoolkit.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import il.co.amikashkash.aquariumtoolkit.data.Additive


@Composable
fun AdditiveItem(
    additive: Additive, onUpdateClick: () -> Unit,
    onFillDosageClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp),
        //.clickable { onClick() },
        elevation = cardElevation(
            defaultElevation = 10.dp // Set the default elevation here
        )

    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f)) {
                    Row {
                        Text(text = "Additive Name: ")
                        Text(text = additive.name, fontWeight = FontWeight.ExtraBold)

                    }
                    Row {

                        Text("Additive Dose:  ")
                        Text("${additive.addingDose} ml", fontWeight = FontWeight.ExtraBold)
                    }
                    Row {
                        Text("Dose for volume:  ")
                        Text(
                            "${additive.forVolumeDose} litters",
                            fontWeight = FontWeight.ExtraBold
                        )

                    }


                }//column


                Spacer(modifier = Modifier.width(16.dp))
                IconButton(onClick = onUpdateClick) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Update Additive",
                        modifier = Modifier.size(24.dp)
                    )
                }
                IconButton(onClick = onFillDosageClick) {
                    Icon(
                        imageVector = Icons.Filled.Send,
                        contentDescription = "Fill Dosage",
                        modifier = Modifier.size(24.dp)
                    )
                }


            }//row


        } //column
    } //card
}



