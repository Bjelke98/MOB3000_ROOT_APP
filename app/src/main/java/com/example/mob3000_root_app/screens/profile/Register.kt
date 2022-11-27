package com.example.mob3000_root_app.screens.profile

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.navigation.Screen
import com.example.mob3000_root_app.components.navigation.navigateUpTo
import com.example.mob3000_root_app.components.viewmodel.AppViewModel
import com.example.mob3000_root_app.data.apiResponse.NewUser

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
                Text(text = stringResource(id = R.string.register), fontSize = MaterialTheme.typography.headlineLarge.fontSize)
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
                    label = { Text(stringResource(id = R.string.firstname)) },

                    placeholder = { Text(stringResource(id = R.string.write_firstname)) },
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
                    label = { Text(stringResource(id = R.string.lastname)) },
                    placeholder = { Text(stringResource(id = R.string.write_lastname)) },
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
                    label = { Text(stringResource(id = R.string.email)) },
                    placeholder = { Text(stringResource(id = R.string.example_mail)) },
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
                    label = { Text(stringResource(id = R.string.password)) },
                    placeholder = { stringResource(id = R.string.exempel) },
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
                    label = { Text(stringResource(id = R.string.setting_confirm_password)) },
                    placeholder = { Text(stringResource(id = R.string.exempel)) },
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
                        Text(stringResource(id = R.string.already_user))
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
                        Text(stringResource(id = R.string.register))
                    }
                }
            }
        }
    }
}