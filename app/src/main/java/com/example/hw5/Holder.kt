package com.example.hw5

import androidx.annotation.DrawableRes
import java.util.*

object Holder {
    private val persons = mutableListOf<Person>()

    fun createCollectionPerson(name: String): MutableList<Person> {
            val person = Person(
                name
            )
            persons.add(person)
        return persons
    }
}

data class Person(
    val name: String
)