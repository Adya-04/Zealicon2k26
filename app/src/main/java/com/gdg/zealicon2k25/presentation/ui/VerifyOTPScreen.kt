package com.gdg.zealicon2k25.presentation.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdg.zealicon2k25.R
import com.gdg.zealicon2k25.data.models.OtpRequest
import com.gdg.zealicon2k25.data.models.VerifyOtpReq
import com.gdg.zealicon2k25.presentation.ui.components.OtpInputField
import com.gdg.zealicon2k25.presentation.ui.components.PrimaryButton
import com.gdg.zealicon2k25.presentation.ui.theme.BackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.ErrorTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.FrontSpring
import com.gdg.zealicon2k25.presentation.ui.theme.HeadingTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.Outfit
import com.gdg.zealicon2k25.presentation.ui.theme.TicketCardBackgroundColor
import com.gdg.zealicon2k25.presentation.ui.viewmodels.AuthViewModel
import com.gdg.zealicon2k25.presentation.ui.viewmodels.PaymentViewModel
import com.gdg.zealicon2k25.utils.NetworkResult

@Composable
@Preview
fun VerifyOTPScreen(
    verifyToPhoto: () -> Unit = {},
    authViewModel: AuthViewModel,
    verifyToHome: () -> Unit = {},
    paymentViewModel: PaymentViewModel
) {
    var otpValue by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    var isOTPError by remember {
        mutableStateOf(false)
    }
    val resendOtpState by authViewModel.resendState.collectAsState()
    val verifyOtpState by authViewModel.verifyOtpState.collectAsState()
    val userMail = authViewModel.getMail()
    val context = LocalContext.current
    var isResendEnabled by remember { mutableStateOf(true) }
    var isVerifyEnabled by remember { mutableStateOf(false) }
    val loginVerifyOtpState by authViewModel.loginVerifyState.collectAsState()
    val getZealDetails by paymentViewModel.getZealState.collectAsState()

    LaunchedEffect(loginVerifyOtpState.data?.access_token.toString()) {
        if (authViewModel.isLogin) {
            val token = loginVerifyOtpState.data?.access_token.toString()
            paymentViewModel.getZealId2(token)
        }
    }
    when (getZealDetails) {
        is NetworkResult.Success -> {
            getZealDetails.data?.let {
                Log.d("zealIdaarhi", it.zeal_id)
                paymentViewModel.saveZealID(it.zeal_id)
            }
            paymentViewModel.removeGetZealState()
            authViewModel.removeLoginVerifyState()
            verifyToHome()
        }

        is NetworkResult.Error -> {
//                verifyToHome()
            Log.d("zealIdError", getZealDetails.data.toString())
            Log.d("zealIdError", getZealDetails.message.toString())
        }

        else -> {}
    }
    Box(
        modifier = Modifier.fillMaxSize().background(BackgroundColor)
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Image(
                modifier = Modifier.height(189.dp).fillMaxWidth(),
                painter = painterResource(R.drawable.otp_graphic),
                contentDescription = "graphics",
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.padding(top = 24.dp), horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier.padding(start = 20.dp),
                    text = "Verify OTP",
                    fontSize = 30.sp,
                    color = HeadingTextColor,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FrontSpring
                )
                Text(
                    modifier = Modifier.padding(top = 24.dp, start = 20.dp),
                    text = "Enter the OTP sent to your email",
                    fontSize = 18.sp,
                    color = HeadingTextColor,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = Outfit
                )
                OtpInputField(
                    otpText = otpValue,
                    onOtpModified = { value, otpFilled ->
                        otpValue = value
                        isVerifyEnabled = otpFilled
                    },
                    isErrorState = isOTPError,
                    modifier = Modifier.padding(start = 20.dp, top = 20.dp, 20.dp)
                        .focusRequester(focusRequester)
                )
                Text(
                    modifier = Modifier
                        .padding(top = 12.dp, end = 20.dp)
                        .fillMaxWidth()
                        .clickable(enabled = isResendEnabled) {
                            authViewModel.resendOtp(OtpRequest(userMail))
                            isResendEnabled = false
                        },
                    text = "Resend OTP",
                    fontSize = 14.sp,
                    color = HeadingTextColor,
                    fontWeight = FontWeight.Medium,
                    fontFamily = Outfit,
                    textDecoration = TextDecoration.Underline,
                    textAlign = TextAlign.End
                )
                when (resendOtpState) {
                    is NetworkResult.Success -> {
                        isResendEnabled = false
                    }

                    is NetworkResult.Error -> {
                        isResendEnabled = true
                    }

                    is NetworkResult.Loading -> {
                        isResendEnabled = false
                    }

                    else -> {}
                }

            }
        }
        Column(
            modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth()
        ) {
            PrimaryButton(
                text = "Verify",
                enabled = isVerifyEnabled
            ) {
                Log.d("otp chalga", "${otpValue.toLong() + 1}")
                Log.d("otp chalga", userMail.toString())
                if (authViewModel.isLogin) {
                    authViewModel.loginVerifyOtp(
                        VerifyOtpReq(
                            userMail.toString(),
                            otpValue.toLong()
                        )
                    )
//                    LaunchedEffect(loginVerifyOtpState.data?.access_token) {
//                        paymentViewModel.getZealId()
//                    }
                } else {
                    authViewModel.verifyOtp(VerifyOtpReq(userMail.toString(), otpValue.toLong()))
                }
            }
            when (verifyOtpState) {
                is NetworkResult.Error -> {

                    Row(
                        modifier = Modifier.padding(20.dp, 10.dp, 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start

                    ) {
                        Image(
                            modifier = Modifier.padding(end = 4.dp).size(12.dp),
                            painter = painterResource(id = R.drawable.info),
                            contentDescription = "trophy",
                            alignment = Alignment.Center,
                        )

                        Text(
                            text = verifyOtpState.message.toString(),
                            fontSize = 14.sp,
                            fontFamily = Outfit,
                            fontWeight = FontWeight.Normal,
                            color = ErrorTextColor
                        )
                    }
                }

                is NetworkResult.Start -> {}
                is NetworkResult.Loading -> {
                    isVerifyEnabled = false
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(20.dp, 10.dp, 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(32.dp), color = TicketCardBackgroundColor
                        )
                    }
                }

                is NetworkResult.Success -> {
                    verifyOtpState.data?.let {
                        authViewModel.saveToken(it.init_token)
                    }
                    verifyToPhoto()
                    Log.d("OtpVerify", verifyOtpState.data.toString())
                }
            }
            if (loginVerifyOtpState is NetworkResult.Error) {
                Row(
                    modifier = Modifier.padding(20.dp, 10.dp, 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Image(
                        modifier = Modifier.padding(end = 4.dp).size(12.dp),
                        painter = painterResource(id = R.drawable.info),
                        contentDescription = "trophy"
                    )
                    Text(
                        text = "Try Resending the OTP",
                        fontSize = 14.sp,
                        fontFamily = Outfit,
                        fontWeight = FontWeight.Normal,
                        color = ErrorTextColor
                    )
                }
            }

            when (loginVerifyOtpState) {
                is NetworkResult.Error -> {

                    Row(
                        modifier = Modifier.padding(20.dp, 10.dp, 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start

                    ) {
                        Image(
                            modifier = Modifier.padding(end = 4.dp).size(12.dp),
                            painter = painterResource(id = R.drawable.info),
                            contentDescription = "trophy",
                            alignment = Alignment.Center,
                        )

                        Text(
                            text = verifyOtpState.message.toString(),
                            fontSize = 14.sp,
                            fontFamily = Outfit,
                            fontWeight = FontWeight.Normal,
                            color = ErrorTextColor
                        )
                    }
                }

                is NetworkResult.Start -> {}
                is NetworkResult.Loading -> {
                    isVerifyEnabled = false
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(20.dp, 10.dp, 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(32.dp), color = TicketCardBackgroundColor
                        )
                    }
                }

                is NetworkResult.Success -> {
                    Log.d("access_token", loginVerifyOtpState.data?.refresh_token.toString())
                    Log.d("refresh_token", loginVerifyOtpState.data?.access_token.toString())
                    authViewModel.saveAccessToken(loginVerifyOtpState.data?.access_token.toString())
                    authViewModel.saveRefreshToken(loginVerifyOtpState.data?.refresh_token.toString())
                    Toast.makeText(
                        context,
                        "${loginVerifyOtpState.data?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            Spacer(modifier = Modifier.height(52.dp))
        }
    }
}

