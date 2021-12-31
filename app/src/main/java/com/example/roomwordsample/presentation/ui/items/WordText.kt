package com.example.roomwordsample.presentation.ui.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.roomwordsample.R
import com.example.roomwordsample.domain.entity.Word

@Composable
fun WordText(
    word: Word
) {
    Text(
        text = word.word,
        modifier = Modifier
            .background(colorResource(id = R.color.holo_orange_light))
            .fillMaxWidth()
    )
    Spacer(
        modifier = Modifier
            .size(8.dp)
    )
}

@Preview
@Composable
fun WordTextPreview() {
    WordText(word = Word("あ"))
}
