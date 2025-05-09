package com.gdg.zealicon2k25.presentation.ui

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gdg.zealicon2k25.MainActivity
import com.gdg.zealicon2k25.R
import com.gdg.zealicon2k25.data.models.CheckoutResponse
import com.gdg.zealicon2k25.lockOrientationPortrait
import com.gdg.zealicon2k25.presentation.ui.components.PaymentErrorBottomSheet
import com.gdg.zealicon2k25.presentation.ui.components.PaymentSuccessBottomSheet
import com.gdg.zealicon2k25.presentation.ui.components.PrimaryButton
import com.gdg.zealicon2k25.presentation.ui.components.SecodaryButton
import com.gdg.zealicon2k25.presentation.ui.theme.BackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.ErrorTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.FrontSpring
import com.gdg.zealicon2k25.presentation.ui.theme.HeadingTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.Outfit
import com.gdg.zealicon2k25.presentation.ui.theme.TicketCardBackgroundColor
import com.gdg.zealicon2k25.presentation.ui.viewmodels.PaymentViewModel
import com.gdg.zealicon2k25.utils.NetworkResult
import com.gdg.zealicon2k25.utils.PaymentState
import com.razorpay.Checkout
import org.json.JSONObject

@Composable
@Preview
fun PurchaseZealScreen(
    continueWithoutZealOnClick: () -> Unit = {},
    homeOnClick: () -> Unit = {},
    paymentViewModel: PaymentViewModel,
    activity: Activity
) {
    val checkoutState by paymentViewModel.checkoutState.collectAsState()
    val bottomSheetState by paymentViewModel.bottomSheetState.collectAsState()
    val paymentVerifyState by paymentViewModel.paymentVerifyState.collectAsState()
    val getZealState by paymentViewModel.getZealState.collectAsState()
    val accessToken by paymentViewModel.accessToken.collectAsState(initial = "")
    val zealId by paymentViewModel.zealId.collectAsState(initial = "")
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        activity.lockOrientationPortrait()
    }

    var showErrorSheet by remember {
        mutableStateOf(false)
    }

    var showSuccessSheet by remember {
        mutableStateOf(false)
    }
