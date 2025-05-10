package com.gdg.zealicon2k25.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdg.zealicon2k25.R
import com.gdg.zealicon2k25.data.models.TeamMember
import com.gdg.zealicon2k25.presentation.ui.components.SecondaryTeamMemberCard
import com.gdg.zealicon2k25.presentation.ui.theme.BackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.ButtonRippleColor
import com.gdg.zealicon2k25.presentation.ui.theme.HeadingTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.Outfit

@Composable
@Preview
fun TeamScreen(
    backOnClick: () -> Unit = {}
) {
    val totalHeight = LocalConfiguration.current.screenHeightDp

    val teamMembers = listOf(
        TeamMember(
            name = "Deepanshu",
            role = "Technical Head",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/Deepanshu_tech_head.jpg?alt=media&token=502d93ad-31bd-4f21-bb2e-8e95c9db2c38"
        ),
        TeamMember(
            name = "Sri Krishn",
            role = "Finance Head",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/Sri%20Krishn%20Pandey_Finance%20Head.jpg?alt=media&token=1ea81613-26a2-4a13-af89-a5aa73e0661a"
        ),
        TeamMember(
            name = "Shivansh",
            role = "Co Fest Secretary",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/Shivansh%20Pathak%20Co%20Fest%20Sec.jpg?alt=media&token=9fa0cf2a-72fb-483f-aa96-d86938064139"
        ),
        TeamMember(
            name = "Yash",
            role = "Merchandise",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/yash_shekhar__merchandsie%20head.jpg?alt=media&token=b4f540ff-0530-410f-9640-3cac049eaedc"
        ),
        TeamMember(
            name = "Mridul",
            role = "Creative Head",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/Mridul%20Tiwari%20-%20OORJA%20-%20Creative%20Head.jpg?alt=media&token=1d3e5cf8-ceb8-40fb-a91d-33a518679b78"
        ),
        TeamMember(
            name = "Shreyansh",
            role = "Committee Member",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/ShreyanshChaurasia.jpg?alt=media&token=4cc19b3f-cfef-4ffd-a237-c6a2cb3bfd16"
        ),
        TeamMember(
            name = "Taran",
            role = "Sponsorship Head",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/Taran_Sponsership%20head.heic?alt=media&token=f5daa012-bf6d-4057-985b-783c1dd4c6ce"
        ),
        TeamMember(
            name = "Akash",
            role = "Security Head",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/Akash%20Goel%20-%20Security%20head%20.jpg?alt=media&token=1739f01b-254f-45d8-b537-09022613c75a"
        ),
        TeamMember(
            name = "Keshav",
            role = "Decoration Head",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/Keshav%20Bajpai%20Decoration%20Head.jpg?alt=media&token=7f65119a-fc75-4df9-b9e8-ac977554679e"
        ),
        TeamMember(
            name = "Ashutosh",
            role = "Sponsorship Head",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/IMG_20250429_025153.jpg?alt=media&token=b930e91a-1fc4-44f3-8a16-eb55ffea7313"
        ), TeamMember(
            name = "Lakshya",
            role = "Resource Head",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/Lakshya%20Kumar%20Verma%20-%20Resource%20Head.jpg?alt=media&token=f8437836-37a2-4d2a-9182-6ca35096db06"
        ), TeamMember(
            name = "Gaurav",
            role = "Creative Head",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/Gaurav%20verma%20-%20Creative%20head.jpg?alt=media&token=479bb231-e74a-4aad-a388-1c9a9a883a4b"
        ), TeamMember(
            name = "Shikhar",
            role = "Management Head",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/Shikhar%20Chaudhary%E2%80%A6Management%20head.heic?alt=media&token=7124dd8f-51d8-4b29-bfaf-5d3e138610c7"
        ),
        TeamMember(
            name = "Shreyansh",
            role = "Cultural Head",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/Shreyansh%20Dubey_Cultural%20Head.jpg?alt=media&token=e40dfaf2-1660-4387-9790-176b46e1309e"
        ),
        TeamMember(
            name = "Chandrakamal",
            role = "Artish Head",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/Chandrakamal%20Singh%20Artish%20Head.heif?alt=media&token=6ea04bbf-99f9-479d-bd6f-a42a45a3214e"
        ),
        TeamMember(
            name = "Abhidhamma",
            role = "Stage and Production Head",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/Abhidhamma%20Sheel_Stage%20and%20Production%20Head.jpg?alt=media&token=d76db82f-e17d-40c0-a568-449ddfee4305"
        ),
        TeamMember(
            name = "Avishkar",
            role = "Technical Head",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/avishkar_arjan_technical_head.jpeg?alt=media&token=3f5dd63b-46a9-4d07-ac88-f95782f401ea"
        ),
        TeamMember(
            name = "Anshul",
            role = "Committee Member",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/Anshul%20Mittal%20.jpg?alt=media&token=66660dd8-7c04-4ec8-b20c-406e0502c87a"
        ),
        TeamMember(
            name = "Sachin",
            role = "IEEE",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/Sachin%20Jha%20-IEEE.jpg?alt=media&token=014dafe5-380c-482e-a9dd-e6843ff814b8"
        )
    )
    val techTeam = listOf(
        TeamMember(
            name = "Ayush",
            role = "Android Dev",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/WhatsApp%20Image%202025-04-25%20at%2022.29.03.jpeg?alt=media&token=c3454bf0-0f18-4ed7-827d-ead01b11fd34"
        ),
        TeamMember(
            name = "Kanak",
            role = "Android Dev",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/1632571792402.jpg?alt=media&token=a830f46a-33fb-4e36-9643-0be0fe932bb7"
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Column (
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(R.drawable.back_arrow),
                    contentDescription = "back",
                    modifier = Modifier
                        .padding(top = 20.dp, start = 20.dp, bottom = 20.dp)
                        .size(32.dp)
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
                    text = "Team",
                    fontSize = 28.sp,
                    fontFamily = Outfit,
                    color = HeadingTextColor,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 20.dp, start = 12.dp, bottom = 20.dp)
                )
            }
            Text(
                text = "Management Team",
                fontSize = 24.sp,
                fontFamily = Outfit,
                color = HeadingTextColor,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 20.dp, start = 20.dp, bottom = 20.dp)
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, bottom = 32.dp)
//                    .height(height = totalHeight.dp)
            ) {
                items(teamMembers) { member ->
                    SecondaryTeamMemberCard(
                        imageUrl = member.imageUrl,
                        name = member.name,
                        role = member.role
                    )
                }
            }
            Text(
                text = "Technical Team",
                fontSize = 24.sp,
                fontFamily = Outfit,
                color = HeadingTextColor,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 20.dp, start = 20.dp, bottom = 20.dp)
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, bottom = 32.dp)
//                    .height(height = )
            ) {
                items(techTeam) { member ->
                    SecondaryTeamMemberCard(
                        imageUrl = member.imageUrl,
                        name = member.name,
                        role = member.role
                    )
                }
            }
        }
    }
}