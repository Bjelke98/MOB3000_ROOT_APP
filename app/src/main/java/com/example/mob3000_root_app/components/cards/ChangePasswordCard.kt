package com.example.mob3000_root_app.components.cards

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
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
import com.example.mob3000_root_app.components.viewmodel.LoginViewModel
import com.example.mob3000_root_app.data.apiRequest.PasswordChange
import com.example.mob3000_root_app.data.apiResponse.ResponseStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePassword(
    loginViewModel: LoginViewModel
) {
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var confirmPassword by remember { mutableStateOf(TextFieldValue("")) }
    var oldPassword by remember { mutableStateOf(TextFieldValue("")) }

    val context = LocalContext.current
    val successToast =  Toast.makeText(context, stringResource(id = R.string.toast_password_changed), Toast.LENGTH_SHORT)
    val errorToast =  Toast.makeText(context, stringResource(id = R.string.toast_something_went_wrong), Toast.LENGTH_SHORT)
    val samePassToast= Toast.makeText(context, stringResource(id = R.string.toast_write_same_password), Toast.LENGTH_SHORT)


    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stringResource(id = R.string.setting_change_password), style = MaterialTheme.typography.headlineSmall)
        OutlinedTextField(
            value=password,
            leadingIcon={ Icon(imageVector= Icons.Default.Lock,contentDescription=null) },
            modifier= Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            label={ Text(text= stringResource(id = R.string.setting_new_password)) },
            placeholder={ Text(text="********") },
            keyboardOptions= KeyboardOptions(keyboardType= KeyboardType.Password, imeAction = ImeAction.Next),
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
            keyboardOptions= KeyboardOptions(keyboardType= KeyboardType.Password, imeAction = ImeAction.Next),
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
            placeholder={ Text(stringResource(id = R.string.placeholder_pw)) },
            keyboardOptions= KeyboardOptions(keyboardType= KeyboardType.Password, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    ChangePassword(loginViewModel, password, confirmPassword,oldPassword,successToast,errorToast,samePassToast)
                }
            ),
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
                ChangePassword(
                    loginViewModel,
                    password,
                    confirmPassword,
                    oldPassword,
                    successToast,
                    errorToast,
                    samePassToast
                )
            }) {
                Text(stringResource(id = R.string.setting_change_password))
            }
        }
    }
}

private fun ChangePassword(
    loginViewModel: LoginViewModel,
    password: TextFieldValue,
    confirmPassword: TextFieldValue,
    oldPassword: TextFieldValue,
    successToast: Toast,
    errorToast: Toast,
    samePassToast: Toast
){
    if (password.text.equals(confirmPassword.text)){
        loginViewModel.changePassword(PasswordChange(oldPassword.text, password.text)){ status: ResponseStatus? ->
            if (status != null) {
                if(status.status!=210){
                    successToast.show()
                } else {
                    errorToast.show()
                }
            }
        }
    } else{
        samePassToast.show()
    }
}