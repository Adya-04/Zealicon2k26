package com.gdg.zealicon2k25.presentation.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints.Companion.fixedHeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdg.zealicon2k25.R
import com.gdg.zealicon2k25.presentation.ui.components.EventGridItem
import com.gdg.zealicon2k25.presentation.ui.components.TeamMemberCard
import com.gdg.zealicon2k25.presentation.ui.theme.BackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.ButtonRippleColor
import com.gdg.zealicon2k25.presentation.ui.theme.HeadingTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.Outfit

@Composable
@Preview

fun ContactUsScreen(
    backOnClick: () -> Unit = {}
){
    val context = LocalContext.current
    val height=LocalConfiguration.current.screenHeightDp
    Box(
        modifier = Modifier.fillMaxSize()
            .background(BackgroundColor)
    ){
        Column(modifier = Modifier.verticalScroll(rememberScrollState())){
            Row(modifier = Modifier.fillMaxWidth()){
                Image(
                    painter = painterResource(R.drawable.back_arrow),
                    contentDescription = "back",
                    modifier = Modifier.padding(top = 20.dp, start = 20.dp, bottom = 20.dp)
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
                    text = "Contact Us",
                    fontSize = 28.sp,
                    fontFamily = Outfit,
                    color = HeadingTextColor,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 20.dp, start = 12.dp, bottom = 20.dp)
                )
            }
            Image(
                painter = painterResource(R.drawable.map),
                contentDescription = "map",
                modifier = Modifier.padding(top = 32.dp , end = 20.dp , start = 20.dp)
                    .height(221.dp).clickable {
                        val url =
                            "https://www.google.com/maps/place/JSS+College/@28.6133052,77.3587192,17.67z/data=!4m6!3m5!1s0x390ce56a04d5c921:0x80d0b2139369bc42!8m2!3d28.6133087!4d77.3606258!16s%2Fg%2F11dzt18qmv?entry=ttu&g_ep=EgoyMDI1MDUwMy4wIKXMDSoASAFQAw%3D%3D"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                    }
            )
            Image(
                painter = painterResource(R.drawable.star),
                contentScale = ContentScale.Crop,
                contentDescription = "star",
                modifier = Modifier.padding(top = 20.dp , end = 19.dp , start = 19.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .height(height.dp)
            ) {
                items(4) {
                    TeamMemberCard()
                }
            }
        }
    }
}