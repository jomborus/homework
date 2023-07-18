package ru.otus.basicarchitecture.ui.info

data class State(
    val name: String,
    val surname: String,
    val birthday: String,
    val address: String,
    val interests: List<String>
)
