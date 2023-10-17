package com.favoratti.lucifer.business

import com.favoratti.lucifer.data.Database

class Data(
    private val database: Database = Database()
) {

    companion object {
        const val HIDDEN_KEY = ','
    }

    private var demonPraise = database.getDemonPraise()

    var hiddenAnswer: String = ""
    var question: String = ""

    private var isTypingHiddenAnswer = false

    fun updateValue(value: String) {
        value.run {
            updateDemonPraiseIfNeeded(this)
            if (isNewChar(this)) {
                getNewChar(this)
            }
        }
    }

    fun clean() {
        demonPraise = database.getDemonPraise()

        hiddenAnswer = ""
        question = ""

        isTypingHiddenAnswer = false
    }

    private fun updateDemonPraiseIfNeeded(value: String) {
        if (value.isEmpty()) {
            demonPraise = database.getDemonPraise()
        }
    }

    private fun isNewChar(value: String) = question.length < value.length

    private fun getNewChar(value: String) {
        value.last().run {
            if (isHiddenKey(this)) {
                isTypingHiddenAnswer = isTypingHiddenAnswer.not()
            } else {
                if (isTypingHiddenAnswer) {
                    updateHiddenAnswer(this)
                } else {
                    updateQuestion(this)
                }
            }
        }
    }

    private fun isHiddenKey(newChar: Char) = newChar == HIDDEN_KEY

    private fun updateQuestion(newChar: Char) {
        question += newChar
    }

    private fun updateHiddenAnswer(newChar: Char) {
        hiddenAnswer += newChar
        question += getNextQuestionChar()
    }

    private fun getNextQuestionChar(): String {
        val startIndex = question.length + hiddenAnswer.length
        val endIndex = startIndex + 1
        return question.substring(startIndex, endIndex)
    }
}