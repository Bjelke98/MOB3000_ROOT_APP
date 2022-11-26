package com.example.mob3000_root_app.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.viewmodel.AppViewModel

@Composable
fun Profile(appVM: AppViewModel) {
    val testColors: CardColors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.background);
    val user = appVM.loginVM.loginStatusResponse.user ?: return

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ){

        Card(modifier = Modifier.padding(10.dp),colors = testColors) {
            Column(
                modifier = Modifier
                    //.fillMaxWidth()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .size(72.dp)
                            .clip(shape = CircleShape),
                        painter = painterResource(id = R.drawable.wow),
                        contentDescription = "Your Image"
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(weight = 3f, fill = false)
                                .padding(start = 16.dp)
                        ) {

                            // Brukernavn
                            Text(
                                text = user.firstname,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )

                            Spacer(modifier = Modifier.height(5.dp))

                            // Epost
                            Text(
                                text = user.email,

                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        // Edit knapp
                        IconButton(
                            modifier = Modifier
                                .weight(weight = 1f, fill = false),
                            onClick = {
                                appVM.navController.navigate("Settings_screen")
                            }) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = Icons.Outlined.Edit,
                                contentDescription = ""
                            )
                        }
                    }
                }
                var roller:StringBuilder = StringBuilder()
                if(user.admin) roller.append(stringResource(id = R.string.admin_role) )
                if(user.editor)roller.append(stringResource(id = R.string.editor_role))
                if(user.rootMember)roller.append(stringResource(id = R.string.root_role))
                //Roller
                Text(
                    text =stringResource(id = R.string.roles) +": " +roller.toString().dropLast(2)
                )

                Spacer(modifier = Modifier.height(10.dp))
                //Fornavn
                Text(
                    text = stringResource(id = R.string.firstname) + ": " + user.firstname
                )

                Spacer(modifier = Modifier.height(10.dp))
                //Etternavn
                Text(
                    text = stringResource(id = R.string.lastname) + ": " +user.lastname
                )
            }
        }
    }
}