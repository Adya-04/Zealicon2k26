package com.gdg.zealicon2k25.presentation.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gdg.zealicon2k25.R
import com.gdg.zealicon2k25.data.models.OtpRequest
import com.gdg.zealicon2k25.presentation.ui.components.PrimaryButton
import com.gdg.zealicon2k25.presentation.ui.components.PrimaryTextField
import com.gdg.zealicon2k25.presentation.ui.theme.BackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.ErrorTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.FrontSpring
import com.gdg.zealicon2k25.presentation.ui.theme.HeadingTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.Outfit
import com.gdg.zealicon2k25.presentation.ui.theme.PhotoBorderColor
import com.gdg.zealicon2k25.presentation.ui.theme.TextFieldBackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.TicketCardBackgroundColor
import com.gdg.zealicon2k25.presentation.viewmodels.AuthViewModel
import com.gdg.zealicon2k25.utils.Common.isValidEmail
import com.gdg.zealicon2k25.utils.NetworkResult

@Composable
fun RegisterScreen(
    registerOnClick: () -> Unit = {},
    authViewModel: AuthViewModel
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    val context = LocalContext.current
    val otpState by authViewModel.otpResponseLiveData.collectAsState()

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
                painter = painterResource(id = R.drawable.register_graphic),
                contentDescription = "graphics",
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.padding(top = 24.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                    text = "Let’s get you Registered",
                    fontSize = 30.sp,
                    color = HeadingTextColor,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FrontSpring,
                    lineHeight = 1.5.em
                )
                Text(
                    modifier = Modifier.padding(top = 24.dp, start = 20.dp),
                    text = "Name",
                    fontSize = 18.sp,
                    color = HeadingTextColor,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = Outfit
                )
                Spacer(modifier = Modifier.height(5.dp))
                PrimaryTextField(
                    value = name,
                    placeholder = "Enter your Name",
                    onValueChange = { name = it },
                )
                Text(
                    modifier = Modifier.padding(top = 24.dp, start = 20.dp),
                    text = "Email",
                    fontSize = 18.sp,
                    color = HeadingTextColor,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = Outfit
                )
                Spacer(modifier = Modifier.height(5.dp))
                PrimaryTextField(
                    value = email,
                    placeholder = "Enter your Email",
                    onValueChange = { email = it },
                )
                Text(
                    modifier = Modifier.padding(top = 24.dp, start = 20.dp),
                    text = "Phone",
                    fontSize = 18.sp,
                    color = HeadingTextColor,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = Outfit
                )
                Spacer(modifier = Modifier.height(5.dp))
                PrimaryTextField(
                    value = phone,
                    placeholder = "Enter your Phone No.",
                    onValueChange = {
                        if (it.length <= 10) {
                            phone = it
                        }
                    },
                )
                Spacer(modifier = Modifier.height(30.dp))
                PrimaryButton(
                    text = "Send OTP"
                ) {
                    if (email.isBlank()) {
                        Toast.makeText(
                            context,
                            "Email can't be empty",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (!isValidEmail(email)) {
                        Toast.makeText(
                            context,
                            "Please provide a valid mail",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if(name.isBlank()){
                        Toast.makeText(
                            context,
                            "Name can't be empty",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if(phone.length < 10){
                        Toast.makeText(
                            context,
                            "Invalid Phone Number",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Log.d("message" , "email sent is : $email")
                        authViewModel.setmail(email)
                        authViewModel.setName(name)
                        authViewModel.setPhone(phone.toLong())
                        authViewModel.getOtp(OtpRequest(email))
                    }
                }
                when (otpState) {
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
                                text = otpState.message.toString(),
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
                        registerOnClick()
                    }

                    is NetworkResult.Start<*> -> {}
                }
            }
        }
    }
}

@Composable
@Preview
fun Preview(){
    RegisterScreen(
        authViewModel  = hiltViewModel()
    )
}
