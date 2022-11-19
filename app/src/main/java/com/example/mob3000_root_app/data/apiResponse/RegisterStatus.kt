package com.example.mob3000_root_app.data.apiResponse

data class RegisterStatus(
    val registerStatus: Boolean = false,
    val newUser: NewUser? = null
) {
    override fun toString(): String {
        var out = "Status: $registerStatus"
            if(newUser != null)
                out += "\n$"
        return out
    }
}
