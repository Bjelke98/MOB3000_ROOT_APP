package com.example.mob3000_root_app.components.cards

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.navigation.Screen
import com.example.mob3000_root_app.components.navigation.navigateUpTo
import com.example.mob3000_root_app.components.viewmodel.AppViewModel
import com.example.mob3000_root_app.data.apiRequest.DeleteUser
import com.example.mob3000_root_app.data.apiResponse.ResponseStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun deleteUser(
    appVM: AppViewModel
) {
    val loginViewModel = appVM.loginVM
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var confirmPassword by remember { mutableStateOf(TextFieldValue("")) }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stringResource(id = R.string.setting_delete_user), style = MaterialTheme.typography.headlineSmall);
        OutlinedTextField(
            value=password,
            leadingIcon={ Icon(imageVector= Icons.Default.Lock,contentDescription=null) },
            modifier= Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            label={ Text(text= stringResource(id = R.string.password)) },
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
            placeholder={ Text(stringResource(id = R.string.placeholder_pw)) },
            keyboardOptions= KeyboardOptions(keyboardType= KeyboardType.Password),
            visualTransformation= PasswordVisualTransformation(),
            onValueChange={
                confirmPassword=it
            }
        )
        val context = LocalContext.current
        val userDeletedToastText = stringResource(id = R.string.toast_user_deleted)
        val somethingWentWrongToastText = stringResource(id = R.string.toast_something_went_wrong)
        val writeSamePasswordToastText = stringResource(id = R.string.toast_write_same_password)
        Row(
            Modifier
                .padding(5.dp)
                .align(Alignment.End)){
            Button(onClick = {
                if (password.text.equals(confirmPassword.text)){
                    loginViewModel.deleteUser(DeleteUser(password.text)){ status: ResponseStatus? ->
                        if (status != null) {
                            if(status.status!=210){
                                loginViewModel.getLoginStatus()
                                navigateUpTo(appVM.navController, Screen.Home)
                                Toast.makeText(context, userDeletedToastText, Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, somethingWentWrongToastText, Toast.LENGTH_SHORT).show()
                            }
                        } else Toast.makeText(context, somethingWentWrongToastText, Toast.LENGTH_SHORT).show()
                    }
                } else Toast.makeText(context, writeSamePasswordToastText, Toast.LENGTH_SHORT).show()
            }) {
                Text(stringResource(id = R.string.setting_delete_user))
            }
        }
    }
}