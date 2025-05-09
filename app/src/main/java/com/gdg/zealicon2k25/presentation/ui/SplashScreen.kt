package com.gdg.zealicon2k25.presentation.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdg.zealicon2k25.R
import com.gdg.zealicon2k25.presentation.ui.theme.BackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.ErrorTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.Outfit
import com.gdg.zealicon2k25.presentation.ui.theme.TicketCardBackgroundColor
import com.gdg.zealicon2k25.presentation.ui.viewmodels.AuthViewModel
import com.gdg.zealicon2k25.utils.NetworkResult
import kotlinx.coroutines.delay

@Composable
@Preview
fun SplashScreen(
    authViewModel: AuthViewModel,
    navigateToHome: () -> Unit = {},
    navigateToWelcome: () -> Unit = {}
){
    val refreshState by authViewModel.refreshTokenState.collectAsState()
    val accessToken by authViewModel.accessToken.collectAsState(initial = "")

    LaunchedEffect(accessToken) {
        Log.d("access_token",accessToken)
        if (accessToken == "Default_init"){
            delay(2000)
            navigateToWelcome()
        }else{
            authViewModel.refreshToken()
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackgroundColor)
    ){
        Image(
            painter = painterResource(R.drawable.welcome_graphic),
            contentDescription = "graphics"
        )

        Image(
            modifier = Modifier.align(Alignment.BottomCenter),
            painter = painterResource(R.drawable.welcome_graphic_2),
            contentDescription = "graphics"
        )

        Image(
            modifier = Modifier
                .padding(bottom = 150.dp)
                .size(250.dp)
                .align(Alignment.Center),
            painter = painterResource(R.drawable.zealicon_logo_final_07),
            contentDescription = "zealicon_logo"
        )
        when(refreshState){
            is NetworkResult.Error -> {
                Row(
                    modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 100.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start

                ) {
                    Image(
                        modifier = Modifier.padding(end = 4.dp).size(25.dp),
                        painter = painterResource(id = R.drawable.clock),
                        contentDescription = "trophy",
                        alignment = Alignment.Center,
                        colorFilter = ColorFilter.tint(BackgroundColor)
                    )

                    Text(
                        modifier = Modifier.clickable {
                            authViewModel.refreshToken()
                        },
                        text = "Loading...",
                        fontSize = 25.sp,
                        fontFamily = Outfit,
                        fontWeight = FontWeight.Normal,
                        color = BackgroundColor,
                    )
                }
            }
            is NetworkResult.Loading -> {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(20.dp, 10.dp, 20.dp, 100.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(45.dp), color = TicketCardBackgroundColor
                    )
                }
            }
            is NetworkResult.Start -> {}
            is NetworkResult.Success -> {
                navigateToHome()
                refreshState.data?.let {
                    authViewModel.saveAccessToken(it.access_token)
                }
                authViewModel.removeRefreshState()
            }
        }
    }
}