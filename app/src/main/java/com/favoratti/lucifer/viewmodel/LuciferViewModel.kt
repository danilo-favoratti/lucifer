package com.favoratti.lucifer.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.favoratti.lucifer.business.Data
import com.favoratti.lucifer.data.Database

class LuciferViewModel(
    database: Database = Database()
) : ViewModel() {

    private val data = Data(database)

    private val mQuestionState = mutableStateOf("")
    val questionState: State<String> = mQuestionState

    private val mHiddenAnswersState = mutableStateListOf<Pair<String, String>>()
    val hiddenAnswersState: SnapshotStateList<Pair<String, String>> = mHiddenAnswersState

    fun onValueChanged(value: String) {
        data.updateValue(value)
        updateState()
    }

    fun onQuestionSent() {
        mHiddenAnswersState.add(
            Pair(
                mQuestionState.value,
                data.hiddenAnswer.ifEmpty { Database.NO_HIDDEN_ANSWER_DEFAULT })
        )
        data.clean()
        updateState()
    }

    private fun updateState() {
        mQuestionState.value = data.question
    }
}