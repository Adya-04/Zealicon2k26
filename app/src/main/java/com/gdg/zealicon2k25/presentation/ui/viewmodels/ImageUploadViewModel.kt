package com.gdg.zealicon2k25.presentation.ui.viewmodels

import android.app.Application
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cloudinary.android.MediaManager
import com.cloudinary.android.callback.ErrorInfo
import com.cloudinary.android.callback.UploadCallback
import com.gdg.zealicon2k25.data.models.SignCloudinaryResponse
import com.gdg.zealicon2k25.domain.repository.ImageRepository
import com.gdg.zealicon2k25.utils.FileUtils
import com.gdg.zealicon2k25.utils.UploadState
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageUploadViewModel @Inject constructor(
    private val imageRepository: ImageRepository
): ViewModel() {
    val uploadState: StateFlow<UploadState> get() = imageRepository.uploadState

    fun uploadToCloudinary(
        context: Context,
        imageUri: Uri,
        signCloudinaryResponse: SignCloudinaryResponse,
    ){
        viewModelScope.launch {
            imageRepository.uploadToCloudinary(
                context = context,
                imageUri = imageUri,
                signCloudinaryResponse = signCloudinaryResponse
            )
        }
    }

    fun removeUploadState(){
        imageRepository.removeUploadState()

    }
}