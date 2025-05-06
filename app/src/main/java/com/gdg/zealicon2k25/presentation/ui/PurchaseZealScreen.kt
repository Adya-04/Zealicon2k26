package com.gdg.zealicon2k25.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.gdg.zealicon2k25.R
import com.gdg.zealicon2k25.presentation.ui.components.PaymentErrorBottomSheet
import com.gdg.zealicon2k25.presentation.ui.components.PaymentSuccessBottomSheet
import com.gdg.zealicon2k25.presentation.ui.components.PrimaryButton
import com.gdg.zealicon2k25.presentation.ui.components.SecodaryButton
import com.gdg.zealicon2k25.presentation.ui.theme.BackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.FrontSpring
import com.gdg.zealicon2k25.presentation.ui.theme.HeadingTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.Outfit

@Composable
@Preview
fun PurchaseZealScreen(
    continueWithoutZealOnClick: () -> Unit = {},
    homeOnClick: () -> Unit = {}
) {
    var showSheet by remember {
        mutableStateOf(false)
    }
    if(showSheet){
        PaymentSuccessBottomSheet(
            onDismiss = {
                showSheet = false
            },
            homeOnClick = {
                homeOnClick()
            }
        )
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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
            ) {
                PrimaryButton(
                    text = "Pay Now"
                ) {
                    showSheet = true
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