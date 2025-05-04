package com.gdg.zealicon2k25.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdg.zealicon2k25.R
import com.gdg.zealicon2k25.presentation.ui.theme.HeadingTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.Outfit
import com.gdg.zealicon2k25.presentation.ui.theme.cardBackground
import com.gdg.zealicon2k25.presentation.ui.theme.cardBorderColor

@Composable
@Preview
fun PrizeCard(
    modifier: Modifier = Modifier,
    prize:String
) {
    Box(
        modifier = modifier.background(
            color = cardBackground,
            shape = RoundedCornerShape(12.dp),
        )
            .height(100.dp)
            .fillMaxWidth()
            .border(width = 1.dp, color = cardBorderColor, shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(18.dp))
    )
    {
        Text(
            text = "Prize",
            fontSize = 16.sp,
            fontFamily = Outfit,
            color = HeadingTextColor,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(top = 20.dp, start = 20.dp, bottom = 20.dp)
        )

        Text(
            text = prize,
            fontSize = 40.sp,
            fontFamily = Outfit,
            fontWeight = FontWeight.SemiBold,
            color = HeadingTextColor,
            modifier = Modifier.padding(top = 32.dp, start = 20.dp)
        )

        Image(
            painter = painterResource(R.drawable.prize_graphic),
            contentDescription = "graphic image",
            modifier = Modifier.align(Alignment.BottomEnd)
                .padding(end = 8.dp)
                .graphicsLayer {
                    translationY = 55f
                }
        )
    }
}