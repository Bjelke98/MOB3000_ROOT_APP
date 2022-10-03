package com.example.mob3000_root_app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register(navController: NavHostController) {
    var fornavn       by remember{ mutableStateOf(TextFieldValue("")) }
    var etternavn     by remember{ mutableStateOf(TextFieldValue("")) }
    var epost         by remember{ mutableStateOf(TextFieldValue("")) }
    var passord       by remember{ mutableStateOf(TextFieldValue("")) }
    var gjentaPassord by remember{ mutableStateOf(TextFieldValue("")) }

    Column() {
        OutlinedTextField(
            value=fornavn,
            leadingIcon={Icon(imageVector= Icons.Default.Person,contentDescription=null)},
            modifier= Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            keyboardOptions= KeyboardOptions(keyboardType= KeyboardType.Text),
            label={Text(text="Fornavn")},
            placeholder={Text(text="Skriv ditt etternavn")},
            onValueChange={
                fornavn=it
            }
        )

        OutlinedTextField(
            value=etternavn,
            leadingIcon={Icon(imageVector= Icons.Default.Person,contentDescription=null)},
            modifier= Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            keyboardOptions= KeyboardOptions(keyboardType= KeyboardType.Text),
            label={Text(text="Etternavn")},
            placeholder={Text(text="Skriv ditt etternavn")},
            onValueChange={
                etternavn=it
            }
        )

        OutlinedTextField(
            value=epost,
            leadingIcon={Icon(imageVector= Icons.Default.Email,contentDescription=null)},
            modifier= Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            label={Text(text="Epost*")},
            placeholder={Text(text="Example")},
            onValueChange={
                epost=it
            },
            keyboardOptions= KeyboardOptions(keyboardType= KeyboardType.Password)
        )

        OutlinedTextField(
            value=passord,
            leadingIcon={Icon(imageVector= Icons.Default.Lock,contentDescription=null)},
            modifier= Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            label={Text(text="Passord*")},
            placeholder={Text(text="Example")},
            visualTransformation= PasswordVisualTransformation(),
            onValueChange={
                passord=it
            },
            keyboardOptions= KeyboardOptions(keyboardType= KeyboardType.Password)
        )

        OutlinedTextField(
            value=gjentaPassord,
            leadingIcon={Icon(imageVector= Icons.Default.Lock,contentDescription=null)},
            modifier= Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            label={Text(text="Gjenta passord*")},
            placeholder={Text(text="Example")},
            visualTransformation= PasswordVisualTransformation(),
            onValueChange={
                gjentaPassord=it
            },
            keyboardOptions= KeyboardOptions(keyboardType= KeyboardType.Password)
        )

        Row(
            modifier = Modifier.padding(vertical = 24.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            TextButton(onClick = {},

                ) {
                Text("Login her.")
            }
            Button(

                onClick = {},
            ) {
                Text("Registrer bruker")
            }
        }
    }
}