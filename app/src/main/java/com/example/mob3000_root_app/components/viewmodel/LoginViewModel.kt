package com.example.mob3000_root_app.components.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mob3000_root_app.data.RootService
import com.example.mob3000_root_app.data.apiRequest.DeleteUser
import com.example.mob3000_root_app.data.apiRequest.NameChange
import com.example.mob3000_root_app.data.apiRequest.PasswordChange
import com.example.mob3000_root_app.data.apiRequest.UserLoginInfo
import com.example.mob3000_root_app.data.apiResponse.LoginStatus
import com.example.mob3000_root_app.data.apiResponse.NewUser
import com.example.mob3000_root_app.data.apiResponse.ResponseStatus
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel(){
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

    // Registrer bruker
    fun registerUser(userRegisterInfo: NewUser, cb: (result: String?)->Unit){
        viewModelScope.launch {
            val apiService = RootService.getInstance()
            try {
                val registerStatus = apiService.addUser(userRegisterInfo)
                Log.i("registrerinfo", registerStatus)
                cb.invoke(registerStatus)
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.i("registrerinfo", errorMessage)
                cb.invoke(null)
            }
        }
    }

    fun changeName(nameChange: NameChange, cb: (status: ResponseStatus)-> Unit){
        viewModelScope.launch {
            val apiService = RootService.getInstance()
            try {
                val responseStatus = apiService.updateUser(nameChange)
                cb.invoke(responseStatus)
                // må bytte navn til det endra navnet
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun changePassword(passwordChange: PasswordChange, cb: (status: ResponseStatus)-> Unit){
        viewModelScope.launch {
            val apiService = RootService.getInstance()
            try {
                val responseStatus = apiService.newPassword(passwordChange)
                cb.invoke(responseStatus)
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun deleteUser(deleteUser: DeleteUser, cb: (status: ResponseStatus)-> Unit){
        viewModelScope.launch {
            val apiService = RootService.getInstance()
            try {
                val responseStatus = apiService.deleteUser(deleteUser)
                cb.invoke(responseStatus)
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun logoutUser(){
        viewModelScope.launch {
            val apiService = RootService.getInstance()
            try {
                apiService.logout()
                getLoginStatus()
                //du får tilbake status kode 200 eller 210
            }
            catch (e: Exception) {
                getLoginStatus() // må ha den her siden logout vil alltid gi en error hehe
                errorMessage = e.message.toString()
            }
        }
    }
}