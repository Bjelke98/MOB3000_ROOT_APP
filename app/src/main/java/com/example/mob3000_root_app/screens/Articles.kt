package com.example.mob3000_root_app.screens

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
import com.example.mob3000_root_app.components.cards.Article
import com.example.mob3000_root_app.data.ArticleData
import com.example.mob3000_root_app.data.ArticleType
import com.example.mob3000_root_app.data.RootService
import kotlinx.coroutines.launch

class ArticlesModel : ViewModel() {
    var articleListResponse: List<ArticleData> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")
    var focusedArticle by mutableStateOf<ArticleData?>(null)
        private set

    fun getArticleList() {
        viewModelScope.launch {
            val apiService = RootService.getInstance()
            try {
                val articleList = apiService.getArticles()
                articleListResponse = articleList
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun focusArticle(focusArticle: ArticleData){
        focusedArticle = focusArticle
    }
}

@Composable
fun Articles(
    navController: NavHostController,
    articleModel: ArticlesModel,
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
                    Article(
                        navController,
                        articleData = article, ArticleType.VERTICAL_ARTICLE,
//                        focusedArticleModel
                        { articleModel.focusArticle(article) }
                    )
                }
            }
        }
    }
}


