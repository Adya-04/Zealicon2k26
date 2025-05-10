package com.gdg.zealicon2k25.presentation.ui.components

import android.R.attr.onClick
import android.R.attr.text
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.gdg.zealicon2k25.R
import com.gdg.zealicon2k25.presentation.ui.theme.ButtonRippleColor
import com.gdg.zealicon2k25.presentation.ui.theme.HeadingTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.MerchCardBackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.Outfit

@Composable
fun TeamMemberCard(
    name: String,
    imgUrl: String,
    role: String,
    contact: String
){
    Box(
        modifier = Modifier
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
        Column {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 5.dp, 5.dp)
                    .height(210.dp)
                    .clip(RoundedCornerShape(6.dp)),
                model = imgUrl,
                contentDescription = "Event Image",
                contentScale = ContentScale.Crop
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = role,
                    fontSize = 12.sp,
                    fontFamily = Outfit,
                    fontWeight = FontWeight.Normal,
                    color = HeadingTextColor,
                    modifier = Modifier.padding(top=4.dp)
                )
                Text(
                    text = name,
                    fontSize = 16.sp,
                    fontFamily = Outfit,
                    fontWeight = FontWeight.SemiBold,
                    color = HeadingTextColor,
                )
                Text(
                    text = contact,
                    fontSize = 12.sp,
                    fontFamily = Outfit,
                    fontWeight = FontWeight.Normal,
                    color = HeadingTextColor,
                    modifier = Modifier.padding( bottom = 5.dp)
                )
            }
        }
    }
}