package com.example.mob3000_root_app.data

data class LoginStatus(
    val loginStatus: Boolean = false,
    val user: User? = null
) {
    override fun toString(): String {
        var out = "Status: $loginStatus"
        if (user!=null)
            out+="\n$user"
        return out
    }
}
