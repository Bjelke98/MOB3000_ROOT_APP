package com.example.mob3000_root_app.components.image

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.viewmodel.conditional

@Composable
fun ImageDisplayBox(width: Dp, height: Dp, imageUrl: String, uri: Uri?, context: Context,
                    isNew: Boolean, isChosen: Boolean, borderColor: Color
){
    Box(
        modifier = Modifier
            .size(width, height)
            .conditional(
                isNew && !isChosen || imageUrl == "defaultArticle.png" || imageUrl == "defaultEvent.png",
                ifTrue = {border(2.dp, borderColor, RectangleShape)}
            )
    ) {
        if(!isChosen) {
            FullSizeAsyncImage(imageUrl)
        }
        else {
            BitmapDisplay(uri, context)
        }
    }
}

@Composable
fun FullSizeAsyncImage(path: String){
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(
                "https://linrik.herokuapp.com/api/resources/$path"
            )
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.testing),
        contentDescription = (stringResource(id = R.string.image_load_failed)),
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth()
    )
}



@Composable
fun BitmapDisplay(uri: Uri?, context: Context){
    var tempBitmap: Bitmap?
    uri?.let {
        val source = ImageDecoder.createSource(context.contentResolver, uri)

        tempBitmap = ImageDecoder.decodeBitmap(source)

        tempBitmap?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}