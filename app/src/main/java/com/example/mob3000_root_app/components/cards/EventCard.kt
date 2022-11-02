package com.example.mob3000_root_app.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mob3000_root_app.data.EventItem

import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.data.EventData
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter



@Composable
fun EventCard(event: EventData) {
    val testColors: CardColors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.background)

    val dateTimeFrom = ZonedDateTime.parse(event.dateFrom)
    val dateTimeTo = ZonedDateTime.parse(event.dateTo)

    val dateFormatMonth = DateTimeFormatter.ofPattern("MMM")
    val dateFormatDay = DateTimeFormatter.ofPattern("dd")
    val dateFormatFull = DateTimeFormatter.ofPattern("dd-mm-yyyy")

    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
        colors = testColors
    ) {
        Box(modifier = Modifier.height(200.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://linrik.herokuapp.com/api/resources/" + event.image)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.sauce),
                contentDescription = (event.title),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
                    .padding(bottom = 20.dp)
            )
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
                    text = dateTimeFrom.format(dateFormatDay),
                    textAlign = TextAlign.Center
                )

                Text(
                    modifier = Modifier
                        .padding(1.dp),
                    color = Color.Red,
                    style = TextStyle(fontSize = 15.sp),
                    textAlign = TextAlign.Center,
                    text =  dateTimeFrom.format(dateFormatMonth)
                )

            }


        }
        Column() {

            Text(modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = event.title,
                style = MaterialTheme.typography.headlineMedium)
        }

        Row() {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp)) {

                Box(modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.CenterStart)
                ) {

                    Row() {
                        Icon(Icons.Filled.LocationOn, "menu")
                        Text(
                            textAlign = TextAlign.Start,
                            text = "Gullbringvegen 36"
                        )
                    }

                }



            }
        }
        Box(modifier = Modifier
            .padding(5.dp)
//            .align(Alignment.CenterEnd)
        ) {

            Row() {
                Text(modifier = Modifier
                    .padding(horizontal = 12.dp),
                    textAlign = TextAlign.Start,
                    text = 5.toString())
                Text(
                    text = "are going")

                Icon(Icons.Filled.ToggleOn, "menu")
            }

        }




    }
}