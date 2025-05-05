package com.gdg.zealicon2k25.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdg.zealicon2k25.R
import com.gdg.zealicon2k25.presentation.ui.components.CardBackground
import com.gdg.zealicon2k25.presentation.ui.components.PrizeCard
import com.gdg.zealicon2k25.presentation.ui.components.RegisterButton
import com.gdg.zealicon2k25.presentation.ui.components.SecondaryCardBackground
import com.gdg.zealicon2k25.presentation.ui.theme.BackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.HeadingTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.Outfit

@Composable
@Preview
fun EventDetailScreen(
//    eventName:String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(top = 16.dp)
    ) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(R.drawable.back_arrow),
                    modifier = Modifier.padding(top = 20.dp, start = 20.dp, bottom = 20.dp),
                    contentDescription = "arrow",
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "LineUp",
                    fontSize = 28.sp,
                    fontFamily = Outfit,
                    color = HeadingTextColor,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 20.dp, start = 12.dp, bottom = 20.dp)
                )
            }
            Image(
                painter = painterResource(R.drawable.game_image),
                modifier = Modifier.padding(horizontal = 20.dp).fillMaxWidth().height(250.dp),
                contentDescription = "eventImage",
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(
                    start = 20.dp, end = 20.dp, top = 20.dp, bottom = 8.dp
                ),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                CardBackground(
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                    image = R.drawable.calendar,
                    detail = "15 May"
                )
                SecondaryCardBackground(
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                    image = R.drawable.clock,
                    details = "13 : 00"
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                SecondaryCardBackground(
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                    details = "AB1, 1st Floor",
                    image = R.drawable.location_marker
                )
                CardBackground(
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                    image = R.drawable.phone,
                    detail = "999999999"
                )
            }

            PrizeCard(
                prize = "₹15,000",
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp, end = 20.dp, start = 20.dp)
            )

            Text(
                text = "About LineUp",
                fontSize = 24.sp,
                fontFamily = Outfit,
                color = HeadingTextColor,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 24.dp, start = 20.dp)
            )

            Text(
                text = "Lorem ipsum dolor sit amet consectetur. Mollis pulvinar fermentum turpis feugiat sed orci. Cursus sed lorem diam a ut. Nibh egestas praesent facilisis et viverra phasellus donec at nascetur. Elit urna dui sit nibh. Sagittis habitant bibendum suscipit elementum. Dignissim condimentum dolor massa eget massa porttitor vel consectetur. Sit aliquam sed bibendum massa quam eget sodales.",
                fontSize = 16.sp,
                fontFamily = Outfit,
                color = HeadingTextColor,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(top = 24.dp, start = 20.dp, end = 20.dp)
            )
            Text(
                text = "Instructions",
                fontSize = 24.sp,
                fontFamily = Outfit,
                color = HeadingTextColor,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 24.dp, start = 20.dp)
            )

            Text(
                text = "Lorem ipsum dolor sit amet consectetur. Mollis pulvinar fermentum turpis feugiat sed orci. Cursus sed lorem diam a ut. Nibh egestas praesent facilisis et viverra phasellus donec at nascetur. Elit urna dui sit nibh. Sagittis habitant bibendum suscipit elementum. Dignissim condimentum dolor massa eget massa porttitor vel consectetur. Sit aliquam sed bibendum massa quam eget sodales. Lorem ipsum dolor sit amet consectetur. Mollis pulvinar fermentum turpis feugiat sed orci. Cursus sed lorem diam a ut. Nibh egestas praesent facilisis et viverra phasellus donec at nascetur. Elit urna dui sit nibh. Sagittis habitant bibendum suscipit elementum. Dignissim condimentum dolor massa eget massa porttitor vel consectetur. Sit aliquam sed bibendum massa quam eget sodales.",
                fontSize = 16.sp,
                fontFamily = Outfit,
                color = HeadingTextColor,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(top = 24.dp, start = 20.dp, end = 20.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, bottom = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                RegisterButton(
                    modifier = Modifier
                ) {}
            }


        }
    }
}