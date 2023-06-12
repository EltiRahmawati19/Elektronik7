package id.ac.unpas.elektronik7.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.w3c.dom.Text

@Entity
data class Komputer(
    @PrimaryKey val id:String,
    val merk: String,
    val jenis: String,
    val harga: Int,
    val dapatDiupgrade: Boolean,
    val spesifikasi: String
)

//enum class Computer  {
//    Laptop,
//    Desktop,
//    AIO
//}
