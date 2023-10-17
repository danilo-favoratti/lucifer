package com.favoratti.lucifer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.favoratti.lucifer.ui.theme.LuciferTheme
import com.favoratti.lucifer.viewmodel.LuciferViewModel

class MainActivity : ComponentActivity() {

    private val mLuciferViewModel: LuciferViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LuciferTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppSection(luciferViewModel = mLuciferViewModel)
                }
            }
        }
    }
}

@Composable
fun AppSection(luciferViewModel: LuciferViewModel) {
    Column {
        QuestionSection(luciferViewModel = luciferViewModel)
        AnswerSection(luciferViewModel = luciferViewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionSection(
    luciferViewModel: LuciferViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 150.dp)
    ) {
        TextField(
            value = luciferViewModel.questionState.value,
            onValueChange = luciferViewModel::onValueChanged
        )
        Button(onClick = luciferViewModel::onQuestionSent) {
            Text(text = "Send to Hell")
        }
    }
}

@Composable
fun AnswerSection(
    luciferViewModel: LuciferViewModel
) {
    luciferViewModel.hiddenAnswersState.forEach {
        Card {
            Text(text = it.first, color = Color.Black)
        }
        Card {
            Text(text = it.second, color = Color.Red)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LuciferTheme {
        AppSection(luciferViewModel = LuciferViewModel())
    }
}