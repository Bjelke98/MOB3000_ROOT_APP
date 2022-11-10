package com.example.mob3000_root_app.components.navigation

sealed class Screen(val route: String) {
    // Profile Screens
    object Login:               Screen(route = "login_screen")
    object Profile:             Screen(route = "profile_screen")
    object Register:            Screen(route = "register_screen")
    object Settings:            Screen(route = "settings_screen")

    // Content Screens
    object Home:                Screen(route = "home_screen")
    object Articles:            Screen(route = "article_screen")
    object About:               Screen(route = "about_screen")
    object Events:              Screen(route = "events_screen")
    object ArticleFull:         Screen(route = "article_full_screen")
    object EventFull:           Screen(route = "event_full_screen")

    // Admin Screens
    object ArticleAdmin:        Screen(route = "article_admin")
    object ArticleEditAdmin:    Screen(route = "article_edit_admin")
    object EventAdmin:          Screen(route = "event_admin")
    object EditEvent:           Screen(route = "edit_event")
}
