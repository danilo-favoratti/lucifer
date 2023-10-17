package com.favoratti.lucifer

import com.favoratti.lucifer.data.Database
import com.favoratti.lucifer.viewmodel.LuciferViewModel
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class LuciferViewModelTest {
    companion object {
        private const val DEMON_PRAISE = "Demon praise example."
    }

    private val luciferViewModel = LuciferViewModel(Database(listOf(DEMON_PRAISE)))

    @Test
    fun `When hidden key is not pressed, no hidden answer is available`() {
        val message = "Test message with no hidden key."
        typeMessage(message)

        luciferViewModel.onQuestionSent()

        assertThat(luciferViewModel.hiddenAnswersState[0].first, equalTo(message))
        assertThat(luciferViewModel.hiddenAnswersState[0].second, equalTo(Database.NO_HIDDEN_ANSWER_DEFAULT))
    }

    @Test
    fun `When hidden key is pressed at the beginning, hidden answer is available`() {
        val message = ", answer, Test message with no hidden key."
        typeMessage(message)

        luciferViewModel.onQuestionSent()

        assertThat(
            luciferViewModel.hiddenAnswersState[0].first,
            equalTo("Demon p Test message with no hidden key.")
        )
        assertThat(luciferViewModel.hiddenAnswersState[0].second, equalTo(" answer"))
    }

    private fun typeMessage(message: String) {
        var value = ""
        message.chars().forEach {
            value += it.toChar()
            luciferViewModel.onValueChanged(value)
        }
    }
}