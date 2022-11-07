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
import com.example.mob3000_root_app.data.*
import kotlinx.coroutines.launch

class ArticlesModel : ViewModel() {
    var articleListResponse: List<ArticleData> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")
    var focusedArticle by mutableStateOf<ArticleData?>(null)
        private set
    val postedStatus: ResponseStatus by mutableStateOf(ResponseStatus(0))

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

    fun postComment(text: String, articleID: String) {
        viewModelScope.launch {
            val apiService = RootService.getInstance()
            try {
                val postedStatus = apiService.postComment("article",CommentData(text, articleID))
                Log.i("CommentStatus", postedStatus.toString())
            } catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.i("Catch", errorMessage)
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
                    ArticleCard(
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


