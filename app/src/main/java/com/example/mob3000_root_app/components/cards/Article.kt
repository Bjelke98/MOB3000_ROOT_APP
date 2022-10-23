package com.example.mob3000_root_app.components.cards

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.navigation.Screen
import com.example.mob3000_root_app.components.navigation.navigateUpTo
import com.example.mob3000_root_app.components.navigation.navigateUpToFullArticle
import com.example.mob3000_root_app.data.ArticleData
import com.example.mob3000_root_app.data.ArticleType
import kotlinx.coroutines.launch


@Composable
fun Article(navController: NavHostController, data : ArticleData, type: ArticleType) {

    val testColors: CardColors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.background)

    val configuration = LocalConfiguration.current
    val contentHeight60per = (configuration.screenHeightDp.dp/3)*2
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

                   AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://linrik.herokuapp.com/api/resources/" + data.image)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.sauce),
                    contentDescription = ("Image could not load"),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(.5f)
                )

                Column(
                    Modifier
                        .fillMaxHeight(.75f)
                        .padding(5.dp)
                ) {
                    Text(text = data.title,
                        Modifier
                            .fillMaxWidth()
                            .padding(5.dp), style = MaterialTheme.typography.headlineSmall
                    )

                    Text(text = data.description,
                        Modifier
                            .fillMaxWidth()
                            .padding(5.dp), style = MaterialTheme.typography.bodyMedium,
                        overflow = TextOverflow.Ellipsis
                    )

                }
                Row(
                    Modifier
                        .padding(5.dp)
                        .align(Alignment.End)){
                    Button(onClick = {
//                        coroutineScope.launch { val data = ArticleAPI.retrofitService.getAllArticles() }
                        navigateUpToFullArticle(navController, Screen.ArticleFull, data)

                    }) {
                        Text(text = "Learn More")
                    }
                }
            }
        }
    }
}