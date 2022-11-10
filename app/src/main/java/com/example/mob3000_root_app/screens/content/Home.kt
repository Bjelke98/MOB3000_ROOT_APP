@file:JvmName("HomeOldKt")

package com.example.mob3000_root_app.screens.content

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mob3000_root_app.components.cards.ArticleCard
import com.example.mob3000_root_app.components.cards.EventCard
import com.example.mob3000_root_app.components.navigation.eventViewModel
import com.example.mob3000_root_app.components.viewmodel.ArticleViewModel
import com.example.mob3000_root_app.components.viewmodel.EventViewModel
import com.example.mob3000_root_app.data.*

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Home(
    navController: NavHostController,
    articleViewModel: ArticleViewModel,
    eventsModel: EventViewModel
//, focusedArticleModel: FocusedArticleModel
) {

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
                items(items = articleViewModel.articleListResponse){ article ->
                    ArticleCard(navController, articleData = article, ArticleType.HORIZONTAL_ARTICLE, { articleViewModel.focusArticle(article) })
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
                items(items = eventsModel.eventListResponse){ event ->
                    EventCard(navController, event = event, ArticleType.HORIZONTAL_ARTICLE, { eventViewModel.focusEvent(event)})
                }
            }
        }
    }
}