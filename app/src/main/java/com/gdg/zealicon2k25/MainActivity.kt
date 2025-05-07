package com.gdg.zealicon2k25

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.gdg.zealicon2k25.presentation.navigation.RootNavGraph
import com.gdg.zealicon2k25.presentation.ui.EventDetailScreen
import com.gdg.zealicon2k25.presentation.ui.MenuScreen
import com.gdg.zealicon2k25.presentation.ui.PhotoUploadScreen
import com.gdg.zealicon2k25.presentation.ui.theme.Zealicon2K25Theme
import com.gdg.zealicon2k25.presentation.ui.viewmodels.ImageUploadViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Zealicon2K25Theme {
               RootNavGraph(rememberNavController())
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