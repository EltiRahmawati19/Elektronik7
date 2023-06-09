package id.ac.unpas.elektronik7.repositories

import com.benasher44.uuid.uuid4
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import id.ac.unpas.elektronik7.model.SistemSmartphone
import id.ac.unpas.elektronik7.model.Smartphone
import id.ac.unpas.elektronik7.networks.SmartphoneApi
import id.ac.unpas.elektronik7.persistences.SmartphoneDao
import javax.inject.Inject

class SmartphoneRepository @Inject constructor(
    private val api: SmartphoneApi,
    private val dao: SmartphoneDao
) : Repository {

    suspend fun loadItems(
        onSuccess: (List<Smartphone>) -> Unit,
        onError: (List<Smartphone>, String) -> Unit
    ) {
        val list: List<Smartphone> = dao.getList()
        api.all()
            // handle the case when the API request gets a success response.
            .suspendOnSuccess {
                data.whatIfNotNull {
                    it.data?.let { list ->
                        dao.insertAll(list)
                        val items: List<Smartphone> = dao.getList()
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
        model : String,
        warna : String,
        storage : Int,
        tanggalRilis : String,
        sistemOperasi : SistemSmartphone,
        onSuccess: (Smartphone) -> Unit,
        onError: (Smartphone?, String) -> Unit
    ) {
        val id = uuid4().toString()
        val item = Smartphone(id, model, warna, storage, tanggalRilis, sistemOperasi)
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
        model : String,
        warna : String,
        storage : Int,
        tanggalRilis : String,
        sistemOperasi : SistemSmartphone,
        onSuccess: (Smartphone) -> Unit,
        onError: (Smartphone?, String) -> Unit
    ) {
        val item = Smartphone(id, model, warna, storage, tanggalRilis, sistemOperasi)
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

    suspend fun find(id: String) : Smartphone? {
        return dao.find(id)
    }
}