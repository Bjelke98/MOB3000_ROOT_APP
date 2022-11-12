package com.example.mob3000_root_app.screens.admin

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
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
import androidx.navigation.NavHostController
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.viewmodel.ArticleViewModel
import java.io.File
//import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleEditAdmin(navHost: NavHostController, articleViewModel: ArticleViewModel) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

   val context = LocalContext.current

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

    Box(
        Modifier
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

                Button(onClick = { datePickerDialog.show() }) {
                    Text(stringResource(R.string.datepicker_from_button))
                }

                Button(onClick = {
                    Toast.makeText(context, "This function is coming soon™", Toast.LENGTH_SHORT).show()
                }) {
                    Text(stringResource(R.string.upload_picture_button))
                }
            }
        }
}


@Preview(showBackground = true, widthDp = 150, heightDp = 1920 )
@Composable
fun myPreview(){
 ArticleEditAdmin(navHost = NavHostController(LocalContext.current), articleViewModel = ArticleViewModel())
}
