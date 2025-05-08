package com.gdg.zealicon2k25.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.gdg.zealicon2k25.R
import com.gdg.zealicon2k25.presentation.ui.components.ZealiconTicket
import com.gdg.zealicon2k25.presentation.ui.theme.BackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.ButtonRippleColor
import com.gdg.zealicon2k25.presentation.ui.theme.HeadingTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.Outfit
import com.gdg.zealicon2k25.presentation.ui.viewmodels.PaymentViewModel
import com.gdg.zealicon2k25.utils.NetworkResult

@Composable
@Preview(heightDp = 2000)
fun EntryPass(
    backOnClick: () -> Unit = {},
    paymentViewModel: PaymentViewModel
) {
    val getZealIdState by paymentViewModel.getZealState.collectAsState()
    LaunchedEffect(Unit) {
        paymentViewModel.getZealId()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor),
    ) {
        when(getZealIdState){
            is NetworkResult.Error -> {

            }
            is NetworkResult.Loading -> {

            }
            is NetworkResult.Start -> {}
            is NetworkResult.Success -> {

                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    Row(
                        modifier = Modifier.padding(start = 20.dp, top = 30.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
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
                                    backOnClick()
                                },
                            painter = painterResource(R.drawable.back),
                            contentDescription = "back"
                        )
                        Text(
                            modifier = Modifier.padding(start = 12.dp),
                            text = "Entry Pass",
                            fontSize = 28.sp,
                            color = HeadingTextColor,
                            fontWeight = FontWeight.Medium,
                            fontFamily = Outfit
                        )
                    }
                    Spacer(modifier = Modifier.height(35.dp))
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        getZealIdState.data?.let {
                            ZealiconTicket(
                                name = it.userDetails.name,
                                id = it.zeal_id
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(48.dp))
                    Image(
                        modifier = Modifier.fillMaxWidth(),
                        painter = painterResource(R.drawable.pass_graphic),
                        contentDescription = "graphic"
                    )
                    Text(
                        modifier = Modifier.padding(top = 24.dp, start = 20.dp),
                        text = "ID Card",
                        fontSize = 18.sp,
                        color = HeadingTextColor,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = Outfit
                    )
                    getZealIdState.data?.let {
                        AsyncImage(
                            model = it.userDetails.id_card,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp, start = 20.dp, end = 20.dp)
                                .height(213.dp),
                            contentDescription = "ID Card",
                            contentScale = ContentScale.Crop,
                            placeholder = painterResource(R.drawable.default_placeholder),
                            error = painterResource(R.drawable.default_placeholder)
                        )
                    }
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp, start = 20.dp, end = 20.dp),
                        text = "In order to gain entry, present your ID to the security personnel and verify that it matches the one displayed.",
                        fontSize = 14.sp,
                        color = HeadingTextColor,
                        fontWeight = FontWeight.Normal,
                        fontFamily = Outfit,
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Image(
                        modifier = Modifier.fillMaxWidth(),
                        painter = painterResource(R.drawable.pass_graphic),
                        contentDescription = "graphic"
                    )
                    Text(
                        modifier = Modifier.padding(top = 24.dp, start = 20.dp),
                        text = "Selfie",
                        fontSize = 18.sp,
                        color = HeadingTextColor,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = Outfit
                    )
                    getZealIdState.data?.let {
                        AsyncImage(
                            model = it.userDetails.id_card,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp, start = 20.dp, end = 20.dp)
                                .height(213.dp),
                            contentDescription = "ID Card",
                            contentScale = ContentScale.Crop,
                            placeholder = painterResource(R.drawable.default_placeholder),
                            error = painterResource(R.drawable.default_placeholder)
                        )
                    }
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp, start = 20.dp, end = 20.dp),
                        text = "You will be matched with the selfie that you have uploaded by the Security at the gate during your entry.",
                        fontSize = 14.sp,
                        color = HeadingTextColor,
                        fontWeight = FontWeight.Normal,
                        fontFamily = Outfit,
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Image(
                        modifier = Modifier.fillMaxWidth(),
                        painter = painterResource(R.drawable.pass_graphic),
                        contentDescription = "graphic"
                    )
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
    }
}