package com.example.mob3000_root_app.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.mob3000_root_app.data.ArticleData
import com.example.mob3000_root_app.data.ArticleType


@Composable
fun Article(data :ArticleData, type: ArticleType) {

    val title = data.title
    val image = data.image
    val  imageDescription = data.imageDescription

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

                Image(
                    painterResource(image), imageDescription,
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(.5f)
                    , contentScale = ContentScale.Crop
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

                    Text(text = "Subheader",
                        Modifier
                            .fillMaxWidth()
                            .padding(5.dp), style = MaterialTheme.typography.titleMedium
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
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Learn More")
                    }
                }
            }
        }
    }
}
