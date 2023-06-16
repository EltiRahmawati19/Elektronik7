package id.ac.unpas.elektronik7.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.benasher44.uuid.uuid4
import id.ac.unpas.elektronik7.model.JenisKomputer
//import id.ac.unpas.elektronik7.model.Computer
import id.ac.unpas.elektronik7.ui.theme.Purple700
import id.ac.unpas.elektronik7.ui.theme.Teal200
import kotlinx.coroutines.launch


//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormPencatatanKomputer(navController: NavHostController, id: String? = null, modifier: Modifier){
    val viewModel = hiltViewModel<PengelolaanKomputerViewModel>()

    val dapatDiupgradeOptions = listOf(0, 1);

    val merk = remember { mutableStateOf(TextFieldValue("")) }
    val jenis = remember { mutableStateOf(JenisKomputer.LAPTOP) }
    val harga = remember { mutableStateOf(TextFieldValue("")) }
    val (dapatDiupgrade, setDapatDiupgrade) = remember { mutableStateOf(dapatDiupgradeOptions[0]) }
    val spesifikasi = remember { mutableStateOf(TextFieldValue("")) }

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
            label = { Text(text = "Merk") },
            value = merk.value,
            onValueChange = {
                merk.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization =
                KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text
            ),
            placeholder = { Text(text = "Merk") }
        )

        Column(Modifier.padding(4.dp)){
            Text(text = "Jenis", fontSize = 10.sp)
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = jenis.value == JenisKomputer.LAPTOP,
                    onClick = { jenis.value = JenisKomputer.LAPTOP }
                )
                Text(text = "Laptop", fontSize = 12.sp)
                Spacer(modifier = Modifier.width(8.dp))
                RadioButton(
                    selected = jenis.value == JenisKomputer.DESKTOP,
                    onClick = { jenis.value = JenisKomputer.DESKTOP }
                )
                Text(text = "Desktop", fontSize = 12.sp)
                Spacer(modifier = Modifier.width(8.dp))
                RadioButton(
                    selected = jenis.value == JenisKomputer.AIO,
                    onClick = { jenis.value = JenisKomputer.AIO }
                )
                Text(text = "AIO", fontSize = 12.sp)
            }
        }

        Row(
            Modifier
                .selectableGroup()
                .padding(top = 8.dp)
        ) {
            dapatDiupgradeOptions.forEach { text ->
                Row(
                    Modifier
                        .selectable(
                            selected = (text == dapatDiupgrade),
                            onClick = { setDapatDiupgrade(text) },
                            role = Role.RadioButton
                        )
                        .padding(end = 20.dp)
                ) {
                    RadioButton(
                        selected = (text == dapatDiupgrade),
                        onClick = { setDapatDiupgrade(text) }
                    )

                    val dapatDiupgradeText = when (text) {
                        0 -> "Tidak"
                        else -> "Ya"
                    }

                    Text (
                        text = dapatDiupgradeText,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(top = 12.dp)
                    )
                }
            }
        }

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
            label = { Text(text = "Spesifikasi") },
            value = spesifikasi.value,
            onValueChange = {
                spesifikasi.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization =
                KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text
            ),
            placeholder = { Text(text = "Spesifikasi") }
        )
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
//                if(merk.value.text.isNotBlank() && merk.value.text.isNotBlank() && dapatDiupgrade != dapatDiupgradeOptions[0] && spesifikasi.value.text.isNotBlank()) {
                    if (id == null) {
                        scope.launch {
                            viewModel.insert(
                                merk.value.text,
                                jenis.value,
                                harga.value.hashCode(),
                                dapatDiupgrade,
                                spesifikasi.value.text
                            )
                        }
                    } else {
                        scope.launch {
                            viewModel.update(
                                id,
                                merk.value.text,
    //                        jenis.value.text,
                                jenis.value,
                                harga.value.hashCode(),
                                dapatDiupgrade,
                                spesifikasi.value.text
                            )
                        }
//                    }
                    if (!isLoading.value) {
                        navController.navigate("pengelolaan-toko_elektronik")
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
                merk.value = TextFieldValue("")
                jenis.value = JenisKomputer.LAPTOP
                harga.value = TextFieldValue("")
                setDapatDiupgrade(dapatDiupgradeOptions[0])
                spesifikasi.value = TextFieldValue("")
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
            viewModel.loadItem(id) { Komputer ->
                Komputer?.let {
                    merk.value = TextFieldValue(Komputer.merk)
                    jenis.value = JenisKomputer.LAPTOP
                    harga.value = TextFieldValue(Komputer.harga.toString())
                    setDapatDiupgrade(Komputer.dapatDiupgrade)
                    spesifikasi.value = TextFieldValue(Komputer.spesifikasi)
                }
            }
        }
    }
}