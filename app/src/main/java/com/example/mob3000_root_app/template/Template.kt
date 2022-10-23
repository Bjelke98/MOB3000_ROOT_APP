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
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.ArticleApiService.ArticleApiService
import com.example.mob3000_root_app.components.navigation.AppNavHost
import com.example.mob3000_root_app.components.navigation.Screen
import com.example.mob3000_root_app.components.navigation.navigateUpTo
import com.example.mob3000_root_app.data.ArticleData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Template(
    navController: NavHostController
) {
    var expanded by remember { mutableStateOf(false) }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val BASE_URL = "https://linrik.herokuapp.com/api/"

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL).build()

    val retrofitService: ArticleApiService by lazy{
        retrofit.create(ArticleApiService::class.java)
    }

    fun closeDrawer(){
        scope.launch { drawerState.close() }
    }
    Box(modifier = Modifier.wrapContentSize(Alignment.TopEnd).offset(0.dp, 65.dp)){
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(text = {Text(text = "Login")},
                onClick = { expanded = false;  navigateUpTo(navController, Screen.Login)})
            DropdownMenuItem(text = { Text(text = "Registrer") }, onClick = {  expanded = false; navigateUpTo(navController, Screen.Register)})
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
                    //Title
                    Text(text = stringResource(id = R.string.company_name), modifier = Modifier.padding(16.dp), fontSize = MaterialTheme.typography.headlineMedium.fontSize)
                    //content
                    NavigationDrawerItem(
                        label = { Text(text = stringResource(id = R.string.nav_label_home)) },
                        selected = navBackStackEntry?.destination?.route==Screen.Home.route,
                        onClick = {
                            navigateUpTo(navController, Screen.Home)
                            closeDrawer()
                        })
                    NavigationDrawerItem(
                        label = { Text(text = stringResource(id = R.string.nav_label_home)+"2") },
                        selected = navBackStackEntry?.destination?.route==Screen.Home2.route,
                        onClick = {
                            navigateUpTo(navController, Screen.Home2)
                            closeDrawer()
                        })
                    NavigationDrawerItem(
                        label = { Text(text = "Articles") },
                        selected = navBackStackEntry?.destination?.route==Screen.Articles.route,
                        onClick = {
                            navigateUpTo(navController, Screen.Articles)
                            closeDrawer()
                        })
                    NavigationDrawerItem(
                        label = { Text(text = "Events") },
                        selected = navBackStackEntry?.destination?.route==Screen.Events.route,
                        onClick = {
                            navigateUpTo(navController, Screen.Events)
                            closeDrawer()
                        })
                    NavigationDrawerItem(
                        label = { Text(text = "About Us") },
                        selected = navBackStackEntry?.destination?.route==Screen.About.route,
                        onClick = {
                            navigateUpTo(navController, Screen.About)
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
                        IconButton(onClick = {  expanded = true }) { // Login og registrer
                            Icon(
                                imageVector = Icons.Filled.AccountCircle,
                                contentDescription = "Localized description"
                            )
                        }
                    }
                )
            },
        ){ innerPadding -> AppNavHost(Modifier.padding(innerPadding), navController = navController, articleAPI = retrofitService) }
    }
}