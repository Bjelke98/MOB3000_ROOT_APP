package com.example.mob3000_root_app.components.cards

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.data.apiResponse.EventData
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@Composable
fun ShowDateAndTime(
    dateTimeTo: OffsetDateTime,
    dateTimeFrom: OffsetDateTime,
    datePickerDialog: DatePickerDialog,
    timePickerState: TimePickerDialog
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxHeight()
//            .border(1.dp, MaterialTheme.colorScheme.primary)
            .background(MaterialTheme.colorScheme.surfaceVariant),
    ) {
        val dateFormatMonth = DateTimeFormatter.ofPattern("MMM")
        Column(
            Modifier
                .padding(15.dp)
                .width(50.dp)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(
                modifier = Modifier
                    .background(Color.White)
                    .padding(0.dp),

                style = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                text = dateTimeFrom.dayOfMonth.toString(),
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier
                    .padding(1.dp),

                color = MaterialTheme.colorScheme.primary,
                style = TextStyle(fontSize = 15.sp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                text = dateTimeFrom.format(dateFormatMonth).uppercase()

            )
        }

        Column(Modifier.padding(15.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            ShowTime(dateTimeTo, dateTimeFrom)
            Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                Button(onClick = {
                    datePickerDialog.show()
                }) {
//                                Text(stringResource(R.string.datepicker_select_date))
                    Icon(
                        painter = painterResource(
                            id = R.drawable.ic_baseline_calendar_month_24
                        ),
                        contentDescription = "Choose Date"
                    )
                }

                Button(onClick = {
                    timePickerState.show()
                }) {
//                                Text(stringResource(R.string.datepicker_hour))
                    Icon(
                        painter = painterResource(
                            id = R.drawable.ic_baseline_access_time_24
                        ),
                        contentDescription = "Choose Date"
                    )
                }
            }
        }
    }
}

@Composable
fun ShowTime(
    dateTimeTo: OffsetDateTime,
    dateTimeFrom: OffsetDateTime
) {
    //val dateTimeFrom = ZonedDateTime.parse(eventData.dateFrom)
    val dateFormatHour = DateTimeFormatter.ofPattern("HH:mm")
    Box(
        Modifier.background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            Modifier
                .padding(vertical = 2.dp)
        ) {
            Icon(
                Icons.Filled.Timer, "timer",
                tint = MaterialTheme.colorScheme.primary
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 10.dp),
                textAlign = TextAlign.Start,
                text = dateTimeFrom.format(dateFormatHour) + " - " + dateTimeTo.format(
                    dateFormatHour
                )
            )
        }
    }
}

@Composable
fun ShowDate(
    eventData: EventData
) {
    val dateTimeFrom = Instant.parse(eventData.dateFrom).atOffset(ZoneOffset.ofHours(2))

    val dateFormatMonth = DateTimeFormatter.ofPattern("MMM")
    val dateFormatDay = DateTimeFormatter.ofPattern("dd")

    Column(
        Modifier
            .padding(15.dp)
            .width(50.dp)
            .background(Color.White)
            .border(1.dp, MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally) {


        Text(
            modifier = Modifier
                .background(Color.White)
                .padding(0.dp),

            style = TextStyle(
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            text = dateTimeFrom.format(dateFormatDay),
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier
                .padding(1.dp),

            color = MaterialTheme.colorScheme.primary,
            style = TextStyle(fontSize = 15.sp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            text =  dateTimeFrom.format(dateFormatMonth).uppercase()

        )

    }
}