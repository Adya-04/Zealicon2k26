package com.gdg.zealicon2k25.presentation.ui

import android.util.Log
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdg.zealicon2k25.R
import com.gdg.zealicon2k25.presentation.ui.components.MerchCard
import com.gdg.zealicon2k25.presentation.ui.theme.BackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.ButtonRippleColor
import com.gdg.zealicon2k25.presentation.ui.theme.ErrorTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.HeadingTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.Outfit
import com.gdg.zealicon2k25.presentation.ui.theme.TicketCardBackgroundColor
import com.gdg.zealicon2k25.presentation.ui.viewmodels.AuthViewModel
import com.gdg.zealicon2k25.presentation.ui.viewmodels.MerchViewModel
import com.gdg.zealicon2k25.utils.NetworkResult

@Preview
@Composable
fun MerchListScreen(authViewModel: AuthViewModel ,
                    merchViewModel: MerchViewModel,
                    merchDetails: () -> Unit = {},
) {
    val totalHeight = LocalConfiguration.current.screenHeightDp
    val accessToken by authViewModel.accessToken.collectAsState("")
    val merchState by merchViewModel.merchState.collectAsState()

    LaunchedEffect(accessToken) {
        Log.d("DEBUG", "LaunchedEffect called")
        if (accessToken.isNotEmpty()) {
            Log.d("DEBUG", "Calling getEvents()")
            Log.d("DEBUG", accessToken)
            merchViewModel.getMerch(accessToken)
        }
    }
    Box(
        modifier = Modifier.fillMaxSize().background(BackgroundColor)
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
                .padding(bottom = 20.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(R.drawable.back_arrow),
                    contentDescription = "back",
                    modifier = Modifier.padding(top = 20.dp, start = 20.dp, bottom = 20.dp)
                        .size(32.dp).clickable(
                            enabled = true,
                            indication = rememberRipple(
                                bounded = true, color = ButtonRippleColor
                            ),
                            interactionSource = remember { MutableInteractionSource() },
                            role = Role.Button
                        ) {},
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "Store",
                    fontSize = 28.sp,
                    fontFamily = Outfit,
                    color = HeadingTextColor,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 20.dp, start = 12.dp, bottom = 20.dp)
                )
            }
            when (merchState) {
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
                            text = merchState.message.toString(),
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
                    val merchList = merchState.data?.merch
                    Log.d("Event",merchList.toString())

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .height(totalHeight.dp)
                    ) {
                        merchList?.let {
                            items(it.size) { index ->
                                val merch = merchList[index]
                                MerchCard(merch , onMerchClick ={
                                    Log.d("MerchClick", "Clicked merch: ${merch.title}")
                                    merchViewModel.setMerch(merch)
                                    merchDetails()
                                } )
                            }
                        }
                    }
                }
                is NetworkResult.Start<*> -> {}
            }
        }
    }
}