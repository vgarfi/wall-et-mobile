package com.example.wall_etmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import com.example.wall_etmobile.ui.theme.MainPurple
import com.example.wall_etmobile.ui.theme.WalletMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        window.statusBarColor = MainPurple.toArgb()
        setContent {
            WalletMobileTheme {
                MainApp()
            }
        }
    }
}