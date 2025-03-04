package il.co.amikashkash.aquariumtoolkit.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import il.co.amikashkash.aquariumtoolkit.data.Additive


@Composable
fun AdditiveItem(additive: Additive, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .clickable { onClick() },
        elevation = cardElevation(
            defaultElevation = 10.dp // Set the default elevation here
        )

    )
    {
        Column(modifier = Modifier.padding(16.dp)) {
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
                Text("${additive.forVolumeDose} litters", fontWeight = FontWeight.ExtraBold)

            }


        }

    }
}



