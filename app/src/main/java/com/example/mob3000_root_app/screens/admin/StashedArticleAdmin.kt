package com.example.mob3000_root_app.screens.admin

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.StrictMode
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider
import androidx.navigation.NavHostController
import com.example.mob3000_root_app.BuildConfig
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.viewmodel.ArticleViewModel
import java.io.ByteArrayOutputStream
import java.io.File
import java.net.URI
//import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StashedArticleEditAdmin(navHost: NavHostController, articleViewModel: ArticleViewModel) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

// val datePicker = MaterialDatePicker.Builder.dateRangePicker()
//  .setTitleText( stringResource(com.example.mob3000_root_app.R.string.datepicker_select_date) )
//  .build()

    val context = LocalContext.current

    val pictureHeight = 200
    val pictureWidth = 150

    val tempImageFile by remember { mutableStateOf( File(context.cacheDir,"userImage.jpg") ) }
    tempImageFile.createNewFile()

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    var imageFile by remember { mutableStateOf( File("") ) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ){
            uri: Uri? ->
        run {

            imageUri = uri

            var file = File(uri?.lastPathSegment ?: uri.toString())
            val packageName: String = context.applicationContext.packageName
            val authority = "$packageName.provider"
            val nuri = FileProvider.getUriForFile(context.applicationContext, authority, file)
//            val nuri = FileProvider.getUriForFile(Objects.requireNonNull(context.applicationContext),
//                BuildConfig.APPLICATION_ID + ".provider", file)

            if (Build.VERSION.SDK_INT >= 24) {
                try {
                    val m = StrictMode::class.java.getMethod("disableDeathOnFileUriExposure")
                    m.invoke(null)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            val cropIntent = Intent(Intent.ACTION_GET_CONTENT)
            cropIntent.setDataAndType(nuri, "image/*")
            cropIntent.putExtra("crop", true)
            cropIntent.putExtra("aspectX", 1)
            cropIntent.putExtra("aspectY", 1)
            cropIntent.putExtra("outputX", 512)
            cropIntent.putExtra("outputY", 512)
            cropIntent.putExtra("return-data", true)
            cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, nuri)
            cropIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            cropIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)

            val intent2 = Intent("com.android.camera.action.CROP", imageUri)
//                .setType("image/*")
                .putExtra("crop", "true")
                .putExtra("aspectX", 1)
                .putExtra("aspectY", 1)
                .putExtra("outputX", pictureWidth)
                .putExtra("outputY", pictureHeight)
                .putExtra("scale", true)
                .putExtra("scaleUpIfNeeded", true)
                .putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile))
                .putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString())

            startActivity(context, cropIntent, null)
        }
    }

    val intent: Intent = Intent(Intent.ACTION_GET_CONTENT, null)
        .setType("image/*")
        .putExtra("crop", "true")
        .putExtra("aspectX", 1)
        .putExtra("aspectY", 1)
        .putExtra("outputX", pictureWidth)
        .putExtra("outputY", pictureHeight)
        .putExtra("scale", true)
        .putExtra("scaleUpIfNeeded", true)
        .putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempImageFile))
        .putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString())

    var intent2: Intent

//  Lært herfra https://www.youtube.com/watch?v=cJxo96eTHVU
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    var date by remember { mutableStateOf("") }

    val datePickerDialog = DatePickerDialog(
        LocalContext.current, {d, setYear, setMonth, setDay ->
            val month = setMonth+1
            date = "$day - $month - %year"
        }, year, month, day
    )

    val testColors: CardColors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.background)

    Box(
        Modifier
//            .background(MaterialTheme.colorScheme.surfaceVariant)
            .fillMaxSize()
            .padding(20.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(stringResource(id = R.string.edit_card_title)) },
            )

            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text(stringResource(id = R.string.edit_card_description)) }
            )

            Button(onClick = {
//                datePickerDialog.show()
                Toast.makeText(context, "This function is coming soon™", Toast.LENGTH_SHORT).show()
            }) {
                Text(stringResource(R.string.datepicker_from_button))
            }

            Button(onClick = {
                launcher.launch("image/*")

                if(imageUri != null){
                    val source = ImageDecoder
                        .createSource(context.contentResolver, imageUri!!)

                    bitmap = ImageDecoder.decodeBitmap(source)

                    tempImageFile.outputStream().use{
                        val byteArrStream = ByteArrayOutputStream()
                        bitmap!!.compress(Bitmap.CompressFormat.JPEG,80,byteArrStream)
                        it.write(byteArrStream.toByteArray())
                    }
                }


            }) {
                Text(stringResource(R.string.upload_picture_button))
            }

            imageUri?.let {
                val source = ImageDecoder
                    .createSource(context.contentResolver,it)

                bitmap = ImageDecoder.decodeBitmap(source)

                bitmap?.let {  btm ->
                    Image(
                        bitmap = btm.asImageBitmap(),
                        contentDescription =null,
                        modifier = Modifier.size(300.dp)
                    )
                }
            }
        }
    }
//    }
}
