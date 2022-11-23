package com.example.mob3000_root_app.screens.admin

import android.app.DatePickerDialog
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mob3000_root_app.MainActivity
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.cards.showDateAndTime
import com.example.mob3000_root_app.components.navigation.Screen
import com.example.mob3000_root_app.components.navigation.navigateUpTo
import com.example.mob3000_root_app.components.viewmodel.AppViewModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.Instant
import java.time.ZoneOffset
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditEvent(appVM: AppViewModel) {

    val isNewEvent = appVM.ppEventVM.isNewEvent
    val ppEventVM = appVM.ppEventVM
    var title by remember { mutableStateOf(
        if( isNewEvent) ""
        else appVM.ppEventVM.focusedEvent.title
    )}

    var description by remember { mutableStateOf(
        if( isNewEvent) ""
        else appVM.ppEventVM.focusedEvent.description
    )}

    var dateFrom by remember { mutableStateOf(
        if(isNewEvent) Instant.now()
        else Instant.parse(ppEventVM.focusedEvent.dateFrom)
    )}

    var dateTo by remember { mutableStateOf(
        if(isNewEvent) Instant.now()
        else Instant.parse(ppEventVM.focusedEvent.dateTo)
    )}

    val context = LocalContext.current


    var imageUri by remember { mutableStateOf<Uri?>( null) }
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }


    //  Lært herfra https://www.youtube.com/watch?v=cJxo96eTHVU

    var dateTimeFrom by remember{ mutableStateOf(
        if (isNewEvent) Instant.parse(Instant.now().toString()).atOffset(ZoneOffset.ofHours(1))
        else Instant.parse(ppEventVM.focusedEvent.dateFrom).atOffset(ZoneOffset.ofHours(2))
    ) }
    var dateTimeTo by remember{ mutableStateOf(
        if (isNewEvent) Instant.parse(Instant.now().toString()).atOffset(ZoneOffset.ofHours(1))
        else Instant.parse(ppEventVM.focusedEvent.dateTo).atOffset(ZoneOffset.ofHours(2))
    ) }


    val datePickerDialog = DatePickerDialog(
        context,
        0,
        null,
        dateTimeFrom.year,
        dateTimeFrom.monthValue-1,
        dateTimeFrom.dayOfMonth
    )

   /* MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }
    ) {
        datepicker { date ->
            print(date.toString())
        }
    }*/

    val timeDialogState = rememberMaterialDialogState()
    MaterialDialog(
        dialogState = timeDialogState,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }
    ) {
        timepicker { time ->

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
    val incompleteFieldsToast = Toast.makeText(context, stringResource(id = R.string.fill_title_descr), Toast.LENGTH_SHORT)
    val testColors: CardColors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.background)
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if(title.isNotBlank() && description.isNotBlank()) {
                        if(appVM.ppEventVM.isNewEvent)
                            ppEventVM.postEvent(title, description, dateFrom, dateTo, imageUri, context)
                        else
                            ppEventVM.updateEvent(
                                title,
                                description,
                                dateFrom,
                                dateTo,
                                ppEventVM.focusedEvent._id,
                                imageUri,
                                context
                            )

                        navigateUpTo(appVM.navController, Screen.EventAdmin)
                    }
                    else {
                        incompleteFieldsToast.show()
                        Log.i("Post", "missing Info")
                    }
                },
                contentColor = MaterialTheme.colorScheme.onPrimary,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Filled.Send, "Edit Event")
            }
        }
    ){ padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
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

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .border(1.dp, MaterialTheme.colorScheme.primary)

            ) {
                showDateAndTime(dateTimeTo, dateTimeFrom)
            }


            Row() {
                Button(onClick = {
                    datePickerDialog.show()
                    Toast.makeText(context, "This function is coming soon™", Toast.LENGTH_SHORT).show()
                }) {
                    Text(stringResource(R.string.datepicker_select_date))
                }

                Button(onClick = {
                    // legge in timePicker
                    timeDialogState.show()
                    Toast.makeText(context, "This function is coming soon™", Toast.LENGTH_SHORT).show()
                }) {
                    Text(stringResource(R.string.datepicker_hour))
                }

            }

            Button(onClick = {
                launcher.launch("image/*")

            }) {
                Text(stringResource(R.string.upload_picture_button))
            }


            Box(
                modifier = Modifier
                    .size(300.dp, 250.dp)
                    .border(2.dp, MaterialTheme.colorScheme.onSurfaceVariant, RectangleShape)
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
