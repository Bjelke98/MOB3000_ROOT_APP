package com.example.mob3000_root_app.data.apiResponse

import com.squareup.moshi.Json

data class NewUser(
    @Json(name = "firstname") val firstname: String,
    @Json(name = "lastname") val lastname: String,
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String
){
    override fun toString(): String {
        return "NewUser: \n" +
                "\t_firstname = '$firstname' \n" +
                "\t_lastname = '$lastname' \n" +
                "\t_epost = '$email' \n" +
                "\t_password  = '$password' \n"
    }
}
