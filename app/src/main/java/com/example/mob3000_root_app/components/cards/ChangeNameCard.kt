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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun changeName(
    user: User,
    loginViewModel: LoginViewModel
) {
    var firstname by remember{ mutableStateOf(TextFieldValue(user.firstname)) }
    var lastname by remember{ mutableStateOf(TextFieldValue(user.lastname)) }
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
        val context = LocalContext.current;
        Row(
            Modifier
                .padding(5.dp)
                .align(Alignment.End)){
            Button(onClick = {
                loginViewModel.changeName(NameChange(firstname.text, lastname.text)){ status: ResponseStatus? ->
                    if (status != null) {
                        if(status.status!=210){
                            Toast.makeText(context, "Navn endret", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Noe gikk galt", Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            }) {
                Text(stringResource(id = R.string.setting_change_name))
            }
        }
    }
}