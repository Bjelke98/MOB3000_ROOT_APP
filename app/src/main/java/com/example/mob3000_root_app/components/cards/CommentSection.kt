package com.example.mob3000_root_app.components.cards

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mob3000_root_app.data.Comment
import com.example.mob3000_root_app.ui.theme.Underlined

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CommentSection(comments:List<Comment>, isCommenting: Boolean, onCommentingChanged: () -> Unit, keyboardController: SoftwareKeyboardController){
    var comment by remember{ mutableStateOf(TextFieldValue("")) }
    val focusRequester = remember { FocusRequester() }
    var height = if(!isCommenting) 150.dp else 50.dp
//    val keyboardController = LocalSoftwareKeyboardController.current

    Column {

        LazyColumn(
            Modifier.conditional(isCommenting, ifTrue = { fillMaxHeight( .5f)}, ifFalse = {fillMaxHeight(.7f)})
        ){
            items(items = comments){ item->
                run {
                    Comment(item)
                }
            }
        }

        Box(
            Modifier
                .border(2.dp, Color.Companion.Gray, MaterialTheme.shapes.small)

        ){
            OutlinedTextField(
                value = comment,
//                readOnly = !isCommenting,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Comment,
                        contentDescription = null
                    )
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .conditional( isCommenting, ifTrue = {fillMaxHeight()} )
                    .height(height)
                    .onFocusChanged {
                        if(isCommenting) keyboardController.hide()
                        onCommentingChanged()
                    }
                    .onFocusEvent { keyboardController.show() },
//                    .focusRequester(focusRequester),
                placeholder = { Text(text = "Write what's on your mind") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                onValueChange = {
                    comment = it
                }
            )
        }
    }
}

@Composable
fun Comment(comment:Comment){
    Card(
        Modifier
            .fillMaxWidth()
            .padding(5.dp),
            shape = MaterialTheme.shapes.small){
        Text(text = comment.user.firstname + " " + comment.user.lastname,
            Modifier.padding(5.dp),
            style = Underlined
        )
        Text(text = comment.comment,
        Modifier.padding(start = 5.dp, bottom = 5.dp))
    }
}

fun Modifier.conditional(
    condition: Boolean,
    ifTrue: Modifier.() -> Modifier,
    ifFalse: (Modifier.() -> Modifier)? = null
): Modifier {
    return if (condition) {
        then(ifTrue(Modifier))
    } else if (ifFalse != null) {
        then(ifFalse(Modifier))
    } else {
        this
    }
}

@Preview
@Composable
fun MyPreview(){
//    CommentSection(comments = ArticleTestdata().comments, true, {})
}