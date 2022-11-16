package com.example.mob3000_root_app.components.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mob3000_root_app.bjelkeTests.UploadFileTest
import com.example.mob3000_root_app.bjelkeTests.UploadFileTestVM
import com.example.mob3000_root_app.components.navigation.Screen

class AppViewModel(
    var navController: NavHostController,
    var loginVM: LoginViewModel = LoginViewModel(),
    var articleVM: ArticleViewModel = ArticleViewModel(),
    var eventVM: EventViewModel = EventViewModel(),
    val startDestination: String = Screen.Home.route,
    val uploadVM: UploadFileTestVM = UploadFileTestVM()
) : ViewModel() {

}