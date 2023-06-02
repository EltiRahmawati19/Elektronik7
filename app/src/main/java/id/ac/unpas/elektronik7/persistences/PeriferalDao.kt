package id.ac.unpas.elektronik7.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.elektronik7.model.Periferal

@Dao
interface PeriferalDao {
    @Query("SELECT * FROM Periferal")
    fun loadAll (): LiveData<List<Periferal>>

    @Query("SELECT * FROM Periferal WHERE id = :id")
    fun find(id: String) : Periferal?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: Periferal)

    @Delete
    fun delete(item: Periferal)
}