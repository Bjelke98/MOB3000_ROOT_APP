package com.example.mob3000_root_app.components.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mob3000_root_app.components.viewmodel.ArticleViewModel
import com.example.mob3000_root_app.components.viewmodel.EventViewModel
import com.example.mob3000_root_app.components.viewmodel.LoginViewModel
import com.example.mob3000_root_app.screens.admin.ArticleAdmin
import com.example.mob3000_root_app.screens.admin.EventAdmin
import com.example.mob3000_root_app.screens.content.*
import com.example.mob3000_root_app.screens.profile.*

//var loginViewModel = LoginViewModel()
var articleViewModel = ArticleViewModel()
var eventViewModel = EventViewModel()

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = Screen.Home.route,
    loginViewModel: LoginViewModel
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ){
        // Navigate Content
        composable( route = Screen.Home.route ){
            articleViewModel.getArticleList()
            eventViewModel.getEventList()
            Home(
                navController,
                articleViewModel = articleViewModel,
                eventsModel = eventViewModel
            )
        }
        composable( route = Screen.Articles.route ) {
            articleViewModel.getArticleList()
            Articles(navController, articleViewModel = articleViewModel)
        }
        composable( route = Screen.ArticleFull.route ) {
            articleViewModel.getArticleList()
            ArticleFull(navController, loginViewModel, articleViewModel)
        }
        composable( route = Screen.About.route ){ About() }

        composable( route = Screen.Events.route ){
            eventViewModel.getEventList()
            Events(navController, eventViewModel = eventViewModel)
        }

        composable( route = Screen.EventFull.route) {
            eventViewModel.getEventList()
            EventFull(navController, loginViewModel, eventViewModel )
        }

        // Navigate Admin
        composable( route = Screen.ArticleAdmin.route ) {
            articleViewModel.getArticleList()
            ArticleAdmin(navController, articleViewModel)
        }
        composable( route = Screen.EventAdmin.route ){
            eventViewModel.getEventList()
            EventAdmin(navController, eventViewModel)
        }

        // Navigate Profile
        composable( route = Screen.Login.route ){ Login(navController, loginViewModel) }
        composable( route = Screen.Register.route ){ Register(navController) }
        composable( route = Screen.Profile.route ) { Profile(navController = navController)}
        composable( route = Screen.Settings.route ){ Settings(navController, loginViewModel) }
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