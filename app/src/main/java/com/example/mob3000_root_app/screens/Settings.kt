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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.navigation.Screen
import com.example.mob3000_root_app.components.navigation.navigateUpTo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings(navController: NavHostController) {
    var firstname by remember{ mutableStateOf(TextFieldValue("Henrik")) }
    var lastname by remember{ mutableStateOf(TextFieldValue("Lindam")) }
    var password by remember { mutableStateOf(TextFieldValue(""))}
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
                // column for å bytte navn
                if (nameSelected){
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = stringResource(id = R.string.setting_change_name), style = MaterialTheme.typography.headlineSmall);
                        OutlinedTextField(
                            value = firstname,
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            label = { Text(text =  stringResource(id = R.string.firstname)) },
                            placeholder = { Text(text = stringResource(id = R.string.firstname)) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            onValueChange = {
                                firstname = it
                            }
                        )
                        OutlinedTextField(
                            value = lastname,
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            label = { Text(text = stringResource(id = R.string.lastname)) },
                            placeholder = { Text(text = stringResource(id = R.string.lastname)) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            onValueChange = {
                                lastname = it
                            }
                        )
                        Row(
                            Modifier
                                .padding(5.dp)
                                .align(Alignment.End)){
                            Button(onClick = {

                            }) {
                                Text(stringResource(id = R.string.setting_change_name))
                            }
                        }
                    }
                }

                //column for å bytte passord
                if (passwordSelected){
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = stringResource(id = R.string.setting_change_password), style = MaterialTheme.typography.headlineSmall);
                        OutlinedTextField(
                            value=password,
                            leadingIcon={Icon(imageVector= Icons.Default.Lock,contentDescription=null)},
                            modifier= Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            label={Text(text= stringResource(id = R.string.setting_new_password))},
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
                            label={Text(text=stringResource(id = R.string.setting_confirm_password))},
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
                            label={Text(text=stringResource(id = R.string.setting_old_password))},
                            placeholder={Text(text="********")},
                            keyboardOptions= KeyboardOptions(keyboardType= KeyboardType.Password),
                            visualTransformation= PasswordVisualTransformation(),
                            onValueChange={
                                password=it
                            }
                        )
                        Row(
                            Modifier
                                .padding(5.dp)
                                .align(Alignment.End)){
                            Button(onClick = {

                            }) {
                                Text(stringResource(id = R.string.setting_change_password))
                            }
                        }
                    }
                }
                // column for å slette bruker
                if (deleteUserSelected){
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = stringResource(id = R.string.setting_delete_user), style = MaterialTheme.typography.headlineSmall);
                        OutlinedTextField(
                            value=password,
                            leadingIcon={Icon(imageVector= Icons.Default.Lock,contentDescription=null)},
                            modifier= Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            label={Text(text=stringResource(id = R.string.password))},
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
                            label={Text(text=stringResource(id = R.string.setting_confirm_password))},
                            placeholder={Text(text="********")},
                            keyboardOptions= KeyboardOptions(keyboardType= KeyboardType.Password),
                            visualTransformation= PasswordVisualTransformation(),
                            onValueChange={
                                password=it
                            }
                        )
                    }
                    Row(
                        Modifier
                            .padding(5.dp)
                            .align(Alignment.End)){
                        Button(onClick = {

                        }) {
                            Text(stringResource(id = R.string.setting_delete_user))
                        }
                    }
                }

            }
        }
    }
}