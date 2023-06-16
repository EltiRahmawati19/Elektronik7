package id.ac.unpas.elektronik7.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.elektronik7.model.Periferal

@Dao
interface PeriferalDao {
    @Query("SELECT * FROM Periferal ORDER BY id DESC")
    fun loadAll (): LiveData<List<Periferal>>

    @Query("SELECT * FROM Periferal ORDER BY id DESC")
    suspend fun getList(): List<Periferal>

    @Query("SELECT * FROM Periferal WHERE id = :id")
    suspend fun find(id: String) : Periferal?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: Periferal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<Periferal>)

    @Delete
    fun delete(item: Periferal)

    @Query("DELETE FROM Periferal WHERE id = :id")
    fun delete(id: String)
}