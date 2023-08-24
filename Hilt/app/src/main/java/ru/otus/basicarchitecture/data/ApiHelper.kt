package ru.otus.basicarchitecture.data

import retrofit2.Response
import javax.inject.Inject

class ApiHelper @Inject constructor(
    private val suggestionsApi: SuggestionsApi
) {
    suspend fun getClue(query: String): Response<Suggestions> =
        suggestionsApi.getClue(query)
}