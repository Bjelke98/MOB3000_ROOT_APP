package com.example.mob3000_root_app.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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
import com.example.mob3000_root_app.data.apiResponse.ArticleData



@Composable
fun EditArticleCard(
    navHost: NavHostController,
    articleData : ArticleData,
    editFocus: ()-> Unit
){
    val testColors: CardColors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.background)
    val image = articleData.image ?: "defaultArticle.jpg"
    Card(Modifier.padding(horizontal = 15.dp), colors = testColors) {
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
                        .data("https://linrik.herokuapp.com/api/resources/$image")
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
                text  = articleData.title,
                Modifier.widthIn(0.dp,150.dp).padding(10.dp) ,
                overflow = TextOverflow.Ellipsis,
            )

            Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
                OutlinedButton( onClick = { /*TODO*/ },
                    Modifier.size(90.dp,40.dp)
                ) {
                    Text(text  = "Delete")
                }

                OutlinedButton(
                    onClick = { /*TODO*/
                        editFocus()
                        navigateUpTo(navHost, Screen.EditArticle)
                    },
                    Modifier.size(90.dp,40.dp)) {
                    Text(text  = "Edit")
                }
            }
        }
    }
}
