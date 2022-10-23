package com.example.mob3000_root_app.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.ArticleApiService.ArticleApiService
import com.example.mob3000_root_app.components.cards.Article
import com.example.mob3000_root_app.components.cards.EventCard
import com.example.mob3000_root_app.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Home2(navController: NavHostController, articleList: List<ArticleData>, eventList: List<EventData>) {

    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            Modifier
                .padding(10.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(15.dp))
        {

            Text(text = "Recent Articles",
                Modifier.fillMaxWidth(),
                style =  MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                items(items = articleList){ article ->
                    Article(navController, data = article, ArticleType.HORIZONTAL_ARTICLE)
                }
            }

            Text(   text = "Recent Events",
                    Modifier.fillMaxWidth(),
                    style =  MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                items(items = eventList){ event ->
                    EventCard(event)
                }
            }
        }
    }
}