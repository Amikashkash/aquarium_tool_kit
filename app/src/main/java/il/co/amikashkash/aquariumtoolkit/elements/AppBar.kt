package il.co.amikashkash.aquariumtoolkit.elements


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import il.co.amikashkash.aquariumtoolkit.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(navController: NavController, title: String) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    TopAppBar(title = {
        Text(
            text = title,
            color = colorResource(R.color.beige),
            modifier = Modifier
                .padding(start = 4.dp)
                .heightIn(max = 24.dp)
        )
    },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,

            ),
        modifier = Modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (currentRoute != "homescreen") {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = colorResource(R.color.beige)

                    )
                }
            }
        }
    )

}


//@Preview
//@Composable
//fun AppBarPreview() {
//    AppBar(navController = null)
//}
