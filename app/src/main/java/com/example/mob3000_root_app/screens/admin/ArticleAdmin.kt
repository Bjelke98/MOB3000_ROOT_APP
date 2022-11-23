@file:JvmName("ArticleAdminaKt")

package com.example.mob3000_root_app.screens.admin

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mob3000_root_app.components.viewmodel.AppViewModel
import com.example.mob3000_root_app.components.cards.EditArticleCard

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ArticleAdmin(
    appVM: AppViewModel
) {
    Scaffold(modifier = Modifier
        .background(MaterialTheme.colorScheme.surfaceVariant),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ },
//                modifier = Modifier
//                    .align(alignment = Alignment.End),
                contentColor = MaterialTheme.colorScheme.onPrimary,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Filled.Add, "New article")
            }
        }
    ) { padding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.surfaceVariant)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .weight(1f)
            ) {
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                }
                items(items = appVM.articleVM.articleListResponse) { article ->
                    EditArticleCard(
                        appVM = appVM,
                        articleData = article,
                        editFocus = { appVM.ppArticleVM.focusArticle(article) }
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(70.dp))
                }
            }
        }
    }

}