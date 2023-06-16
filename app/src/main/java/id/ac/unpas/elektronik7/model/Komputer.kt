package id.ac.unpas.elektronik7.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.w3c.dom.Text

@Entity
data class Komputer(
    @PrimaryKey val id:String,
    val merk: String,
    val jenis: JenisKomputer,
    val harga: Int,
    val dapatDiupgrade: Int,
    val spesifikasi: String
)

enum class JenisKomputer(val value: String)  {
    LAPTOP("Laptop"),
    DESKTOP("Desktop"),
    AIO("AIO")
}
