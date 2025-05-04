package com.gdg.zealicon2k25.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdg.zealicon2k25.R
import com.gdg.zealicon2k25.presentation.ui.theme.BackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.HeadingTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.Outfit

@Composable
@Preview

fun AboutUsScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = BackgroundColor)
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth()
                .padding(top=16.dp)) {
                Image(
                    painter = painterResource(R.drawable.back_arrow),
                    modifier = Modifier.padding(top = 20.dp, start = 20.dp),
                    contentDescription = "arrow",
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "About Us",
                    fontSize = 28.sp,
                    fontFamily = Outfit,
                    color = HeadingTextColor,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 20.dp, start = 12.dp)
                )
            }
            Text(
                text = "Lorem ipsum dolor sit amet consectetur. Mollis pulvinar fermentum turpis feugiat sed orci. Cursus sed lorem diam a ut. Nibh egestas praesent facilisis et viverra phasellus donec at nascetur. Elit urna dui sit nibh. Sagittis habitant bibendum suscipit elementum. Dignissim condimentum dolor massa eget massa porttitor vel consectetur. Sit aliquam sed bibendum massa quam eget sodales.",
                fontSize = 16.sp,
                fontFamily = Outfit,
                color = HeadingTextColor,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(top = 33.dp, start = 20.dp, end = 20.dp)
            )
        }
    }
}