package com.example.mob3000_root_app.template

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.navigation.NavHostController
import com.example.mob3000_root_app.components.navigation.Screen
import com.example.mob3000_root_app.components.navigation.navigateUpTo


@Composable
fun DropdownAccount(navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.TopEnd)){
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(text = {Text(text = "Login")},
                onClick = { navigateUpTo(navController, Screen.Login)})
            DropdownMenuItem(text = { Text(text = "Registrer") }, onClick = { navigateUpTo(navController, Screen.Login) })
        }
    }
}
