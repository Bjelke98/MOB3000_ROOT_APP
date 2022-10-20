package com.example.mob3000_root_app.components.navigation

sealed class Screen(val route: String) {
    object Home: Screen(route = "home_screen")
    object Home2: Screen(route = "home2_screen")
    object Login: Screen(route = "login_screen")
    object Register: Screen(route = "register_screen")
    object Articles: Screen(route = "article_screen")
    object About: Screen(route = "about_screen")
    object Events: Screen(route = "events_screen")
    object ArticleFull: Screen(route = "article_full_screen")
    object B: Screen(route = "b_screen")
    object C: Screen(route = "c_screen")
    object D: Screen(route = "d_screen")
    object AboutUs: Screen(route = "about_us_screen")
}
