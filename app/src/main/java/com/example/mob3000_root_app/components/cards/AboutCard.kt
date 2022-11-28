package com.example.mob3000_root_app.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
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
        Column {
            Row(modifier = Modifier
                .height(115.dp)
                .padding(horizontal = 15.dp, vertical = 15.dp)
            ) {
                Image(
                    painter = painterResource(person.image), "Dog",
                    modifier = Modifier
                        .size(84.dp)
                        .clip(CircleShape)                       // clip to the circle shape
                        .border(1.dp, MaterialTheme.colorScheme.secondary, CircleShape),
                    contentScale = ContentScale.Crop,
                )
                Column(
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .padding(5.dp),
                        style = TextStyle(
                            fontSize = 20.sp
                        ),
                        text = person.name
                    )

                    Text(
                        modifier = Modifier
                            .padding(5.dp),
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        ),
                        text = person.role
                    )
                }
            }
            Row(modifier = Modifier
                .padding(horizontal = 15.dp)
            ) {

                Text(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth(),
                    text = person.desc
                )
            }
        }
    }
}