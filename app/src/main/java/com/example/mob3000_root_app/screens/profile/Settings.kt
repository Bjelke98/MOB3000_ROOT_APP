package com.example.mob3000_root_app.screens.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.cards.changeName
import com.example.mob3000_root_app.components.cards.changePassword
import com.example.mob3000_root_app.components.cards.deleteUser
import com.example.mob3000_root_app.components.navigation.Screen
import com.example.mob3000_root_app.components.navigation.navigateUpTo
import com.example.mob3000_root_app.components.viewmodel.LoginViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun Settings(
    navController: NavHostController,
    loginViewModel: LoginViewModel
) {
    var nameSelected by remember { mutableStateOf(false) }
    var passwordSelected by remember { mutableStateOf(false) }
    var deleteUserSelected by remember { mutableStateOf(false) }

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
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth(1f)
            ) {
                Row(
                    modifier = Modifier.padding(vertical = 24.dp),
                    horizontalArrangement = Arrangement.Center
                ) {

                    TextButton(
                        onClick = {
                            nameSelected = !nameSelected
                            if(nameSelected)
                                passwordSelected = false
                                deleteUserSelected = false

                        },
                    ) {
                        Text(stringResource(id = R.string.setting_change_name))
                    }
                    TextButton(
                        onClick = {
                            passwordSelected = !passwordSelected
                            if (passwordSelected)
                                nameSelected = false
                                deleteUserSelected = false;
                        },
                    ) {
                        Text(stringResource(id = R.string.setting_change_password))
                    }
                    Button(
                        onClick = {
                            deleteUserSelected = !deleteUserSelected
                            if(deleteUserSelected)
                                nameSelected = false
                                passwordSelected = false
                        },
                    ) {
                        Text(stringResource(id = R.string.setting_delete_user))
                    }
                }
                val user = loginViewModel.loginStatusResponse.user
                if (user != null){
                // column for å bytte navn
                if (nameSelected){ changeName(user, loginViewModel) }

                //column for å bytte passord
                if (passwordSelected){ changePassword() }

                // column for å slette bruker
                if (deleteUserSelected){ deleteUser() }
                }
            }
        }
    }
}