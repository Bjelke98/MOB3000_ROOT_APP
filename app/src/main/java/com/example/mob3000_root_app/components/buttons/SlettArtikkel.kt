package com.example.mob3000_root_app.components.buttons

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


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