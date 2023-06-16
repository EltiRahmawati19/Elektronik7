package id.ac.unpas.elektronik7.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
//import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.benasher44.uuid.uuid4
import id.ac.unpas.elektronik7.model.JenisPeriferal
//import id.ac.unpas.elektronik7.model.Perif
import id.ac.unpas.elektronik7.model.Periferal
import id.ac.unpas.elektronik7.persistences.PeriferalDao
import id.ac.unpas.elektronik7.ui.theme.Purple700
import id.ac.unpas.elektronik7.ui.theme.Teal200
import kotlinx.coroutines.launch

//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormPencatatanPeriferal(navController: NavHostController, id: String? = null, modifier: Modifier) {
    val viewModel = hiltViewModel<PengelolaanPeriferalViewModel>()

    val nama = remember { mutableStateOf(TextFieldValue("")) }
    val harga = remember { mutableStateOf(TextFieldValue("")) }
    val deskripsi = remember { mutableStateOf(TextFieldValue("")) }
    val jenis = remember { mutableStateOf(JenisPeriferal.KEYBOARD) }

    val isLoading = remember {
        mutableStateOf(false)
    }
    val buttonLabel = if (isLoading.value) "Mohon tunggu..." else "Simpan"

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            label = { Text(text = "Nama") },
            value = nama.value,
            onValueChange = {
                nama.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization =
                KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text
            ),
            placeholder = { Text(text = "Nama") }
        )
        OutlinedTextField(
            label = { Text(text = "Harga") },
            value = harga.value,
            onValueChange = {
                harga.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType =
                KeyboardType.Decimal
            ),
            placeholder = { Text(text = "5") }
        )
        OutlinedTextField(
            label = { Text(text = "Deskripsi") },
            value = deskripsi.value,
            onValueChange = {
                deskripsi.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization =
                KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text
            ),
            placeholder = { Text(text = "Deskripsi") }
        )

        Column(Modifier.padding(4.dp)) {
            Text(text = "Jenis", fontSize = 10.sp)
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = jenis.value == JenisPeriferal.KEYBOARD,
                    onClick = { jenis.value = JenisPeriferal.KEYBOARD }
                )
                Text(text = "Keyboard", fontSize = 12.sp)
                Spacer(modifier = Modifier.width(8.dp))
                RadioButton(
                    selected = jenis.value == JenisPeriferal.MOUSE,
                    onClick = { jenis.value = JenisPeriferal.MOUSE }
                )
                Text(text = "Mouse", fontSize = 12.sp)
            }
        }

        val loginButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700,
            contentColor = Teal200
        )
        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )
        Row(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
        ) {
            Button(modifier = Modifier.weight(5f), onClick = {
//                if (nama.value.text.isNotBlank() && nama.value.text.isNotBlank() && !isNotBlankjenisOptions[0] && deskripsi.value.text.isNotBlank()) {
                    if (id == null) {
                        scope.launch {
                            viewModel.insert(
                                nama.value.text,
                                Integer.parseInt(harga.value.text),
                                deskripsi.value.text,
                                jenis.value
                            )
                        }
                    } else {
                        scope.launch {
                            viewModel.update(
                                id,
                                nama.value.text,
                                Integer.parseInt(harga.value.text),
                                deskripsi.value.text,
                                jenis.value
                            )
                        }
//                    }
                    if (!isLoading.value) {
                        navController.navigate("pengelolaan-periferal")
                    }
                }
            }, colors = loginButtonColors) {
                Text(
                    text = buttonLabel,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }

            Button(modifier = Modifier.weight(5f), onClick = {
                nama.value = TextFieldValue("")
                harga.value = TextFieldValue("")
                deskripsi.value = TextFieldValue("")
                jenis.value = JenisPeriferal.KEYBOARD
            }, colors = resetButtonColors) {
                Text(
                    text = "Reset",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
        }
    }

    viewModel.isLoading.observe(LocalLifecycleOwner.current) {
        isLoading.value = it
    }

    if (id != null) {
        LaunchedEffect(scope) {
            viewModel.loadItem(id) { Periferal ->
                Periferal?.let {
                    nama.value = TextFieldValue("")
                    harga.value = TextFieldValue(Periferal.harga.toString())
                    deskripsi.value = TextFieldValue("")
                    jenis.value = JenisPeriferal.KEYBOARD
                }
            }
        }
    }
}