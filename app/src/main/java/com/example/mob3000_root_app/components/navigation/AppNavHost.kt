package com.example.mob3000_root_app.components.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mob3000_root_app.components.viewmodel.AppViewModel
import com.example.mob3000_root_app.screens.admin.ArticleAdmin
import com.example.mob3000_root_app.screens.admin.EventAdmin
import com.example.mob3000_root_app.screens.content.*
import com.example.mob3000_root_app.screens.profile.*

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    appVM: AppViewModel
) {
    NavHost(
        modifier = modifier,
        navController = appVM.navController,
        startDestination = appVM.startDestination,
    ){
        // Navigate Content
        composable( route = Screen.Home.route ){
            appVM.articleVM.getArticleList()
            appVM.articleVM.getArticleList()
            Home(appVM)
        }
        composable( route = Screen.Articles.route ) {
            appVM.articleVM.getArticleList()
            Articles(appVM)
        }
        composable( route = Screen.ArticleFull.route ) {
            appVM.articleVM.getArticleList()
            ArticleFull(appVM)
        }
        composable( route = Screen.About.route ){ About() }

        composable( route = Screen.Events.route ){
            appVM.eventVM.getEventList()
            Events(appVM)
        }

        composable( route = Screen.EventFull.route) {
            appVM.eventVM.getEventList()
            EventFull(appVM)
        }

        // Navigate Admin
        composable( route = Screen.ArticleAdmin.route ) {
            appVM.articleVM.getArticleList()
            ArticleAdmin(appVM)
        }
        composable( route = Screen.EventAdmin.route ){
            appVM.eventVM.getEventList()
            EventAdmin(appVM)
        }

        // Navigate Profile
        composable( route = Screen.Login.route ){ Login(appVM) }
        composable( route = Screen.Register.route ){ Register(appVM) }
        composable( route = Screen.Profile.route ) { Profile(appVM)}
        composable( route = Screen.Settings.route ){ Settings(appVM) }
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