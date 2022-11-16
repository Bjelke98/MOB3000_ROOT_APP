package com.example.mob3000_root_app.bjelkeTests

import android.net.Uri
import android.os.Build
import android.os.ParcelFileDescriptor
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.net.toFile
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mob3000_root_app.data.RootService
import com.example.mob3000_root_app.data.apiResponse.EventData
import com.example.mob3000_root_app.data.apiResponse.ResponseStatus
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class UploadFileTestVM : ViewModel() {

    var errorMessage: String by mutableStateOf("")

    fun newArticle(filePath: Uri) {
        viewModelScope.launch {
            val apiService = RootService.getInstance()
            Log.i("UploadTest", "Start trycatch")

//            if (Build.VERSION.SDK_INT >= 29) {
//                try  {
//                    Log.i("Open FileDescriptor", "Opening..")
//                    val imagePFD: ParcelFileDescriptor? = context.getContentResolver().openFileDescriptor(
//                        image,"r"
//                    )
//                    Log.i("Open FileDescriptor", "Done..")
//
//                    if (imagePFD != null) {
//                        Log.i("Attemt DecoideFileDesc", "Decoding..")
//                        val bitmap = BitmapFactory.decodeFileDescriptor(imagePFD.fileDescriptor);
//                        Log.i("Attemt DecoideFileDesc", "Done")
//
//                        if(image != null) {
////                                        val bitmap = BitmapFactory.decodeFile(image.absolutePath)
//                            val byteArrOutStream = ByteArrayOutputStream()
//                            Log.i("Attemt Bitmap Compress", "Compressing..")
//                            bitmap.compress(Bitmap.CompressFormat.JPEG, 30, byteArrOutStream)
//                            Log.i("Attemt Bitmap Compress", "Done..")
//
//                            Log.i("Attemt Retrofit Build", "Building..")
//                            builder.addFormDataPart("image", image.toString(), RequestBody.create(MultipartBody.FORM, byteArrOutStream.toByteArray()))
//                            Log.i("Attemt Retrofit Build", "Done")
//
//                            file.writeBytes(byteArrOutStream.toByteArray())
//                        }
//                    }
//                } catch ( ex: IOException) {
//                    Log.i("Catch",ex.toString())
//                }
//            } else {
//
//                Log.i("Catch","Old Version of Android") /* TODO */
//            }

            try {
                val file = File(filePath.path)
//                val file = filePath.toFile()
                val reqFile: RequestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
                val body: MultipartBody.Part = MultipartBody.Part.createFormData("image", file.name, reqFile)
                val title: RequestBody = "title".toRequestBody("text/plain".toMediaTypeOrNull())
                val description: RequestBody = "description".toRequestBody("text/plain".toMediaTypeOrNull())
//                reqFile: RequestBody = RequestBody.create(MediaType.parse("image/*"), file);
//                MultipartBody.Part body = MultipartBody.Part.createFormData("upload", file.getName(), reqFile);
//                RequestBody name = RequestBody.create(MediaType.parse("text/plain"), "upload_test");
//
                val response: ResponseStatus = apiService.bjelkeNewArticle(title, description, body)
                Log.i("UploadTest", response.toString())
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.i("UploadTest", errorMessage)
            }
        }
    }
}