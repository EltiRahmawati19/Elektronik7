package id.ac.unpas.elektronik7.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.elektronik7.model.Komputer

@Dao
interface KomputerDao {
    @Query("SELECT * FROM Komputer ORDER BY id DESC")
    fun loadAll(): LiveData<List<Komputer>>

    @Query("SELECT * FROM Komputer ORDER BY id DESC")
    suspend fun getList(): List<Komputer>

    @Query("SELECT * FROM Komputer WHERE id = :id")
    suspend fun find(id: String): Komputer?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: Komputer)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<Komputer>)

    @Delete
    fun delete(item: Komputer)

    @Query("DELETE FROM Komputer WHERE id = :id")
    fun delete(id: String)
}