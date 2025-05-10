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
            role = "Merchandise Head",
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
            role = "Production Head",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/Abhidhamma%20Sheel_Stage%20and%20Production%20Head.jpg?alt=media&token=d76db82f-e17d-40c0-a568-449ddfee4305"
        ),
        TeamMember(
            name = "Avishkar",
            role = "Technical Head",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/avishkar_arjan_technical_head.jpeg?alt=media&token=3f5dd63b-46a9-4d07-ac88-f95782f401ea"
        ),
        TeamMember(
            name = "Anshul",
            role = "Marketing Head",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/Anshul%20Mittal%20.jpg?alt=media&token=66660dd8-7c04-4ec8-b20c-406e0502c87a"
        ),
        TeamMember(
            name = "Sachin",
            role = "IEEE",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/Sachin%20Jha%20-IEEE.jpg?alt=media&token=014dafe5-380c-482e-a9dd-e6843ff814b8"
        ),
        TeamMember(
            name = "Kartikey",
            role = "Member Secretary",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/KumarKartikeyPandey_Member_Sec.jpg?alt=media&token=f5d00c52-435b-452e-90b4-78ab83f59bf7"
        ),
        TeamMember(
            name = "Harsh",
            role = "Media Head",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/harsh%20madan-media%20head.jpg?alt=media&token=d3cd3b33-7375-4b05-90a6-05679bbf0b54"
        ),
        TeamMember(
            name = "Nazeel",
            role = "Management Head",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/nazeel%20farooqui-management%20head.jpg?alt=media&token=852fab6f-2f9c-4383-a5b5-c4c5cf8f4ed9"
        ),
        TeamMember(
            name = "Ayush",
            role = "Android Developer",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/20250510_191309.jpg?alt=media&token=b4e23d9b-a6ff-44a6-94ff-502da146a9b9"
        ),
        TeamMember(
            name = "Kanak",
            role = "Android Developer",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/WhatsApp%20Image%202024-12-25%20at%2011.31.43.jpeg?alt=media&token=477bad72-78e2-4b6f-bd04-2e87ff24b545"
        ),
        TeamMember(
            name = "Sparsh",
            role = "Designer",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/WhatsApp%20Image%202025-05-10%20at%2019.15.25.jpeg?alt=media&token=b6d9b202-5367-4a41-bb83-777bb14f4e1b"
        ),
        TeamMember(
            name = "Ramit",
            role = "Web Developer",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/WhatsApp%20Image%202025-05-10%20at%2017.24.22.jpeg?alt=media&token=760adaba-ac82-45d2-af50-806aff2bfbd2"
        ),
        TeamMember(
            name = "Parth",
            role = "Web Developer",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/IMG_20250218_005240~2.jpg?alt=media&token=82d46d85-f846-421b-bd8f-cbe98bb9a371"
        ),
        TeamMember(
            name = "Muskan",
            role = "Web Developer",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/Muskan.jpg?alt=media&token=c113d82b-a7e1-4582-b23b-4ce9ef15fdd0"
        ),
        TeamMember(
            name = "Harsh",
            role = "Designer",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/WhatsApp%20Image%202025-05-10%20at%2017.26.17.jpeg?alt=media&token=a1594354-6085-4ba7-b0b8-e3cb542a96d6"
        ),
        TeamMember(
            name = "Anushka",
            role = "Designer",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/Anushka.jpeg?alt=media&token=e44d6262-812d-456e-958c-d5edf83270c8"
        ),
        TeamMember(
            name = "Insha",
            role = "Designer",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/Insha.jpeg?alt=media&token=3ebcc2b5-9c2f-46cb-9176-3bd1cbad2f14"
        ),
        TeamMember(
            name = "Unnat",
            role = "Web Developer",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/WhatsApp%20Image%202025-05-10%20at%2019.16.31.jpeg?alt=media&token=cce5fb54-312d-4832-8010-08374f6d7b4a"
        ),
        TeamMember(
            name = "Rohit",
            role = "Web Developer",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/Rohit%20Pandey.jpg?alt=media&token=ec921da3-48b2-4fec-b832-acf806749319"
        ),
        TeamMember(
            name = "Amish",
            role = "Web Developer",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/Amish.jpeg?alt=media&token=ee9ed18e-64d2-4c4f-83b1-fac480b4f1e8"
        ),
        TeamMember(
            name = "Devansh",
            role = "Web Developer",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/Devansh.jpeg?alt=media&token=8edac6de-3943-4bba-bc9d-eb5cb3b5fdb2"
        ),
        TeamMember(
            name = "Krati",
            role = "Web Developer",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/Krati.jpg?alt=media&token=0566e003-cc46-4ec0-ba0a-145cb06bc68c"
        ),
        TeamMember(
            name = "Pulak",
            role = "Web Developer",
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/zealicon-2k24.appspot.com/o/Pulak%20Jakhmola.jpg?alt=media&token=7003ba02-0a67-46c7-bd35-f426c5e16903"
        ),
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Column(
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
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, bottom = 32.dp)
                    .height(height = totalHeight.dp)
            ) {
                items(teamMembers) { member ->
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