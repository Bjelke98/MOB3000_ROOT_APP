package com.example.mob3000_root_app.components.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.viewmodel.LoginViewModel
import com.example.mob3000_root_app.data.apiRequest.PasswordChange
import com.example.mob3000_root_app.data.apiResponse.ResponseStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun changePassword(
    loginViewModel: LoginViewModel
) {
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var confirmPassword by remember { mutableStateOf(TextFieldValue("")) }
    var oldPassword by remember { mutableStateOf(TextFieldValue("")) }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stringResource(id = R.string.setting_change_password), style = MaterialTheme.typography.headlineSmall);
        OutlinedTextField(
            value=password,
            leadingIcon={ Icon(imageVector= Icons.Default.Lock,contentDescription=null) },
            modifier= Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            label={ Text(text= stringResource(id = R.string.setting_new_password)) },
            placeholder={ Text(text="********") },
            keyboardOptions= KeyboardOptions(keyboardType= KeyboardType.Password),
            visualTransformation= PasswordVisualTransformation(),
            onValueChange={
                password=it
            }
        )
        OutlinedTextField(
            value=confirmPassword,
            leadingIcon={ Icon(imageVector= Icons.Default.Lock,contentDescription=null) },
            modifier= Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            label={ Text(text= stringResource(id = R.string.setting_confirm_password)) },
            placeholder={ Text(text="********") },
            keyboardOptions= KeyboardOptions(keyboardType= KeyboardType.Password),
            visualTransformation= PasswordVisualTransformation(),
            onValueChange={
                confirmPassword=it
            }
        )
        OutlinedTextField(
            value=oldPassword,
            leadingIcon={ Icon(imageVector= Icons.Default.Lock,contentDescription=null) },
            modifier= Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            label={ Text(text= stringResource(id = R.string.setting_old_password)) },
            placeholder={ Text(text="********") },
            keyboardOptions= KeyboardOptions(keyboardType= KeyboardType.Password),
            visualTransformation= PasswordVisualTransformation(),
            onValueChange={
                oldPassword=it
            }
        )
        Row(
            Modifier
                .padding(5.dp)
                .align(Alignment.End)){
            Button(onClick = {
                if (password.text.equals(confirmPassword.text)){
                    loginViewModel.changePassword(PasswordChange(password.text, oldPassword.text)){ status: ResponseStatus? ->
                        if(status!=null){
                            print(status)
                        } else {

                        }
                    }
                } else{
                    //må skrive likt passord
                }

            }) {
                Text(stringResource(id = R.string.setting_change_password))
            }
        }
    }
}