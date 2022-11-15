package com.example.mob3000_root_app.screens.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mob3000_root_app.components.cards.ArticleCard
import com.example.mob3000_root_app.components.viewmodel.ArticleViewModel
import com.example.mob3000_root_app.data.*

@Composable
fun Articles(
    navController: NavHostController,
    articleViewModel: ArticleViewModel,
//    focusedArticleModel: FocusedArticleModel
) {

    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {

        Column(Modifier.padding(10.dp)) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                items(items = articleViewModel.articleListResponse){ article ->
                    ArticleCard(
                        navController,
                        articleData = article, ArticleType.VERTICAL_ARTICLE,
                        { articleViewModel.focusArticle(article) }
                    )
                }
            }
        }
    }
}

