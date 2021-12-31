package com.example.roomwordsample.presentation.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.roomwordsample.R
import com.example.roomwordsample.app.WordsApplication
import com.example.roomwordsample.domain.entity.Word
import com.example.roomwordsample.presentation.ui.items.WordText
import com.example.roomwordsample.presentation.ui.theme.RoomWordTheme

class MainActivity : ComponentActivity() {
    private val newWordActivityRequestCode = 1
    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((application as WordsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val words by wordViewModel.allWords.observeAsState()

            RoomWordTheme {
                MainScreen(
                    words = words ?: listOf(),
                    onClickFab = {
                        val intent = Intent(this@MainActivity, NewWordActivity::class.java)
                        startActivityForResult(intent, newWordActivityRequestCode)
                    }
                )
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(NewWordActivity.EXTRA_REPLY)?.let {
                val word = Word(it)
                wordViewModel.insert(word)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    @Composable
    fun MainScreen(
        words: List<Word>,
        onClickFab: () -> Unit
    ) {
        MaterialTheme {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "List of registered words"
                            )
                        }
                    )
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = onClickFab
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = stringResource(R.string.add_word)
                        )
                    }
                }
            ) {
                LazyColumn(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    items(words) { word ->
                        WordText(word)
                    }
                }
            }
        }
    }

    @Preview
    @Composable
    fun MainScreenPreview() {
        RoomWordTheme {
            MainScreen(listOf(Word("test1"), Word("test2")), {})
        }
    }
}