package com.gdg.zealicon2k25.presentation.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.gdg.zealicon2k25.R
import com.gdg.zealicon2k25.data.models.VerifyOtpReq
import com.gdg.zealicon2k25.presentation.ui.components.OtpInputField
import com.gdg.zealicon2k25.presentation.ui.components.PrimaryButton
import com.gdg.zealicon2k25.presentation.ui.theme.BackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.ErrorTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.FrontSpring
import com.gdg.zealicon2k25.presentation.ui.theme.HeadingTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.Outfit
import com.gdg.zealicon2k25.presentation.ui.theme.TicketCardBackgroundColor
import com.gdg.zealicon2k25.presentation.viewmodels.AuthViewModel
import com.gdg.zealicon2k25.utils.NetworkResult

@Composable
@Preview
fun VerifyOTPScreen(
    verifyToPhoto: () -> Unit = {},
    authViewModel: AuthViewModel,
    verifyToHome: () -> Unit = {},
) {
    var otpValue by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    var isOTPError by remember {
        mutableStateOf(false)
    }
    val verifyOtpState by authViewModel.verifyOtpState.collectAsState()
    val userMail = authViewModel.getMail()
    val context = LocalContext.current
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
                    },
                    isErrorState = isOTPError,
                    modifier = Modifier.padding(start = 20.dp, top = 20.dp, 20.dp)
                        .focusRequester(focusRequester)
                )
                Text(
                    modifier = Modifier.padding(top = 12.dp, end = 20.dp).fillMaxWidth(),
                    text = "Resend OTP",
                    fontSize = 14.sp,
                    color = HeadingTextColor,
                    fontWeight = FontWeight.Medium,
                    fontFamily = Outfit,
                    textDecoration = TextDecoration.Underline,
                    textAlign = TextAlign.End
                )
            }
        }
        Column(
            modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth()
        ) {
            PrimaryButton(
                text = "Verify"
            ) {
                Log.d("otp chalga","${otpValue.toLong()+1}")
                Log.d("otp chalga",userMail.toString())
                authViewModel.verifyOtp(VerifyOtpReq(userMail.toString(), otpValue.toLong()))
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
                            text = "OTP is invalid",
                            fontSize = 12.sp,
                            fontFamily = Outfit,
                            fontWeight = FontWeight.Normal,
                            color = ErrorTextColor
                        )
                    }
                }

                is NetworkResult.Start -> {}
                is NetworkResult.Loading -> {
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
                    if(authViewModel.isLogin){
                        verifyToHome()
                    }else{
                        verifyToPhoto()
                    }
                    verifyOtpState.data?.let {
                        authViewModel.saveToken(it.init_token)
                    }
                }
            }
            Spacer(modifier = Modifier.height(52.dp))
        }
    }
    Spacer(Modifier.height(52.dp))
}

