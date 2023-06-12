package id.ac.unpas.elektronik7.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.w3c.dom.Text

@Entity
data class Periferal(
    @PrimaryKey val id:String,
    val nama: String,
    val harga: Int,
    val deskripsi: String,
    val jenis: String
)

//enum class Perif {
//    Mouse,
//    Keyboard
//}
