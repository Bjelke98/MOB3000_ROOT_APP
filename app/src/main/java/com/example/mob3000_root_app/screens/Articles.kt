package com.example.mob3000_root_app.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.mob3000_root_app.components.cards.ArticleCard
import com.example.mob3000_root_app.components.models.ArticleModel
import com.example.mob3000_root_app.data.*
import kotlinx.coroutines.launch

@Composable
fun Articles(
    navController: NavHostController,
    articleModel: ArticleModel,
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
                items(items = articleModel.articleListResponse){ article ->
                    ArticleCard(
                        navController,
                        articleData = article, ArticleType.VERTICAL_ARTICLE,
                        { articleModel.focusArticle(article) }
                    )
                }
            }
        }
    }
}


