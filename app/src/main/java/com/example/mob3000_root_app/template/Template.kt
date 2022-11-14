package com.example.mob3000_root_app.template

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.navigation.AppNavHost
import com.example.mob3000_root_app.components.navigation.Screen
import com.example.mob3000_root_app.components.navigation.navigateUpTo
import com.example.mob3000_root_app.components.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Template(
    navController: NavHostController,
    loginViewModel: LoginViewModel
) {
    var expanded by remember { mutableStateOf(false) }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()


    fun closeDrawer(){
        scope.launch { drawerState.close() }
    }
    Box(modifier = Modifier
        .wrapContentSize(Alignment.TopEnd)
        .offset(0.dp, 65.dp)){
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            // Profile
//            if(loginViewModel.loginStatusResponse.user==null){
//                DropdownMenuItem(text = {Text(text = "Login")}, onClick = { expanded = false;  navigateUpTo(navController, Screen.Login)})
//                DropdownMenuItem(text = { Text(text = "Registrer") }, onClick = {  expanded = false; navigateUpTo(navController, Screen.Register)})
//            } else {
//                DropdownMenuItem(text = { Text(text = "Profil") }, onClick = {  expanded = false; navigateUpTo(navController, Screen.Profile)})
//                DropdownMenuItem(text = { Text(text = "Instillinger") }, onClick = { expanded = false; navigateUpTo(navController, Screen.Settings) })
//                DropdownMenuItem(text = { Text(text = "Logg ut") }, onClick = { expanded = false; loginViewModel.logoutUser() ;navigateUpTo(navController, Screen.Home) })
//            }
            DropdownMenuItem(text = {Text(text = "Login")}, onClick = { expanded = false;  navigateUpTo(navController, Screen.Login)})
            DropdownMenuItem(text = { Text(text = "Registrer") }, onClick = {  expanded = false; navigateUpTo(navController, Screen.Register)})
            DropdownMenuItem(text = { Text(text = "Profile") }, onClick = {  expanded = false; navigateUpTo(navController, Screen.Profile)})
            DropdownMenuItem(text = { Text(text = "Settings") }, onClick = { expanded = false; navigateUpTo(navController, Screen.Settings) })
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(300.dp),
            ){
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                Column {
                    // Title
                    Text(text = stringResource(id = R.string.company_name), modifier = Modifier.padding(16.dp), fontSize = MaterialTheme.typography.headlineMedium.fontSize)

                    // Content
                    RootDrawerItem(R.string.nav_label_home, navBackStackEntry, Screen.Home, navController, ::closeDrawer)
                    RootDrawerItem(R.string.nav_label_articles, navBackStackEntry, Screen.Articles, navController, ::closeDrawer)
                    RootDrawerItem(R.string.nav_label_events, navBackStackEntry, Screen.Events, navController, ::closeDrawer)
                    RootDrawerItem(R.string.nav_label_about_us, navBackStackEntry, Screen.About, navController, ::closeDrawer)

                    // Admin
//                    if(loginViewModel.loginStatusResponse.user?.editor == true){
//                        RootDrawerItem(R.string.nav_label_manage_articles, navBackStackEntry, Screen.ArticleAdmin, navController, ::closeDrawer)
//                        RootDrawerItem(R.string.nav_label_manage_events, navBackStackEntry, Screen.EventAdmin, navController, ::closeDrawer)
//                    }
                    RootDrawerItem(R.string.nav_label_manage_articles, navBackStackEntry, Screen.ArticleAdmin, navController, ::closeDrawer)
                    RootDrawerItem(R.string.nav_label_manage_events, navBackStackEntry, Screen.EventAdmin, navController, ::closeDrawer)
                }
            }
        }
    ){
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
                        IconButton(onClick = {  expanded = true }) { // Login og registrer
                            Icon(
                                imageVector = Icons.Filled.AccountCircle,
                                contentDescription = "Localized description"
                            )
                        }
                    }
                )
            },
        ){ innerPadding -> AppNavHost(Modifier.padding(innerPadding), navController = navController, loginViewModel = loginViewModel) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootDrawerItem(stringResourceId: Int, navBackStackEntry: NavBackStackEntry?, screen: Screen, navController: NavHostController, closeDrawer: ()->Unit ) {
    NavigationDrawerItem(
        label = { Text(text = stringResource(id = stringResourceId)) },
        selected = navBackStackEntry?.destination?.route==screen.route,
        onClick = {
            navigateUpTo(navController, screen)
            closeDrawer()
        }
    )
}