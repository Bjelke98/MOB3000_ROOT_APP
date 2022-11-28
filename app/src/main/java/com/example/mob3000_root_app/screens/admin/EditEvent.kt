package com.example.mob3000_root_app.screens.admin

import android.app.DatePickerDialog
import android.app.TimePickerDialog
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.scrollbar.Carousel
import com.example.mob3000_root_app.components.cards.ShowDateAndTime
import com.example.mob3000_root_app.components.image.ImageDisplayBox
import com.example.mob3000_root_app.components.navigation.Screen
import com.example.mob3000_root_app.components.navigation.navigateUpTo
import com.example.mob3000_root_app.components.scrollbar.rememberCarouselScrollState
import com.example.mob3000_root_app.components.scrollbar.verticalScroll
import com.example.mob3000_root_app.components.viewmodel.AppViewModel
import com.example.mob3000_root_app.data.apiResponse.ResponseStatus
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditEvent(appVM: AppViewModel) {

    val isNewEvent = appVM.ppEventVM.isNewEvent
    val ppEventVM = appVM.ppEventVM
    val context = LocalContext.current
    var title by remember {
        mutableStateOf(
            if (isNewEvent) ""
            else appVM.ppEventVM.focusedEvent.title
        )
    }
    var description by remember {
        mutableStateOf(
            if (isNewEvent) ""
            else appVM.ppEventVM.focusedEvent.description
        )
    }
    val image = if(!ppEventVM.isNewEvent)
    {ppEventVM.focusedEvent.image ?: "defaultEvent.png" }
    else
        { "defaultEvent.png" }
    var isImageChosen by remember { mutableStateOf(false) }

    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val scrollState = rememberCarouselScrollState()

    //<editor-fold desc="Date&Time">

    var dateTimeFrom by remember {
        mutableStateOf(
            if (isNewEvent) Instant.parse(Instant.now().toString()).atOffset(ZoneOffset.ofHours(1))
            else Instant.parse(ppEventVM.focusedEvent.dateFrom).atOffset(ZoneOffset.ofHours(2))
        )
    }
    var dateTimeTo by remember {
        mutableStateOf(
            if (isNewEvent) Instant.parse(Instant.now().toString()).atOffset(ZoneOffset.ofHours(1))
            else Instant.parse(ppEventVM.focusedEvent.dateTo).atOffset(ZoneOffset.ofHours(2))
        )
    }

    val datePickerDialog = DatePickerDialog(
        context,
        0,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            dateTimeFrom = OffsetDateTime.of(
                year,
                month + 1,
                dayOfMonth,
                dateTimeFrom.hour,
                dateTimeFrom.minute,
                dateTimeFrom.second,
                dateTimeFrom.nano,
                ZoneOffset.ofHours(0)
            )
            dateTimeTo = OffsetDateTime.of(
                year,
                month + 1,
                dayOfMonth,
                dateTimeTo.hour,
                dateTimeTo.minute,
                dateTimeTo.second,
                dateTimeTo.nano,
                ZoneOffset.ofHours(0)
            )
        },
        dateTimeFrom.year,
        dateTimeFrom.monthValue - 1,
        dateTimeFrom.dayOfMonth
    )

    val timePickerStateTo = TimePickerDialog(
        context,
        { _: TimePicker, hour: Int, minute: Int ->
            dateTimeTo = OffsetDateTime.of(
                dateTimeTo.year,
                dateTimeTo.monthValue,
                dateTimeTo.dayOfMonth,
                hour,
                minute,
                dateTimeTo.second,
                dateTimeTo.nano,
                ZoneOffset.ofHours(0)
            )
        },
        dateTimeTo.hour,
        dateTimeTo.minute,
        true
    )

    val timePickerDialog = TimePickerDialog(
        context,
        { _: TimePicker, hour: Int, minute: Int ->
            dateTimeFrom = OffsetDateTime.of(
                dateTimeFrom.year,
                dateTimeFrom.monthValue,
                dateTimeFrom.dayOfMonth,
                hour,
                minute,
                dateTimeFrom.second,
                dateTimeFrom.nano,
                ZoneOffset.ofHours(0)
            )
            timePickerStateTo.show()
        },
        dateTimeFrom.hour,
        dateTimeFrom.minute,
        true
    )

