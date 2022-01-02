package com.example.roomwordsample.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roomwordsample.R
import com.example.roomwordsample.app.WordsApplication
import com.example.roomwordsample.domain.entity.Word
import com.example.roomwordsample.presentation.ui.theme.RoomWordTheme

class NewWordActivity : ComponentActivity() {
    private val wordViewModel: NewWordViewModel by viewModels {
        NewWordViewModelFactory((application as WordsApplication).repository)
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoomWordTheme {
                NewWordScreen(
                    onClickSave = { text ->
                        val word = Word(text)
                        wordViewModel.insert(word)
                        finish()
                    }
                )
            }
        }
    }

    @Composable
    fun NewWordScreen(
        onClickSave: (String) -> Unit
    ) {
        var text by rememberSaveable {
            mutableStateOf("")
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Register new words"
                        )
                    }
                )
            },
        ) {
            Column {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .sizeIn(minHeight = 48.dp)
                        .padding(16.dp),
                    value = text,
                    textStyle = TextStyle(fontSize = 18.sp),
                    label = {
                        Text(stringResource(R.string.hint_word))
                    },
                    onValueChange = {
                        text = it
                    }
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    onClick = {
                        onClickSave(text)
                    }
                ) {
                    Text(
                        color = colorResource(R.color.buttonLabel),
                        text = stringResource(R.string.button_save)
                    )
                }
            }
        }
    }

    @Preview
    @Composable
    fun NewWordScreenPreview() {
        RoomWordTheme {
            NewWordScreen({})
        }
    }
}