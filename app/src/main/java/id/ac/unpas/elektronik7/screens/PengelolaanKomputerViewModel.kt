package id.ac.unpas.elektronik7.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
//import id.ac.unpas.elektronik7.model.Computer
import id.ac.unpas.elektronik7.model.Komputer
import id.ac.unpas.elektronik7.persistences.KomputerDao
import org.w3c.dom.Text
import javax.inject.Inject


@HiltViewModel
class PengelolaanKomputerViewModel @Inject constructor(private val komputerDao: KomputerDao) : ViewModel() {
    val list : LiveData<List<Komputer>> = komputerDao.loadAll()

    suspend fun insert(id: String,
                       merk: String,
                       jenis: String,
                       harga: Int,
                       dapatDiupgrade: Boolean,
                       spesifikasi: String) {
        val item = Komputer(id, merk, jenis, harga, dapatDiupgrade, spesifikasi)
        komputerDao.insertAll(item)
    }
}