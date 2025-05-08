package com.gdg.zealicon2k25.presentation.ui

import android.Manifest
import android.content.ContentValues
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.gdg.zealicon2k25.R
import com.gdg.zealicon2k25.data.models.IdCard
import com.gdg.zealicon2k25.data.models.OtpRequest
import com.gdg.zealicon2k25.data.models.Photo
import com.gdg.zealicon2k25.data.models.SignupRequest
import com.gdg.zealicon2k25.presentation.ui.components.PrimaryButton
import com.gdg.zealicon2k25.presentation.ui.theme.BackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.ButtonRippleColor
import com.gdg.zealicon2k25.presentation.ui.theme.ErrorTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.FrontSpring
import com.gdg.zealicon2k25.presentation.ui.theme.HeadingTextColor
import com.gdg.zealicon2k25.presentation.ui.theme.Outfit
import com.gdg.zealicon2k25.presentation.ui.theme.PhotoBorderColor
import com.gdg.zealicon2k25.presentation.ui.theme.TextFieldBackgroundColor
import com.gdg.zealicon2k25.presentation.ui.theme.TicketCardBackgroundColor
import com.gdg.zealicon2k25.presentation.ui.viewmodels.ImageUploadViewModel
import com.gdg.zealicon2k25.presentation.viewmodels.AuthViewModel
import com.gdg.zealicon2k25.utils.Common.isValidEmail
import com.gdg.zealicon2k25.utils.NetworkResult
import com.gdg.zealicon2k25.utils.UploadState
import kotlinx.coroutines.flow.collect
import org.jetbrains.annotations.Async
import kotlin.math.sign

