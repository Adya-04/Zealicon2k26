package com.gdg.zealicon2k25.presentation.ui

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdg.zealicon2k25.R
import com.gdg.zealicon2k25.presentation.ui.components.EventGridItem
import com.gdg.zealicon2k25.presentation.ui.components.EventTabComponent
import com.gdg.zealicon2k25.presentation.ui.components.ViewTicketButton
import com.gdg.zealicon2k25.presentation.ui.theme.BackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.ButtonBackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.ButtonBackgroundColor2
import com.gdg.zealicon2k25.presentation.ui.theme.ButtonBorderColor
import com.gdg.zealicon2k25.presentation.ui.theme.ButtonRippleColor
import com.gdg.zealicon2k25.presentation.ui.theme.BuyButtonTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.HeadingTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.MerchCardBackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.Outfit
import com.gdg.zealicon2k25.presentation.ui.theme.TicketCardBackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.TicketCardBorderColor

@Composable
@Preview
fun HomeScreen(
    menuOnClick: () -> Unit = {},
    entryPass: () -> Unit = {},
    eventDetails: () -> Unit = {},
    buyZealClick: () -> Unit = {}
) {
    val eventTabs = listOf("Cultural", "Technical", "Registered")
    var selected by remember { mutableStateOf(0) }
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val fixedHeight = (screenHeight - 122).dp
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 29.dp, end = 29.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Hello Ayush",
                    fontSize = 28.sp,
                    color = HeadingTextColor,
                    fontWeight = FontWeight.Medium,
                    fontFamily = Outfit
                )
                Image(
                    modifier = Modifier.clickable(
                        enabled = true,
                        indication = rememberRipple(
                            bounded = true,
                            color = ButtonRippleColor
                        ),
                        interactionSource = remember { MutableInteractionSource() },
                        role = Role.Button
                    ) {
                        menuOnClick()
                    },
                    painter = painterResource(R.drawable.menu_button),
                    contentDescription = "menu"
                )
            }
            Box(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, top = 18.dp)
                    .height(168.dp)
                    .clickable(
                        enabled = true,
                        indication = rememberRipple(
                            bounded = true,
                            color = ButtonRippleColor
                        ),
                        interactionSource = remember { MutableInteractionSource() },
                        role = Role.Button
                    ) {
                        buyZealClick()
                    }
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = TicketCardBackgroundColor)
                    .border(
                        width = 2.dp,
                        color = TicketCardBorderColor,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .fillMaxWidth()
            ) {
                Image(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .height(127.dp),
                    painter = painterResource(R.drawable.ticket_card_graphic),
                    contentDescription = "graphic"
                )
                Column(
                    modifier = Modifier
                        .padding(top = 8.dp, start = 8.dp, end = 10.dp)
                        .align(Alignment.TopStart),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        modifier = Modifier.width(248.dp),
                        text = "Hey,\nyou don’t have a Zeal ID !",
                        fontSize = 22.sp,
                        color = BackgroundColor,
                        fontWeight = FontWeight.Medium,
                        fontFamily = Outfit
                    )
                    Text(
                        modifier = Modifier.width(248.dp),
                        text = "You cannot enter the Zealicon-24 without a valid Zeal ID.",
                        fontSize = 12.sp,
                        color = BackgroundColor,
                        fontWeight = FontWeight.Medium,
                        fontFamily = Outfit
                    )
                }
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 12.dp, bottom = 12.dp)
                        .height(40.dp)
                        .width(117.dp)
                        .shadow(
                            elevation = 5.dp,
                            shape = RoundedCornerShape(16.dp),
                            clip = false
                        )
                        .clickable(
                            enabled = true,
                            indication = rememberRipple(
                                bounded = true,
                                color = ButtonRippleColor
                            ),
                            interactionSource = remember { MutableInteractionSource() },
                            role = Role.Button

                        ) {
                            buyZealClick()
                        }
                        .clip(RoundedCornerShape(6.dp))
                        .background(color = BackgroundColor)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Buy Now",
                            fontSize = 16.sp,
                            color = BuyButtonTextColor,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = Outfit
                        )
                        Image(
                            modifier = Modifier
                                .padding(start = 3.dp)
                                .size(16.dp),
                            alignment = Alignment.BottomEnd,
                            painter = painterResource(R.drawable.arrow_right_small),
                            contentDescription = "arrow",
                            colorFilter = ColorFilter.tint(BuyButtonTextColor)
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                    .height(323.dp)
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
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = "Merchandise Store",
                        fontSize = 22.sp,
                        color = HeadingTextColor,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = Outfit
                    )
                    Row(
                        modifier = Modifier.padding(12.dp, 32.dp, 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(end = 6.dp)
                                .height(175.dp)
                                .weight(1f)
                                .aspectRatio(1f)
                                .clickable(

                                ) {

                                }
                                .background(color = Color.White)
                        )
                        Box(
                            modifier = Modifier
                                .padding(start = 6.dp)
                                .height(175.dp)
                                .weight(1f)
                                .aspectRatio(1f)
                                .clickable(

                                ) {

                                }
                                .background(color = Color.White)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            modifier = Modifier,
                            text = "View All",
                            fontSize = 16.sp,
                            color = HeadingTextColor,
                            fontWeight = FontWeight.Medium,
                            fontFamily = Outfit,
                            textDecoration = TextDecoration.Underline,
                        )
                        Image(
                            modifier = Modifier
                                .padding(start = 3.dp)
                                .size(16.dp),
                            alignment = Alignment.BottomEnd,
                            painter = painterResource(R.drawable.arrow_right_small),
                            contentDescription = "arrow",
                            colorFilter = ColorFilter.tint(BuyButtonTextColor)
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    color = HeadingTextColor,
                    thickness = 1.dp,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)

                )
                Text(
                    text = "EVENTS",
                    fontSize = 24.sp,
                    fontFamily = Outfit,
                    fontWeight = FontWeight.SemiBold,
                    color = HeadingTextColor
                )
                Divider(
                    color = HeadingTextColor,
                    thickness = 1.dp,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)

                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Spacer(modifier = Modifier.width(20.dp))
                eventTabs.forEachIndexed { index, s ->
                    EventTabComponent(
                        selected = index == selected,
                        text = s,
                        onClick = {
                            selected = index
                        }
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
            }
            Spacer(modifier = Modifier.height(20.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .height(fixedHeight)
            ) {
                items(20) {
                    EventGridItem(
                        onClick = {
                            eventDetails()
                        }
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(130.dp))
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(112.dp)
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            ButtonBackgroundColor2
                        )
                    )
                ),
            contentAlignment = Alignment.Center,
        ) {
            ViewTicketButton(
                onClick = {
                    entryPass()
                }
            )
        }
    }
}