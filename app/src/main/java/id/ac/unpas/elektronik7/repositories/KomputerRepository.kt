package id.ac.unpas.elektronik7.repositories

import com.benasher44.uuid.uuid4
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import id.ac.unpas.elektronik7.model.JenisKomputer
import id.ac.unpas.elektronik7.model.Komputer
import id.ac.unpas.elektronik7.networks.KomputerApi
import id.ac.unpas.elektronik7.persistences.KomputerDao
import javax.inject.Inject

class KomputerRepository @Inject constructor(
    private val api: KomputerApi,
    private val dao: KomputerDao
) : Repository {

    suspend fun loadItems(
        onSuccess: (List<Komputer>) -> Unit,
        onError: (List<Komputer>, String) -> Unit
    ) {
        val list: List<Komputer> = dao.getList()
        api.all()
            // handle the case when the API request gets a success response.
            .suspendOnSuccess {
                data.whatIfNotNull {
                    it.data?.let { list ->
                        dao.insertAll(list)
                        val items: List<Komputer> = dao.getList()
                        onSuccess(items)
                    }
                }
            }
            // handle the case when the API request gets an error response.
            // e.g. internal server error.
            .suspendOnError {
                onError(list, message())
            }
            // handle the case when the API request gets an exception response.
            // e.g. network connection error.
            .suspendOnException {
                onError(list, message())
            }
    }

    suspend fun insert(
        merk : String,
        jenis : JenisKomputer,
        harga : Int,
        dapatDiupgrade : Int,
        spesifikasi : String,
        onSuccess: (Komputer) -> Unit,
        onError: (Komputer?, String) -> Unit
    ) {
        val id = uuid4().toString()
        val item = Komputer(id, merk, jenis, harga, dapatDiupgrade, spesifikasi)
        dao.insertAll(item)
        api.insert(item)
            // handle the case when the API request gets a success response.
            .suspendOnSuccess {
                onSuccess(item)
            }
            // handle the case when the API request gets an error response.
            // e.g. internal server error.
            .suspendOnError {
                onError(item, message())
            }
            // handle the case when the API request gets an exception response.
            // e.g. network connection error.
            .suspendOnException {
                onError(item, message())
            }
    }

    suspend fun update(
        id : String,
        merk : String,
        jenis : JenisKomputer,
        harga : Int,
        dapatDiupgrade : Int,
        spesifikasi : String,
        onSuccess: (Komputer) -> Unit,
        onError: (Komputer?, String) -> Unit
    ) {
        val item = Komputer(id, merk, jenis, harga, dapatDiupgrade, spesifikasi)
        dao.insertAll(item)
        api.update(id, item)
            // handle the case when the API request gets a success response.
            .suspendOnSuccess {
                onSuccess(item)
            }
            // handle the case when the API request gets an error response.
            // e.g. internal server error.
            .suspendOnError {
                onError(item, message())
            }
            // handle the case when the API request gets an exception response.
            // e.g. network connection error.
            .suspendOnException {
                onError(item, message())
            }
    }

    suspend fun delete(id: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        dao.delete(id)
        api.delete(id)
            // handle the case when the API request gets a success response.
            .suspendOnSuccess {
                onSuccess()
            }
            // handle the case when the API request gets an error response.
            // e.g. internal server error.
            .suspendOnError {
                onError(message())
            }
            // handle the case when the API request gets an exception response.
            // e.g. network connection error.
            .suspendOnException {
                onError(message())
            }
    }

    suspend fun find(id: String) : Komputer? {
        return dao.find(id)
    }
}