package com.gdg.zealicon2k25.utils

sealed class UploadState {
    object Idle : UploadState()
    object Loading : UploadState()
    data class Success(val url: String, val publicId: String) : UploadState()
    data class Error(val message: String) : UploadState()
}