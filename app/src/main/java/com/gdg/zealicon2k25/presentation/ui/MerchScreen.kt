package com.gdg.zealicon2k25.presentation.ui

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.gdg.zealicon2k25.R
import com.gdg.zealicon2k25.data.models.CheckoutResponse
import com.gdg.zealicon2k25.data.models.MerchCheckoutRequest
import com.gdg.zealicon2k25.data.models.MerchCheckoutResponse
import com.gdg.zealicon2k25.presentation.ui.components.EventTabComponent
import com.gdg.zealicon2k25.presentation.ui.components.MerchPaymentErrorBottomSheet
import com.gdg.zealicon2k25.presentation.ui.components.MerchPaymentSuccessBottomSheet
import com.gdg.zealicon2k25.presentation.ui.components.PrimaryButton
import com.gdg.zealicon2k25.presentation.ui.components.PrimaryTextField
import com.gdg.zealicon2k25.presentation.ui.theme.BackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.ErrorTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.HeadingTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.Outfit
import com.gdg.zealicon2k25.presentation.ui.theme.TicketCardBackgroundColor
import com.gdg.zealicon2k25.presentation.ui.viewmodels.MerchViewModel
import com.gdg.zealicon2k25.presentation.ui.viewmodels.PaymentViewModel
import com.gdg.zealicon2k25.utils.NetworkResult
import com.gdg.zealicon2k25.utils.PaymentState
import com.razorpay.Checkout
import org.json.JSONObject

@Composable
@Preview
fun MerchScreen(
    merchViewModel: MerchViewModel,
    backOnClick: () -> Unit = {},
    paymentViewModel: PaymentViewModel,
    activity: Activity
) {
    val merch = merchViewModel.selectedMerch
    val merchCheckoutState by paymentViewModel.merchCheckoutState.collectAsState()
    val merchPaymentVerifyState by paymentViewModel.merchPaymentVerifyState.collectAsState()
    val paymentBottomSheetState by paymentViewModel.paymentBottomSheetState.collectAsState()
    var quantity by remember { mutableStateOf("1") }

    var size by remember {
        mutableStateOf("S")
    }
    var selected by remember { mutableStateOf(0) }
    when(paymentBottomSheetState){
        PaymentState.Error -> {
            MerchPaymentErrorBottomSheet(
                onDismiss = {
                    paymentViewModel.updatePaymentBottomSheetState(PaymentState.Idle)
                },
                retryOnclick = {
                    paymentViewModel.merchCheckout(
                        MerchCheckoutRequest(
                            merch_id = merch?._id.toString(),
                            size = size,
                            quantity = 1
                        )
                    )
                }
            )
        }
        PaymentState.Idle -> {}
        PaymentState.Success -> {
            MerchPaymentSuccessBottomSheet(
                onDismiss = {
                    paymentViewModel.updatePaymentBottomSheetState(PaymentState.Idle)
                }
            )
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        val context = LocalContext.current

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(R.drawable.back),
                    contentDescription = "back",
                    modifier = Modifier
                        .padding(top = 20.dp, start = 20.dp)
                        .clickable {
                            backOnClick()
                        }
                )
                Text(
                    text = "Buy Merch",
                    fontSize = 28.sp,
                    fontFamily = Outfit,
                    color = HeadingTextColor,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 20.dp, start = 12.dp)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ) {
                AsyncImage(
                    model = merch?.photo,
                    contentDescription = "merch image",
                    modifier = Modifier
                        .height(221.dp)
                        .align(Alignment.Center),
                    contentScale = ContentScale.Fit
                )
            }

            merch?.let {
                if (it.sizes.isNotEmpty()) {

                    Text(
                        text = "Choose your Fit :",
                        fontSize = 24.sp,
                        fontFamily = Outfit,
                        color = HeadingTextColor,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 20.dp, start = 20.dp, bottom = 10.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,

                        ) {
                        Spacer(modifier = Modifier.width(20.dp))

                        it.sizes.forEachIndexed { index, s ->
                            EventTabComponent(
                                text = s,
                                selected = selected == index,
                                onClick = {
                                    selected = index
                                    size = s
                                }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.width(20.dp))
            }

            Text(
                text = "Description :",
                fontSize = 24.sp,
                fontFamily = Outfit,
                color = HeadingTextColor,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 20.dp, start = 20.dp, bottom = 10.dp)
            )
            Text(
                text = merch?.description.toString(),
                fontSize = 16.sp,
                fontFamily = Outfit,
                color = HeadingTextColor,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(start = 20.dp, end = 20.dp)
            )
            Text(
                text = "Quantity :",
                fontSize = 24.sp,
                fontFamily = Outfit,
                color = HeadingTextColor,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 20.dp, start = 20.dp, bottom = 10.dp)
            )
            PrimaryTextField(
                value = quantity,
                placeholder = "Enter the quantity",
                onValueChange = {
                    quantity = it
                },
                keyBoardType = KeyboardType.Number
            )
            Text(
                text = "Help and Support :",
                fontSize = 24.sp,
                fontFamily = Outfit,
                color = HeadingTextColor,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 20.dp, start = 20.dp, bottom = 10.dp)
            )
            Text(
                text = "For any Query related to the T-shirt, contact Merchandise Head - ",
                fontSize = 16.sp,
                fontFamily = Outfit,
                color = HeadingTextColor,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(start = 20.dp, end = 20.dp)
            )
            Text(
                text = "Shreyansh Chaurasiya  :  89485 58104",
                fontSize = 16.sp,
                fontFamily = Outfit,
                color = HeadingTextColor,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 4.dp, bottom = 20.dp)
            )
            Spacer(modifier = Modifier.height(100.dp))
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        ){
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
            ) {
                when(merchCheckoutState){
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
                                text = merchCheckoutState.message.toString(),
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
                    is NetworkResult.Start -> {

                    }
                    is NetworkResult.Success -> {
                        paymentViewModel.updateMerchPaymentState(true)
                        merchCheckoutState.data?.let {
                            LaunchedEffect(it) {
                                startRazorpayPayment(activity, it)
                            }
                        }
                    }
                }
                when(merchPaymentVerifyState){
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
                                text = merchPaymentVerifyState.message.toString(),
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
                    is NetworkResult.Start -> {

                    }
                    is NetworkResult.Success -> {
                        Toast.makeText(context, "Payment Verified Successfully", Toast.LENGTH_SHORT).show()
                        paymentViewModel.removePaymentVerifyState()
                    }
                }
                PrimaryButton(
                    text = "Register Your Order",
                ) {
                    merch?.let {
                        if(it.sizes.isEmpty()){
                            paymentViewModel.merchCheckout(
                                MerchCheckoutRequest(
                                    merch_id = it._id,
                                    size = "",
                                    quantity = quantity.toInt()
                                )
                            )
                        }else{
                            paymentViewModel.merchCheckout(
                                MerchCheckoutRequest(
                                    merch_id = it._id,
                                    size = size,
                                    quantity = quantity.toInt()
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun startRazorpayPayment(
    activity: Activity,
    merchCheckoutResponse: MerchCheckoutResponse
) {
    val checkout = Checkout()
    checkout.setKeyID("rzp_live_QhZ5hdVS97ySIU")

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
        option.put("order_id", merchCheckoutResponse.order.id)

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