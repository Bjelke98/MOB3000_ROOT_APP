package com.example.mob3000_root_app.components.viewmodel

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.ParcelFileDescriptor
import android.provider.MediaStore
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toFile
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mob3000_root_app.data.RootService
import com.example.mob3000_root_app.data.apiResponse.ArticleData
import com.example.mob3000_root_app.data.apiResponse.emptyArticleData
import kotlinx.coroutines.launch
import okhttp3.MediaType
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

    fun focusArticle(focusArticle: ArticleData){
        focusedArticle = focusArticle
    }

    fun postArticle(title: String, description: String, image: File?) {
        viewModelScope.launch {
            val api = RootService.getInstance()
            try {
                val builder: MultipartBody.Builder = MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("title", title)
                    .addFormDataPart("description", description)

                if(image != null) {
                    val bitmap = BitmapFactory.decodeFile(image.absolutePath)
                    val byteArrOutStream = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 30, byteArrOutStream)

                    builder.addFormDataPart("image", image.name, RequestBody.create(MultipartBody.FORM, byteArrOutStream.toByteArray()))
                }

                val reqBody: RequestBody = builder.build()
//                val post = api.postArticle(reqBody)
//                val post = image?.let {
//                    api.postArticle(
//                        title = title,
//                        description,
//                        image = it
//                    )
//                }
//                Log.i("Try Post Article", post.toString())
            }
            catch (e : Exception){
                Log.i("Catch", e.toString())
            }
        }
    }

    fun postArticleTest(title: String, description: String, image: Uri, context: Context) {
        viewModelScope.launch {
            val api = RootService.getInstance()
            try {
                val builder: MultipartBody.Builder = MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("title", title)
                    .addFormDataPart("description", description)

                val projection = arrayOf(
                    MediaStore.Images.ImageColumns._ID,
                    MediaStore.Images.ImageColumns.DATA,
                    MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME,
                    MediaStore.Images.ImageColumns.DATE_TAKEN,
                    MediaStore.Images.ImageColumns.MIME_TYPE,
                    MediaStore.Images.ImageColumns.DISPLAY_NAME
                )

//                val cursor: Cursor? = context.contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                projection, null, null, MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC");

                Log.i("Creating TempFIle","Creating...")
                val file: File = File.createTempFile("image", ".jpeg")
                Log.i("Creating TempFile"," Done")

//                Log.i("Uri toFile"," Attempt")
//                if (cursor != null) {
//                    if (cursor.moveToFirst()) {

                    if (Build.VERSION.SDK_INT >= 29) {
                        // You can replace '0' by 'cursor.getColumnIndex(MediaStore.Images.ImageColumns._ID)'
                        // Note that now, you read the column '_ID' and not the column 'DATA'

//                            Log.i("Create ImageUri", "Opening..")
//                            val  imageUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                                cursor.getInt(0).toLong()
//                            );

                        // now that you have the media URI, you can decode it to a bitmap
                        try  {
                            Log.i("Open FileDescriptor", "Opening..")
                            val imagePFD: ParcelFileDescriptor? = context.getContentResolver().openFileDescriptor(
                                image,"r"
                            )
                            Log.i("Open FileDescriptor", "Done..")

                            if (imagePFD != null) {
                                Log.i("Attemt DecoideFileDesc", "Decoding..")
                                val bitmap = BitmapFactory.decodeFileDescriptor(imagePFD.fileDescriptor);
                                Log.i("Attemt DecoideFileDesc", "Done")

                                if(image != null) {
//                                        val bitmap = BitmapFactory.decodeFile(image.absolutePath)
                                    val byteArrOutStream = ByteArrayOutputStream()
                                    Log.i("Attemt Bitmap Compress", "Compressing..")
                                    bitmap.compress(Bitmap.CompressFormat.JPEG, 30, byteArrOutStream)
                                    Log.i("Attemt Bitmap Compress", "Done..")

                                    Log.i("Attemt Retrofit Build", "Building..")
                                    builder.addFormDataPart("image", image.toString(), RequestBody.create(MultipartBody.FORM, byteArrOutStream.toByteArray()))
                                    Log.i("Attemt Retrofit Build", "Done")

                                    file.writeBytes(byteArrOutStream.toByteArray())
                                }
                            }
                        } catch ( ex: IOException) {
                            Log.i("Catch",ex.toString())
                        }
                    } else {

                        Log.i("Catch","Old Version of Android") /* TODO */
                    }

//                        val reqBody: RequestBody = builder.build()
        //                val post = api.postArticle(reqBody)
                    val titlePart = title.toRequestBody(MultipartBody.FORM)
                    val descriptionPart = description.toRequestBody(MultipartBody.FORM)
//                    val filePart = file.asRequestBody(context.contentResolver.getType(image)?.toMediaTypeOrNull())
                    val filePart = file.asRequestBody("image/*".toMediaTypeOrNull())

                    val outFile = MultipartBody.Part.createFormData("image", file.name, filePart)

            //              Contexxt contentResolver issues.

                    val post = image.let {
                        api.postArticle(
                            title = titlePart,
                            descriptionPart,
                            image = outFile
                        )
                    }
                    Log.i("Try Post Article", post.toString())
                    }
//                }
//            }
            catch (e : Exception){
                Log.i("Catch", e.toString())
            }
        }
    }
}