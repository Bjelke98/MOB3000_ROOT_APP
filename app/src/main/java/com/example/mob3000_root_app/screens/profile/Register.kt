package com.example.mob3000_root_app.screens.profile

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mob3000_root_app.components.navigation.Screen
import com.example.mob3000_root_app.components.navigation.navigateUpTo
import com.example.mob3000_root_app.components.viewmodel.AppViewModel
import com.example.mob3000_root_app.data.apiResponse.NewUser

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
                modifier = Modifier.padding(6.dp).verticalScroll(rememberScrollState())
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
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
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
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
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
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next)
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
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next)
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
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done)
                )

                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextButton(
                        onClick = {
                            navigateUpTo(appVM.navController, Screen.Login)
                        }) {
                        Text("Har bruker allerede? Login her.")
                    }
                    val context = LocalContext.current
                    Button(
                        onClick = {
                            appVM.loginVM.registerUser(NewUser(
                                firstname.text,
                                lastname.text,
                                epost.text,
                                password.text
                            )) {
                                if(it == null) {
                                    appVM.loginVM.getLoginStatus()
                                    navigateUpTo(appVM.navController, Screen.Home)
                                }
                                else {
                                    Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                                }
                            }
                        },
                    ) {
                        Text("Registrer")
                    }
                }
            }
        }
    }
}