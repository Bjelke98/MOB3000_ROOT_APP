package com.example.mob3000_root_app.components.template

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mob3000_root_app.Article
import com.example.mob3000_root_app.ArticleData
import com.example.mob3000_root_app.navigation.AppNavHost
import com.example.mob3000_root_app.navigation.Screen
import com.example.mob3000_root_app.navigation.navigateUpTo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Template(
    navController: NavHostController
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    fun closeDrawer(){
        scope.launch { drawerState.close() }
    }
    val drawerList = listOf(
        "Hjem",
        "Artikkel",
        "Event",
        "Om oss"
    )
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(300.dp),
            ){
                Column {
                    //Title
                    Text(text = "Root", modifier = Modifier.padding(16.dp))
                    //content
                    NavigationDrawerItem(
                        label = { Text(text = "Home") },
                        selected = true,
                        onClick = {
                            navigateUpTo(navController, Screen.Home)
                            closeDrawer()
                        })
                    NavigationDrawerItem(
                        label = { Text(text = "B") },
                        selected = false,
                        onClick = {
                            navigateUpTo(navController, Screen.B)
                            closeDrawer()
                        })
                    NavigationDrawerItem(
                        label = { Text(text = "C") },
                        selected = false,
                        onClick = {
                            navigateUpTo(navController, Screen.C)
                            closeDrawer()
                        })
//                    for(item in drawerList){
//                        NavigationDrawerItem(
//                            label = { Text(text = item) },
//                            selected = false,
//                            onClick = { /*TODO*/ })
//                    }
                }
            }
        }
    ){
//      drawerState.isClosed
//      onClick = { scope.launch { drawerState.open() } }
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    title = {
                        Text(
                            "Root",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(
                                imageVector = Icons.Filled.AccountCircle,
                                contentDescription = "Localized description"
                            )
                        }
                    }
                )
            },
        ){ innerPadding -> AppNavHost(Modifier.padding(innerPadding), navController) }
    }
}