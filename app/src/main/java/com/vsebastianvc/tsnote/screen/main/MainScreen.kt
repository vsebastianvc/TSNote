package com.vsebastianvc.tsnote.screen.main

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vsebastianvc.tsnote.R
import com.vsebastianvc.tsnote.components.NoteButton
import com.vsebastianvc.tsnote.components.NoteInputText
import com.vsebastianvc.tsnote.model.Note
import com.vsebastianvc.tsnote.utils.formatDate

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel()
) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_ts),
                    contentDescription = "TS",
                    tint = Color.Unspecified
                )
            }
        }
        )
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CreateNote(mainViewModel = mainViewModel)
            Divider(modifier = Modifier.padding(10.dp), thickness = 2.dp)
            NoteList(mainViewModel = mainViewModel)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CreateNote(mainViewModel: MainViewModel) {

    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val toastMessage = stringResource(R.string.note_added)

    NoteInputText(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        text = title,
        label = stringResource(R.string.title),
        onTextChange = {
            if (it.all { char ->
                    char.isLetter() || char.isWhitespace()
                }) title = it

        })

    NoteInputText(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        text = description,
        label = stringResource(R.string.add_a_note),
        onTextChange = {
            if (it.all { char ->
                    char.isDefined()
                }) description = it
        })

    NoteButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        text = stringResource(R.string.save),
        onClick = {
            if (title.isNotEmpty() && description.isNotEmpty()) {
                mainViewModel.addNote(
                    Note(
                        title = title,
                        description = description
                    )
                )
                title = ""
                description = ""
                keyboardController?.hide()
                focusManager.clearFocus()

                Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
            }
        })
}

@Composable
fun NoteList(mainViewModel: MainViewModel) {
    val noteList = mainViewModel.getAllNotes()
    if (noteList.isEmpty()) {
        EmptyNoteListScreen()
    } else {
        LazyColumn {
            items(noteList) { noteItem ->
                NoteRow(note = noteItem, onNoteClicked = {
                    mainViewModel.removeNote(noteItem)
                })
            }
        }
    }
}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked: (Note) -> Unit
) {
    Surface(
        modifier
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = MaterialTheme.colors.surface,
        elevation = 6.dp
    ) {
        Column(
            modifier
                .clickable {
                    onNoteClicked(note)
                }
                .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                text = note.description,
                style = MaterialTheme.typography.subtitle1
            )
            Text(
                text = formatDate(note.entryDate.time),
                style = MaterialTheme.typography.caption
            )
        }
    }
}

@Composable
fun EmptyNoteListScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_notes),
            contentDescription = "Add Note",
            modifier = Modifier.size(100.dp)
        )
        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = stringResource(R.string.no_notes_yet),
            style = MaterialTheme.typography.h3,
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 24.sp
        )
    }
}


@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    MainScreen()
}
