package com.example.mob3000_root_app.components.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mob3000_root_app.screens.Articles
import com.example.mob3000_root_app.screens.Home
import com.example.mob3000_root_app.screens.Login
import com.example.mob3000_root_app.screens.Register

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = Screen.Home.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ){
        composable( route = Screen.Home.route ){ Home(navController) }
        composable( route = Screen.Login.route ){ Login(navController) }
        composable( route = Screen.Register.route ){ Register(navController) }
        composable( route = Screen.B.route ){ TestText("A") }
        composable( route = Screen.C.route ){ TestText("B") }
        composable( route = Screen.Articles.route ){ Articles(navController) }
    }
}

@Composable
fun TestText(display: String) {
    Text(text = display)
}