package com.gdg.zealicon2k25.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdg.zealicon2k25.R
import com.gdg.zealicon2k25.presentation.ui.theme.HeadingTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.Outfit
import com.gdg.zealicon2k25.presentation.ui.theme.cardBackground

@Composable
@Preview

fun MenuCard(
    modifier: Modifier = Modifier,
    text:String ,
    image :Int,
    vector:Int,
    padding:Dp
) {
    Box(
        modifier = modifier
            .background(color = cardBackground, shape = RoundedCornerShape(18.dp))
            .width(189.dp)
            .clip(RoundedCornerShape(18.dp))
    ) {
        Row {
            Image(
                painter = painterResource(vector),
                contentScale = ContentScale.Crop,
                contentDescription = "info",
                modifier = Modifier.padding(end = 8.dp, top = 12.dp, start = 12.dp)
            )
            Text(
                text = text,
                fontFamily = Outfit,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = HeadingTextColor,
                modifier = Modifier.padding(top = 12.dp )
            )
        }
        Image(
            painter = painterResource(image),
            contentDescription = "graphic",
            contentScale = ContentScale.Crop,
            modifier = Modifier.align(Alignment.BottomEnd)
                .padding(top = padding)
        )
    }
}