package com.example.mob3000_root_app.components.navigation

sealed class Screen(val route: String, val name: String) {

    // Profile Screens
    object Login:           Screen(route = "login_screen", name = "Login")
    object Profile:         Screen(route = "profile_screen", name = "Profile")
    object Register:        Screen(route = "register_screen", name = "Registrer")
    object Settings:        Screen(route = "settings_screen", name = "Instillinger")

    // Content Screens
    object Home:            Screen(route = "home_screen", name = "/root")
    object Articles:        Screen(route = "article_screen", name = "Artikler")
    object About:           Screen(route = "about_screen", name = "Om oss")
    object Events:          Screen(route = "events_screen", name = "Arrangementer")
    object ArticleFull:     Screen(route = "article_full_screen", name = "Artikkel")
    object EventFull:       Screen(route = "event_full_screen", name = "Arrangement")

    // Admin Screens
    object ArticleAdmin:    Screen(route = "article_admin", name = "Admin artikkel")
    object EventAdmin:      Screen(route = "event_admin", name = "Admin event")
    object EditArticle:     Screen(route = "edit_article", name = "Rediger artikkel")
    object EditEvent:       Screen(route = "edit_event", name = "Rediger event")
}
