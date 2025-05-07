package com.gdg.zealicon2k25.presentation.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.gdg.zealicon2k25.R
import com.gdg.zealicon2k25.data.models.OtpRequest
import com.gdg.zealicon2k25.presentation.ui.components.PrimaryButton
import com.gdg.zealicon2k25.presentation.ui.theme.BackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.FrontSpring
import com.gdg.zealicon2k25.presentation.ui.theme.HeadingTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.Outfit
import com.gdg.zealicon2k25.presentation.ui.theme.PhotoBorderColor
import com.gdg.zealicon2k25.presentation.ui.theme.TextFieldBackgroundColor
import com.gdg.zealicon2k25.utils.Common.isValidEmail

@Composable
@Preview
fun PhotoUploadScreen() {
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
                    text = "Upload Pictures",
                    fontSize = 30.sp,
                    color = HeadingTextColor,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FrontSpring,
                    lineHeight = 1.5.em
                )
                Text(
                    modifier = Modifier.padding(top = 24.dp, start = 20.dp),
                    text = "Upload your ID Card",
                    fontSize = 18.sp,
                    color = HeadingTextColor,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = Outfit
                )
                Box(
                    modifier = Modifier
                        .padding(start = 20.dp, top = 5.dp, end = 20.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .height(161.dp)
                        .background(color = TextFieldBackgroundColor)
                        .border(
                            width = 2.dp,
                            color = PhotoBorderColor,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .fillMaxWidth()
                ) {
                    Image(
                        modifier = Modifier,
                        painter = painterResource(R.drawable.take_photo_graphic),
                        contentDescription = "graphics",
                        contentScale = ContentScale.Crop
                    )
                }
                Text(
                    modifier = Modifier.padding(top = 24.dp, start = 20.dp),
                    text = "Upload your Selfie",
                    fontSize = 18.sp,
                    color = HeadingTextColor,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = Outfit
                )
                Box(
                    modifier = Modifier
                        .padding(start = 20.dp, top = 5.dp, end = 20.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .height(161.dp)
                        .background(color = TextFieldBackgroundColor)
                        .border(
                            width = 2.dp,
                            color = PhotoBorderColor,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .fillMaxWidth()
                ) {
                    Image(
                        modifier = Modifier,
                        painter = painterResource(R.drawable.take_photo_graphic),
                        contentDescription = "graphics",
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }

        Column(
            modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth()
        ) {
            PrimaryButton(
                text = "Register Now"
            ) {

            }
            Spacer(Modifier.height(52.dp))
        }
    }
}