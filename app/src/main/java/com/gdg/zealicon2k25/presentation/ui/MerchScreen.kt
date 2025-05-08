package com.gdg.zealicon2k25.presentation.ui

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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.gdg.zealicon2k25.R
import com.gdg.zealicon2k25.presentation.ui.components.EventTabComponent
import com.gdg.zealicon2k25.presentation.ui.components.PrimaryButton
import com.gdg.zealicon2k25.presentation.ui.theme.BackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.HeadingTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.Outfit
import com.gdg.zealicon2k25.presentation.ui.viewmodels.MerchViewModel

@Composable
@Preview
fun MerchScreen(
    merchViewModel: MerchViewModel
) {
    val merch = merchViewModel.selectedMerch
    Box(
        modifier = Modifier.fillMaxSize().background(BackgroundColor)
    ) {
        val context = LocalContext.current
        var selected by remember { mutableStateOf(0) }

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(R.drawable.back),
                    contentDescription = "back",
                    modifier = Modifier.padding(top = 20.dp , start = 20.dp)
                        .clickable {

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


            Text(
                text = "Choose your Fit :",
                fontSize = 24.sp,
                fontFamily = Outfit,
                color = HeadingTextColor,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 20.dp, start = 20.dp, bottom = 10.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Spacer(modifier = Modifier.width(20.dp))
                EventTabComponent(
                    selected == selected, text = "S", onClick = {})

                EventTabComponent(
                    selected == selected, text = "M", onClick = {})

                EventTabComponent(
                    selected == selected, text = "L", onClick = {})

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
            Box{
                Column(
                    modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth()
                        .padding(bottom = 24.dp)
                ) {
                    PrimaryButton(
                        text = "Register Your Order",
                    ) {}
                }
            }
        }
    }
}