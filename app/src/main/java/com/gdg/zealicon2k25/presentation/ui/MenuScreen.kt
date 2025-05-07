package com.gdg.zealicon2k25.presentation.ui

import android.content.Intent
import androidx.compose.ui.platform.LocalContext
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdg.zealicon2k25.R
import com.gdg.zealicon2k25.presentation.ui.components.MenuCard
import com.gdg.zealicon2k25.presentation.ui.components.SecodaryButton
import com.gdg.zealicon2k25.presentation.ui.components.SecondaryMenuCard
import com.gdg.zealicon2k25.presentation.ui.theme.BackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.ButtonRippleColor
import com.gdg.zealicon2k25.presentation.ui.theme.HeadingTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.Outfit

@Composable
@Preview
fun MenuScreen(
    contactUsClick: () -> Unit = {},
    aboutUsClick: () -> Unit = {},
    backOnClick: () -> Unit = {}
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(top = 16.dp)
    ) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(R.drawable.back_arrow),
                    contentDescription = "back",
                    modifier = Modifier
                        .padding(top = 20.dp, start = 20.dp, bottom = 20.dp)
                        .clickable(
                            enabled = true,
                            indication = rememberRipple(
                                bounded = true,
                                color = ButtonRippleColor
                            ),
                            interactionSource = remember { MutableInteractionSource() },
                            role = Role.Button
                        ) {
                            backOnClick()
                        },
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "Menu",
                    fontSize = 28.sp,
                    fontFamily = Outfit,
                    color = HeadingTextColor,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 20.dp, start = 12.dp, bottom = 20.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 20.dp, end = 20.dp, top = 20.dp, bottom = 10.dp
                    ),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                MenuCard(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .height(143.dp),
                    image = R.drawable.about_us_graphic,
                    text = "About Us",
                    vector = R.drawable.info,
                    padding = 54.dp,
                    onClick = {
                        aboutUsClick()
                    }
                )
                MenuCard(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .height(143.dp),
                    image = R.drawable.team_graphic,
                    text = "Team",
                    vector = R.drawable.info,
                    padding = 36.dp,
                    onClick = {

                    }
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 20.dp, end = 20.dp, bottom = 10.dp
                    ),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                MenuCard(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .height(143.dp),

                    image = R.drawable.contact_graphic,
                    text = "Contact Us",
                    vector = R.drawable.contact_us,
                    padding = 49.dp,
                    onClick = {
                        contactUsClick()
                    }
                )
                MenuCard(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .height(143.dp),
                    image = R.drawable.share_graphic,
                    text = "Share App",
                    vector = R.drawable.share,
                    padding = 15.dp,
                    onClick = {
                        val sendIntent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, "Check out the Zealicon 2K25 app! Download it now: https://play.google.com/store/apps/..")
                            type = "text/plain"
                        }
                        val shareIntent = Intent.createChooser(sendIntent, null)
                        context.startActivity(shareIntent)
                    }
                )
            }
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)) {
                SecondaryMenuCard()
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        ) {
            SecodaryButton(
                text = "Log out"
            ) {
            }
        }
    }
}