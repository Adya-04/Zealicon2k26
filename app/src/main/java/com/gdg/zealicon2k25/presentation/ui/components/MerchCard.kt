package com.gdg.zealicon2k25.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.gdg.zealicon2k25.data.models.Merch
import com.gdg.zealicon2k25.presentation.ui.theme.ButtonRippleColor
import com.gdg.zealicon2k25.presentation.ui.theme.HeadingTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.MerchCardBackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.Outfit

@Composable
@Preview
fun MerchCard(
    merch: Merch ,
    onMerchClick:() ->Unit = {}
){
    Box(
        modifier = Modifier
            .height(284.dp)
            .clickable(
                enabled = true,
                indication = rememberRipple(
                    bounded = true,
                    color = ButtonRippleColor,
                ),
                interactionSource = remember { MutableInteractionSource() },
                role = Role.Button,
            ) {
                onMerchClick()
            }
            .clip(RoundedCornerShape(12.dp))
            .background(color = MerchCardBackgroundColor)
            .border(width = 1.dp, color = HeadingTextColor, shape = RoundedCornerShape(12.dp))
            .fillMaxWidth()
    ){
        Column {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 5.dp, 5.dp)
                    .height(210.dp)
                    .clip(RoundedCornerShape(6.dp)),
                model = merch.photo,
                contentDescription = "Event Image",
                contentScale = ContentScale.Fit
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.align(Alignment.CenterHorizontally)
                    .padding(horizontal = 2.dp),
            ) {

                Text(
                    text = merch.title,
                    fontSize = 20.sp,
                    fontFamily = Outfit,
                    fontWeight = FontWeight.SemiBold,
                    color = HeadingTextColor,
                    modifier = Modifier.padding(top = 4.dp),
                )

                Text(
                    text = "₹ " + merch.price.toString(),
                    fontSize = 16.sp,
                    fontFamily = Outfit,
                    fontWeight = FontWeight.Normal,
                    color = HeadingTextColor,
                    modifier = Modifier.padding(horizontal = 2.dp)
                )
            }
        }
    }
}