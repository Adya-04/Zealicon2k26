package com.gdg.zealicon2k25.presentation.ui.viewmodels

import android.app.Application
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cloudinary.android.MediaManager
import com.cloudinary.android.callback.ErrorInfo
import com.cloudinary.android.callback.UploadCallback
import com.gdg.zealicon2k25.utils.FileUtils
import com.gdg.zealicon2k25.utils.UploadState
import dagger.hilt.android.internal.Contexts.getApplication
import javax.inject.Inject

class ImageUploadViewModel @Inject constructor(
    application: Application
): ViewModel() {
    private val _uploadState = mutableStateOf<UploadState>(UploadState.Idle)
    val uploadState: State<UploadState> = _uploadState

    fun uploadImage(fileUri: Uri, context: Context) {
        _uploadState.value = UploadState.Loading

        val filePath = FileUtils.getPath(context, fileUri) // See FileUtils below

        MediaManager.get().upload(filePath)
            .callback(object : UploadCallback {
                override fun onStart(requestId: String) {}

                override fun onProgress(requestId: String, bytes: Long, totalBytes: Long) {}

                override fun onSuccess(requestId: String, resultData: Map<*, *>) {
                    Log.d("TAG", resultData.toString())
                    val secureUrl = resultData["secure_url"] as? String ?: ""
                    val publicId = resultData["public_id"] as? String ?: ""
                    _uploadState.value = UploadState.Success(secureUrl, publicId)
                }

                override fun onError(requestId: String, error: ErrorInfo) {
                    _uploadState.value = UploadState.Error(error.description)
                }

                override fun onReschedule(requestId: String, error: ErrorInfo) {
                    _uploadState.value = UploadState.Error("Rescheduled: ${error.description}")
                }
            })
            .dispatch()
    }
}