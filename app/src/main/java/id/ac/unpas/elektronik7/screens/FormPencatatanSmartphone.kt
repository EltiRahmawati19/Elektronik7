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
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
//import androidx.compose.material.ExperimentalMaterial3Api
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
//import id.ac.unpas.elektronik7.model.Hp
import java.time.LocalDate
import id.ac.unpas.elektronik7.model.Smartphone
import id.ac.unpas.elektronik7.persistences.SmartphoneDao
import id.ac.unpas.elektronik7.ui.theme.Purple700
import id.ac.unpas.elektronik7.ui.theme.Teal200
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import id.ac.unpas.elektronik7.model.SistemSmartphone
import kotlinx.coroutines.launch
import java.util.Date

//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormPencatatanSmartphone(navController: NavHostController, id: String? = null, modifier: Modifier) {
    val viewModel = hiltViewModel<PengelolaanSmartphoneViewModel>()

    val tanggalDialogState = rememberMaterialDialogState()

    val model = remember { mutableStateOf(TextFieldValue("")) }
    val warna = remember { mutableStateOf(TextFieldValue("")) }
    val storage = remember { mutableStateOf(TextFieldValue("")) }
    val tanggalRilis = remember { mutableStateOf(TextFieldValue("")) }
    val sistemOperasi = remember { mutableStateOf(SistemSmartphone.ANDROID) }

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
            label = { Text(text = "Model") },
            value = model.value,
            onValueChange = {
                model.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization =
                KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text
            ),
            placeholder = { Text(text = "Model") }
        )
        OutlinedTextField(
            label = { Text(text = "Warna") },
            value = warna.value,
            onValueChange = {
                warna.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization =
                KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text
            ),
            placeholder = { Text(text = "Warna") }
        )
        OutlinedTextField(
            label = { Text(text = "Storage") },
            value = storage.value,
            onValueChange = {
                storage.value = it
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
            label = { Text(text = "Tanggal Rilis") },
            value = tanggalRilis.value,
            onValueChange = {
                tanggalRilis.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .clickable {
                    tanggalDialogState.show()
                },
            textStyle = TextStyle(color = Color.Black)
        )

        Column(Modifier.padding(4.dp)){
            Text(text = "Jenis", fontSize = 10.sp)
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = sistemOperasi.value == SistemSmartphone.ANDROID,
                    onClick = { sistemOperasi.value = SistemSmartphone.ANDROID }
                )
                Text(text = "Mouse", fontSize = 12.sp)
                Spacer(modifier = Modifier.width(8.dp))
                RadioButton(
                    selected = sistemOperasi.value == SistemSmartphone.ANDROID,
                    onClick = { sistemOperasi.value = SistemSmartphone.ANDROID }
                )
                Text(text = "Keyboard", fontSize = 12.sp)
            }
        }

        val loginButtonColors = ButtonDefaults.buttonColors(
            backgroundColor  = Purple700,
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
//                if (model.value.text.isNotBlank() && model.value.text.isNotBlank() && sistemOperasi != sistemOperasiOptions[0] && warna.value.text.isNotBlank()) {
                    if (id == null) {
                        scope.launch {
                            viewModel.insert(
                                model.value.text,
                                warna.value.text,
                                Integer.parseInt(storage.value.text),
                                tanggalRilis.value.text,
                                sistemOperasi.value
                            )
                        }
                    } else {
                        scope.launch {
                            viewModel.update(
                                id,
                                model.value.text,
                                warna.value.text,
                                Integer.parseInt(storage.value.text),
                                tanggalRilis.value.text,
                                sistemOperasi.value
                            )
                        }
//                    }
                    if (!isLoading.value) {
                        navController.navigate("pengelolaan-smartphone")
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
                model.value = TextFieldValue("")
                warna.value = TextFieldValue("")
                storage.value = TextFieldValue("")
                tanggalRilis.value = TextFieldValue("")
                sistemOperasi.value = SistemSmartphone.ANDROID
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
            viewModel.loadItem(id) { Smartphone ->
                Smartphone?.let {
                    model.value = TextFieldValue(Smartphone.model)
                    warna.value = TextFieldValue(Smartphone.warna)
                    storage.value = TextFieldValue(Smartphone.storage.toString())
                    tanggalRilis.value = TextFieldValue(Smartphone.tanggalRilis)
                    sistemOperasi.value = SistemSmartphone.ANDROID
                }
            }
        }
    }
}