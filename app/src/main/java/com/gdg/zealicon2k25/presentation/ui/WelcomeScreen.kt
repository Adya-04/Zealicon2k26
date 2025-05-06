package com.gdg.zealicon2k25.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gdg.zealicon2k25.R
import com.gdg.zealicon2k25.presentation.ui.components.PrimaryButton
import com.gdg.zealicon2k25.presentation.ui.components.SecodaryButton
import com.gdg.zealicon2k25.presentation.ui.theme.BackgroundColor

@Composable
fun WelcomeScreen(
    loginOnClick: () -> Unit,
    registerOnClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Image(
            painter = painterResource(R.drawable.welcome_graphic),
            contentDescription = "graphics"
        )

        Image(
            modifier = Modifier.align(Alignment.BottomCenter),
            painter = painterResource(R.drawable.welcome_graphic_2),
            contentDescription = "graphics"
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x2DFFFFFF))
        )
        Column(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
            ) {
                PrimaryButton(
                    text = "Get your Zeal ID"
                ) {
                    registerOnClick()
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 50.dp),
            ) {
                SecodaryButton(
                    text = "Login"
                ) {
                    loginOnClick()
                }
            }
        }
    }
}