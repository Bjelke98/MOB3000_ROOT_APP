package com.example.mob3000_root_app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mob3000_root_app.components.cards.Article
import com.example.mob3000_root_app.data.ArticleTestdata
import com.example.mob3000_root_app.data.ArticleType

@Composable
fun Home(navController: NavHostController) {

    Box( Modifier.background(MaterialTheme.colorScheme.surfaceVariant) )
    {
        val articleList = ArticleTestdata().dataList

        Column(Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp))
        {

            Text(   text = "Recent Articles",
                Modifier.fillMaxWidth(),
                style =  MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )

            LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp))
            {
                items(items = articleList){ article ->
                    Article(data = article, ArticleType.VERTICAL_ARTICLE)
                }
            }
        }
    }
}