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
import com.example.mob3000_root_app.components.ArticleApiService.ArticleApiService
import com.example.mob3000_root_app.components.cards.Article
import com.example.mob3000_root_app.data.ArticleData
import com.example.mob3000_root_app.data.ArticleTestdata
import com.example.mob3000_root_app.data.ArticleType
import kotlinx.coroutines.launch

@Composable
fun Articles(navController: NavHostController, articleList: List<ArticleData>) {

    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {

        Column(Modifier.padding(10.dp)) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                items(items = articleList){ article ->
                    Article(navController ,data = article, ArticleType.VERTICAL_ARTICLE)
                }
            }
        }
    }
}

class ArticlesModel : ViewModel() {
    var articleListResponse: List<ArticleData> by mutableStateOf(listOf())

    var errorMessage: String by mutableStateOf("")

    fun getArticleList() {
        viewModelScope.launch {
            val apiService = ArticleApiService.getInstance()
            try {
                val articleList = apiService.getArticles()
                articleListResponse = articleList
                Log.i("data", articleList.get(0).image)
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}