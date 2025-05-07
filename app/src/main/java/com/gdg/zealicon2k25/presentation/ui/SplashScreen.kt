package com.gdg.zealicon2k25.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gdg.zealicon2k25.R
import com.gdg.zealicon2k25.presentation.ui.theme.BackgroundColor
import kotlinx.coroutines.delay

@Composable
@Preview
fun SplashScreen(){
    Box(
        modifier = Modifier.fillMaxSize()
            .background(color = BackgroundColor)
    ){
        Image(
            painter = painterResource(R.drawable.instagram),
            contentDescription = "splash",
            modifier = Modifier.size(120.dp)
                .align(Alignment.Center)
        )
    }
    LaunchedEffect(key1 = true) {
        delay(2000)
    }
}