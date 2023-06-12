package id.ac.unpas.elektronik7.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Smartphone(
    @PrimaryKey val id:String,
    val model: String,
    val warna: String,
    val storage: Int,
    val tanggalRilis: String,
    val sistemOperasi: String
)

//enum class Hp {
//    Android,
//    iOS
//}
