package com.gdg.zealicon2k25

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gdg.zealicon2k25.presentation.ui.EntryPass
import com.gdg.zealicon2k25.presentation.ui.EventDetailScreen
import com.gdg.zealicon2k25.presentation.ui.HomeScreen
import com.gdg.zealicon2k25.presentation.ui.MenuScreen
import com.gdg.zealicon2k25.presentation.ui.RegisterScreen
import com.gdg.zealicon2k25.presentation.ui.theme.Zealicon2K25Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Zealicon2K25Theme {
                EntryPass()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Zealicon2K25Theme {
        Greeting("Android")
    }
}