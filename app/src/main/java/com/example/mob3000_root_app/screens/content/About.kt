package com.example.mob3000_root_app.screens.content

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.cards.AboutCard
import com.example.mob3000_root_app.components.cards.AboutFAQCARD
import com.example.mob3000_root_app.data.AboutData
import com.example.mob3000_root_app.data.AboutFAQ

@Composable
fun About() {
    val aboutList = listOf(
        AboutData("Adrian Dahl", "Administrende direktør", "Han liker bananer men spiser ikke bananer men har bananer i kjøleskapet", R.drawable.rektor),
        AboutData("Henrik Lindam", "Direktør service", "Kroa er best", R.drawable.opinion_wrong),
        AboutData("Olav Pålerud", "Direktør forretningsstøtte", "Jeg er Olav, det er pog fordi jeg har navnet Olav i navnet mitt", R.drawable.sauce),
        AboutData("John Ivar Lilleørene", "Viserektor forskining", "Sleeper resident", R.drawable.testing),
        AboutData("Krister Iversen", "2x times Champion", "Bilister Trister Minister Klister Brødrister Frister Rister Angrefrister Akseptfrister Bilregister Assister Antimilitarister Annenstatminister", R.drawable.wow),
        AboutData("Herman Simonsen", "Meget høy kar", "Litt plagsomt at Krister er 2x Champion", R.drawable.herman),
        )

    val aboutFaqList = listOf(
        AboutFAQ("Hvem er Root?","En Linjeforening"),
        AboutFAQ("Hvordan får jeg en A?","Jobbe med MOB3000")
    )

    LazyColumn() {

        item { Text(modifier = Modifier
            .padding(5.dp),
            text = "About us") }

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