@Composable
fun PhotoUploadScreen(
    authViewModel: AuthViewModel,
    imageViewModel: ImageUploadViewModel,
    registerOnClick: () -> Unit = {}
) {
    val initToken by authViewModel.initToken.collectAsState("Loading")
    val accessToken by authViewModel.accessToken.collectAsState("Loading")
    val refreshToken by authViewModel.refreshToken.collectAsState("Loading")
    val signCloudFlowPhoto by authViewModel.signCloudinaryFlowPhoto.collectAsState()
    val signCloudFlowId by authViewModel.signCloudinaryFlowId.collectAsState()
    val uploadState by imageViewModel.uploadState.collectAsState()
    var idCardUrl by remember { mutableStateOf("") }
    var selfieUrl by remember { mutableStateOf("") }
    var idPublic by remember { mutableStateOf("") }
    var selfiePublic by remember { mutableStateOf("") }
    var idUploading by remember { mutableStateOf(false) }
    var selfieUploading by remember { mutableStateOf(false) }
    val signupFlow by authViewModel.signupFlow.collectAsState()
    val context = LocalContext.current

    var selfieImageUri by remember { mutableStateOf<Uri?>(null) }
    var idCardUri by remember { mutableStateOf<Uri?>(null) }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val granted = permissions.all { it.value }
        if (!granted) {
            Toast.makeText(context, "Permissions required", Toast.LENGTH_SHORT).show()
        }
    }

    fun createImageUri(): Uri {
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(
                MediaStore.Images.Media.DISPLAY_NAME,
                "captured_image_${System.currentTimeMillis()}"
            )
        }
        return context.contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues
        )!!
    }

    val selfieCapturedUri = remember { mutableStateOf<Uri?>(null) }
    val idCapturedUri = remember { mutableStateOf<Uri?>(null) }

    val selfieCameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        selfieCapturedUri.value?.let { uri ->
            if (success) {
                selfieImageUri = uri
                authViewModel.removeSignCloudinaryState()
                selfieUploading = true
                imageViewModel.uploadToCloudinary(
                    context = context,
                    imageUri = uri,
                    signCloudinaryResponse = signCloudFlowPhoto.data!!
                )
//                onUploadImage(uri, "front")
            }
        }
    }

    val idCameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        idCapturedUri.value?.let { uri ->
            if (success) {
                idCardUri = uri
                authViewModel.removeSignCloudinaryState()
                idUploading = true
                imageViewModel.uploadToCloudinary(
                    context = context,
                    imageUri = uri,
                    signCloudinaryResponse = signCloudFlowId.data!!
                )
//                onUploadImage(uri, "back")
            }
        }
    }


    LaunchedEffect(Unit) {
        cameraPermissionLauncher.launch(
            arrayOf(
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
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
                        .clickable(
                            enabled = true,
                            indication = rememberRipple(
                                bounded = true,
                                color = ButtonRippleColor
                            ),
                            interactionSource = remember { MutableInteractionSource() },
                            role = Role.Button

                        ) {
                            authViewModel.signCloudinaryId(
                                initToken = initToken
                            )
                        }
                        .background(color = TextFieldBackgroundColor)
                        .border(
                            width = 2.dp,
                            color = PhotoBorderColor,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    if (idCardUrl.isEmpty()) {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(161.dp),
                            painter = rememberAsyncImagePainter(
                                model = idCardUri ?: R.drawable.take_photo_graphic
                            ),
                            contentDescription = "graphics",
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        AsyncImage(
                            model = idCardUrl,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(161.dp),
                            contentDescription = "selfie",
                            contentScale = ContentScale.Crop
                        )
                    }

                }
                when (signCloudFlowId) {
                    is NetworkResult.Error -> {
                        Row(
                            modifier = Modifier.padding(20.dp, 10.dp, 20.dp),
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
                                text = signCloudFlowId.message.toString(),
                                fontSize = 12.sp,
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
                                modifier = Modifier.size(32.dp), color = TicketCardBackgroundColor
                            )
                        }
                    }

                    is NetworkResult.Start -> {}
                    is NetworkResult.Success -> {
                        signCloudFlowId.data?.let {
                            val uri = createImageUri()
                            idCapturedUri.value = uri
                            idCameraLauncher.launch(uri)
                        }
                        Log.d("IMAGE", signCloudFlowId.data.toString())
                    }
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
                        .clickable(
                            enabled = true,
                            indication = rememberRipple(
                                bounded = true,
                                color = ButtonRippleColor
                            ),
                            interactionSource = remember { MutableInteractionSource() },
                            role = Role.Button

                        ) {
                            authViewModel.signCloudinaryPhoto(
                                initToken = initToken
                            )
                        }
                        .background(color = TextFieldBackgroundColor)
                        .border(
                            width = 2.dp,
                            color = PhotoBorderColor,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .fillMaxWidth()
                ) {
                    if (selfieUrl.isEmpty()) {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(161.dp),
                            painter = rememberAsyncImagePainter(
                                model = selfieImageUri ?: R.drawable.take_photo_graphic
                            ),
                            contentDescription = "graphics",
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        AsyncImage(
                            model = selfieUrl,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(161.dp),
                            contentDescription = "selfie",
                            contentScale = ContentScale.Crop
                        )
                    }

                }
                when (uploadState) {
                    is UploadState.Error -> {
                        val error = uploadState as UploadState.Error
                        Row(
                            modifier = Modifier.padding(20.dp, 10.dp, 20.dp),
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
                                text = error.message,
                                fontSize = 12.sp,
                                fontFamily = Outfit,
                                fontWeight = FontWeight.Normal,
                                color = ErrorTextColor
                            )
                        }
                    }

                    is UploadState.Idle -> {}
                    is UploadState.Loading -> {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp, 10.dp, 20.dp, 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(32.dp), color = TicketCardBackgroundColor
                                )
                                Text(
                                    modifier = Modifier.padding(top = 10.dp),
                                    text = "Uploading Image...",
                                    fontSize = 18.sp,
                                    fontFamily = Outfit,
                                    fontWeight = FontWeight.Normal,
                                    color = TicketCardBackgroundColor
                                )
                            }
                        }
                    }

                    is UploadState.Success -> {
                        val success = uploadState as UploadState.Success
                        if (idUploading) {
                            idCardUrl = success.url
                            idPublic = success.publicId
                            idUploading = false
                        } else {
                            selfieUrl = success.url
                            selfiePublic = success.publicId
                            selfieUploading = false
                        }
                        Toast.makeText(context, "Image Uploaded Successfully", Toast.LENGTH_SHORT)
                            .show()
                        Log.d("IMAGE", "${selfiePublic} ${idPublic}")
                        Log.d("IMAGE", "${selfieUrl} ${idCardUrl}")
                        imageViewModel.removeUploadState()
                    }
                }
                when (signCloudFlowPhoto) {
                    is NetworkResult.Error -> {
                        Row(
                            modifier = Modifier.padding(20.dp, 10.dp, 20.dp),
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
                                text = signCloudFlowPhoto.message.toString(),
                                fontSize = 12.sp,
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
                                modifier = Modifier.size(32.dp), color = TicketCardBackgroundColor
                            )
                        }
                    }

                    is NetworkResult.Start -> {}
                    is NetworkResult.Success -> {
                        signCloudFlowPhoto.data?.let {
                            val uri = createImageUri()
                            selfieCapturedUri.value = uri
                            selfieCameraLauncher.launch(uri)
                        }
                        Log.d("IMAGE", signCloudFlowPhoto.data.toString())
                    }
                }
                when(signupFlow){
                    is NetworkResult.Error -> {
                        Row(
                            modifier = Modifier.padding(20.dp, 10.dp, 20.dp, 10.dp),
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
                                text = signupFlow.message.toString(),
                                fontSize = 12.sp,
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
                                modifier = Modifier.size(32.dp), color = TicketCardBackgroundColor
                            )
                        }
                    }
                    is NetworkResult.Start -> {}
                    is NetworkResult.Success -> {
                        registerOnClick()
                        signupFlow.data?.let {
                            authViewModel.saveAccessToken(it.access_token)
                            authViewModel.saveRefreshToken(it.refresh_token)
                        }
                        Log.d("TOKEN", "${accessToken} ${refreshToken}")
                    }
                }
                Spacer(Modifier.height(30.dp))

                PrimaryButton(
                    enabled = idCardUrl.isNotEmpty() && selfieUrl.isNotEmpty(),
                    text = "Register Now"
                ) {
                    authViewModel.signup(
                        SignupRequest(
                            id_card = IdCard(public_id = idPublic, secure_url = idCardUrl),
                            photo = Photo(public_id = selfiePublic, secure_url = selfieUrl),
                            name = authViewModel.getName(),
                            phone = authViewModel.getPhone()
                        ),
                        initToken = initToken
                    )
                }

                Spacer(Modifier.height(50.dp))

            }

        }

    }
}