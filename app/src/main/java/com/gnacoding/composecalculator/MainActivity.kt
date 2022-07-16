package com.gnacoding.composecalculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gnacoding.composecalculator.ui.theme.ComposeCalculatorTheme
import com.gnacoding.composecalculator.ui.theme.MediumBlue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            ComposeCalculatorTheme {
            val context = LocalContext.current
                Calculator(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MediumBlue)
                        .padding(12.dp),
                ){
                    Toast.makeText(context,it,Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}