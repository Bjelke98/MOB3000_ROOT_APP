package com.example.mob3000_root_app.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mob3000_root_app.data.apiResponse.EventData
import java.time.Instant
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Composable
fun showDateAndTime(
    eventData: EventData
) {

    //val dateTimeFrom by remember { mutableStateOf(ZonedDateTime.parse(eventData.dateFrom)) }
    val dateTimeFrom = Instant.parse(eventData.dateFrom).atOffset(ZoneOffset.ofHours(2))
    val dateFormatHours = DateTimeFormatter.ofPattern("HH")
    val dateFormatMonth = DateTimeFormatter.ofPattern("MMM")
        Column(
            Modifier
                .padding(15.dp)
                .width(50.dp)
                .background(Color.White),
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
                text =  dateTimeFrom.format(dateFormatMonth).uppercase()

            )
        }
        
        showTime(eventData = eventData)
    
}

@Composable
fun showTime(eventData: EventData) {
    //val dateTimeFrom = ZonedDateTime.parse(eventData.dateFrom)
    val dateTimeFrom = Instant.parse(eventData.dateFrom).atOffset(ZoneOffset.ofHours(2))
    val dateTimeTo = Instant.parse(eventData.dateTo).atOffset(ZoneOffset.ofHours(2))
    val dateFormatHour = DateTimeFormatter.ofPattern("HH:mm")
    Row(
        Modifier
            .padding(vertical = 2.dp)
    ) {
        Icon(
            Icons.Filled.Timer, "timer",
            tint = MaterialTheme.colorScheme.primary)
        Text(
            modifier = Modifier
                .padding(horizontal = 10.dp),
            textAlign = TextAlign.Start,
            text = dateTimeFrom.format(dateFormatHour) + " - " + dateTimeTo.format(dateFormatHour)
        )
    }
}

@Composable
fun showDate(
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