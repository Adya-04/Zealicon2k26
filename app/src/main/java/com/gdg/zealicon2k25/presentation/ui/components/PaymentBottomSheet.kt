package com.gdg.zealicon2k25.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.gdg.zealicon2k25.R
import com.gdg.zealicon2k25.presentation.ui.theme.BackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.ButtonBackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.ErrorTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.FrontSpring
import com.gdg.zealicon2k25.presentation.ui.theme.HeadingTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.Outfit
import com.gdg.zealicon2k25.presentation.ui.theme.SuccessTextColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentSuccessBottomSheet(
    onDismiss: () -> Unit,
    homeOnClick: () -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    ModalBottomSheet(onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = {}
    ) {
        Box (
            modifier = Modifier
                .height(390.dp)
                .fillMaxWidth()
                .background(BackgroundColor)
        ) {
            Image(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .size(160.dp),
                painter = painterResource(R.drawable.payment_graphic),
                contentDescription = "graphics",
                contentScale = ContentScale.Crop
            )
            Image(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(160.dp)
                    .rotate(90f),
                painter = painterResource(R.drawable.payment_graphic),
                contentDescription = "graphics",
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.padding(top = 24.dp),
                    painter = painterResource(R.drawable.payment_success),
                    contentDescription = "payment_success"
                )
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = "Payment Successful",
                    fontSize = 18.sp,
                    color = SuccessTextColor,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = Outfit
                )
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = "Your ZEAL ID is",
                    fontSize = 16.sp,
                    color = HeadingTextColor,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FrontSpring
                )
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = "ZEAL 6969",
                    fontSize = 48.sp,
                    color = HeadingTextColor,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FrontSpring,
                    lineHeight = 1.5.em
                )
            }
            Column (
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
            ) {
                PrimaryButton(
                    text = "Home",
                    arrow = false
                ) {
                    homeOnClick()
                    onDismiss()
                }
                Spacer(Modifier.height(36.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentErrorBottomSheet(
    onDismiss: () -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    ModalBottomSheet(onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = {}
    ) {
        Box(
            modifier = Modifier
                .height(390.dp)
                .fillMaxWidth()
                .background(BackgroundColor)
        ) {
            Image(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .size(160.dp),
                painter = painterResource(R.drawable.payment_graphic_2),
                contentDescription = "graphics",
                contentScale = ContentScale.Crop
            )
            Image(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(160.dp)
                    .rotate(90f),
                painter = painterResource(R.drawable.payment_graphic_2),
                contentDescription = "graphics",
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.padding(top = 24.dp),
                    painter = painterResource(R.drawable.payment_error),
                    contentDescription = "payment_success"
                )
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = "Payment Failed",
                    fontSize = 18.sp,
                    color = ErrorTextColor,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = Outfit
                )
                Text(
                    modifier = Modifier
                        .padding(top = 42.dp, start = 20.dp, end = 20.dp)
                        .fillMaxWidth(),
                    text = "Oops, If your money has been deducted then please contact the team.",
                    fontSize = 16.sp,
                    color = HeadingTextColor,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FrontSpring,
                    textAlign = TextAlign.Center,
                    lineHeight = 1.5.em
                )
            }
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
            ) {
                PrimaryButton(
                    text = "Retry",
                    arrow = false
                ) { }
                Text(
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .fillMaxWidth(),
                    text = "Support",
                    fontSize = 20.sp,
                    color = ButtonBackgroundColor,
                    fontWeight = FontWeight.Black,
                    fontFamily = FrontSpring,
                    textAlign = TextAlign.Center,
                    textDecoration = TextDecoration.Underline
                )
                Spacer(Modifier.height(32.dp))
            }
        }
    }
}