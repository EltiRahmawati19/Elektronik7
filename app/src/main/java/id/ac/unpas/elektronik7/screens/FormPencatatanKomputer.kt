package id.ac.unpas.elektronik7.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
//import androidx.compose.material.ExperimentalMaterial3Api
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benasher44.uuid.uuid4
import id.ac.unpas.elektronik7.model.Komputer
import id.ac.unpas.elektronik7.persistences.KomputerDao
import id.ac.unpas.elektronik7.ui.theme.Purple700
import id.ac.unpas.elektronik7.ui.theme.Teal200
import kotlinx.coroutines.launch


//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormPencatatanKomputer(komputerDao: KomputerDao){
    val merk = remember { mutableStateOf(TextFieldValue("")) }
    val jenis = remember { mutableStateOf(TextFieldValue("")) }
    val harga = remember { mutableStateOf(TextFieldValue("")) }
    val dapatDiupgrade = remember { mutableStateOf(TextFieldValue("")) }
    val spesifikasi = remember { mutableStateOf(TextFieldValue("")) }

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
        OutlinedTextField(
            label = { Text(text = "Jenis") },
            value = jenis.value,
            onValueChange = {
                jenis.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization =
                KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text
            ),
            placeholder = { Text(text = "Jenis") }
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
            placeholder = { Text(text = "Harga") }
        )
        OutlinedTextField(
            label = { Text(text = "Dapat Di-upgrade") },
            value = dapatDiupgrade.value,
            onValueChange = {
                dapatDiupgrade.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization =
                KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text
            ),
            placeholder = { Text(text = "Upgrade") }
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
                val id = uuid4().toString()
                val item = Komputer(id,
                    merk.value.text, jenis.value.text,
                    harga.value.text, dapatDiupgrade.value.text,
                    spesifikasi.value.text)
                scope.launch {
                    komputerDao.insertAll(item)
                }
                    merk.value = TextFieldValue("")
                    jenis.value = TextFieldValue("")
                    harga.value = TextFieldValue("")
                    dapatDiupgrade.value = TextFieldValue("")
                    spesifikasi.value = TextFieldValue("")
                }, colors = loginButtonColors) {
                Text(
                    text = "Simpan",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
            Button(modifier = Modifier.weight(5f), onClick = {
                merk.value = TextFieldValue("")
                jenis.value = TextFieldValue("")
                harga.value = TextFieldValue("")
                dapatDiupgrade.value = TextFieldValue("")
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
}