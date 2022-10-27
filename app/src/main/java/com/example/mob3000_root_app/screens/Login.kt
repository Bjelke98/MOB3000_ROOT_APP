package com.example.mob3000_root_app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mob3000_root_app.App
import com.example.mob3000_root_app.components.navigation.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(navController: NavHostController) {
    var epost by remember{ mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }

    val testColors: CardColors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.background);

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ){

        Card(modifier = Modifier.padding(10.dp),colors = testColors) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(6.dp)
            ) {
                Text(text = "Login", fontSize = MaterialTheme.typography.headlineLarge.fontSize)
                OutlinedTextField(
                    value=epost,
                    leadingIcon={Icon(imageVector= Icons.Default.Person,contentDescription=null)},
                    modifier= Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    label={Text(text="Epost")},
                    placeholder={Text(text="example@hotmail.com")},
                    keyboardOptions= KeyboardOptions(keyboardType= KeyboardType.Email),
                    onValueChange={
                        epost=it
                    }
                )

                OutlinedTextField(
                    value=password,
                    leadingIcon={Icon(imageVector= Icons.Default.Lock,contentDescription=null)},
                    modifier= Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    label={Text(text="Password")},
                    placeholder={Text(text="Pepsi > Cola")},
                    keyboardOptions= KeyboardOptions(keyboardType= KeyboardType.Password),
                    visualTransformation= PasswordVisualTransformation(),
                    onValueChange={
                        password=it
                    }
                )

                Row(
                    modifier = Modifier.padding(vertical = 24.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextButton(onClick = {
                        navController.navigate("register_screen"){
                        }
                    }) {
                        Text("Create new user")
                    }
                    Button(
                        onClick = {},
                    ) {
                        Text("Login")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 65)
@Composable
fun LoginNavPreview() {
    App(rememberNavController())
}

@Preview(showBackground = true, widthDp = 400, heightDp = 600)
@Composable
fun LoginPreview() {
    Login(navController = rememberNavController())
}

//    navigateUpTo(navController, Screen.Home)