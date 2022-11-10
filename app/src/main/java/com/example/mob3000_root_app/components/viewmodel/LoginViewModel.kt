package com.example.mob3000_root_app.components.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mob3000_root_app.data.RootService
import com.example.mob3000_root_app.data.apiRequest.UserLoginInfo
import com.example.mob3000_root_app.data.apiResponse.LoginStatus
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel(){
//    var loginStatusResponse:LoginStatus by remember {
//        mutableStateOf(LoginStatus(user = null))
//    }
    var loginStatusResponse: LoginStatus by mutableStateOf(LoginStatus(user = null))
    var errorMessage: String by mutableStateOf("")
    fun getLoginStatus() {
        viewModelScope.launch {
            val apiService = RootService.getInstance()
            try {
                val loginStatus = apiService.getUser()
                loginStatusResponse = loginStatus
                Log.i("loginStatus", loginStatus.toString())
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    // Login uten callback
    fun loginUser(userLoginInfo: UserLoginInfo){
        viewModelScope.launch {
            val apiService = RootService.getInstance()
            try {
                val loginStatus = apiService.loginUser(userLoginInfo)
                loginStatusResponse = loginStatus
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
    // Login med callback
    fun loginUser(userLoginInfo: UserLoginInfo, cb: (result: LoginStatus)->Unit){
        viewModelScope.launch {
            val apiService = RootService.getInstance()
            try {
                val loginStatus = apiService.loginUser(userLoginInfo)
                loginStatusResponse = loginStatus
                cb.invoke(loginStatus)
            }
            catch (e: Exception) {
                cb.invoke(LoginStatus(user = null))
                errorMessage = e.message.toString()
            }
        }
    }
}