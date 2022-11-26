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
import com.example.mob3000_root_app.data.apiResponse.EventData
import com.example.mob3000_root_app.data.apiResponse.ResponseStatus
import com.example.mob3000_root_app.data.apiResponse.emptyEventData
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.time.OffsetDateTime

class PostPutEventVM: ViewModel() {


    var focusedEvent by mutableStateOf(emptyEventData)
    var isNewEvent by mutableStateOf(true)

    fun focusEvent(focusEvent: EventData){
        focusedEvent = focusEvent
    }

    fun newEvent(){
        isNewEvent = true
    }

    fun editEvent(eventData: EventData){
        focusEvent(eventData)
        isNewEvent = false
    }

    fun updateEvent(title: String, description: String, dateFrom: OffsetDateTime, dateTo: OffsetDateTime, eventID: String, imageUri: Uri?, context: Context, cb: (status: ResponseStatus?)->Unit){
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
                        Log.i("Open FileDescriptor", "Opening..")
                        val imagePFD: ParcelFileDescriptor? =
                            context.contentResolver.openFileDescriptor(
                                imageUri, "r"
                            )
                        Log.i("Open FileDescriptor", "Done..")

                        if (imagePFD != null) {
                            Log.i("Attemt DecoideFileDesc", "Decoding..")
                            val bitmap = BitmapFactory.decodeFileDescriptor(imagePFD.fileDescriptor)
                            Log.i("Attemt DecoideFileDesc", "Done")

//                          Skriv bilde inn i filen
                            val byteArrOutStream = ByteArrayOutputStream()
                            Log.i("Attemt Bitmap Compress", "Compressing..")
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 30, byteArrOutStream)
                            Log.i("Attemt Bitmap Compress", "Done..")

                            Log.i("Attemt write", "writing..")
                            file.writeBytes(byteArrOutStream.toByteArray())
                            Log.i("Attemt write", "done")
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
                    val reqFile: RequestBody = file.asRequestBody("image/*".toMediaTypeOrNull()) as RequestBody
                    body = MultipartBody.Part.createFormData("image", file.name, reqFile)
                    Log.i("File == null?", "Not Null")
                }
                else {
                    body = null
                    Log.i("File == null?", "Null")
                }
                val eventIDPart: RequestBody = eventID.toRequestBody("text/plain".toMediaTypeOrNull())
                val titlePart: RequestBody = title.toRequestBody("text/plain".toMediaTypeOrNull())
                val descriptionPart: RequestBody = description.toRequestBody("text/plain".toMediaTypeOrNull())
                val dateFromPart: RequestBody = dateFrom.toString().toRequestBody("text/plain".toMediaTypeOrNull())
                val dateToPart: RequestBody = dateTo.toString().toRequestBody("text/plain".toMediaTypeOrNull())
                val response: ResponseStatus = apiService.updateEvent(eventIDPart, titlePart, descriptionPart, dateFromPart, dateToPart, body)
                Log.i("UploadTest", response.toString())
                cb.invoke(response)
            }
            catch (e: Exception) {
                cb.invoke(null)
                val errorMessage = e.message.toString()
                Log.i("UploadTest", errorMessage)
            }
        }
    }

    fun postEvent(title: String, description: String, dateFrom: OffsetDateTime, dateTo: OffsetDateTime, imageUri: Uri?, context: Context, cb: (status: ResponseStatus?)->Unit) {
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
                        Log.i("Open FileDescriptor", "Opening..")
                        val imagePFD: ParcelFileDescriptor? =
                            context.contentResolver.openFileDescriptor(
                                imageUri, "r"
                            )
                        Log.i("Open FileDescriptor", "Done..")

                        if (imagePFD != null) {
                            Log.i("Attemt DecoideFileDesc", "Decoding..")
                            val bitmap = BitmapFactory.decodeFileDescriptor(imagePFD.fileDescriptor)
                            Log.i("Attemt DecoideFileDesc", "Done")

//                          Skriv bilde inn i filen
                            val byteArrOutStream = ByteArrayOutputStream()
                            Log.i("Attemt Bitmap Compress", "Compressing..")
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 30, byteArrOutStream)
                            Log.i("Attemt Bitmap Compress", "Done..")

                            Log.i("Attemt write", "writing..")
                            file.writeBytes(byteArrOutStream.toByteArray())
                            Log.i("Attemt write", "done")
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
                    val reqFile: RequestBody = file.asRequestBody("image/*".toMediaTypeOrNull()) as RequestBody
                    body = MultipartBody.Part.createFormData("image", file.name, reqFile)
                    Log.i("File == null?", "Not Null")
                }
                else {
                    body = null
                    Log.i("File == null?", "Null")
                }
                val titlePart: RequestBody = title.toRequestBody("text/plain".toMediaTypeOrNull())
                val descriptionPart: RequestBody = description.toRequestBody("text/plain".toMediaTypeOrNull())
                val dateFromPart: RequestBody = dateFrom.toString().toRequestBody("text/plain".toMediaTypeOrNull())
                val dateToPart: RequestBody = dateTo.toString().toRequestBody("text/plain".toMediaTypeOrNull())
                val response: ResponseStatus = apiService.postEvent(titlePart, descriptionPart, dateFromPart, dateToPart, body)
                Log.i("UploadTest", response.toString())
                cb.invoke(response)
            }
            catch (e: Exception) {
                cb.invoke(null)
                val errorMessage = e.message.toString()
                Log.i("UploadTest", errorMessage)
            }
        }
    }
}