package com.example.mob3000_root_app.screens.admin

//import com.google.android.material.datepicker.MaterialDatePicker

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.viewmodel.AppViewModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditArticle(appVM: AppViewModel) {

    val ppArticleVM = appVM.ppArticleVM
    var title by remember { mutableStateOf(appVM.ppArticleVM.focusedArticle.title) }
    var description by remember { mutableStateOf(appVM.ppArticleVM.focusedArticle.description) }
    val context = LocalContext.current

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }


    //  Lært herfra https://www.youtube.com/watch?v=cJxo96eTHVU
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    var date by remember { mutableStateOf("") }

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

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        run {
            if (uri != null) {
                imageUri = uri
            }
        }
    }

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
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
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
                dateDialogState.show()
                Toast.makeText(context, "This function is coming soon™", Toast.LENGTH_SHORT).show()
            }) {
                Text(stringResource(R.string.datepicker_from_button))
            }

            Button(
                onClick = {
                    if(title.isNotBlank() && description.isNotBlank()) {
                        
                        ppArticleVM.updateArticle(title, description, ppArticleVM.focusedArticle._id, imageUri, context)
//                        ppArticleVM.postArticle(title, description, imageUri, context)
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

            Box(
                modifier = Modifier
                    .size(300.dp,250.dp)
                    .border(2.dp, MaterialTheme.colorScheme.onSurfaceVariant,RectangleShape)
            ) {
                imageUri?.let {
                    val source = ImageDecoder
                        .createSource(context.contentResolver,it)

                    bitmap = ImageDecoder.decodeBitmap(source)

                    bitmap?.let {  btm ->
                        Image(
                            bitmap = btm.asImageBitmap(),
                            contentDescription =null,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}