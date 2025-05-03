package com.gdg.zealicon2k25

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gdg.zealicon2k25.presentation.ui.LoginScreen
import com.gdg.zealicon2k25.presentation.ui.PurchaseZealScreen
import com.gdg.zealicon2k25.presentation.ui.RegisterScreen
import com.gdg.zealicon2k25.presentation.ui.VerifyOTPScreen
import com.gdg.zealicon2k25.presentation.ui.WelcomeScreen
import com.gdg.zealicon2k25.presentation.ui.theme.Zealicon2K25Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Zealicon2K25Theme {
              VerifyOTPScreen()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Zealicon2K25Theme {
        Greeting("Android")
    }
}