package com.example.mob3000_root_app.components.cards

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.*
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.mob3000_root_app.R
import com.example.mob3000_root_app.components.viewmodel.AppViewModel
import com.example.mob3000_root_app.data.apiResponse.Comment
import com.example.mob3000_root_app.ui.theme.Underlined
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CommentSectionArticle(isCommenting: Boolean,
                    onCommentingChanged: () -> Unit,
                    keyboardController: SoftwareKeyboardController,
                    focusManager: FocusManager,
                    appVM: AppViewModel,
                    articleID: String
){
    val articleVM = appVM.articleVM

    var comment by remember{
        mutableStateOf(TextFieldValue(text = "", selection = TextRange(0)))
    }
    var commentList by remember {
        mutableStateOf(appVM.reverseArray(articleVM.focusedArticle.comments ) as List<Comment>)
    }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column(
         Modifier.fillMaxHeight()
    ) {
        Box{
            OutlinedTextField(
                value = comment.text,
                onValueChange = {
                    comment = TextFieldValue( it , TextRange(it.length))
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Comment,
                        contentDescription = null
                    )
                },
                singleLine = false,
                maxLines = 2,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .onFocusChanged {
                        if (isCommenting) keyboardController.hide()
                        onCommentingChanged()
                    }
                    .onFocusEvent {
                        keyboardController.show()
                    },
//                    .focusRequester(focusRequester),
                placeholder = { Text(stringResource(id = R.string.comment_textfield_placeholder)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Send),
                keyboardActions = KeyboardActions(
                    onSend = {
                        if( comment.text.isNotEmpty() )
                            articleVM.postComment(articleID, comment.text){
                                articleVM.focusArticleByID(articleID) {response ->
                                    if(response) {
                                        commentList = appVM.reverseArray(articleVM.focusedArticle.comments) as List<Comment>
                                        onCommentingChanged()
                                        comment = TextFieldValue(text = "", selection = TextRange(0))
                                        focusManager.clearFocus(true)
                                        coroutineScope.launch {
                                            listState.animateScrollToItem(0)
                                        }
                                    }
                                }
                            }
                    }
                )
            )
        }
        LazyColumn(
            Modifier.heightIn(0.dp, 300.dp),
            state = listState
        ){
            items(items =
            commentList
//            articleModel.focusedArticle.comments
            ){ item->
                run {
                    Comment(item)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CommentSectionEvent(isCommenting: Boolean,
                        onCommentingChanged: () -> Unit,
                        keyboardController: SoftwareKeyboardController,
                        focusManager: FocusManager,
                        appVM: AppViewModel,
                        eventId: String
){
    val eventVM = appVM.eventVM

    var comment by remember{
        mutableStateOf(TextFieldValue(text = "", selection = TextRange(0)))
    }
    var commentList by remember {
        mutableStateOf( appVM.reverseArray( eventVM.focusedEvent.comments) as List<Comment>)
    }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        Modifier.fillMaxHeight()
    ) {
        Box{
            OutlinedTextField(
                value = comment.text,
                onValueChange = {
                    comment = TextFieldValue( it , TextRange(it.length))
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Comment,
                        contentDescription = null
                    )
                },
                singleLine = false,
                maxLines = 2,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .onFocusChanged {
                        if (isCommenting) keyboardController.hide()
                        onCommentingChanged()
                    }
                    .onFocusEvent {
                        keyboardController.show()
                    },
//                    .focusRequester(focusRequester),
                placeholder = { Text(stringResource(id = R.string.comment_textfield_placeholder)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Send),
                keyboardActions = KeyboardActions(
                    onSend = {
                        if( comment.text.isNotEmpty() )
                            eventVM.postComment(eventId, comment.text){
                                eventVM.focusEventByID(eventId) {response ->
                                    if(response) {
                                        commentList = appVM.reverseArray(eventVM.focusedEvent.comments) as List<Comment>
                                        onCommentingChanged()
                                        comment = TextFieldValue(text = "", selection = TextRange(0))
                                        focusManager.clearFocus(true)
                                        coroutineScope.launch {
                                            listState.animateScrollToItem(0)
                                        }
                                    }
                                }
                            }
                    },
                )
            )
        }
        LazyColumn(Modifier.heightIn(0.dp, 300.dp)
        ){
            items(items = commentList ) { item->
                run {
                    Comment(item)
                }
            }
        }
    }
}

@Composable
fun Comment(comment: Comment){
    Card(
        Modifier
            .fillMaxWidth()
            .padding(5.dp),
            shape = MaterialTheme.shapes.small){
        Text(text = comment.user.firstname + " " + comment.user.lastname,
            Modifier.padding(5.dp),
            style = Underlined
        )
        Text(
            text = comment.comment,
            Modifier.padding(start = 5.dp, bottom = 5.dp)
        )
    }
}