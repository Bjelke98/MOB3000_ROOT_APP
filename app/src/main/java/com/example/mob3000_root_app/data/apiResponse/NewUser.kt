package com.example.mob3000_root_app.data.apiResponse

data class NewUser(
    val firstname: String,
    val lastname: String,
    val email: String,
    val password: String,

){
    override fun toString(): String {
        return "NewUser: \n" +
                "\t_firstname = '$firstname' \n" +
                "\t_firstname = '$lastname' \n" +
                "\t_firstname = '$email' \n" +
                "\t_password  = '$password' \n"
    }
}
