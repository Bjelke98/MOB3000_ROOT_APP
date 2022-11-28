package com.example.mob3000_root_app.screens.content

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.cards.ArticleCard
import com.example.mob3000_root_app.components.cards.EventCard
import com.example.mob3000_root_app.components.scrollbar.Carousel
import com.example.mob3000_root_app.components.scrollbar.rememberCarouselScrollState
import com.example.mob3000_root_app.components.scrollbar.verticalScroll
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
    val scrollState = rememberCarouselScrollState()
    val scrollbarWidth = 32.dp // size + scrollbar-padding(10.dp) & firstCol-padding(10.dp)
    val screenWidth = LocalConfiguration.current.screenWidthDp

    val screenHeight = LocalConfiguration.current.screenHeightDp
    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
            Column(
                Modifier
                    .padding(10.dp)
                    .width( if(screenHeight.dp > 400.dp) screenWidth.dp else screenWidth.dp-scrollbarWidth)
                    .fillMaxHeight()
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {

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
                        EventCard(event = event, ArticleType.HORIZONTAL_ARTICLE, appVM)
                    }
                }
            }
        Column(
                horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
        ) {
            Box(
                modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.Center,
            ) {
                Carousel(
                    state = scrollState,
                    modifier = Modifier
                        .width(12.dp)
                        .padding(end = 10.dp)
                        .fillMaxHeight(.9f)
                        .fillMaxWidth()
                )
            }
        }
    }
}