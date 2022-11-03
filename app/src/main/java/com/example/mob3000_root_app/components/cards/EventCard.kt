package com.example.mob3000_root_app.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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

import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.navigation.Screen
import com.example.mob3000_root_app.components.navigation.navigateUpTo
import com.example.mob3000_root_app.data.ArticleType
import com.example.mob3000_root_app.data.EventData
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter



@Composable
fun EventCard(
    event: EventData,
    type: ArticleType
    )
{

    val address: String = if (event.address == null)  "Adresse" else event.address

    val dateTimeFrom = ZonedDateTime.parse(event.dateFrom)
    val dateTimeTo = ZonedDateTime.parse(event.dateTo)

    val dateFormatFromHour = DateTimeFormatter.ofPattern("hh:mm")
    val dateFormatToHour = DateTimeFormatter.ofPattern("hh:mm")

    val dateFormatMonth = DateTimeFormatter.ofPattern("MMM")
    val dateFormatDay = DateTimeFormatter.ofPattern("dd")
    val dateFormatFull = DateTimeFormatter.ofPattern("dd-mm-yyyy")

    val testColors: CardColors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.background)

    val configuration = LocalConfiguration.current
    val contentHeight60per = (configuration.screenHeightDp.dp/2)
    val contentWidth80per = (configuration.screenWidthDp.dp/10)*8

    val horizontalColMods = Modifier.width(contentWidth80per)
    val verticalColMods = Modifier.fillMaxWidth()

    Card(
        Modifier
            .fillMaxWidth()
            .height(contentHeight60per)
        , colors = testColors){
        Box(Modifier.fillMaxSize()) {
            Column(modifier = if( type == (ArticleType.VERTICAL_ARTICLE)) verticalColMods else horizontalColMods) {
                Box() {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://linrik.herokuapp.com/api/resources/" + event.image)
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(R.drawable.testing),
                        contentDescription = ("Image could not load"),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(.5f)
                    )

                    Column(
                        Modifier
                            .padding(15.dp)
                            .width(50.dp)
                            .background(Color.White).border(1.dp, MaterialTheme.colorScheme.primary),
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

                Column(
                    Modifier
                        .fillMaxHeight(.7f)
                        .padding(5.dp)
                ) {
                    Text(text = event.title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Row(
                        Modifier
                            .padding(vertical = 2.dp)
                    ) {
                        Icon(Icons.Filled.People, "people",
                            tint = MaterialTheme.colorScheme.primary)
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 10.dp),
                            textAlign = TextAlign.Start,
                            text = event.participants.size.toString()
                        )
                        Text(
                            text = "attending"
                        )


                    }

                    Row(
                        Modifier
                            .padding(vertical = 2.dp)
                    ) {
                        Icon(Icons.Filled.Timer, "location",
                            tint = MaterialTheme.colorScheme.primary)
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 10.dp),
                            textAlign = TextAlign.Start,
                            text = dateTimeFrom.format(dateFormatFromHour) + " - " + dateTimeTo.format(dateFormatToHour)
                        )
                    }

                    Row(
                        Modifier
                            .padding(vertical = 2.dp)
                    ) {
                        Icon(Icons.Filled.LocationOn, "location",
                            tint = MaterialTheme.colorScheme.secondary)
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 10.dp),
                            textAlign = TextAlign.Start,
                            text = address
                        )
                    }



                }

//              Bli med og kart-greie




//              Button
                Row(
                    Modifier
                        .padding(5.dp)
                        .align(Alignment.End)){
                    Button(onClick = {
/*                        navController.currentBackStackEntry?.savedStateHandle?.set(key = "article", value = data)
                        focusArticle()
                        navigateUpTo(navController, Screen.ArticleFull)
*/

                    }) {
                        Text(
                            text = "Details"
                        )
                    }
                }

            }

        }
    }
    /*
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
                placeholder = painterResource(R.drawable.testing),
                contentDescription = (event.title),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()

                    .padding(bottom = 5.dp)

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
                textAlign = TextAlign.Left,
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

     */
}