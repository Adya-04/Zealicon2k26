package com.gdg.zealicon2k25.presentation.ui

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.gdg.zealicon2k25.R
import com.gdg.zealicon2k25.data.models.EnrollEventReq
import com.gdg.zealicon2k25.data.models.Event
import com.gdg.zealicon2k25.presentation.ui.components.CardBackground
import com.gdg.zealicon2k25.presentation.ui.components.PrizeCard
import com.gdg.zealicon2k25.presentation.ui.components.RegisterButton
import com.gdg.zealicon2k25.presentation.ui.components.SecondaryCardBackground
import com.gdg.zealicon2k25.presentation.ui.theme.BackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.ButtonRippleColor
import com.gdg.zealicon2k25.presentation.ui.theme.ErrorTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.HeadingTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.Outfit
import com.gdg.zealicon2k25.presentation.ui.theme.TicketCardBackgroundColor
import com.gdg.zealicon2k25.presentation.ui.viewmodels.AuthViewModel
import com.gdg.zealicon2k25.presentation.ui.viewmodels.EventsViewModel
import com.gdg.zealicon2k25.presentation.ui.viewmodels.PaymentViewModel
import com.gdg.zealicon2k25.utils.Common.formatEventDate
import com.gdg.zealicon2k25.utils.Common.formatEventTime
import com.gdg.zealicon2k25.utils.NetworkResult

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun EventDetailScreen(
    backOnClick: () -> Unit = {},
    eventsViewModel: EventsViewModel ,
    paymentViewModel: PaymentViewModel,
    authViewModel: AuthViewModel
) {
    val context= LocalContext.current
    val accessToken by authViewModel.accessToken.collectAsState("")
    val eventEnrollState by eventsViewModel.enrollEvent.collectAsState()
    val zeal by paymentViewModel.zealId.collectAsState("")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        val eventDetails=eventsViewModel.selectedEvent
        Log.d("selected",eventDetails.toString())
        Column(modifier = Modifier.verticalScroll(rememberScrollState())
            .padding(top=16.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(R.drawable.back_arrow),
                    modifier = Modifier.padding(top = 20.dp, start = 20.dp, bottom = 20.dp)
                        .clickable(
                            enabled = true,
                            indication = rememberRipple(
                                bounded = true,
                                color = ButtonRippleColor
                            ),
                            interactionSource = remember { MutableInteractionSource() },
                            role = Role.Button
                        ) {
                            backOnClick()
                        },
                    contentDescription = "arrow",
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = eventDetails?.title.toString(),
                    fontSize = 28.sp,
                    fontFamily = Outfit,
                    color = HeadingTextColor,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 20.dp, start = 12.dp, bottom = 20.dp)
                )
            }
            AsyncImage(
                model = eventDetails?.image,
                modifier = Modifier.padding(horizontal = 20.dp).fillMaxWidth().height(250.dp),
                contentDescription = "eventImage",
                contentScale = ContentScale.Fit,
                placeholder = painterResource(R.drawable.game_image),
                error = painterResource(R.drawable.game_image)
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(
                    start = 20.dp, end = 20.dp, top = 20.dp, bottom = 8.dp
                ),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                CardBackground(
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                    image = R.drawable.calendar,
                    detail = formatEventDate(eventDetails?.event_start)
                )
                SecondaryCardBackground(
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                    image = R.drawable.clock,
                    details = formatEventTime(eventDetails?.event_start)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                SecondaryCardBackground(
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                    details = eventDetails?.venue.toString(),
                    image = R.drawable.location_marker
                )
                CardBackground(
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                    image = R.drawable.phone,
                    detail = eventDetails?.contact_info?.trim().toString()
                )
            }

            PrizeCard(
                prize = "₹ " + eventDetails?.prize.toString(),
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp, end = 20.dp, start = 20.dp)
            )

            Text(
                text = "About " + eventDetails?.title.toString(),
                fontSize = 24.sp,
                fontFamily = Outfit,
                color = HeadingTextColor,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 24.dp, start = 20.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                )

            Text(
                text = eventDetails?.description.toString(),
                fontSize = 16.sp,
                fontFamily = Outfit,
                color = HeadingTextColor,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(top = 24.dp, start = 20.dp, end = 20.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, bottom = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                RegisterButton(
                    modifier = Modifier.clickable {

                    }
                ) {
                    Log.d("eventRegister1","$zeal")
                    if(zeal!="Default_init"){
                        Log.d("eventRegister2","${eventDetails?._id}")
                        Log.d("eventRegister3","$accessToken")
                        eventsViewModel.enrollEvent(accessToken , EnrollEventReq(eventDetails?._id.toString() , "ENROLL"))
                    }else {
                        Log.d("eventRegister4","zeal not found")
                        Toast.makeText(context, "Buy Zeal Id first", Toast.LENGTH_SHORT).show()
                    }
                }
                when(eventEnrollState){
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
                                text = eventEnrollState.message.toString(),
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

                    is NetworkResult.Start<*> -> {

                    }
                    is NetworkResult.Success<*> -> {
                        val msg=eventEnrollState.data?.message
                        Toast.makeText(context , msg, Toast.LENGTH_SHORT).show()
                        eventsViewModel.removeEventEnrollState()
                    }
                }
            }
        }
    }
}