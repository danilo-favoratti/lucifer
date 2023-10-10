package com.favoratti.lucifer.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.lifecycle.ViewModel

class LuciferViewModel: ViewModel() {

    private val mQuestionState = mutableStateOf("")
    val questionState: State<String> = mQuestionState

    private val mAnswersState = mutableStateListOf<Pair<String, String>>()
    val answersState: SnapshotStateList<Pair<String, String>> = mAnswersState

    fun onValueChanged(value: String) {
        mQuestionState.value = value
    }

    fun onQuestionSent() {
        mAnswersState.add(Pair(mQuestionState.value, mQuestionState.value + "2"))
        mQuestionState.value = ""
    }
}