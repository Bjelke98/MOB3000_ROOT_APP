package com.example.mob3000_root_app.components.cards

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.viewmodel.EventViewModel
import com.example.mob3000_root_app.data.apiResponse.EventData
import com.example.mob3000_root_app.data.apiResponse.ResponseStatus
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun EditableEvent(
    eventData : EventData,
    eventViewModel: EventViewModel
){
    val image = eventData.image ?: "defaultEvent.jpg"

    val testColors= MaterialTheme.colorScheme.background
    val openDialog = remember { mutableStateOf(false) }

    val editSwipe = SwipeAction(
        icon = painterResource(R.drawable.edit_icon),
        background = Color.Cyan,
        onSwipe = {


            // navigere til edit event
        }
    )
    val deleteSwipe = SwipeAction(
        icon = painterResource(R.drawable.delete_icon),
        background = Color.Red,
        onSwipe = {
            openDialog.value = true
        }
    )
    val context = LocalContext.current
    if (openDialog.value){
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = "Slett " + eventData.title)
            },
            text = {
                Text(text = "Er du sikker på av du vil slette: \n" + eventData.title)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        eventViewModel.deleteEventById(eventData._id){ status: ResponseStatus? ->
                            if(status!=null){
                                eventViewModel.getEventList()
                            } else {
                                Toast.makeText(context, "Error deleting event", Toast.LENGTH_LONG).show()
                            }
                        }
                        openDialog.value = false
                    }
                ) {
                    Text("Slett")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text("Avbryt")
                }
            }
        )
    }


    SwipeableActionsBox(
        modifier = Modifier,// må endres til å hente fra bakrunnen til
        startActions = listOf(editSwipe),
        endActions = listOf(deleteSwipe),
        swipeThreshold = (LocalConfiguration.current.screenWidthDp.dp/100)*15,
        backgroundUntilSwipeThreshold = testColors
    ) {
        Card() {
            Row(
                Modifier
                    .padding(10.dp)
                    .height(100.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    Modifier
                        .sizeIn(0.dp, 0.dp, 100.dp, 100.dp)
                ) {


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
                    )
                }

                Text(
                    text  = eventData.title,
                    Modifier
                        .widthIn(0.dp, 200.dp)
                        .padding(10.dp) ,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }



}