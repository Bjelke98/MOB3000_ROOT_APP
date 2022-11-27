package com.example.mob3000_root_app.screens.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.cards.changeName
import com.example.mob3000_root_app.components.cards.changePassword
import com.example.mob3000_root_app.components.cards.deleteUser
import com.example.mob3000_root_app.components.viewmodel.AppViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun Settings(appVM: AppViewModel) {
    val loginVM = appVM.loginVM

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
            .fillMaxWidth(1f),
            colors = testColors
        ) {
            Column(
                //verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth(1f)
                    .verticalScroll(rememberScrollState())
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
                val user = loginVM.loginStatusResponse.user
                if (user != null){
                // column for å bytte navn
                if (nameSelected){ changeName(user, loginVM) }

                //column for å bytte passord
                if (passwordSelected){ changePassword(loginVM) }

                // column for å slette bruker
                if (deleteUserSelected){ deleteUser(loginVM) }
                }
            }
        }
    }
}