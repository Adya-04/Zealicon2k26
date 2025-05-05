package com.gdg.zealicon2k25.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdg.zealicon2k25.R
import com.gdg.zealicon2k25.presentation.ui.theme.BackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.HeadingTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.Outfit
import com.gdg.zealicon2k25.presentation.ui.theme.cardBackground

@Composable
@Preview
fun SecondaryMenuCard(

) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color= cardBackground , shape = RoundedCornerShape(18.dp))
            .padding(top = 12.dp, start = 12.dp)
            .clip(RoundedCornerShape(18.dp))
    ) {
        Column() {
            Text(
                text = "Follow us on",
                fontFamily = Outfit,
                fontSize = 18.sp,
                color = HeadingTextColor,
                modifier = Modifier.padding(start = 12.dp , top = 8.dp)
            )
            Row {
                Image(
                    painter = painterResource(R.drawable.instagram),
                    contentScale = ContentScale.Crop,
                    contentDescription = "info",
                    modifier = Modifier.padding(end = 4.dp, top = 12.dp, start = 12.dp)
                )
                Text(
                    text = "Instagram",
                    fontFamily = Outfit,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = HeadingTextColor,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        }
        Image(
            painter = painterResource(R.drawable.red_fort),
            contentScale = ContentScale.Crop,
            contentDescription = "red fort",
            modifier = Modifier.padding(top = 55.dp)
                .align(Alignment.BottomEnd)
        )
    }
}