package com.example.mob3000_root_app.screens.profile

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
// import androidx.compose.foundation.gestures.ModifierLocalScrollableContainerProvider.value
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.twotone.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mob3000_root_app.components.navigation.Screen
import com.example.mob3000_root_app.components.navigation.navigateUpTo
import com.example.mob3000_root_app.components.viewmodel.AppViewModel
import com.example.mob3000_root_app.components.viewmodel.LoginViewModel
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
                Text(text = "Login", fontSize = MaterialTheme.typography.headlineLarge.fontSize)

                OutlinedTextField(
                    value=epost,
                    leadingIcon={
                        Icon(imageVector= Icons.Default.Person,contentDescription=null)},
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
                    label={Text(text="Password")},
                    // placeholder={Text(text="********")},
                    keyboardOptions= KeyboardOptions(keyboardType= KeyboardType.Password),
                    onValueChange={
                        password=it
                    },
                )

                Row(
                    modifier = Modifier.padding(vertical = 24.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextButton(onClick = {
                        Log.i("loginStatus", loginVM.loginStatusResponse.toString())
                        navigateUpTo(navController, Screen.Register)
                        //Log.i("loginStatus", loginViewModel.getLoginStatus().toString())
                    }) {
                        Text("Create new user")
                    }
                    val context = LocalContext.current
                    Button(
                        onClick = {
                            loginVM.loginUser(UserLoginInfo(epost.text, password.text)){ cbLoginStatus->
                                if(cbLoginStatus.loginStatus) {
                                    navigateUpTo(navController, Screen.Home)
                                } else {
                                    Toast.makeText(context, "Login error, please try again", Toast.LENGTH_LONG).show()
                                }
                            }
                        },
                    ) {
                        Text("Login")
                    }
                }
            }
        }
    }
}