package com.favoratti.lucifer.data

import kotlin.random.Random

class Database(
    private val demonPraises: List<String> = listOf(
        "Querido e amado demonio mestre de todos os seres vivos dessa terra.",
        "Oh grande ser das trevas, lorde de todo o universo. Ser supremo.",
        "Amado e desejado rei das trevas, maravilhoso e estupendo."
    )
) {

    companion object {
        const val NO_HIDDEN_ANSWER_DEFAULT = "Cade meu elogio?"
    }

    fun getDemonPraise() = demonPraises[Random.nextInt(0, demonPraises.size)]
}