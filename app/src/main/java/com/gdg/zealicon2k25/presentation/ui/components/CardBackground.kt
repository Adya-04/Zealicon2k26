package com.gdg.zealicon2k25.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdg.zealicon2k25.R
import com.gdg.zealicon2k25.presentation.ui.theme.Outfit
import com.gdg.zealicon2k25.presentation.ui.theme.cardBackground
import com.gdg.zealicon2k25.presentation.ui.theme.cardBorderColor
import com.gdg.zealicon2k25.presentation.ui.theme.cardTextColor

@Composable
@Preview
fun CardBackground(
    modifier: Modifier = Modifier,
    image: Int,
    detail: String
) {
    Box(
        modifier = modifier.background(
            color = cardBackground,
            shape = RoundedCornerShape(12.dp)
        )
            .height(64.dp)
            .border(width = 1.dp, color = cardBorderColor, shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
    ) {
        Image(
            painter = painterResource(R.drawable.flower_graphics),
            contentDescription = "graphic image",
            modifier = Modifier.align(Alignment.TopEnd)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = "info",
                modifier = Modifier.padding(end = 4.dp , start = 2.dp)
            )
            Text(
                text = detail,
                fontFamily = Outfit,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = cardTextColor,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(end = 2.dp)
            )
        }

        Image(
            painter = painterResource(R.drawable.flower_graphics_2),
            contentDescription = "graphic image",
            modifier = Modifier.align(Alignment.BottomStart)
        )
    }
}