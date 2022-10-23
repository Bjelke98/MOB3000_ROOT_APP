package com.example.mob3000_root_app.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.cards.CommentSection
import com.example.mob3000_root_app.data.ArticleData
import com.example.mob3000_root_app.data.ArticleTestdata

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ArticleFull(
    navController: NavHostController, in_articleData: ArticleData?
) {
    var isCommenting by remember{ mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val articleData = in_articleData!!

//    val articleData = focusedArticleModel.focusedArticle!!

    Log.d("FullArticle", articleData.title)

    Box(
        Modifier
        .clickable(
            interactionSource = interactionSource,
            indication = null    // this gets rid of the ripple effect
        ) {
            focusManager.clearFocus(true)
            keyboardController?.hide()
        }
    ) {
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
                        text = articleData.title, Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.headlineLarge,
                    )
                }
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://linrik.herokuapp.com/api/resources/" + articleData.image)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.sauce),
                    contentDescription = (articleData.description),
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

                if (keyboardController != null) {
                    CommentSection(
                        comments = ArticleTestdata().comments,
                        isCommenting = isCommenting,
                        onCommentingChanged = {isCommenting = !isCommenting},
                        keyboardController = keyboardController
                    )
                }

            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ArticlePreview() {
//    ArticleFull(rememberNavController()/*,ArticleTestdata().dataList[1]*/, )
}