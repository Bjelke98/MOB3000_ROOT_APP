package com.example.mob3000_root_app.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleDown
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mob3000_root_app.data.AboutData
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.data.AboutFAQ

@Composable
fun AboutFAQCARD(faq: AboutFAQ) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()

    ) {

        Column() {
            Row(modifier = Modifier
                .padding(5.dp)
                /*.clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource()},
                    onClick = onClickItem
                )
                
                 */
            )
            {
                Icon(Icons.Filled.ArrowDropDown, "arrow",
                    tint = MaterialTheme.colorScheme.primary)
                Text(
                    style = TextStyle(
                        fontSize = 20.sp
                    ),
                    text = faq.q
                )
            }
            Text(modifier = Modifier
                .padding(5.dp),
                text = faq.a)

        }
    }
}