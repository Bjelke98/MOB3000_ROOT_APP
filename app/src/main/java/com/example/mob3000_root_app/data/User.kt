package com.example.mob3000_root_app.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val _id: String,
    val firstname: String,
    val lastname: String,
    val email: String,
    val rootMember: Boolean,
    val admin: Boolean,
    val editor: Boolean
) : Parcelable {
    override fun toString(): String {
        return "User:\n\t_id='$_id'\n\tfirstname='$firstname'\n\tlastname='$lastname'\n\temail='$email'\n\trootMember=$rootMember\n\tadmin=$admin\n\teditor=$editor"
    }
}