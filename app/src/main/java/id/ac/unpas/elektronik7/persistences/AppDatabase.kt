package id.ac.unpas.elektronik7.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.elektronik7.model.Komputer
import id.ac.unpas.elektronik7.model.Periferal
import id.ac.unpas.elektronik7.model.Smartphone

@Database(entities = [Komputer::class, Periferal::class, Smartphone::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun komputerDao(): KomputerDao
    abstract fun periferalDao(): PeriferalDao
    abstract fun smartphoneDao(): SmartphoneDao


}