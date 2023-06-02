package id.ac.unpas.elektronik7.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.elektronik7.model.Komputer

@Dao
interface KomputerDao {
    @Query("SELECT * FROM Komputer")
    fun loadAll(): LiveData<List<Komputer>>

    @Query("SELECT * FROM Komputer WHERE id = :id")
    fun find(id: String): Komputer?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: Komputer)

    @Delete
    fun delete(item: Komputer)
}