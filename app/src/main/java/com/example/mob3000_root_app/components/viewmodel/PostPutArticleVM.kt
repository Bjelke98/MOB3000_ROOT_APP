package com.example.mob3000_root_app.components.viewmodel

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.ParcelFileDescriptor
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mob3000_root_app.data.RootService
import com.example.mob3000_root_app.data.apiResponse.ArticleData
import com.example.mob3000_root_app.data.apiResponse.ResponseStatus
import com.example.mob3000_root_app.data.apiResponse.emptyArticleData
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException

class PostPutArticleVM: ViewModel() {


    var focusedArticle by mutableStateOf(emptyArticleData)
    var isNewArticle by mutableStateOf(true)

    fun focusArticle(article: ArticleData){
        focusedArticle = article
    }
    fun newArticle(){
        isNewArticle = true
    }
    fun editArticle(article: ArticleData){
        focusArticle(article)
        isNewArticle = false
    }
    fun updateArticle(title: String, description: String, articleID: String, imageUri: Uri?, context: Context){
        viewModelScope.launch {
            val apiService = RootService.getInstance()

            var file: File? = null

            if(imageUri != null) {
//                Om Android versjon er v.10 eller nyere: Håndter fil på ny måte

//                //Initialiserer fil med gyldig mediatype
                file = File.createTempFile("imagefile", ".jpeg")
                if (Build.VERSION.SDK_INT >= 29) {
                    try {
                        val imagePFD: ParcelFileDescriptor? =
                            context.contentResolver.openFileDescriptor(
                                imageUri, "r"
                            )

                        if (imagePFD != null) {
                            val bitmap = BitmapFactory.decodeFileDescriptor(imagePFD.fileDescriptor)

//                          Skriv bilde inn i filen
                            val byteArrOutStream = ByteArrayOutputStream()
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 30, byteArrOutStream)

                            file.writeBytes(byteArrOutStream.toByteArray())
                        }
                        imagePFD?.close()

                    } catch (ex: IOException) {
                        Log.i("Catch", ex.toString())
                    }
                } else {

                    Log.i("Catch", "Old Version of Android")
//                eventuell håndtering av gamle versjoner
                }
            }

            try {
                val body: MultipartBody.Part?
                if(file != null) {
                    val reqFile: RequestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
                    body = MultipartBody.Part.createFormData("image", file.name, reqFile)
                    Log.i("File == null?", "Not Null")
                }
                else {
                    body = null
                    Log.i("File == null?", "Null")
                }
                val titlePart: RequestBody = title.toRequestBody("text/plain".toMediaTypeOrNull())
                val descriptionPart: RequestBody = description.toRequestBody("text/plain".toMediaTypeOrNull())
                val idPart: RequestBody = articleID.toRequestBody("text/plain".toMediaTypeOrNull())
                val response: ResponseStatus = apiService.updateArticle(titlePart, descriptionPart, idPart, body)
                Log.i("UploadTest", response.toString())
            }
            catch (e: Exception) {
                val errorMessage = e.message.toString()
                Log.i("UploadTest", errorMessage)
            }
        }
    }

    fun postArticle(title: String, description: String, imageUri: Uri?, context: Context) {
        viewModelScope.launch {
            val apiService = RootService.getInstance()
            Log.i("UploadTest", "Start trycatch")

            var file: File? = null

            if(imageUri != null) {
//                Om Android versjon er v.10 eller nyere: Håndter fil på ny måte

//                //Initialiserer fil med gyldig mediatype
                file = File.createTempFile("imagefile", ".jpeg")
                if (Build.VERSION.SDK_INT >= 29) {
                    try {
                        val imagePFD: ParcelFileDescriptor? =
                            context.contentResolver.openFileDescriptor(
                                imageUri, "r"
                            )

                        if (imagePFD != null) {
                            val bitmap = BitmapFactory.decodeFileDescriptor(imagePFD.fileDescriptor)

//                          Skriv bilde inn i filen
                            val byteArrOutStream = ByteArrayOutputStream()
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 30, byteArrOutStream)

                            file.writeBytes(byteArrOutStream.toByteArray())
                        }
                        imagePFD?.close()

                    } catch (ex: IOException) {
                        Log.i("Catch", ex.toString())
                    }
                } else {

                    Log.i("Catch", "Old Version of Android")
//                eventuell håndtering av gamle versjoner
                }
            }

            try {
                val body: MultipartBody.Part?
                if(file != null) {
                    val reqFile: RequestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
                    body = MultipartBody.Part.createFormData("image", file.name, reqFile)
                }
                else {
                    body = null
                }
                val titlePart: RequestBody = title.toRequestBody("text/plain".toMediaTypeOrNull())
                val descriptionPart: RequestBody = description.toRequestBody("text/plain".toMediaTypeOrNull())
                val response: ResponseStatus = apiService.postArticle(titlePart, descriptionPart, body)
                Log.i("UploadTest", response.toString())
            }
            catch (e: Exception) {
                val errorMessage = e.message.toString()
                Log.i("UploadTest", errorMessage)
            }
        }
    }
}