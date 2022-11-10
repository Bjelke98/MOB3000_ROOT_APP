package com.example.mob3000_root_app.components.cards

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.data.apiResponse.EventData

@Composable
fun EditableEvent(
    eventData : EventData
){
    val defaultImage = eventData.image ?: "defaultEvent.jpg"

    Card() {
        Row(
            Modifier
                .padding(10.dp)
                .height(100.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(Modifier.sizeIn(0.dp,0.dp,100.dp,100.dp)) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://linrik.herokuapp.com/api/resources/$defaultImage")
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
                Modifier.widthIn(0.dp,200.dp).padding(10.dp) ,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}