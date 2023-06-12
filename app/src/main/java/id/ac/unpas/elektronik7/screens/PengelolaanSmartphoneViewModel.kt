package id.ac.unpas.elektronik7.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.unpas.elektronik7.model.Smartphone
import id.ac.unpas.elektronik7.persistences.SmartphoneDao
import java.util.Date
import javax.inject.Inject


@HiltViewModel
class PengelolaanSmartphoneViewModel @Inject constructor(private val smartphoneDao: SmartphoneDao) : ViewModel() {
    val list : LiveData<List<Smartphone>> = smartphoneDao.loadAll()

    suspend fun insert(id: String,
                       model: String,
                       warna: String,
                       storage: Int,
                       tanggalRilis: String,
                       sistemOperasi: String) {
        val item = Smartphone(id, model, warna, storage, tanggalRilis, sistemOperasi)
        smartphoneDao.insertAll(item)
    }

}