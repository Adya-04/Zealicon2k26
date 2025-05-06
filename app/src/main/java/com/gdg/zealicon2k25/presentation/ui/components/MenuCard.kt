package com.gdg.zealicon2k25.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdg.zealicon2k25.R
import com.gdg.zealicon2k25.presentation.ui.theme.ButtonRippleColor
import com.gdg.zealicon2k25.presentation.ui.theme.HeadingTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.Outfit
import com.gdg.zealicon2k25.presentation.ui.theme.cardBackground

@Composable

fun MenuCard(
    modifier: Modifier = Modifier,
    text: String,
    image: Int,
    vector: Int,
    padding: Dp,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .background(color = cardBackground, shape = RoundedCornerShape(18.dp))
            .clickable(
                enabled = true,
                indication = rememberRipple(
                    bounded = true,
                    color = ButtonRippleColor
                ),
                interactionSource = remember { MutableInteractionSource() },
                role = Role.Button
            ) {
                onClick()
            }
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
                modifier = Modifier.padding(top = 12.dp)
            )
        }
        Image(
            painter = painterResource(image),
            contentDescription = "graphic",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(90.dp)
        )
    }
}