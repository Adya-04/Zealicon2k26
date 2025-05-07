package com.gdg.zealicon2k25.domain.repository

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.State
import com.gdg.zealicon2k25.data.models.SignCloudinaryResponse
import com.gdg.zealicon2k25.data.remote.AuthApi
import com.gdg.zealicon2k25.data.remote.ImageUploadApi
import com.gdg.zealicon2k25.utils.Constants.CLOUDINARY_API_KEY
import com.gdg.zealicon2k25.utils.UploadState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.http.Part
import java.io.File
import javax.inject.Inject

class ImageRepository @Inject constructor(
    private val authApi: AuthApi,
    private val imageApi: ImageUploadApi
) {
    private var _uploadState = MutableStateFlow <UploadState>(UploadState.Idle)
    val uploadState: StateFlow<UploadState> get() = _uploadState


    suspend fun uploadToCloudinary(
        context: Context,
        imageUri: Uri,
        signCloudinaryResponse: SignCloudinaryResponse,
    ): Pair<String, String>? {
        _uploadState.value = UploadState.Loading
        try{
            val file = withContext(Dispatchers.IO) {
                File.createTempFile("upload", ".jpeg", context.cacheDir)
            }
            Log.d("IMAGE", "1")
            context.contentResolver.openInputStream(imageUri)?.use { input ->
                file.outputStream().use { output -> input.copyTo(output) }
            }
            Log.d("IMAGE", "2")

            val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
            val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

            val timestamp = signCloudinaryResponse.timestamp.toString()
            val publicId = signCloudinaryResponse.public_id
            val folder = signCloudinaryResponse.folder
            val signature = signCloudinaryResponse.token
            val apiKey = CLOUDINARY_API_KEY

            Log.d("IMAGE", "3")


            val result = imageApi.uploadToCloudinary(
                file = body,
                apiKey = apiKey.toRequestBody("text/plain".toMediaTypeOrNull()),
                timestamp = timestamp.toRequestBody("text/plain".toMediaTypeOrNull()),
                signature = signature.toRequestBody("text/plain".toMediaTypeOrNull()),
                publicId = publicId.toRequestBody("text/plain".toMediaTypeOrNull()),
                folder = folder.toRequestBody("text/plain".toMediaTypeOrNull())
            )

            Log.d("IMAGE", "4")

            Log.d("IMAGE", result.toString())


            if (result.isSuccessful) {
                Log.d("IMAGE", "5")
                val json = JSONObject(result.body()!!.string())
                val secureUrl = json.getString("secure_url")
                val returnedPublicId = json.getString("public_id")
                _uploadState.value = UploadState.Success(secureUrl, returnedPublicId)
                return secureUrl to returnedPublicId
            }

            Log.d("IMAGE", "6")

            _uploadState.value = UploadState.Error("Something went wrong")
            return null
        }catch (e: Exception){
            _uploadState.value = UploadState.Error(e.message ?: "Unknown error occurred")
            return null
        }
    }

    fun removeUploadState(){
        _uploadState.value = UploadState.Idle
    }
}