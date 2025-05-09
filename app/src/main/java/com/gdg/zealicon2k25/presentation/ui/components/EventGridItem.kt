package com.gdg.zealicon2k25.presentation.ui.components

import android.R.attr.onClick
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.gdg.zealicon2k25.R
import com.gdg.zealicon2k25.data.models.Event
import com.gdg.zealicon2k25.presentation.ui.theme.ButtonBackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.ButtonBorderColor
import com.gdg.zealicon2k25.presentation.ui.theme.ButtonRippleColor
import com.gdg.zealicon2k25.presentation.ui.theme.EventButtonTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.HeadingTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.MerchCardBackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.Outfit

@Composable
@Preview
fun EventGridItem(
    event: Event,
    onClick: () -> Unit = {}
) {
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
                onClick()
            }
            .clip(RoundedCornerShape(12.dp))
            .background(color = MerchCardBackgroundColor)
            .border(width = 1.dp, color = HeadingTextColor, shape = RoundedCornerShape(12.dp))
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = event.image,
            modifier = Modifier.fillMaxWidth()
                .padding(5.dp, 5.dp, 5.dp)
                .height(210.dp)
                .clip(RoundedCornerShape(6.dp)),
            contentDescription = "Event Image",
            contentScale = ContentScale.Fit,
            placeholder = painterResource(R.drawable.default_placeholder),
            error = painterResource(R.drawable.default_placeholder)
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
                    text ="Day 1" ,
                    fontSize = 12.sp,
                    fontFamily = Outfit,
                    fontWeight = FontWeight.Normal,
                    color =  HeadingTextColor
                )
                Text(
                    text = event.title,
                    fontSize = 16.sp,
                    fontFamily = Outfit,
                    fontWeight = FontWeight.SemiBold,
                    color =  HeadingTextColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    )
            }
//            Image(
//                modifier = Modifier.rotate(180f),
//                painter = painterResource(R.drawable.back),
//                contentDescription = "go_arrow"
//            )
        }
    }
}
// EventsResponse(events=[Event(_id=681a2e0dc8c2ac9ddebde2d6, contact_info=codingclub@example.com, description=A 24-hour coding event to build innovative tech solutions., enrollment_end=2025-05-15T00:00:00.000Z, enrollment_start=2025-05-10T00:00:00.000Z, event_end=2025-05-21T00:00:00.000Z, event_start=2025-05-20T00:00:00.000Z, prize=50000, society=NCS, title=Hackathon 2025, type=TECHNICAL, venue=Auditorium Hall A), Event(_id=681a2e85c8c2ac9ddebde2dd, contact_info=codegolf@example.com, description=Write the shortest and smartest code to solve tough problems., enrollment_end=2025-06-25T00:00:00.000Z, enrollment_start=2025-06-20T00:00:00.000Z, event_end=2025-06-30T00:00:00.000Z, event_start=2025-06-30T00:00:00.000Z, prize=10000, society=NCS, title=Code Golf Tournament, type=TECHNICAL, venue=Lab 3, CS Block)], message=Events fetched successfully!, success=true)
