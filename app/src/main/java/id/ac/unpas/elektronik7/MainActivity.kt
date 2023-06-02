package id.ac.unpas.elektronik7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import id.ac.unpas.elektronik7.screens.PengelolaanKomputerScreen
import id.ac.unpas.elektronik7.screens.PengelolaanPeriferalScreen
import id.ac.unpas.elektronik7.screens.PengelolaanSmartphoneScreen
import id.ac.unpas.elektronik7.ui.theme.Elektronik7Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Elektronik7Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    PengelolaanKomputerScreen()
                    PengelolaanPeriferalScreen()
//                    PengelolaanSmartphoneScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Elektronik7Theme {
        PengelolaanKomputerScreen()
    }
}