package com.example.mob3000_root_app.components.cards

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.viewmodel.LoginViewModel
import com.example.mob3000_root_app.data.apiRequest.NameChange
import com.example.mob3000_root_app.data.apiResponse.ResponseStatus
import com.example.mob3000_root_app.data.apiResponse.User
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeName(
    user: User,
    loginViewModel: LoginViewModel
) {
    var firstname by remember{ mutableStateOf(TextFieldValue(user.firstname)) }
    var lastname by remember{ mutableStateOf(TextFieldValue(user.lastname)) }
    val context = LocalContext.current
    val nameChangedToastText = stringResource(id = R.string.toast_name_changed)
    val somethingWentWrongToastText = stringResource(id = R.string.toast_something_went_wrong)

    val successToast = Toast.makeText(context, nameChangedToastText, Toast.LENGTH_SHORT)
    val errorToast = Toast.makeText(context, somethingWentWrongToastText, Toast.LENGTH_SHORT)


    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stringResource(id = R.string.setting_change_name), style = MaterialTheme.typography.headlineSmall)
        OutlinedTextField(
            value = firstname,
            onValueChange = {
                firstname = it
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            label = { Text(text =  stringResource(id = R.string.firstname)) },
            placeholder = { Text(text = stringResource(id = R.string.firstname)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    ChangeName(loginViewModel, firstname, lastname, errorToast, successToast)
                }
            )
        )
        OutlinedTextField(
            value = lastname,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            label = { Text(text = stringResource(id = R.string.lastname)) },
            onValueChange = {
                lastname = it
            },
            placeholder = { Text(text = stringResource(id = R.string.lastname)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    ChangeName(loginViewModel, firstname, lastname, errorToast, successToast)
                }
            )
        )
        Row(
            Modifier
                .padding(5.dp)
                .align(Alignment.End)){
            Button(onClick = {
                ChangeName(loginViewModel, firstname, lastname, errorToast, successToast)
            }) {
                Text(stringResource(id = R.string.setting_change_name))
            }
        }
    }
}
private fun ChangeName(loginViewModel:LoginViewModel,
                       firstname:TextFieldValue,
                       lastname: TextFieldValue,
                       errorToast: Toast,
                       successToast: Toast
){
    loginViewModel.changeName(NameChange(firstname.text, lastname.text)){ status: ResponseStatus? ->
        if (status != null) {
            if(status.status!=210){
                loginViewModel.getLoginStatus()
                successToast.show()
            } else {
                errorToast.show()
            }
        } else errorToast.show()

    }
}