package id.ac.unpas.elektronik7.screens

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Phone
import androidx.compose.ui.graphics.vector.ImageVector
import id.ac.unpas.elektronik7.R

enum class Menu (
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String
) {

    PENGELOLAAN_KOMPUTER(R.string.pengelolaan_komputer, Icons.Default.Menu, "pengelolaan-komputer"),
    PENGELOLAAN_PERIFERAL(R.string.pengelolaan_periferal, Icons.Default.List, "pengelolaan-periferal"),
    PENGELOLAAN_SMARTPHONE(R.string.pengelolaan_smartphone, Icons.Default.Phone, "pengelolaan-smartphone");

    companion object {
        fun getTabFromResource(@StringRes resource: Int) : Menu {
            return when (resource) {
                R.string.pengelolaan_komputer -> PENGELOLAAN_KOMPUTER
                R.string.pengelolaan_periferal -> PENGELOLAAN_PERIFERAL
                else -> PENGELOLAAN_SMARTPHONE
            }
        }
    }
}