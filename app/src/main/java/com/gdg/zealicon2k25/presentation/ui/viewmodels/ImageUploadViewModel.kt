package com.gdg.zealicon2k25.presentation.ui.viewmodels

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.gdg.zealicon2k25.data.models.SignCloudinaryResponse
import com.gdg.zealicon2k25.domain.repository.ImageRepository
import com.gdg.zealicon2k25.utils.UploadState
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class ImageUploadViewModel @Inject constructor(
    private val imageRepository: ImageRepository
): ViewModel() {
    val uploadState: StateFlow<UploadState> get() = imageRepository.uploadState

    suspend fun uploadToCloudinary(
        context: Context,
        imageUri: Uri,
        signCloudinaryResponse: SignCloudinaryResponse,
    ){
        imageRepository.uploadToCloudinary(
            context = context,
            imageUri = imageUri,
            signCloudinaryResponse = signCloudinaryResponse
        )
    }
}