package com.example.mob3000_root_app.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mob3000_root_app.data.AboutData
import com.example.mob3000_root_app.R

@Composable
fun AboutCard(person: AboutData) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()


    ) {
        Box(modifier = Modifier.height(200.dp)) {
            Image(painter = painterResource(R.drawable.testing), "Dog",
                Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )


        }
        Column() {


            Text(modifier = Modifier
                .padding(5.dp),
                style = TextStyle(
                    fontSize = 20.sp
                ),
                text = person.name)

            Text(modifier = Modifier
                .padding(5.dp),
                text = person.role)

            Text(modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = person.desc)
        }
    }
}