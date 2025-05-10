package com.gdg.zealicon2k25.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdg.zealicon2k25.R
import com.gdg.zealicon2k25.presentation.ui.theme.BackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.ErrorTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.Outfit
import com.gdg.zealicon2k25.presentation.ui.theme.TicketCardBackgroundColor
import com.gdg.zealicon2k25.presentation.ui.viewmodels.AuthViewModel
import com.gdg.zealicon2k25.presentation.ui.viewmodels.PaymentViewModel
import com.gdg.zealicon2k25.utils.NetworkResult

@Composable
fun FetchZealScreen(
    authViewModel: AuthViewModel,
    paymentViewModel: PaymentViewModel,
    verifyToHome: () -> Unit
) {
    val zealIdState by paymentViewModel.getZealState.collectAsState()
    val accessToken by paymentViewModel.accessTokenSet.collectAsState()
    LaunchedEffect(accessToken != "") {
        paymentViewModel.getZealId2(accessToken)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundColor),
        contentAlignment = Alignment.Center
    ){
        when (zealIdState) {
            is NetworkResult.Error -> {
                if(zealIdState.message.toString() == "No payment initiated. Please complete the payment process first."){
                    verifyToHome()
                }else{
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.padding(20.dp, 10.dp, 20.dp),
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
                                text = zealIdState.message.toString(),
                                fontSize = 16.sp,
                                fontFamily = Outfit,
                                fontWeight = FontWeight.Normal,
                                color = ErrorTextColor
                            )
                        }
                        Text(
                            modifier = Modifier.clickable {
                                paymentViewModel.getZealId()
                            }.padding(10.dp),
                            text = "Retry ->",
                            fontSize = 18.sp,
                            fontFamily = Outfit,
                            fontWeight = FontWeight.Normal,
                            color = ErrorTextColor,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                }
            }
            is NetworkResult.Loading -> {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 10.dp, 20.dp, 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(32.dp), color = TicketCardBackgroundColor
                        )
                        Text(
                            modifier = Modifier.padding(top = 10.dp),
                            text = "Authorizing User...",
                            fontSize = 18.sp,
                            fontFamily = Outfit,
                            fontWeight = FontWeight.Normal,
                            color = TicketCardBackgroundColor
                        )
                    }
                }
            }
            is NetworkResult.Start -> {

            }
            is NetworkResult.Success -> {
                zealIdState.data?.let {
                    paymentViewModel.saveZealID(it.zeal_id)
                }
                paymentViewModel.removeGetZealState()
                authViewModel.removeLoginVerifyState()
                verifyToHome()
            }
        }
    }
}