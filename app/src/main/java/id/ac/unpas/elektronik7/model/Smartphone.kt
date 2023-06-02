package id.ac.unpas.elektronik7.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Smartphone(
    @PrimaryKey val id:String,
    val model: String,
    val warna: String,
    val storage: String,
    val tanggalRilis: String,
    val sistemOperasi: String
)
