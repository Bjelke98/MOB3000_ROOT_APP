package com.example.mob3000_root_app.bjelkeTests

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
import com.example.mob3000_root_app.data.apiResponse.ResponseStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException

class UploadFileTestVM : ViewModel() {

    fun newArticle(filePath: Uri, context: Context) {
        viewModelScope.launch {
            val apiService = RootService.getInstance()
            Log.i("UploadTest", "Start trycatch")

            var file = File.createTempFile("imagefile", ".jpeg")

            if (Build.VERSION.SDK_INT >= 29) {
                try  {
                    Log.i("Open FileDescriptor", "Opening..")
                    val imagePFD: ParcelFileDescriptor? = context.contentResolver.openFileDescriptor(
                        filePath,"r"
                    )
                    Log.i("Open FileDescriptor", "Done..")

                    if (imagePFD != null) {
                        Log.i("Attemt DecoideFileDesc", "Decoding..")
                        val bitmap = BitmapFactory.decodeFileDescriptor(imagePFD.fileDescriptor);
                        Log.i("Attemt DecoideFileDesc", "Done")

                        //                                        val bitmap = BitmapFactory.decodeFile(image.absolutePath)
                        val byteArrOutStream = ByteArrayOutputStream()
                        Log.i("Attemt Bitmap Compress", "Compressing..")
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 30, byteArrOutStream)
                        Log.i("Attemt Bitmap Compress", "Done..")

                        Log.i("Attemt write", "writing..")
                        file.writeBytes(byteArrOutStream.toByteArray())
                        Log.i("Attemt write", "done")
                    }
                    imagePFD?.close()

                } catch ( ex: IOException) {
                    Log.i("Catch",ex.toString())
                }
            } else {

                Log.i("Catch","Old Version of Android") /* TODO */
            }

            try {
//                val file = filePath.path?.let { File(it) }
//                val file = filePath.toFile()
                val reqFile: RequestBody = file?.asRequestBody("image/*".toMediaTypeOrNull()) as RequestBody
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
                val errorMessage = e.message.toString()
                Log.i("UploadTest", errorMessage)
            }
        }
    }
}