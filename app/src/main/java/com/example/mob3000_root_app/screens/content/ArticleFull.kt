package com.example.mob3000_root_app.screens.content

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.cards.CommentSectionArticle
import com.example.mob3000_root_app.components.viewmodel.AppViewModel
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ArticleFull(
    appVM: AppViewModel
) {
    val articleVM = appVM.articleVM

    var openComments by remember { mutableStateOf(false) }
    // Blir satt til false i koden ved oppstart
    var isCommenting by remember{ mutableStateOf(true) }
    val interactionSource = remember { MutableInteractionSource() }

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val scrollState = rememberScrollState()
    val image = articleVM.focusedArticle.image ?: "defaultArticle.png"

    val articleData = articleVM.focusedArticle
    val coroutineScope = rememberCoroutineScope()

    //animasjon for kommentarer
    val density = LocalDensity.current

    Surface(
        Modifier
            .background(MaterialTheme.colorScheme.background)
            .clickable(
                interactionSource = interactionSource,
                indication = null    // this gets rid of the ripple effect
            ) {
                focusManager.clearFocus(true)
                keyboardController?.hide()
            }) {
        BoxWithConstraints(Modifier.fillMaxSize()){
            val maxHeightOut = maxHeight
            Column(
                Modifier
                    .fillMaxHeight()
                    .padding(top = 5.dp)
                    .verticalScroll(scrollState), verticalArrangement = Arrangement.SpaceBetween
            ) {
    //            val configuration = LocalConfiguration.current
    //            val contentHeight90per = (configuration.screenWidthDp.dp/10)*9
                if(maxHeightOut>400.dp){
                    Row(
                        Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = articleData.title,
                            Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 10.dp),
                            style = MaterialTheme.typography.headlineLarge
                        )
                    }
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://linrik.herokuapp.com/api/resources/$image")
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(R.drawable.sauce),
                        contentDescription = (articleData.description),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(.5f)
                            .padding(bottom = 20.dp)
                            .heightIn(200.dp, 420.dp)
                    )
                    Text(
                        text = articleData.description,
                        Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        overflow = TextOverflow.Ellipsis
                    )
                } else {
                    Row(Modifier.fillMaxWidth()){
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("https://linrik.herokuapp.com/api/resources/$image")
                                .crossfade(true)
                                .build(),
                            placeholder = painterResource(R.drawable.sauce),
                            contentDescription = (articleData.description),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth(.3f)
                                .padding(bottom = 20.dp)
                                .height(200.dp)
                        )
                        Column(Modifier.fillMaxWidth()) {
                            Text(
                                text = articleData.title,
                                Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp, end = 10.dp),
                                style = MaterialTheme.typography.headlineLarge
                            )
                            Text(
                                text = articleData.description,
                                Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp),
                                style = MaterialTheme.typography.bodyLarge,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
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
                        CommentSectionArticle(
                            onCommentingChanged = { isCommenting = !isCommenting },
                            isCommenting = isCommenting,
                            keyboardController = keyboardController,
                            focusManager = focusManager,
                            appVM = appVM,
                            articleID = articleData._id
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
}