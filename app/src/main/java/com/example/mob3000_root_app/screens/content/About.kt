package com.example.mob3000_root_app.screens.content

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.cards.AboutCard
import com.example.mob3000_root_app.components.cards.AboutFAQCARD
import com.example.mob3000_root_app.data.AboutData
import com.example.mob3000_root_app.data.AboutFAQ

@Composable
fun About() {
    val aboutList = listOf(
        AboutData(
            stringResource(R.string.name_adrian),
            stringResource(R.string.title_ceo),
            stringResource(R.string.title_ceo) + " " + stringResource(R.string.desc_ceo),
            R.drawable.rektor
        ),
        AboutData(
            stringResource(R.string.name_henrik),
            stringResource(R.string.title_ceo),
            stringResource(R.string.title_ceo) + " " + stringResource(R.string.desc_ceo),
            R.drawable.rektor
        ),
        AboutData(
            stringResource(R.string.name_olav),
            stringResource(R.string.title_vice_principal),
            stringResource(R.string.title_vice_principal) + " " + stringResource(R.string.desc_vice_principal_education),
            R.drawable.testing
        ),
        AboutData(
            stringResource(R.string.name_john),
            stringResource(R.string.title_vice_principal),
            stringResource(R.string.title_vice_principal) + " " + stringResource(R.string.desc_vice_principal_research),
            R.drawable.testing
        ),
        AboutData(
            stringResource(R.string.name_krister),
            stringResource(R.string.title_cto),
            stringResource(R.string.title_cto) + " " + stringResource(R.string.desc_cto),
            R.drawable.wow
        ),
        AboutData(
            stringResource(R.string.name_herman),
            stringResource(R.string.title_cto),
            stringResource(R.string.title_cto) + " " + stringResource(R.string.desc_cto),
            R.drawable.wow
        )
    )

    val aboutFaqList = listOf(
        AboutFAQ(
            stringResource(R.string.faq_root),
            stringResource(R.string.faq_root_answer)
        ),
        AboutFAQ(
            stringResource(R.string.faq_comment),
            stringResource(R.string.faq_comment_answer)
        ),
        AboutFAQ(
            stringResource(R.string.faq_event),
            stringResource(R.string.faq_event_answer)
        ),
        AboutFAQ(
            stringResource(R.string.faq_regards),
            stringResource(R.string.faq_regards_answer)
        )
    )

    LazyColumn() {

        items(items = aboutList ) {
                item -> AboutCard(item)
        }

        item { Text(modifier = Modifier
            .padding(5.dp),
            text = "FAQ") }

        items(items = aboutFaqList) {
                item -> AboutFAQCARD(item)
        }
    }

}