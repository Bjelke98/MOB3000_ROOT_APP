package com.example.mob3000_root_app.components.navigation

sealed class Screen(val route: String) {
    object HomeOld: Screen(route = "home_screenOld")
    object Home: Screen(route = "home_screen")
    object Login: Screen(route = "login_screen")
    object Profile: Screen(route = "profile_screen")
    object Register: Screen(route = "register_screen")
    object Settings: Screen(route = "settings_screen")
    object Articles: Screen(route = "article_screen")
    object About: Screen(route = "about_screen")
    object Events: Screen(route = "events_screen")
    object ArticleFull: Screen(route = "article_full_screen")
    object B: Screen(route = "b_screen")
    object C: Screen(route = "c_screen")
    object D: Screen(route = "d_screen")
    object AboutUs: Screen(route = "about_us_screen")
    object ArticleAdmin:Screen(route="article_admin")
    object EventAdmin:Screen(route="event_admin")
}
