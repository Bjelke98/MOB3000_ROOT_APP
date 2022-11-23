package com.example.mob3000_root_app.screens.admin

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.cards.showDateAndTime
import com.example.mob3000_root_app.components.navigation.Screen
import com.example.mob3000_root_app.components.navigation.navigateUpTo
import com.example.mob3000_root_app.components.viewmodel.AppViewModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.Instant
import java.time.OffsetDateTime
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

    val context = LocalContext.current


    var imageUri by remember { mutableStateOf<Uri?>( null) }
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    var changePicture by remember { mutableStateOf(false) }


    //  Lært herfra https://www.youtube.com/watch?v=cJxo96eTHVU+

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
        {_:DatePicker, year: Int, month: Int,dayOfMonth: Int ->
            dateTimeFrom = OffsetDateTime.of(
                year,
                month+1,
                dayOfMonth,
                dateTimeFrom.hour,
                dateTimeFrom.minute,
                dateTimeFrom.second,
                dateTimeFrom.nano,
                ZoneOffset.ofHours(0))
            dateTimeTo = OffsetDateTime.of(
                year,
                month+1,
                dayOfMonth,
                dateTimeTo.hour,
                dateTimeTo.minute,
                dateTimeTo.second,
                dateTimeTo.nano,
                ZoneOffset.ofHours(0)
            )
        },
        dateTimeFrom.year,
        dateTimeFrom.monthValue-1,
        dateTimeFrom.dayOfMonth
    )

    val timePickerStateTo = TimePickerDialog(
        context,
        {_: TimePicker, hour: Int, minute: Int ->
            dateTimeTo = OffsetDateTime.of(
                dateTimeTo.year,
                dateTimeTo.monthValue,
                dateTimeTo.dayOfMonth,
                hour,
                minute,
                dateTimeTo.second,
                dateTimeTo.nano,
                ZoneOffset.ofHours(0))
        },
        dateTimeTo.hour,
        dateTimeTo.minute,
        true
    )

    val timePickerState = TimePickerDialog(
        context,
        {_: TimePicker, hour: Int, minute: Int ->
            dateTimeFrom = OffsetDateTime.of(
                dateTimeFrom.year,
                dateTimeFrom.monthValue,
                dateTimeFrom.dayOfMonth,
                hour,
                minute,
                dateTimeFrom.second,
                dateTimeFrom.nano,
                ZoneOffset.ofHours(0))
            timePickerStateTo.show()
        },
        dateTimeFrom.hour,
        dateTimeFrom.minute,
        true
    )


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
                            ppEventVM.postEvent(title, description, dateTimeFrom, dateTimeTo, imageUri, context)
                        else
                            ppEventVM.updateEvent(
                                title,
                                description,
                                dateTimeFrom,
                                dateTimeTo,
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
                }) {
                    Text(stringResource(R.string.datepicker_select_date))
                }

                Button(onClick = {
                    timePickerState.show()
                }) {
                    Text(stringResource(R.string.datepicker_hour))
                }

            }

            Button(onClick = {
                launcher.launch("image/*")
                changePicture = true;
            }) {
                Text(stringResource(R.string.upload_picture_button))
            }


            Box(
                modifier = Modifier
                    .size(300.dp, 250.dp)
                    .border(2.dp, MaterialTheme.colorScheme.onSurfaceVariant, RectangleShape)
            ) {
                if (isNewEvent || changePicture){
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
                }else{
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://linrik.herokuapp.com/api/resources/${ppEventVM.focusedEvent.image}")
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(R.drawable.testing),
                        contentDescription = (stringResource(id = R.string.image_load_failed)),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
        }
}
