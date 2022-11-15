package com.example.mob3000_root_app.screens.admin

//import com.google.android.material.datepicker.MaterialDatePicker

import android.app.DatePickerDialog
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.Picture
import android.media.Image
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.viewmodel.ArticleViewModel
import com.example.mob3000_root_app.components.viewmodel.PostPutArticleVM
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.io.File
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleEditAdmin(navHost: NavHostController, postPutArticleVM: PostPutArticleVM) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

// val datePicker = MaterialDatePicker.Builder.dateRangePicker()
//  .setTitleText( stringResource(com.example.mob3000_root_app.R.string.datepicker_select_date) )
//  .build()

    val context = LocalContext.current

    val pictureHeight = 200
    val pictureWidth = 150

    var selectedImagePath by remember { mutableStateOf<Uri>( Uri.parse("") ) }

    var tempImageFile by remember { mutableStateOf( File(context.cacheDir,"userImage.jpg") ) }
//    tempImageFile.createNewFile()

    val dateDialogState = rememberMaterialDialogState()
    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }
    ) {
        datepicker { date ->
            // Do stuff with java.time.LocalDate object which is passed in
        }
    }

    val timeDialogState = rememberMaterialDialogState()
    MaterialDialog(
        dialogState = timeDialogState,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }
    ) {
        timepicker { time ->
            // Do stuff with java.time.LocalDate object which is passed in
        }
    }

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    var imageFile by remember { mutableStateOf( File("") ) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        run {
            if (uri != null) {
                selectedImagePath = uri
                imageUri = uri

                tempImageFile = selectedImagePath.path?.let { File(it) }!!
//                val pathString = tempImageFile.path.split(":")
//                Log.i("Image Path", pathString[1])
                Log.i("Image Full Path", tempImageFile.path)

            }
        }
    }

//  Lært herfra https://www.youtube.com/watch?v=cJxo96eTHVU
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    var date by remember { mutableStateOf("") }

    val datePickerDialog by remember { mutableStateOf(
            DatePickerDialog(
            context, {d, setYear, setMonth, setDay ->
                val month = setMonth+1
                date = "$day - $month - %year"
            }, year, month, day)
    ) }

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
            
            Row() {
                
            }

            Button(onClick = {
//                datePickerDialog.show()
                dateDialogState.show()
                Toast.makeText(context, "This function is coming soon™", Toast.LENGTH_SHORT).show()
            }) {
                Text(stringResource(R.string.datepicker_from_button))
            }

            Button(
                onClick = {
                    if(title.isNotBlank() && description.isNotBlank()) {
//                        postPutArticleVM.postArticleTest(title, description, imageFile)
                        imageUri?.let { postPutArticleVM.postArticleTest(title, description, it, context) }

                        Log.i("Post",title+", "+description+", "+imageFile.path)
                    }
                    else
                        Log.i("Post","missing Info")
                }
            ) {
                Text(stringResource(R.string.post_article_button))
            }

            Button(onClick = {
                launcher.launch("image/*")

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



@Preview(showBackground = true, widthDp = 150, heightDp = 1920 )
@Composable
fun myPreview(){
    ArticleEditAdmin(navHost = NavHostController(LocalContext.current), postPutArticleVM = PostPutArticleVM())
}
