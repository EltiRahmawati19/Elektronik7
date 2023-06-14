package id.ac.unpas.elektronik7.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.elektronik7.model.Smartphone

@Dao
interface SmartphoneDao {
    @Query("SELECT * FROM Smartphone")
    fun loadAll(): LiveData<List<Smartphone>>

    @Query("SELECT * FROM Smartphone")
    suspend fun getList(): List<Smartphone>

    @Query("SELECT * FROM Smartphone WHERE id = :id")
    fun find(id: String) : Smartphone?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: Smartphone)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<Smartphone>)

    @Delete
    fun delete(item: Smartphone)

    @Query("DELETE FROM Smartphone WHERE id = :id")
    fun delete(id: String)
}