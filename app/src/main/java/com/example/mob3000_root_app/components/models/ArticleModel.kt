package com.example.mob3000_root_app.components.models

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mob3000_root_app.data.*
import kotlinx.coroutines.launch

class ArticleModel : ViewModel() {

    var articleListResponse: List<ArticleData> by mutableStateOf(listOf())
    var articleByIDResponse by mutableStateOf(emptyArticleData)
    var errorMessage: String by mutableStateOf("")
    var focusedArticle by mutableStateOf(emptyArticleData)
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

    fun postComment(articleID: String, text: String) {
        viewModelScope.launch {
            val apiService = RootService.getInstance()
            try {
                val postedStatus = apiService.postComment(
                    "article",
                    CommentData(articleID, text)
                )
                Log.i("CommentStatus", postedStatus.toString())
            } catch (e: Exception) {
                Log.i("Catch", e.message.toString())
            }
        }
    }

    fun postArticleComment(articleID: String, text: String, articleid: String) {
        viewModelScope.launch {
            val apiService = RootService.getInstance()
            try {
                val postedStatus = apiService.postComment(
                    "article",
                    CommentData(articleID, text)
                )
                Log.i("CommentStatus", postedStatus.toString())
                apiService.getArticleByID(articleid)
                focusedArticle = articleByIDResponse
            } catch (e: Exception) {
                Log.i("Catch", e.message.toString())
            }
        }
    }

    fun getArticleByID(articleid: String) {

        viewModelScope.launch {
            val apiService = RootService.getInstance()
            try{
                articleByIDResponse = apiService.getArticleByID(articleid)
                Log.i("Try: Article API Call", articleByIDResponse.toString())
            }
            catch (e: Exception){
                Log.i("Catch", e.message.toString())
            }
        }
    }

    fun focusArticleByID(articleid: String){

        viewModelScope.launch {
            val apiService = RootService.getInstance()
            try{
                focusedArticle = apiService.getArticleByID(articleid)
                Log.i("Try: Article API Call", focusedArticle.title)
            }
            catch (e: Exception){
                Log.i("Catch", e.message.toString())
            }
        }
    }

    fun focusArticle(focusArticle: ArticleData){
        focusedArticle = focusArticle
    }
}