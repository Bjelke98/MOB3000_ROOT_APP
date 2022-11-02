package com.example.mob3000_root_app.components.buttons

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable


@Composable
fun SlettArtikkel() {
    TextButton(
        onClick = { slett()
        }) {
        Text("Slett")
    }
}
private fun slett(){

}