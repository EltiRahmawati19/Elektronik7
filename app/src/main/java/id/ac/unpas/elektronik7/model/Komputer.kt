package id.ac.unpas.elektronik7.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Komputer(
    @PrimaryKey val id:String,
    val merk: String,
    val jenis: String,
    val harga: String,
    val dapatDiupgrade: String,
    val spesifikasi: String
)
