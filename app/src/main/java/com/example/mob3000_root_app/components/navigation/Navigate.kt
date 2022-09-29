package com.example.mob3000_root_app.components.navigation

import androidx.navigation.NavHostController

fun navigateUpTo(navController: NavHostController, screen: Screen){
    navController.navigate(screen.route){
        popUpTo(screen.route)
    }
}