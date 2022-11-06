@file:JvmName("ArticleAdminaKt")

package com.example.mob3000_root_app.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mob3000_root_app.components.editables.EditableArticle

@Composable
fun ArticleAdmin(
    navController: NavHostController,
    articleModel: ArticlesModel
) {
    val testColors: CardColors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.background)

    Surface() {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {

            Text(
                text = "Article Admin",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.headlineLarge
            )

            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(items = articleModel.articleListResponse) { item ->
                    EditableArticle(articleData = item)
                }
            }
        }
    }

}