package com.example.mob3000_root_app.bjelkeTests

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.viewmodel.AppViewModel
import java.io.File

@Composable
fun UploadFileTest(appVM: AppViewModel) {
    val uploadVM = appVM.uploadVM
    val context = LocalContext.current
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        run {
            if (uri != null) {
                imageUri = uri
            }
        }
    }
    Column(Modifier.fillMaxSize()) {
        Button(onClick = {
            launcher.launch("image/*")

        }) {
            Text("Last opp")
        }

        imageUri?.let {
            val source = ImageDecoder
                .createSource(context.contentResolver,it)

            bitmap = ImageDecoder.decodeBitmap(source)

            bitmap?.let {  btm ->
                Image(
                    bitmap = btm.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier.size(300.dp)
                )
            }
        }
        Button(onClick = {
            imageUri?.let { uploadVM.newArticle(it) }
        }) {
            Text(text = "Upload")
        }
    }
}