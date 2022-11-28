package com.example.mob3000_root_app.components.navigation

import com.example.mob3000_root_app.R

sealed class Screen(val route: String, val name: Int) {

    // Profile Screens
    object Login:           Screen(route = "login_screen", name = R.string.login)
    object Profile:         Screen(route = "profile_screen", name = R.string.profile)
    object Register:        Screen(route = "register_screen", name = R.string.register)
    object Settings:        Screen(route = "settings_screen", name = R.string.settings)

    // Content Screens
    object Home:            Screen(route = "home_screen", name = R.string.app_name)
    object Articles:        Screen(route = "article_screen", name = R.string.nav_label_articles)
    object About:           Screen(route = "about_screen", name = R.string.nav_label_about_us)
    object Events:          Screen(route = "events_screen", name = R.string.nav_label_events)
    object ArticleFull:     Screen(route = "article_full_screen", name = R.string.single_article)
    object EventFull:       Screen(route = "event_full_screen", name = R.string.single_event)

    // Admin Screens
    object ArticleAdmin:    Screen(route = "article_admin", name = R.string.article_admin)
    object EventAdmin:      Screen(route = "event_admin", name = R.string.event_admin)
    object EditArticle:     Screen(route = "edit_article", name = R.string.edit_article)
    object EditEvent:       Screen(route = "edit_event", name = R.string.edit_event)
}
