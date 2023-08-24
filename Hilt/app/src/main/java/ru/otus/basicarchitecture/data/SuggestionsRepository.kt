package ru.otus.basicarchitecture.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SuggestionsRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    suspend fun getClue(query: String): Result<Suggestions> {
        return try {
            val response = apiHelper.getClue(query)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Result.success(result)
            } else {
                Result.failure(Throwable("An error occurred"))
            }
        } catch (e: Exception) {
            Result.failure(Throwable("Error occurred ${e.localizedMessage}"))
        }
    }
}