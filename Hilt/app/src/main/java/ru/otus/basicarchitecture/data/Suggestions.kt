package ru.otus.basicarchitecture.data

import com.google.gson.annotations.SerializedName

data class Suggestions(
    val suggestions: List<Value>,
)

data class Value(
    val value: String
)
