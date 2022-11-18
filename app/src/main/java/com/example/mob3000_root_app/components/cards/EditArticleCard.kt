package com.example.mob3000_root_app.components.cards

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.navigation.Screen
import com.example.mob3000_root_app.components.navigation.navigateUpTo
import com.example.mob3000_root_app.components.viewmodel.AppViewModel
import com.example.mob3000_root_app.data.apiResponse.ArticleData
import com.example.mob3000_root_app.data.apiResponse.ResponseStatus


@Composable
fun EditArticleCard(
    appVM: AppViewModel,
    articleData : ArticleData,
    editFocus: ()-> Unit
){
    val image = articleData.image ?: "defaultArticle.jpg"
    val openDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current

    if (openDialog.value){
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = stringResource(id = R.string.delete_button) + articleData.title)
            },
            text = {
                Text(text = stringResource(id = R.string.confirm_delete) + "\n" + articleData.title)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        appVM.articleVM.deleteArticleByID(articleData._id){ status: ResponseStatus? ->
                            if(status!=null){
                                appVM.articleVM.getArticleList()
                            } else {
                                Toast.makeText(context, "Error deleting event", Toast.LENGTH_LONG).show()
                            }
                        }
                        openDialog.value = false
                    }
                ) {
                    Text(stringResource(id = R.string.delete_button))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text(stringResource(id = R.string.cancel_button))
                }
            }
        )
    }

    Card() {
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
                    contentDescription = (stringResource(id = R.string.image_load_failed)),
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
                OutlinedButton(
                    onClick = {
                          openDialog.value = true
                    },
                    Modifier.size(90.dp,40.dp)
                ) {
                    Text(text  = stringResource(id = R.string.delete_button))
                }

                OutlinedButton(
                    onClick = { /*TODO*/
                        editFocus()
                        navigateUpTo(appVM.navController, Screen.EditArticle)
                    },
                    Modifier.size(90.dp,40.dp)) {
                    Text(text  = stringResource(id = R.string.edit_button))
                }
            }
        }
    }
}
