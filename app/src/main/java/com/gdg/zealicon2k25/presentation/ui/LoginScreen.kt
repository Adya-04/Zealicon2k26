package com.gdg.zealicon2k25.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdg.zealicon2k25.R
import com.gdg.zealicon2k25.presentation.ui.components.PrimaryButton
import com.gdg.zealicon2k25.presentation.ui.components.PrimaryTextField
import com.gdg.zealicon2k25.presentation.ui.theme.BackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.FrontSpring
import com.gdg.zealicon2k25.presentation.ui.theme.HeadingTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.Outfit

@Composable
@Preview
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Image(
                modifier = Modifier
                    .height(189.dp)
                    .fillMaxWidth(),
                painter = painterResource(R.drawable.register_graphic),
                contentDescription = "graphics",
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.padding(top = 24.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier.padding(start = 20.dp),
                    text = "Welcome Back",
                    fontSize = 30.sp,
                    color = HeadingTextColor,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FrontSpring
                )
                Text(
                    modifier = Modifier.padding(top = 24.dp, start = 20.dp),
                    text = "Email",
                    fontSize = 18.sp,
                    color = HeadingTextColor,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = Outfit
                )
                Spacer(modifier = Modifier.height(5.dp))
                PrimaryTextField(
                    value = email,
                    placeholder = "Enter your Email",
                    onValueChange = { email = it },
                )
            }
        }
        Column (
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            PrimaryButton(
                text = "Log In"
            ) { }
            Spacer(Modifier.height(52.dp))
        }

    }
}