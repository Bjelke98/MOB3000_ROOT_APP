package com.example.mob3000_root_app.components.cards

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.navigation.Screen
import com.example.mob3000_root_app.components.navigation.navigateUpTo
import com.example.mob3000_root_app.data.apiResponse.ArticleData
import com.example.mob3000_root_app.data.ArticleType


@Composable
fun ArticleCard(
    navController: NavHostController,
    articleData: ArticleData,
    type: ArticleType,
    focusArticle: () -> Unit
) {

    val image = articleData.image ?: "defaultArticle.png"

    val testColors: CardColors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.background)

    val configuration = LocalConfiguration.current
    val contentHeight60per = (configuration.screenHeightDp.dp/3)*2
    val contentWidth80per = (configuration.screenWidthDp.dp/10)*8
    val contentWidth60per = (configuration.screenWidthDp.dp/10)*6

    val horizontalColMods = Modifier.width(contentWidth80per)
    val horizontalColAndViewMods = Modifier.width(contentWidth60per)
    val verticalColMods = Modifier.fillMaxWidth()

    val verticalImageModifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(.5f)
    val horizontalImageModifier = Modifier
        .fillMaxWidth(.4f)
        .fillMaxHeight()

    Card(
        Modifier
            .fillMaxWidth()
            .height(contentHeight60per)
        , colors = testColors){
        BoxWithConstraints(Modifier.fillMaxSize()) {
            // Vertical phone
            if(maxHeight>400.dp) {
                Column(modifier = if( type == (ArticleType.VERTICAL_ARTICLE)) verticalColMods else horizontalColMods) {
                    ArticleAsyncImage(image = image, verticalImageModifier)
                    Column(
                        Modifier
                            .fillMaxHeight(.75f)
                            .padding(5.dp)
                    ) {
                        TitleVertical(text = articleData.title)
                        DescriptionVertical(text = articleData.description)
                    }
                    Row(
                        Modifier
                            .padding(5.dp)
                            .align(Alignment.End)){
                        ReadMoreButton(navController = navController){
                            focusArticle()
                        }
                    }
                }
            } else {
                // Horizontal phone
                Row(modifier = if (type == (ArticleType.VERTICAL_ARTICLE)) verticalColMods else horizontalColAndViewMods) {
                    ArticleAsyncImage(image = image, horizontalImageModifier)
                    Column(
                        Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Column(Modifier.fillMaxHeight(.75f)) {
                            TitleVertical(text = articleData.title)
                            DescriptionVertical(text = articleData.description)
                        }
                        Row(
                            modifier = Modifier
                                .align(Alignment.End)
                                .fillMaxHeight(),
                            verticalAlignment = Alignment.Bottom
                        ) {
                            ReadMoreButton(navController = navController) {
                                focusArticle()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TitleVertical(text:String) {
    Text(text = text,
        Modifier
            .fillMaxWidth()
            .padding(5.dp),
        style = MaterialTheme.typography.headlineSmall
    )
}

@Composable
fun DescriptionVertical(text:String) {
    Text(text = text,
        Modifier
            .fillMaxWidth()
            .padding(5.dp), style = MaterialTheme.typography.bodyMedium,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun ArticleAsyncImage(image: String, modifier: Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data("https://linrik.herokuapp.com/api/resources/$image")
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.testing),
        contentDescription = ("Image could not load"),
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}

@Composable
fun ReadMoreButton(navController: NavHostController, focusArticle: () -> Unit) {
    Button(onClick = {
        focusArticle()
        navigateUpTo(navController, Screen.ArticleFull)
    }) {
        Text(stringResource(id = R.string.read_more))
    }
}