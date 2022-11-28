package com.example.mob3000_root_app.screens.profile

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import com.example.mob3000_root_app.components.navigation.Screen
import com.example.mob3000_root_app.components.navigation.navigateUpTo
import com.example.mob3000_root_app.components.viewmodel.AppViewModel
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.data.apiRequest.UserLoginInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(
    appVM: AppViewModel
  ) {
    val loginVM = appVM.loginVM
    val navController = appVM.navController

    var epost by remember{ mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }

    val testColors: CardColors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.background);

    var isPasswordHidden by remember {
        mutableStateOf(true)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ){

        Card(modifier = Modifier.padding(10.dp),colors = testColors) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(6.dp).verticalScroll(rememberScrollState())
            ) {
                Text(text = stringResource(id = R.string.login), fontSize = MaterialTheme.typography.headlineLarge.fontSize)

                OutlinedTextField(
                    value=epost,
                    leadingIcon={
                        Icon(imageVector= Icons.Default.Person,contentDescription=null)},
                    modifier= Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    label={Text(text=stringResource(id = R.string.email))},
                    placeholder={Text(text=stringResource(id = R.string.email))},
                    keyboardOptions= KeyboardOptions(keyboardType= KeyboardType.Email, imeAction = ImeAction.Next),
                    onValueChange={
                        epost=it
                    }
                )

                OutlinedTextField(
                    value=password,
                    leadingIcon= {
                        Icon(imageVector = Icons.Default.Lock, contentDescription = null)},

                    trailingIcon = {
                        Icon(
                            modifier = Modifier.clickable(
                                onClickLabel =
                                if (isPasswordHidden) {
                                    stringResource(id = R.string.show_password)
                                } else
                                    stringResource(id = R.string.hide_password)
                            ) {
                                isPasswordHidden = !isPasswordHidden
                            },
                            imageVector = if (isPasswordHidden) {
                                Icons.Filled.Visibility
                            } else Icons.Default.VisibilityOff, contentDescription = null
                        )
                    }, visualTransformation = if (!isPasswordHidden) VisualTransformation.None
                    else PasswordVisualTransformation(),

                    modifier= Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    label={Text(stringResource(id = R.string.password))},
                    // placeholder={Text(text="********")},
                    keyboardOptions= KeyboardOptions(keyboardType= KeyboardType.Password, imeAction = ImeAction.Done),
                    onValueChange={
                        password=it
                    },
                )

                Row(
                    modifier = Modifier.padding(vertical = 24.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextButton(onClick = {
                        navigateUpTo(navController, Screen.Register)
                    }) {
                        Text(stringResource(id = R.string.register))
                    }
                    val context = LocalContext.current
                    val loginError = stringResource(id = R.string.toast_login_error)
                    Button(
                        onClick = {
                            loginVM.loginUser(UserLoginInfo(epost.text, password.text)){ cbLoginStatus->
                                if(cbLoginStatus.loginStatus) {
                                    navigateUpTo(navController, Screen.Home)
                                } else {
                                    Toast.makeText(context, loginError, Toast.LENGTH_LONG).show()
                                }
                            }
                        },
                    ) {
                        Text(stringResource(id = R.string.login))
                    }
                }
            }
        }
    }
}