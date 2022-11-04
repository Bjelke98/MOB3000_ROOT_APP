package com.example.mob3000_root_app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings(navController: NavHostController) {
    var firstname by remember{ mutableStateOf(TextFieldValue("Henrik")) }
    var surname by remember{ mutableStateOf(TextFieldValue("Lindam")) }
    var password by remember { mutableStateOf(TextFieldValue(""))}
    var nameSelected by remember { mutableStateOf(false) }
    var passwordSelected by remember { mutableStateOf(false) }

    val testColors: CardColors = CardDefaults.cardColors(
    containerColor = MaterialTheme.colorScheme.background);

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant),
    ){
        Card(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(1f)
            ,colors = testColors
        ) {
            Column(
                //verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(6.dp)
                    .fillMaxWidth(1f)
            ) {
                Row(
                    modifier = Modifier.padding(vertical = 24.dp),
                    horizontalArrangement = Arrangement.Center
                ) {

                    TextButton(
                        onClick = {
                            nameSelected = !nameSelected
                            if(passwordSelected) passwordSelected = false
                        },
                    ) {
                        Text("Endre navn")
                    }
                    TextButton(
                        onClick = {
                            passwordSelected = !passwordSelected
                            if (nameSelected) nameSelected = false
                        },
                    ) {
                        Text("Endre passord")
                    }
                    Button(
                        onClick = {},
                    ) {
                        Text("Slett bruker")
                    }
                }
                if (nameSelected){
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Endre navn", style = MaterialTheme.typography.headlineSmall);
                        OutlinedTextField(
                            value = firstname,
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            label = { Text(text = "Fornavn") },
                            placeholder = { Text(text = "Fornavn") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            onValueChange = {
                                firstname = it
                            }
                        )
                        OutlinedTextField(
                            value = surname,
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            label = { Text(text = "Etternavn") },
                            placeholder = { Text(text = "Etternavn") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            onValueChange = {
                                surname = it
                            }
                        )
                    }
                }

                if (passwordSelected){
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Endre passord", style = MaterialTheme.typography.headlineSmall);
                        OutlinedTextField(
                            value=password,
                            leadingIcon={Icon(imageVector= Icons.Default.Lock,contentDescription=null)},
                            modifier= Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            label={Text(text="New Password")},
                            placeholder={Text(text="********")},
                            keyboardOptions= KeyboardOptions(keyboardType= KeyboardType.Password),
                            visualTransformation= PasswordVisualTransformation(),
                            onValueChange={
                                password=it
                            }
                        )
                        OutlinedTextField(
                            value=password,
                            leadingIcon={Icon(imageVector= Icons.Default.Lock,contentDescription=null)},
                            modifier= Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            label={Text(text="Confirm Password")},
                            placeholder={Text(text="********")},
                            keyboardOptions= KeyboardOptions(keyboardType= KeyboardType.Password),
                            visualTransformation= PasswordVisualTransformation(),
                            onValueChange={
                                password=it
                            }
                        )
                        OutlinedTextField(
                            value=password,
                            leadingIcon={Icon(imageVector= Icons.Default.Lock,contentDescription=null)},
                            modifier= Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            label={Text(text="Old Password")},
                            placeholder={Text(text="********")},
                            keyboardOptions= KeyboardOptions(keyboardType= KeyboardType.Password),
                            visualTransformation= PasswordVisualTransformation(),
                            onValueChange={
                                password=it
                            }
                        )
                    }
                }

            }
        }
    }
}