package com.example.mob3000_root_app.components.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.mob3000_root_app.components.navigation.Screen

class AppViewModel(
    var navController: NavHostController,
    var loginVM: LoginViewModel = LoginViewModel(),
    var articleVM: ArticleViewModel = ArticleViewModel(),
    var eventVM: EventViewModel = EventViewModel(),
    var ppArticleVM: PostPutArticleVM = PostPutArticleVM(),
    val startDestination: String = Screen.Home.route,
) : ViewModel() {

}