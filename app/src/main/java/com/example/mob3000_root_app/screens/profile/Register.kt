package com.example.mob3000_root_app.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mob3000_root_app.components.viewmodel.AppViewModel

//import androidx.compose.foundation.layout.BoxScopeInstance.align

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register(appVM: AppViewModel) {
    var firstname      by remember{ mutableStateOf(TextFieldValue("")) }
    var lastname       by remember{ mutableStateOf(TextFieldValue("")) }
    var epost          by remember{ mutableStateOf(TextFieldValue("")) }
    var password       by remember{ mutableStateOf(TextFieldValue("")) }
    var repeatPassword by remember{ mutableStateOf(TextFieldValue("")) }

    val testColors: CardColors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.background);
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Card(modifier = Modifier.padding(10.dp), colors = testColors) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(6.dp)
            ) {
                Text(text = "Registrer", fontSize = MaterialTheme.typography.headlineLarge.fontSize)
                OutlinedTextField(
                    value = firstname,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                        )
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    label = { Text(text = "Fornavn") },

                    placeholder = { Text(text = "Skriv ditt etternavn") },
                    onValueChange = {
                        firstname = it
                    }
                )

                OutlinedTextField(
                    value = lastname,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null
                        )
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    label = { Text(text = "Etternavn") },
                    placeholder = { Text(text = "Skriv ditt etternavn") },
                    onValueChange = {
                        lastname = it
                    }
                )

                OutlinedTextField(
                    value = epost,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = null
                        )
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    label = { Text(text = "Epost*") },
                    placeholder = { Text(text = "eksempel@hotmail.com") },
                    onValueChange = {
                        epost = it
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                OutlinedTextField(
                    value = password,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null
                        )
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    label = { Text(text = "Passord*") },
                    placeholder = { Text(text = "Eksempel") },
                    visualTransformation = PasswordVisualTransformation(),
                    onValueChange = {
                        password = it
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                OutlinedTextField(
                    value = repeatPassword,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null
                        )
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    label = { Text(text = "Gjenta passord*") },
                    placeholder = { Text(text = "Eksempel") },
                    visualTransformation = PasswordVisualTransformation(),
                    onValueChange = {
                        repeatPassword = it
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextButton(
                        onClick = {
                            appVM.navController.navigate("login_screen")
                        }) {
                        Text("Har bruker allerede? Login her.")
                    }
                    Button(
                        onClick = {

                        },
                    ) {
                        Text("Registrer bruker")
                    }
                }
            }
        }
    }
}