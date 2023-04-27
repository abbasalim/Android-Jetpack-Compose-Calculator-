package com.gnacoding.composecalculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.gnacoding.composecalculator.ui.theme.ComposeCalculatorTheme
import ir.wave.composecalculator.Calculator

class MainTestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            ComposeCalculatorTheme {
                val context = LocalContext.current
//                Calculator {Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
                Calculator (defValue = 55.0, roundResult = true){Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
            }
        }
    }
}

