package com.example.mob3000_root_app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.navigation.compose.rememberNavController
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.cards.CommentSection
import com.example.mob3000_root_app.components.cards.conditional
import com.example.mob3000_root_app.data.ArticleTestdata

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ArticleFull(
    navController: NavHostController/*, articleData: ArticleData*/
) {

    var isCommenting by remember{ mutableStateOf(false) }
    val data = ArticleTestdata().dataList[2]
    val interactionSource = remember { MutableInteractionSource() }

    Box {
        Surface(Modifier.background(MaterialTheme.colorScheme.background)) {
            Column(
                Modifier
                    .fillMaxHeight()
                    .padding(top = 5.dp), verticalArrangement = Arrangement.SpaceBetween) {

                Row(Modifier.fillMaxWidth()) {
                    IconButton(onClick = { navController.popBackStack() }, Modifier.padding(end = 10.dp)) {
                        Icon(painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24), contentDescription = "BackArrow")
                    }

                    Text(
                        text = data.title, Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.headlineLarge,
                    )
                }
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(data.image)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.sauce),
                    contentDescription = (data.description),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(.5f)
                        .padding(bottom = 20.dp)
                )

//                Text(text = ArticleTestdata().loremIpsum,
//                    Modifier
//                        .fillMaxWidth()
//                        .padding(5.dp), style = MaterialTheme.typography.bodyMedium,
//                    overflow = TextOverflow.Ellipsis
//                )

                IconButton(onClick = { /*navController.popBackStack()*/ },
                    Modifier
                        .padding(5.dp)
                        .align(CenterHorizontally)
                        .weight(1f, false))
                {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_chat_bubble_outline_24),
                        contentDescription = "Comments"
                    )
                }

                CommentSection(comments = ArticleTestdata().comments, isCommenting = isCommenting, onCommentingChanged = {isCommenting = !isCommenting})

            }
        }
    }

}

 fun getEvents(){

//    val liveData = ArticleAPI.retrofitService.getAllArticles()

}

@Preview(showBackground = true)
@Composable
fun ArticlePreview() {
    ArticleFull(rememberNavController()/*,ArticleTestdata().dataList[1]*/)
}