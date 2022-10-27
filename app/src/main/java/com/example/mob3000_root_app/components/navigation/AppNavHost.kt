package com.example.mob3000_root_app.components.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mob3000_root_app.screens.*

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = Screen.Home.route,
) {

    var articlesModel = ArticlesModel()
    var eventsModel = EventsModel()
//    var focusedArticleModel = FocusedArticleModel()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ){
//        composable( route = Screen.HomeOld.route ){ Home(navController) }
        composable( route = Screen.Home.route ){
            Home(
                navController,
                articleModel = articlesModel,
                eventsModel = eventsModel
            )
            articlesModel.getArticleList()
            eventsModel.getEventList()
        }
        composable( route = Screen.Login.route ){ Login(navController) }
        composable( route = Screen.Register.route ){ Register(navController) }
        composable( route = Screen.B.route ){ TestText("A") }
        composable( route = Screen.C.route ){ TestText("B") }

        composable( route = Screen.Articles.route ) {
            Articles(navController, articleModel = articlesModel)
            articlesModel.getArticleList()
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
            ArticleFull(navController, articlesModel.currentArticle)
            articlesModel.getArticleList()
        }
        composable( route = Screen.About.route ){ About() }
        composable( route = Screen.Events.route ){ Events( eventList = eventsModel.eventListResponse); eventsModel.getEventList()}
    }
}

@Composable
fun TestText(display: String) {
    Text(text = display)
}