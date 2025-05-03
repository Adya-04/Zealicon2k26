package com.gdg.zealicon2k25.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.gdg.zealicon2k25.presentation.ui.theme.FrontSpring

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    arrow: Boolean = true,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(start = 20.dp, end = 20.dp)
            .height(60.dp)
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(16.dp),
                clip = false
            )
            .clickable(
                enabled = enabled,
                indication = rememberRipple(
                    bounded = true,
                    color = ButtonRippleColor
                ),
                interactionSource = remember { MutableInteractionSource() },
                role = Role.Button

            ) {
                if(enabled){
                    onClick()
                }
            }
            .clip(RoundedCornerShape(18.dp))
            .background(color = ButtonBackgroundColor)
            .border(width = 2.dp, color = ButtonBorderColor, shape = RoundedCornerShape(18.dp))
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                fontSize = 20.sp,
                fontFamily = FrontSpring,
                fontWeight = FontWeight.Black,
                color = Color.White
            )
            if(arrow){
                Image(
                    modifier = Modifier.padding(start = 3.dp),
                    alignment = Alignment.BottomEnd,
                    painter = painterResource(R.drawable.arrow_right_small),
                    contentDescription = "arrow"
                )
            }
        }
        Image(
            alignment = Alignment.BottomEnd,
            painter = painterResource(R.drawable.button_grahics_1),
            contentDescription = "button_graphics"
        )
        Image(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .rotate(180f),
            painter = painterResource(R.drawable.button_grahics_1),
            contentDescription = "button_graphics"
        )
        Image(
            modifier = Modifier
                .padding(start = 180.dp)
                .align(Alignment.Center)
                .rotate(180f),
            painter = painterResource(R.drawable.button_graphics_2),
            contentDescription = "button_graphics"
        )
    }
}