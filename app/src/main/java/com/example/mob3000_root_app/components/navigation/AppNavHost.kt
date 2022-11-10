package com.example.mob3000_root_app.components.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mob3000_root_app.components.cards.EditArticleCard
import com.example.mob3000_root_app.components.viewmodel.ArticleViewModel
import com.example.mob3000_root_app.components.viewmodel.EditArticleVM
import com.example.mob3000_root_app.components.viewmodel.EventViewModel
import com.example.mob3000_root_app.screens.admin.ArticleAdmin
import com.example.mob3000_root_app.screens.admin.ArticleEditAdmin
import com.example.mob3000_root_app.screens.admin.EventAdmin
import com.example.mob3000_root_app.screens.content.*
import com.example.mob3000_root_app.screens.profile.*

var loginModel = LoginModel()
var articleViewModel = ArticleViewModel()
var eventViewModel = EventViewModel()
var editArticleVM = EditArticleVM()

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = Screen.Home.route,
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ){
        // Navigate Content
        composable( route = Screen.Home.route ){
            Home(
                navController,
                articleViewModel = articleViewModel,
                eventsModel = eventViewModel
            )
            articleViewModel.getArticleList()
            eventViewModel.getEventList()
        }
        composable( route = Screen.Articles.route ) {
            Articles(navController, articleViewModel = articleViewModel)
            articleViewModel.getArticleList()
        }
        composable( route = Screen.ArticleFull.route ) {
            articleViewModel.getArticleList()
            ArticleFull(navController, loginModel, articleViewModel)
        }
        composable( route = Screen.About.route ){ About() }
        composable( route = Screen.Events.route ){
            Events( eventList = eventViewModel.eventListResponse)
            eventViewModel.getEventList()
        }

        // Navigate Admin
        composable( route = Screen.ArticleAdmin.route ) {
            ArticleAdmin(navController, articleViewModel, editArticleVM)
            articleViewModel.getArticleList()
        }
        composable( route = Screen.ArticleEditAdmin.route ) {
            ArticleEditAdmin(navController, articleViewModel)
            articleViewModel.getArticleList()
        }
        composable( route = Screen.EventAdmin.route ){
            EventAdmin(navController, eventViewModel)
            eventViewModel.getEventList()
        }

        // Navigate Profile
        composable( route = Screen.Login.route ){ Login(navController, loginModel) }
        composable( route = Screen.Register.route ){ Register(navController) }
        composable( route = Screen.Profile.route ) { Profile(navController = navController)}
        composable( route = Screen.Settings.route ){ Settings(navController) }
    }
}


/*
//            --- Annen måte å sende data til andre views ---
//            val articleData = navController.previousBackStackEntry?.savedStateHandle?.get<ArticleData>("article")
//            LaunchedEffect(key1 = it ) {
//                Log.d("ArticleFull", "${articleData?.title}")
//            }
//            if (articleData != null) {
//                ArticleFull(navController, articleData = articleData)
//            }
*/