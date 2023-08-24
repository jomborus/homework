package ru.otus.basicarchitecture.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SuggestionsApi {
    @GET("address")
    suspend fun getClue(@Query("query") query: String): Response<Suggestions>
}
