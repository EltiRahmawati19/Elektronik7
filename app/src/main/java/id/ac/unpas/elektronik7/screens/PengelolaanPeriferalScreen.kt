package id.ac.unpas.elektronik7.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import androidx.navigation.NavHostController
import androidx.room.Room
import id.ac.unpas.elektronik7.model.Komputer
import id.ac.unpas.elektronik7.model.Periferal
import id.ac.unpas.elektronik7.persistences.AppDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@Composable
fun PengelolaanPeriferalScreen(navController: NavHostController, modifier: Modifier) {
    val viewModel = hiltViewModel<PengelolaanPeriferalViewModel>()
    val items : List<Periferal> by viewModel.list.observeAsState(initial = listOf())

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxWidth()) {
        Button(onClick = {
            navController.navigate("tambah-pencatatan-periferal")
        }) {
            Text(text = "Tambah")
        }
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items = items, itemContent = { item ->
                Row(modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()) {
                    navController.navigate("edit-pengelolaan-periferal/${item.id}")
                    Column(modifier = Modifier.weight(3f)) {
                    Text(text = "Nama", fontSize = 14.sp)
                    Text(text = item.nama, fontSize = 16.sp, fontWeight =
                    FontWeight.Bold)
                }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Harga", fontSize = 14.sp)
                        Text(text = "Rp.${item.harga},00-", fontSize = 16.sp,
                            fontWeight = FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Deskripsi", fontSize = 14.sp)
                        Text(text = item.deskripsi, fontSize = 16.sp, fontWeight =
                        FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Jenis", fontSize = 14.sp)
                        Text(text = item.jenis.value, fontSize = 16.sp, fontWeight =
                        FontWeight.Bold)
                    }
                }
                Divider(modifier = Modifier.fillMaxWidth())
            })
        }
    }

    LaunchedEffect(scope) {
        viewModel.loadItems()
    }

    viewModel.success.observe(LocalLifecycleOwner.current) {
        if (it) {
            scope.launch {
                viewModel.loadItems()
            }
        }
    }

    viewModel.toast.observe(LocalLifecycleOwner.current) {
        Toast.makeText(context, it, Toast.LENGTH_LONG).show()
    }
}