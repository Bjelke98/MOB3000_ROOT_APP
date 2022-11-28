package com.example.mob3000_root_app.components.cards

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.navigation.Screen
import com.example.mob3000_root_app.components.navigation.navigateUpTo
import com.example.mob3000_root_app.components.viewmodel.AppViewModel
import com.example.mob3000_root_app.data.ArticleType
import com.example.mob3000_root_app.data.apiResponse.EventData
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter



@Composable
fun EventCard(
    event: EventData,
    type: ArticleType,
    appVM: AppViewModel
    )
{
    val image = event.image ?: "defaultEvent.png"

    // Andre dato formateringer
    // val dateFormatMonth = DateTimeFormatter.ofPattern("MMM")
    // val dateFormatDay = DateTimeFormatter.ofPattern("dd")
    // val dateFormatFull = DateTimeFormatter.ofPattern("dd-mm-yyyy")

    val testColors: CardColors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.background)

    val configuration = LocalConfiguration.current
    val contentHeight60per = (configuration.screenHeightDp.dp/3)*2
    val contentWidth80per = (configuration.screenWidthDp.dp/10)*8
    val contentWidth60per = (configuration.screenWidthDp.dp/10)*6

    val horizontalColMods = Modifier.width(contentWidth80per)
    val horizontalColAndViewMods = Modifier.width(contentWidth60per)
    val verticalColMods = Modifier.fillMaxWidth()

    val verticalImageModifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(.5f)
    val horizontalImageModifier = Modifier
        .fillMaxWidth(.4f)
        .fillMaxHeight()

    Card(
        Modifier
            .fillMaxWidth()
            .height(contentHeight60per)
        , colors = testColors){
        BoxWithConstraints(Modifier.fillMaxSize()) {

            if(maxHeight>400.dp){
                //Vertical phone
                Column(modifier = if( type == (ArticleType.VERTICAL_ARTICLE)) verticalColMods else horizontalColMods) {
                    EventImage(image = image, event = event, imageModifier = verticalImageModifier)

                    Column(
                        Modifier
                            .fillMaxHeight(.7f)
                            .padding(5.dp)
                    ) {
                        EventTitle(title = event.title)
                        EventPreviewDetails(event = event)
                    }

                    Row(
                        Modifier
                            .padding(5.dp)
                            .fillMaxWidth()
                            .align(Alignment.End)){
                        DetailsButton(appVM = appVM, event = event)
                    }

                }
            } else {
                // Horizontal phone
                Row(modifier = if( type == (ArticleType.VERTICAL_ARTICLE)) verticalColMods else horizontalColAndViewMods){

                    EventImage(image = image, event = event, imageModifier = horizontalImageModifier)
                    Column (
                        Modifier.fillMaxSize().padding(10.dp)
                    ){
                        Column(
                            Modifier
                                .fillMaxHeight(.75f)
                        ) {
                            EventTitle(title = event.title)
                            EventPreviewDetails(event = event)

                        }
                        Row(
                            Modifier
                                .padding(5.dp)
                                .align(Alignment.End)
                                .fillMaxHeight(),
                            verticalAlignment = Alignment.Bottom
                        ){
                            DetailsButton(appVM = appVM, event = event)
                        }
                    }

                }
            }

        }
    }
}

@Composable
fun DetailsButton(appVM: AppViewModel, event: EventData) {
    Button(onClick = {
        appVM.eventVM.prepFullEvent(event){
            navigateUpTo(appVM.navController, Screen.EventFull)
        }
    }) {
        Text(
            text = ("detaljer").uppercase()
        )
    }
}

@Composable
fun EventTitle(title: String) {
    Text(text = title,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .padding(5.dp),
        style = MaterialTheme.typography.headlineSmall
    )
}

@Composable
fun EventImage(image: String, event: EventData, imageModifier: Modifier) {
    Box {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://linrik.herokuapp.com/api/resources/$image")
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.testing),
            contentDescription = ("Image could not load"),
            contentScale = ContentScale.Crop,
            modifier = imageModifier
        )
        ShowDate(eventData = event)
    }
}

@Composable
fun EventPreviewDetails(event: EventData) {
    val address: String = if (event.address == null)  "Adresse" else event.address
    val dateTimeFrom = Instant.parse(event.dateFrom).atOffset(
        ZoneOffset.ofHours(2))
    val dateTimeTo = Instant.parse(event.dateTo).atOffset(ZoneOffset.ofHours(2))
    val dateFormatFromHour = DateTimeFormatter.ofPattern("HH:mm")
    val dateFormatToHour = DateTimeFormatter.ofPattern("HH:mm")
    Column {
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
}