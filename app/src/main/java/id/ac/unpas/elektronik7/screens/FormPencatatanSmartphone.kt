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
import id.ac.unpas.elektronik7.model.Smartphone
import id.ac.unpas.elektronik7.persistences.SmartphoneDao
import id.ac.unpas.elektronik7.ui.theme.Purple700
import id.ac.unpas.elektronik7.ui.theme.Teal200
import kotlinx.coroutines.launch

//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormPencatatanSmartphone(smartphoneDao: SmartphoneDao) {
    val model = remember { mutableStateOf(TextFieldValue("")) }
    val warna = remember { mutableStateOf(TextFieldValue("")) }
    val storage = remember { mutableStateOf(TextFieldValue("")) }
    val tanggalRilis = remember { mutableStateOf(TextFieldValue("")) }
    val sistemOperasi = remember { mutableStateOf(TextFieldValue("")) }

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
                capitalization =
                KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text
            ),
            placeholder = { Text(text = "Storage") }
        )
        OutlinedTextField(
            label = { Text(text = "Tanggal Rilis") },
            value = tanggalRilis.value,
            onValueChange = {
                tanggalRilis.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "yyyy-mm-dd") }
        )
        OutlinedTextField(
            label = { Text(text = "Sistem Operasi") },
            value = sistemOperasi.value,
            onValueChange = {
                sistemOperasi.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization =
                KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text
            ),
            placeholder = { Text(text = "Sistem Operasi") }
        )
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
               val id = uuid4().toString()
                val item = Smartphone(
                    id,
                    model.value.text, warna.value.text,
                    storage.value.text, tanggalRilis.value.text,
                    sistemOperasi.value.text
                )
                scope.launch{
                smartphoneDao.insertAll(item)}
                model.value = TextFieldValue("")
                warna.value = TextFieldValue("")
                storage.value = TextFieldValue("")
                tanggalRilis.value = TextFieldValue("")
                sistemOperasi.value = TextFieldValue("")
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
                model.value = TextFieldValue("")
                warna.value = TextFieldValue("")
                storage.value = TextFieldValue("")
                tanggalRilis.value = TextFieldValue("")
                sistemOperasi.value = TextFieldValue("")
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