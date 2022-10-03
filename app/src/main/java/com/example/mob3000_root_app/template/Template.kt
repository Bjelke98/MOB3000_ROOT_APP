package com.example.mob3000_root_app.template

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.navigation.AppNavHost
import com.example.mob3000_root_app.components.navigation.Screen
import com.example.mob3000_root_app.components.navigation.navigateUpTo
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
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(300.dp),
            ){
                Column {
                    //Title
                    Text(text = stringResource(id = R.string.company_name), modifier = Modifier.padding(16.dp))
                    //content
                    NavigationDrawerItem(
                        label = { Text(text = stringResource(id = R.string.nav_label_home)) },
                        selected = true,
                        onClick = {
                            navigateUpTo(navController, Screen.Home)
                            closeDrawer()
                        })
                    NavigationDrawerItem(
                        label = { Text(text = "Articles") },
                        selected = false,
                        onClick = {
                            navigateUpTo(navController, Screen.Articles)
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
                            stringResource(id = R.string.company_name),
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
                        IconButton(onClick = { navigateUpTo(navController, Screen.Login) }) {
                            Icon(
                                imageVector = Icons.Filled.AccountCircle,
                                contentDescription = "Localized description"
                            )
                        }
                    }
                )
            },
        ){ innerPadding -> AppNavHost(Modifier.padding(innerPadding), navController = navController) }
    }
}