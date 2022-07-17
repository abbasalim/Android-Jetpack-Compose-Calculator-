package com.gnacoding.composecalculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.gnacoding.composecalculator.ui.theme.ComposeCalculatorTheme
import ir.wave.composecalculator.Calculator
import ir.wave.composecalculator.Utils.CalculatorColors

class MainTestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            ComposeCalculatorTheme {
                val context = LocalContext.current
                Calculator(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

