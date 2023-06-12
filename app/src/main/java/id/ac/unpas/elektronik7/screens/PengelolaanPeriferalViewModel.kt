package id.ac.unpas.elektronik7.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
//import id.ac.unpas.elektronik7.model.Jenis
import id.ac.unpas.elektronik7.model.Periferal
import id.ac.unpas.elektronik7.persistences.PeriferalDao
import org.w3c.dom.Text
import javax.inject.Inject


@HiltViewModel
class PengelolaanPeriferalViewModel @Inject constructor(private val periferalDao: PeriferalDao) : ViewModel() {
    val list : LiveData<List<Periferal>> = periferalDao.loadAll()

    suspend fun insert(id: String,
                       nama: String,
                       harga: Int,
                       deskripsi: String,
                       jenis: String) {
        val item = Periferal(id, nama, harga, deskripsi, jenis)
        periferalDao.insertAll(item)
    }

}