//    val timeFrom = MaterialTimePicker.Builder() venter på at det kommer til compose :)
//        .setTimeFormat(CLOCK_24H)
//        .setHour(dateTimeFrom.hour)
//        .setMinute(dateTimeFrom.minute)
//        .setTitleText("Fra")
//        .setInputMode(INPUT_MODE_KEYBOARD)
//        .build()
//
//    timeFrom.addOnPositiveButtonClickListener(){
//        dateTimeFrom = OffsetDateTime.of(
//            dateTimeFrom.year,
//            dateTimeFrom.monthValue,
//            dateTimeFrom.dayOfMonth,
//            timeFrom.hour,
//            timeFrom.minute,
//            dateTimeFrom.second,
//            dateTimeFrom.nano,
//            ZoneOffset.ofHours(0))
//        timePickerStateTo.setTitle("Til")
//        timePickerStateTo.show()
//    }
    //</editor-fold>

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        run {
            if (uri != null) {
                imageUri = uri
                isImageChosen = true
            }
        }
    }
    val incompleteFieldsToast = Toast.makeText(context, stringResource(id = R.string.fill_title_descr), Toast.LENGTH_SHORT)
    val toastErrUpdate = Toast.makeText(context, stringResource(R.string.toast_error_update_toast), Toast.LENGTH_SHORT)
    val toastSuccessUpdate = Toast.makeText(context, stringResource(R.string.toast_success_update_toast), Toast.LENGTH_SHORT)
    val toastErrCreate = Toast.makeText(context, stringResource(R.string.toast_error_create_toast), Toast.LENGTH_SHORT)
    val toastSuccessCreate = Toast.makeText(context, stringResource(R.string.toast_success_create_toast), Toast.LENGTH_SHORT)

    Scaffold(
        //<editor-fold desc="fab-newEvent">
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if(title.isNotBlank() && description.isNotBlank()) {
                        if(appVM.ppEventVM.isNewEvent)
                            ppEventVM.postEvent(title, description, dateTimeFrom, dateTimeTo, imageUri, context){ status: ResponseStatus? ->
                                if (status != null) {
                                    if(status.status!=210){
                                        toastSuccessCreate.show()
                                    } else {
                                        toastErrCreate.show()
                                    }
                                } else toastErrCreate.show()
                            }
                        else
                            ppEventVM.updateEvent(
                                title,
                                description,
                                dateTimeFrom,
                                dateTimeTo,
                                ppEventVM.focusedEvent._id,
                                imageUri,
                                context
                            ){ status: ResponseStatus? ->
                                if (status != null) {
                                    if(status.status!=210){
                                        toastSuccessUpdate.show()
                                    } else {
                                        toastErrUpdate.show()
                                    }
                                } else
                                    toastErrUpdate.show()
                            }
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
                Icon(Icons.Filled.Send, stringResource(id = R.string.edit_event_lead_text))
            }
        }
        //</editor-fold>
    ){ padding ->

        BoxWithConstraints(Modifier.fillMaxSize()) {
// Vertical View
            if(maxHeight>400.dp) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = title,
                        onValueChange = {title = it},
                        label = { Text(stringResource(id = R.string.edit_card_title)) }
                    )

                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = description,
                        onValueChange = { description = it },
                        label = { Text(stringResource(id = R.string.edit_card_description)) }
                    )

                    ShowDateAndTime(dateTimeTo, dateTimeFrom, datePickerDialog, timePickerDialog)


                    Button(onClick = {
                        launcher.launch("image/*")
                    }) {
                        Text(stringResource(R.string.upload_picture_button))
                    }

                    ImageDisplayBox(
                        width = 300.dp,
                        height = 250.dp,
                        imageUrl = image,
                        uri = imageUri,
                        context = context,
                        isNew = isNewEvent,
                        isChosen = isImageChosen,
                        borderColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                }
            }
            else {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface)
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                        horizontalArrangement = Arrangement.spacedBy(15.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(.5f)
                                .verticalScroll(scrollState),
                            verticalArrangement = Arrangement.spacedBy(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            TextField(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                value = title,
                                onValueChange = { title = it },
                                label = { Text(stringResource(id = R.string.edit_card_title)) }
                            )

                            TextField(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                value = description,
                                onValueChange = { description = it },
                                label = { Text(stringResource(id = R.string.edit_card_description)) }
                            )


                            ShowDateAndTime(dateTimeTo, dateTimeFrom, datePickerDialog, timePickerDialog)

                            Button(onClick = {
                                launcher.launch("image/*")
                            }) {
                                Text(stringResource(R.string.upload_picture_button))
                            }
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(
                                modifier = Modifier.fillMaxHeight(),
                                contentAlignment = Alignment.Center
                            ) {
                                Carousel(
                                    state = scrollState,
                                    modifier = Modifier
                                        .width(8.dp)
                                        .fillMaxHeight(.9f)
                                        .padding(end = 5.dp)
                                )
                            }
                        }
                        BoxWithConstraints(Modifier.fillMaxSize()) {
                            ImageDisplayBox(
                                width = maxWidth,
                                height = maxHeight,
                                imageUrl = image,
                                uri = imageUri,
                                context = context,
                                isNew = isNewEvent,
                                isChosen = isImageChosen,
                                borderColor = MaterialTheme.colorScheme.surfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}