//    if(showSheet){
//        PaymentSuccessBottomSheet(
//            onDismiss = {
//                showSheet = false
//            },
//            homeOnClick = {
//                homeOnClick()
//            }
//        )
//    }

    when (bottomSheetState) {
        PaymentState.Error -> {
            PaymentErrorBottomSheet(
                onDismiss = {
                    paymentViewModel.updateBottomSheetState(PaymentState.Idle)
                },
                retryOnclick = {
                    paymentViewModel.checkout(accessToken)
                }
            )
        }

        PaymentState.Idle -> {}
        PaymentState.Success -> {
            PaymentSuccessBottomSheet(
                onDismiss = {
                    paymentViewModel.updateBottomSheetState(PaymentState.Idle)
                },
                homeOnClick = {
                    homeOnClick()
                },
                zealId = zealId
            )
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
            Image(
                modifier = Modifier
                    .height(189.dp)
                    .fillMaxWidth(),
                painter = painterResource(R.drawable.purchase_zeal_graphic),
                contentDescription = "graphics",
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.padding(top = 24.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier.padding(start = 20.dp),
                    text = "Verification Successful",
                    fontSize = 14.sp,
                    color = HeadingTextColor,
                    fontWeight = FontWeight.Normal,
                    fontFamily = Outfit
                )
                Text(
                    modifier = Modifier.padding(start = 20.dp, top = 8.dp, end = 20.dp),
                    text = "Purchase your ZEAL ID at just ₹300",
                    fontSize = 30.sp,
                    color = HeadingTextColor,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FrontSpring,
                    lineHeight = 1.5.em
                )
                Text(
                    modifier = Modifier.padding(start = 20.dp, top = 8.dp, end = 20.dp),
                    text = "You need to have a valid ZEAL ID to get the entry in the Zealicon 25",
                    fontSize = 16.sp,
                    color = HeadingTextColor,
                    fontWeight = FontWeight.Normal,
                    fontFamily = Outfit,
                    lineHeight = 1.5.em
                )
            }
        }
        Column(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            when (getZealState) {
                is NetworkResult.Error -> {
                    Row(
                        modifier = Modifier.padding(20.dp, 10.dp, 20.dp, 10.dp),
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
                            text = checkoutState.message.toString(),
                            fontSize = 12.sp,
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
                                text = "Fetching Zeal ID...",
                                fontSize = 18.sp,
                                fontFamily = Outfit,
                                fontWeight = FontWeight.Normal,
                                color = TicketCardBackgroundColor
                            )
                        }
                    }
                }

                is NetworkResult.Start -> {}
                is NetworkResult.Success -> {
                    paymentViewModel.updateBottomSheetState(PaymentState.Success)
                    paymentViewModel.saveZealID(getZealState.data?.zeal_id.toString())
                    paymentViewModel.removeGetZealState()
                }
            }
            when (paymentVerifyState) {
                is NetworkResult.Error -> {
                    Row(
                        modifier = Modifier.padding(20.dp, 10.dp, 20.dp, 10.dp),
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
                            text = checkoutState.message.toString(),
                            fontSize = 12.sp,
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
                                text = "Verifying Payment...",
                                fontSize = 18.sp,
                                fontFamily = Outfit,
                                fontWeight = FontWeight.Normal,
                                color = TicketCardBackgroundColor
                            )
                        }
                    }
                }

                is NetworkResult.Start -> {}
                is NetworkResult.Success -> {
                    paymentViewModel.getZealId()
                    paymentViewModel.removePaymentVerifyState()
                }
            }
            when (checkoutState) {
                is NetworkResult.Error -> {
                    Row(
                        modifier = Modifier.padding(20.dp, 10.dp, 20.dp, 10.dp),
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
                            text = checkoutState.message.toString(),
                            fontSize = 12.sp,
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
                            .padding(20.dp, 10.dp, 20.dp, 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(32.dp), color = TicketCardBackgroundColor
                        )
                    }
                }

                is NetworkResult.Start -> {}
                is NetworkResult.Success -> {
                    checkoutState.data?.let {
                        LaunchedEffect(it) {
                            startRazorpayPayment(activity, it)
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
            ) {
                PrimaryButton(
                    text = "Pay Now"
                ) {
                    Log.d("TOKEN", accessToken)
                    paymentViewModel.checkout(
                        accessToken
                    )
//                    showSheet = true
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 50.dp),
            ) {
                SecodaryButton(
                    text = "Continue without ZEAL ID"
                ) {
                    continueWithoutZealOnClick()
                }
            }
        }
    }
}

private fun startRazorpayPayment(
    activity: Activity,
    checkoutResponse: CheckoutResponse,
) {
    val checkout = Checkout()
    checkout.setKeyID("rzp_live_4yXHLG0FayLua2")

    try {
        val option = JSONObject()
        option.put("name", "Zealicon2K25")
        option.put("currency", "INR")
        option.put(
            "image",
            "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/Zealicon%20LOGO%20Final%2002.png?alt=media&token=777cd971-831d-4c30-9078-b7ba3d95c88b"
        )
        option.put("theme.color", BackgroundColor)
        val methodOBJ = JSONObject()
        val upiOBJ = JSONObject()
        upiOBJ.put("intent", false)
        methodOBJ.put("upi", upiOBJ)
        methodOBJ.put("card", false)
        methodOBJ.put("netbanking", false)
        methodOBJ.put("wallet", false)
        methodOBJ.put("bank_transfer", false)
        methodOBJ.put("paylater", false)
        methodOBJ.put("emi", false)
        option.put("method", methodOBJ)
        option.put("order_id", checkoutResponse.order.id)
        option.put("prefill", JSONObject().apply {
            put("email", checkoutResponse.userDetails.email)
            put("contact", checkoutResponse.userDetails.phone)
        })

//        val options = JSONObject().apply {
//            put("name", "ZEALICON2K25")
//            put("description", "Payment for services")
//            put("order_id", checkoutResponse.order.id)
//            put("currency", "INR")
//            put("amount", 100)
//            put("prefill", JSONObject().apply {
//                put("email", checkoutResponse.userDetails.email)
//                put("contact", checkoutResponse.userDetails.phone)
//            })
//        }

        checkout.open(activity, option)

    } catch (e: Exception) {
        e.printStackTrace()
    }
}