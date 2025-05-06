package com.gdg.zealicon2k25.domain.repository

import com.gdg.zealicon2k25.data.remote.AuthApi
import com.gdg.zealicon2k25.data.remote.ImageUploadApi
import javax.inject.Inject

class ImageRepository @Inject constructor(
    authApi: AuthApi,
    imageApi: ImageUploadApi
) {
    suspend fun uploadToCloudinary(){

    }
}