package com.example.mob3000_root_app.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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

import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.navigation.Screen
import com.example.mob3000_root_app.components.navigation.navigateUpTo
import com.example.mob3000_root_app.data.ArticleType
import com.example.mob3000_root_app.data.apiResponse.EventData
import java.time.Instant
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Composable
fun EventCard(
    navController: NavHostController,
    event: EventData,
    type: ArticleType,
    focusEvent: () -> Unit
) {

    val address: String = if (event.address == null)  "Adresse" else event.address
    val image = event.image ?: "defaultEvent.jpg"

    val dateTimeFrom = Instant.parse(event.dateFrom).atOffset(
        ZoneOffset.ofHours(2))
    val dateTimeTo = Instant.parse(event.dateTo).atOffset(ZoneOffset.ofHours(2))
    val dateFormatFromHour = DateTimeFormatter.ofPattern("HH:mm")
    val dateFormatToHour = DateTimeFormatter.ofPattern("HH:mm")

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
                            .data("https://linrik.herokuapp.com/api/resources/$image")
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(R.drawable.testing),
                        contentDescription = ("Image could not load"),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(.5f)
                    )

                    showDate(eventData = event)
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
                        Icon(Icons.Filled.Timer, "timer",
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
/*
                        focusArticle()
                        navigateUpTo(navController, Screen.ArticleFull)
*/

                        focusEvent()
                        navigateUpTo(navController, Screen.EventFull)
                    }) {
                        Text(
                            text = ("detaljer").uppercase()
                        )
                    }
                }

            }

        }
    }
}