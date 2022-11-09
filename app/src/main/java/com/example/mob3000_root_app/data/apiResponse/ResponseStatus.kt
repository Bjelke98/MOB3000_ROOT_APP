package com.example.mob3000_root_app.data.apiResponse

data class ResponseStatus (val status: Int){

    override fun toString(): String {
        return "status: $status"
    }
}