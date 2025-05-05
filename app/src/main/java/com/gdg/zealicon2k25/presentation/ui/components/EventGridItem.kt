package com.gdg.zealicon2k25.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdg.zealicon2k25.R
import com.gdg.zealicon2k25.presentation.ui.theme.ButtonBackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.ButtonBorderColor
import com.gdg.zealicon2k25.presentation.ui.theme.ButtonRippleColor
import com.gdg.zealicon2k25.presentation.ui.theme.EventButtonTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.HeadingTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.MerchCardBackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.Outfit

@Composable
@Preview
fun EventGridItem() {
    Box(
        modifier = Modifier
            .height(284.dp)
            .clickable(
                enabled = true,
                indication = rememberRipple(
                    bounded = true,
                    color = ButtonRippleColor
                ),
                interactionSource = remember { MutableInteractionSource() },
                role = Role.Button

            ) {

            }
            .clip(RoundedCornerShape(12.dp))
            .background(color = MerchCardBackgroundColor)
            .border(width = 1.dp, color = HeadingTextColor, shape = RoundedCornerShape(12.dp))
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp, 5.dp, 5.dp)
                .height(210.dp)
                .clip(RoundedCornerShape(6.dp))
            ,
            painter = painterResource(R.drawable.default_placeholder),
            contentDescription = "Event Image",
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(start = 12.dp, bottom = 15.dp, end =  12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Day-1",
                    fontSize = 12.sp,
                    fontFamily = Outfit,
                    fontWeight = FontWeight.Normal,
                    color =  HeadingTextColor
                )
                Text(
                    text = "Line Up",
                    fontSize = 18.sp,
                    fontFamily = Outfit,
                    fontWeight = FontWeight.SemiBold,
                    color =  HeadingTextColor
                )
            }
            Image(
                modifier = Modifier.rotate(180f),
                painter = painterResource(R.drawable.back),
                contentDescription = "go_arrow"
            )
        }
    }
}