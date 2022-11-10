package com.example.mob3000_root_app.components.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mob3000_root_app.data.apiResponse.ArticleData
import com.example.mob3000_root_app.data.apiResponse.emptyArticleData

class EditArticleVM: ViewModel() {

    var focusedArticle by mutableStateOf(emptyArticleData)

    fun focusArticle(focusArticle: ArticleData){
        focusedArticle = focusArticle
    }
}