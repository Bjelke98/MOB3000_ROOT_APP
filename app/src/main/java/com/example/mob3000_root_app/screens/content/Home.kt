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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.cards.ArticleCard
import com.example.mob3000_root_app.components.cards.EventCard
import com.example.mob3000_root_app.components.viewmodel.AppViewModel
import com.example.mob3000_root_app.data.*

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Home(
    appVM: AppViewModel
) {
    val navController = appVM.navController
    val articleVM = appVM.articleVM
    val eventVM = appVM.eventVM
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

            Text(text = stringResource(id = R.string.recent_articles),
                Modifier.fillMaxWidth(),
                style =  MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                items(items = articleVM.articleListResponse){ article ->
                    ArticleCard(navController, articleData = article, ArticleType.HORIZONTAL_ARTICLE, { articleVM.focusArticle(article) })
                }
            }

            Text(   text = stringResource(id = R.string.recent_events),
                    Modifier.fillMaxWidth(),
                    style =  MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                items(items = eventVM.eventListResponse){ event ->
                    EventCard(navController, event = event, ArticleType.HORIZONTAL_ARTICLE, appVM)
                }
            }
        }
    }
}