package com.example.mob3000_root_app.screens

import android.graphics.Color
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.compose.material3.OutlinedTextField as OutlinedTextField1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings(navController: NavHostController) {
    var editFornavn by remember{ mutableStateOf(TextFieldValue("")) }
    var endrePassord by remember { mutableStateOf(TextFieldValue(""))}

    val testColors: CardColors = CardDefaults.cardColors(
    containerColor = MaterialTheme.colorScheme.background);

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant),
    ){
        Card(modifier = Modifier.padding(10.dp),colors = testColors
        ) {
            Column(
                //verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(6.dp)
            ) {
                Row(
                    modifier = Modifier.padding(vertical = 24.dp),
                    horizontalArrangement = Arrangement.Center
                ) {

                    TextButton(
                        onClick = {
                        },
                    ) {
                        Text("Endre navn")
                    }
                    TextButton(
                        onClick = {},
                    ) {
                        Text("Endre passord")
                    }
                    Button(
                        onClick = {},
                    ) {
                        Text("Slett bruker")
                    }
                }
            }
        }
    }
}