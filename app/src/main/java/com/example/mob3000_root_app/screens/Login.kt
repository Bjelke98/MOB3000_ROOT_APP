package com.example.mob3000_root_app.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mob3000_root_app.App
import com.example.mob3000_root_app.components.navigation.Screen

@Composable
fun Login(navController: NavHostController) {
    Text(text = "LoginScreen")
}

@Preview(showBackground = true, widthDp = 400, heightDp = 65)
@Composable
fun LoginNavPreview() {
    App(rememberNavController())
}

@Preview(showBackground = true, widthDp = 400, heightDp = 400)
@Composable
fun LoginPreview() {
    Login(navController = rememberNavController())
}