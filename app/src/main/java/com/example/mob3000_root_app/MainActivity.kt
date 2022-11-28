package com.example.mob3000_root_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mob3000_root_app.components.viewmodel.AppViewModel
import com.example.mob3000_root_app.components.viewmodel.ArticleViewModel
import com.example.mob3000_root_app.components.viewmodel.EventViewModel
import com.example.mob3000_root_app.components.viewmodel.LoginViewModel
import com.example.mob3000_root_app.data.apiRequest.UserLoginInfo
import com.example.mob3000_root_app.template.Template
import com.example.mob3000_root_app.ui.theme.MOB3000_ROOT_APPTheme

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var appViewModel: AppViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()
            loginViewModel = LoginViewModel()
            loginViewModel.getLoginStatus()
            appViewModel = AppViewModel(
                navController = navController,
                loginVM = loginViewModel
            )
            MOB3000_ROOT_APPTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Template(appViewModel)
                }
            }
        }
    }
}