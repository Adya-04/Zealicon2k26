package com.gdg.zealicon2k25.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdg.zealicon2k25.R
import com.gdg.zealicon2k25.presentation.ui.theme.BackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.FrontSpring
import com.gdg.zealicon2k25.presentation.ui.theme.HeadingTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.Outfit

@Composable
@Preview
fun ZealiconTicket() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp

    val (padding, fontSize) = when {
        screenWidth < 360 -> 47.dp to 14.sp       // Small
        screenWidth < 480 -> 50.dp to 14.sp      // Normal
        screenWidth < 720 -> 50.dp to 16.sp      // Large
        else -> 50.dp to 20.sp                   // X-Large
    }


    val topPadding = when {
        screenWidth < 360 -> (120.dp)
        screenWidth < 480 -> (130.dp)
        screenWidth < 600 -> 140.dp
        else -> 160.dp
    }

    Box(
        modifier = Modifier
            .width(390.dp)
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(R.drawable.ticket_frame),
            contentDescription = "Zealicon Ticket"
        )
        Column{
            Image(
                modifier = Modifier
                    .padding(top = topPadding, start = 58.dp, end = 58.dp),
                painter = painterResource(R.drawable.qr_code),
                contentDescription = "Zealicon Ticket",
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, padding, 10.dp, 0.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ) {
                Text(
                    text = "Ayush Agrawal",
                    fontSize = fontSize,
                    color = BackgroundColor,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = Outfit
                )
                Text(
                    text = "ZEAL 3462",
                    fontSize = fontSize,
                    color = BackgroundColor,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = Outfit
                )
            }
        }
    }
}