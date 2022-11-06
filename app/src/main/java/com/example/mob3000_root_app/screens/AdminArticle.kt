package com.example.mob3000_root_app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import com.example.mob3000_root_app.components.buttons.EndreArtikkel
import com.example.mob3000_root_app.components.buttons.SlettArtikkel
import com.example.mob3000_root_app.components.navigation.Screen
import com.example.mob3000_root_app.components.navigation.navigateUpTo
import com.example.mob3000_root_app.data.ArticleData
import com.example.mob3000_root_app.data.ArticleType


@Composable
fun AdminArticle(navController: NavHostController, data : ArticleData, type: ArticleType) {

    val title = data.title
    val image = data.image
    val imageDescription = "Image loading"

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
                        .data(image)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.sauce),
                    contentDescription = (imageDescription),
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
                    Text(text = "Hello $title!",
                        Modifier
                            .fillMaxWidth()
                            .padding(5.dp), style = MaterialTheme.typography.headlineSmall
                    )

                    Text(text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
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
                        SlettArtikkel()
                        EndreArtikkel()
                }
            }
        }
    }
}