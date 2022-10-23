package com.example.mob3000_root_app.components.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mob3000_root_app.components.ArticleApiService.ArticleApiService
import com.example.mob3000_root_app.data.ArticleData
import com.example.mob3000_root_app.screens.*

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = Screen.Home.route,
) {

    var articles: ArticlesModel = ArticlesModel()
    var events: EventsModel = EventsModel();

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ){
        composable( route = Screen.Home.route ){ Home(navController) }

        composable( route = Screen.Home2.route ){
            Home2(
                navController,
                articleList = articles.articleListResponse,
                eventList = events.eventListResponse
            )
            articles.getArticleList()
            events.getEventList()
        }
        composable( route = Screen.Login.route ){ Login(navController) }
        composable( route = Screen.Register.route ){ Register(navController) }
        composable( route = Screen.B.route ){ TestText("A") }
        composable( route = Screen.C.route ){ TestText("B") }

        composable(
            route = Screen.Articles.route,
            arguments = listOf(
            navArgument("article"){type = NavType.ParcelableType(ArticleData::class.java)},

            )
        ) {
            Articles(navController, articleList = articles.articleListResponse)
            articles.getArticleList()
        }

        composable(
            route = Screen.ArticleFull.route,
//            arguments = listOf(
//                navArgument("article"){type = NavType.ParcelableType(ArticleData::class.java)},
//
//                )
        ) {
//            navController.currentBackStackEntry?.arguments?.get("data")?.let { data ->
            ArticleFull(navController/*, articleData = data*/)
//        }
            articles.getArticleList()
        }
        composable( route = Screen.About.route ){ About() }
        composable( route = Screen.Events.route ){ Events( eventList = events.eventListResponse); events.getEventList()}
    }
}

@Composable
fun TestText(display: String) {
    Text(text = display)
}