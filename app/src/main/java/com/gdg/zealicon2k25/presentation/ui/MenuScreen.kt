package com.gdg.zealicon2k25.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdg.zealicon2k25.R
import com.gdg.zealicon2k25.presentation.ui.components.MenuCard
import com.gdg.zealicon2k25.presentation.ui.components.SecodaryButton
import com.gdg.zealicon2k25.presentation.ui.components.SecondaryMenuCard
import com.gdg.zealicon2k25.presentation.ui.theme.BackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.HeadingTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.Outfit

@Composable
@Preview

fun MenuScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(BackgroundColor)
            .padding(top = 16.dp)
    ) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(R.drawable.back_arrow),
                    contentDescription = "back",
                    modifier = Modifier.padding(top = 20.dp, start = 20.dp, bottom = 20.dp),
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
                    modifier = Modifier.weight(1f).fillMaxWidth()
                        .height(143.dp),
                    image = R.drawable.about_us_graphic,
                    text = "About Us",
                    vector = R.drawable.info,
                    padding = 54.dp
                )
                MenuCard(
                    modifier = Modifier.weight(1f).fillMaxWidth()
                        .height(143.dp),
                    image = R.drawable.team_graphic,
                    text = "Team",
                    vector = R.drawable.info,
                    padding = 36.dp
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(
                    start = 20.dp, end = 20.dp, bottom = 10.dp
                ),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                MenuCard(
                    modifier = Modifier.weight(1f)
                        .fillMaxWidth()
                        .height(143.dp),

                    image = R.drawable.contact_graphic,
                    text = "Contact Us",
                    vector = R.drawable.contact_us,
                    padding = 49.dp
                )
                MenuCard(
                    modifier = Modifier.weight(1f)
                        .fillMaxWidth()
                        .height(143.dp),
                    image = R.drawable.share_graphic,
                    text = "Share App",
                    vector = R.drawable.share,
                    padding = 15.dp
                )
            }
            Row (modifier = Modifier.fillMaxWidth()
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