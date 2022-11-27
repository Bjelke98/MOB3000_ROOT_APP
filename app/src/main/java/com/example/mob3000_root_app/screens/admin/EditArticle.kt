package com.example.mob3000_root_app.screens.admin


import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
import com.example.mob3000_root_app.components.navigation.Screen
import com.example.mob3000_root_app.components.navigation.navigateUpTo
import com.example.mob3000_root_app.components.viewmodel.AppViewModel
import com.example.mob3000_root_app.components.viewmodel.conditional
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditArticle(appVM: AppViewModel) {

    val ppArticleVM = appVM.ppArticleVM
    var title by remember { mutableStateOf(
            if( ppArticleVM.isNewArticle) ""
            else ppArticleVM.focusedArticle.title
    )}
    var description by remember { mutableStateOf(
        if( ppArticleVM.isNewArticle) ""
        else ppArticleVM.focusedArticle.description
    )}
    val image = if(!ppArticleVM.isNewArticle)
                    {ppArticleVM.focusedArticle.image ?: "defaultArticle.png" }
                else
                    { "defaultArticle.png" }
    val context = LocalContext.current

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var isImageChosen by remember { mutableStateOf(false) }

    val incompleteFieldsToast = Toast.makeText(context, stringResource(id = R.string.fill_title_descr), Toast.LENGTH_SHORT)


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        run {
            if (uri != null) {
                imageUri = uri
                isImageChosen = true
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if(title.isNotBlank() && description.isNotBlank()) {
                        if(appVM.ppArticleVM.isNewArticle)
                            ppArticleVM.postArticle(title, description, imageUri, context)
                        else
                            ppArticleVM.updateArticle(
                                title,
                                description,
                                ppArticleVM.focusedArticle._id,
                                imageUri,
                                context
                            )

                        navigateUpTo(appVM.navController,Screen.ArticleAdmin)
                    }
                    else {
                        incompleteFieldsToast.show()
                        Log.i("Post", "missing Info")
                    }
                },
                contentColor = MaterialTheme.colorScheme.onPrimary,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Filled.Send, "Upload article")
            }
        }
    ) { padding ->

        BoxWithConstraints(Modifier.fillMaxSize()) {
// Vertical View
            if(maxHeight>400.dp) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    //Title textfield
                    TextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text(stringResource(id = R.string.edit_card_title)) },
                        maxLines = 2,
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    // Description Textfield
                    TextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text(stringResource(id = R.string.edit_card_description)) },
                        maxLines = 5,
                        modifier = Modifier.fillMaxWidth()
                    )
                    // Image button
                    Button(onClick = {
                        launcher.launch("image/*")

                    }) {
                        Text(stringResource(R.string.upload_picture_button))
                    }

                    ImageDisplayBox(
                        width = 300.dp,
                        height = 250.dp,
                        imageUrl = image,
                        uri = imageUri,
                        context = context,
                        isNew = appVM.ppArticleVM.isNewArticle,
                        isChosen = isImageChosen,
                        MaterialTheme.colorScheme.surfaceVariant
                    )
                }
            }
//  Horizontal View
            else{
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(.5f)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        //Title textfield
                        TextField(
                            value = title,
                            onValueChange = { title = it },
                            label = { Text(stringResource(id = R.string.edit_card_title)) },
                            maxLines = 2,
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                        // Description Textfield
                        TextField(
                            value = description,
                            onValueChange = { description = it },
                            label = { Text(stringResource(id = R.string.edit_card_description)) },
                            maxLines = 3,
                            modifier = Modifier.fillMaxWidth()
                        )

                        // Image button
                        Button(
                            onClick = {
                                launcher.launch("image/*")

                            }) {
                            Text(stringResource(R.string.upload_picture_button))
                        }
                    }
                        BoxWithConstraints(Modifier.fillMaxSize()) {
                        ImageDisplayBox(
                            width = maxWidth,
                            height = maxHeight,
                            imageUrl = image,
                            uri = imageUri,
                            context = context,
                            isNew = appVM.ppArticleVM.isNewArticle,
                            isChosen = isImageChosen,
                            MaterialTheme.colorScheme.surfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ImageDisplayBox(width:Dp, height:Dp, imageUrl: String, uri: Uri?, context: Context,
                    isNew: Boolean, isChosen: Boolean, borderColor: Color
){
    Box(
        modifier = Modifier
            .size(width, height)
            .conditional(
                isNew && !isChosen,
                ifTrue = {border(2.dp, borderColor, RectangleShape)}
            )
    ) {
        if(!isChosen) {
            FullSizeAsyncImage(imageUrl)
        }
        else {
            bitmapDisplay(uri, context)
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
fun bitmapDisplay(uri: Uri?, context: Context){
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