package com.example.mob3000_root_app.components.cards

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleDown
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mob3000_root_app.data.AboutData
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.data.AboutFAQ
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun AboutFAQCARD(faq: AboutFAQ) {

    var openFAQ by remember { mutableStateOf(false) }
    val density = LocalDensity.current

    var scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

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
                IconButton(
                    onClick = {
                        openFAQ = !openFAQ
                    },
                    Modifier
                        .padding(4.dp, bottom = 1.dp)
                ) {
                    Icon(
                        painter = painterResource(
                            id = (
                                    if (!openFAQ) {
                                        R.drawable.ic_baseline_keyboard_arrow_down_24
                                    } else {
                                        R.drawable.ic_baseline_keyboard_arrow_up_24
                                    }
                                    )
                        ),
                        contentDescription = "FAQData"
                    )
                }
                Text(
                    style = TextStyle(
                        fontSize = 20.sp
                    ),
                    text = faq.q
                )
            }
            AnimatedVisibility((openFAQ),
                enter = slideInVertically {
                    with(density) { -30.dp.roundToPx() }
                } + expandVertically(
                    expandFrom = Alignment.Bottom
                )
                ,
                exit = slideOutVertically() + shrinkVertically(shrinkTowards = Alignment.Top)/* + fadeOut()*/

            ){
                //Scroller ned til bunnen når kommentarer åpnes. Kunne ikke settes på
                // onclick fordi kommentarene ble ferdig composed etter launch ble ferdig
                coroutineScope.launch {
                    scrollState.animateScrollTo(2000)
                }
                Text(modifier = Modifier
                    .padding(5.dp),
                    text = faq.a)
            }

        }
    }
}