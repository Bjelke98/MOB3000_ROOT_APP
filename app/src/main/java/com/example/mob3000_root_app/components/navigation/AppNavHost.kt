package com.example.mob3000_root_app.components.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mob3000_root_app.components.models.ArticleModel
import com.example.mob3000_root_app.components.models.EventModel
import com.example.mob3000_root_app.screens.*

var loginModel = LoginModel()
var articleModel = ArticleModel()
var eventModel = EventModel()

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
        composable( route = Screen.Home.route ){
            Home(
                navController,
                articleModel = articleModel,
                eventsModel = eventModel
            )
            articleModel.getArticleList()
            eventModel.getEventList()
        }
        composable( route = Screen.Login.route ){ Login(navController, loginModel) }
        composable( route = Screen.Register.route ){ Register(navController) }

        composable( route = Screen.Profile.route ) { Profile(navController = navController)}
        composable( route = Screen.Settings.route ){ Settings(navController) }

        composable( route = Screen.Articles.route ) {
            Articles(navController, articleModel = articleModel)
            articleModel.getArticleList()
        }
        composable( route = Screen.EditArticles.route ) {
            ArticleAdmin(navController, articleModel)
            articleModel.getArticleList()
        }
        composable( route = Screen.EditEvents.route ){
            EventAdmin(navController, eventModel)
            eventModel.getEventList()

        }

        composable( route = Screen.ArticleFull.route ) {
//            --- Annen måte å sende data til andre views ---
//            val articleData = navController.previousBackStackEntry?.savedStateHandle?.get<ArticleData>("article")
//            LaunchedEffect(key1 = it ) {
//                Log.d("ArticleFull", "${articleData?.title}")
//            }
//            if (articleData != null) {
//                ArticleFull(navController, articleData = articleData)
//            }
            articleModel.getArticleList()
            ArticleFull(navController, loginModel, articleModel)
        }
        composable( route = Screen.About.route ){ About() }

        composable( route = Screen.Events.route ){
            Events( eventList = eventModel.eventListResponse)
            eventModel.getEventList()
        }
    }
}

@Composable
fun TestText(display: String) {
    Text(text = display)
}