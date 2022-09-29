package com.example.mob3000_root_app.components.navigation

sealed class Screen(val route: String) {
    object Home: Screen(route = "home_screen")
    object B: Screen(route = "b_screen")
    object C: Screen(route = "c_screen")
}
