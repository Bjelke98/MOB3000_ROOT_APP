package com.example.mob3000_root_app.components.editables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.data.ArticleData
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@Composable
fun EditableArticle(
    articleData : ArticleData
){
    Card() {
        Row(
            Modifier
                .padding(10.dp)
                .height(100.dp)
                .fillMaxWidth()) {
            Box(Modifier.sizeIn(0.dp,0.dp,100.dp,100.dp)) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://linrik.herokuapp.com/api/resources/" + articleData.image)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.testing),
                    contentDescription = ("Image could not load"),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            Text(text  = articleData.title, Modifier.widthIn(0.dp,200.dp) , overflow = TextOverflow.Ellipsis)

            Column() {
                OutlinedButton( onClick = { /*TODO*/ }) {

                    Text(text  = "Delete", Modifier.align(CenterVertically))
                }

                OutlinedButton(onClick = { /*TODO*/ }) {
                    Text(text  = "Edit", Modifier.align(CenterVertically))
                }
            }

        }
    }
}
