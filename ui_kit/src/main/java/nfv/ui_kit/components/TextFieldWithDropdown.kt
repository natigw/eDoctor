package nfv.ui_kit.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import nfv.ui_kit.countries.Country
import nfv.ui_kit.theme.EDoctorTypography

@Composable
fun TextFieldWithDropdown(
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val jsonString = context.assets.open("countries.json").bufferedReader().use { it.readText() }
    val countryList = Gson().fromJson(jsonString, Array<Country>::class.java).toList()

    //"https://cdn.jsdelivr.net/npm/flag-icon-css/flags/4x3/gb.svg"

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(Icons.Default.MoreVert, contentDescription = "More options")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            countryList.forEach {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = it.countryCode,
                            style = EDoctorTypography.bodyLarge
                        )
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.Person, contentDescription = null
                        )
                    },
                    onClick = {

                    }
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TextFieldWithDropdownPrev() {
    TextFieldWithDropdown()
}