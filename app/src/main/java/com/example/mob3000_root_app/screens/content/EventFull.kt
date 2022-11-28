package com.example.mob3000_root_app.screens.content
import android.content.pm.*
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.cards.CommentSectionEvent
import com.example.mob3000_root_app.components.viewmodel.AppViewModel
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter


@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EventFull(
    appVM: AppViewModel
) {
    val eventVM = appVM.eventVM

    var openComments by remember { mutableStateOf(false) }
    val adresse = eventVM.focusedEvent.address
    // Blir satt til false i koden ved oppstart
    var isCommenting by remember{ mutableStateOf(true) }
    val interactionSource = remember { MutableInteractionSource() }

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var scrollState = rememberScrollState()

    val eventData = eventVM.focusedEvent
    val image = eventVM.focusedEvent.image ?: "defaultEvent.png"
    val coroutineScope = rememberCoroutineScope()

    //animasjon for kommentarer
    val density = LocalDensity.current

    val dateTimeFrom = Instant.parse(eventData.dateFrom).atOffset(
        ZoneOffset.ofHours(2))
    val dateTimeTo = Instant.parse(eventData.dateTo).atOffset(ZoneOffset.ofHours(2))
    val dateFormatFromHour = DateTimeFormatter.ofPattern("HH:mm")
    val dateFormatToHour = DateTimeFormatter.ofPattern("HH:mm")

    val dateFormatMonth = DateTimeFormatter.ofPattern("MMM")
    val dateFormatDay = DateTimeFormatter.ofPattern("dd")
    val dateFormatFull = DateTimeFormatter.ofPattern("dd.MM.yyyy") //

    var eventJoined: Boolean by remember { mutableStateOf(eventVM.joinedEvents.contains(eventData)) }

    var count by remember {
        mutableStateOf(eventData.participants.size)
    }
    Surface(
        Modifier
            .background(MaterialTheme.colorScheme.background)
            .clickable(
                interactionSource = interactionSource,
                indication = null    // skjuler unødvendig visuell indikasjon
            ) {
                focusManager.clearFocus(true)
                keyboardController?.hide()
            }) {
        Column(
            Modifier
                .fillMaxHeight()
                .padding(15.dp)
                .verticalScroll(scrollState), verticalArrangement = Arrangement.SpaceBetween
        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://linrik.herokuapp.com/api/resources/$image")
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.sauce),
                contentDescription = (eventData.description),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(15.dp))
                    .height(200.dp)
            )

            Row(modifier = Modifier
                .padding(top = 5.dp)) {
                Icon(
                    Icons.Filled.CalendarToday, "date",
                    tint = MaterialTheme.colorScheme.primary)
                Text(
                    modifier = Modifier
                        .padding(horizontal = 10.dp),
                    textAlign = TextAlign.Start,
                    text = dateTimeFrom.format(dateFormatFull)

                )
                Text(
                    modifier = Modifier
                        .padding(horizontal = 10.dp),
                    textAlign = TextAlign.Start,
                    text = dateTimeFrom.format(dateFormatFromHour) + " - " + dateTimeTo.format(dateFormatToHour)

                )
            }

            Text(
                text = eventData.title.uppercase(),
                Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                text = eventData.description,
                Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis
            )
            val context = LocalContext.current
            Button(onClick = {
                val gmmIntentUri =
                    Uri.parse("geo:0,0?q=$adresse")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(context,mapIntent,null)
            }) {
                Icon(
                    Icons.Filled.LocationOn, "map")
                Text(
                    modifier = Modifier
                        .padding(horizontal = 10.dp),
                    textAlign = TextAlign.Start,
                    text = "$adresse"
                )
            }
            Button(onClick = {
            }) {
                Icon(
                    Icons.Filled.People, "participants")
                Text(
                    modifier = Modifier
                        .padding(horizontal = 10.dp),
                    textAlign = TextAlign.Start,
                    text = ""+count + " " + stringResource(id = R.string.interested)
                )
            }



            Spacer(modifier = Modifier.weight(1f))
            // Sørge for at påmelding lagrer seg $TODO
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                onClick = {
                        eventJoined = if(!eventJoined) {
                            count++
                            eventVM.joinEvent(appVM.eventVM.focusedEvent._id)
                            true
                        } else {
                            count--
                            eventVM.leaveEvent(appVM.eventVM.focusedEvent._id)
                            false
                        }
                },
                shape = RoundedCornerShape(25.dp),

                ) {
                Text(text = (
                    if(!eventJoined) {
                        stringResource(id = R.string.join)
                    } else {
                        stringResource(id = R.string.sign_off)
                    }
                    ).uppercase(),

                )
                // OnClick text blir til "Meld av".uppercase()
            }

            AnimatedVisibility((openComments),
                enter = slideInVertically {
                    with(density) { -30.dp.roundToPx() }
                } + expandVertically(
                    expandFrom = Alignment.Bottom
                )
                ,
                exit = slideOutVertically() + shrinkVertically(shrinkTowards = Alignment.Top)/* + fadeOut()*/

            ){
                if (keyboardController != null) {
                    CommentSectionEvent(
                        onCommentingChanged = { isCommenting = !isCommenting },
                        isCommenting = isCommenting,
                        keyboardController = keyboardController,
                        focusManager = focusManager,
                        appVM = appVM,
                        eventId = eventData._id
                    )
                }
                //Scroller ned til bunnen når kommentarer åpnes. Kunne ikke settes på
                // onclick fordi kommentarene ble ferdig composed etter launch ble ferdig
                coroutineScope.launch {
                    scrollState.animateScrollTo(2000)
                }
            }

            IconButton(
                onClick = {
                    openComments = !openComments
                },
                Modifier
                    .padding(4.dp, bottom = 1.dp)
                    .align(CenterHorizontally)
            ) {
                Icon(
                    painter = painterResource(
                        id = (
                            if (!openComments) {
                                R.drawable.ic_baseline_chat_bubble_outline_24
                            } else {
                                R.drawable.ic_baseline_keyboard_arrow_down_24
                            }
                        )
                    ),
                    contentDescription = "Comments"
                )
            }
        }
    }
}


