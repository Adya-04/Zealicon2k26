package com.gdg.zealicon2k25.presentation.ui

import android.util.Log
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gdg.zealicon2k25.R
import com.gdg.zealicon2k25.data.models.Event
import com.gdg.zealicon2k25.presentation.ui.components.EventGridItem
import com.gdg.zealicon2k25.presentation.ui.components.EventTabComponent
import com.gdg.zealicon2k25.presentation.ui.components.ViewTicketButton
import com.gdg.zealicon2k25.presentation.ui.theme.BackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.ButtonBackgroundColor2
import com.gdg.zealicon2k25.presentation.ui.theme.ButtonRippleColor
import com.gdg.zealicon2k25.presentation.ui.theme.BuyButtonTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.ErrorTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.HeadingTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.MerchCardBackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.Outfit
import com.gdg.zealicon2k25.presentation.ui.theme.TicketCardBackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.TicketCardBorderColor
import com.gdg.zealicon2k25.presentation.ui.viewmodels.PaymentViewModel
import com.gdg.zealicon2k25.presentation.ui.viewmodels.AuthViewModel
import com.gdg.zealicon2k25.presentation.ui.viewmodels.EventsViewModel
import com.gdg.zealicon2k25.utils.NetworkResult

@Composable
@Preview
fun HomeScreen(
    menuOnClick: () -> Unit = {},
    entryPass: () -> Unit = {},
    eventDetails: () -> Unit = {},
    buyZealClick: () -> Unit = {},
    merchListing:() -> Unit ={},
    eventsViewModel: EventsViewModel ,
    authViewModel: AuthViewModel,
    paymentViewModel: PaymentViewModel
) {
    val eventTabs = EventTabType.entries
    var selected by remember { mutableStateOf(0) }
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val fixedHeight = (screenHeight - 122).dp
    val accessToken by authViewModel.accessToken.collectAsState("")
    val refreshToken by authViewModel.refreshToken.collectAsState("")
    val zealId by paymentViewModel.zealId.collectAsState("")
    val eventState by eventsViewModel.events.collectAsState()
    Log.d("DEBUG", accessToken)
    LaunchedEffect(accessToken) {
        Log.d("DEBUG", "LaunchedEffect called")
        Log.d("DEBUG", "$accessToken + $refreshToken")
        if (accessToken.isNotEmpty()) {
            Log.d("DEBUG", "Calling getEvents()")
            Log.d("DEBUG", accessToken)
            eventsViewModel.getEvents(accessToken, "CULTURAL")
        }
    }
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
                    text = "Hey Zealite!",
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
            if(zealId == "Default_init"){
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
                            text = "You cannot enter the Zealicon-25 without a valid Zeal ID.",
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
                        merchListing()
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
                        modifier = Modifier.padding(12.dp, 32.dp, 12.dp)
                            .clickable(
                                enabled = true,
                                indication = rememberRipple(
                                    bounded = true,
                                    color = ButtonRippleColor
                                ),
                                interactionSource = remember { MutableInteractionSource() },
                                role = Role.Button
                            ) {
                                merchListing()
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end=6.dp)
                                .height(175.dp)
                                .weight(1f)
                                .aspectRatio(1f),
                            painter = painterResource(R.drawable.merch_1),
                            contentDescription = "Event Image",
                            contentScale = ContentScale.Fit
                        )

                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 6.dp)
                                .height(175.dp)
                                .aspectRatio(1f)
                                .weight(1f),
                            painter = painterResource(R.drawable.merch_2),
                            contentDescription = "Event Image",
                            contentScale = ContentScale.Fit
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
                            modifier = Modifier.clickable {
                                merchListing()
                            },
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
                HorizontalDivider(
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
                HorizontalDivider(
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
                        text = s.label,
                        onClick = {
                            selected = index
                            Log.d("homeScreenEvents1", selected.toString())
                            Log.d("homeScreenEvents2", s.toString())
                            Log.d("homeScreenEvents3", accessToken.toString())
                            Log.d("homeScreenEvents4", refreshToken.toString())
                            eventsViewModel.getEvents(accessToken.toString(), s.label.uppercase())
                        }
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
            }
            Spacer(modifier = Modifier.height(20.dp))
            when (eventState) {
                is NetworkResult.Error -> {
                    Row(
                        modifier = Modifier
                            .padding(20.dp, 10.dp, 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start

                    ) {
                        Image(
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .size(12.dp),
                            painter = painterResource(id = R.drawable.info),
                            contentDescription = "trophy",
                            alignment = Alignment.Center,
                        )

                        Text(
                            text = eventState.message.toString(),
                            fontSize = 14.sp,
                            fontFamily = Outfit,
                            fontWeight = FontWeight.Normal,
                            color = ErrorTextColor
                        )
                    }
                }

                is NetworkResult.Loading -> {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp, 10.dp, 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(32.dp),
                            color = TicketCardBackgroundColor
                        )
                    }
                }

                is NetworkResult.Success -> {
                    Log.d("Event","success state")
                    val eventList = eventState.data?.events
                    Log.d("Event",eventList.toString())

                    Log.d("Event",eventList.toString())
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .height(fixedHeight)
                    ) {
                        eventList?.let {
                            items(it.size) { index ->
                                val event = eventList[index]
                                eventsViewModel.setEventList(eventList)
                                EventGridItem(
                                    event = event,
                                    onClick = {
                                        eventsViewModel.setSelectedEvent(event)
                                        eventDetails()
                                    }
                                )
                            }
                        }

                        item {
                            Spacer(modifier = Modifier.height(130.dp))
                        }
                    }
                }

                is NetworkResult.Start<*> -> {}
            }

        }
        if(zealId != "Default_init"){
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
}

enum class EventTabType(val label: String) {
    CULTURAL("Cultural"),
    TECHNICAL("Technical"),
    REGISTERED("Registered")
}