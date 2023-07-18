package ru.otus.basicarchitecture.data

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfilePreferences @Inject constructor(
    context: Context,
) {
    private val preferences = context.getSharedPreferences("profile", Context.MODE_PRIVATE)

    fun putName(name: String) {
        preferences.edit().putString("name", name).apply()
    }

    fun getName(): String {
        return preferences.getString("name", "").orEmpty()
    }

    fun putSurname(surname: String) {
        preferences.edit().putString("surname", surname).apply()
    }

    fun getSurname(): String {
        return preferences.getString("surname", "").orEmpty()
    }

    fun putBirthday(birthday: String) {
        preferences.edit().putString("birthday", birthday).apply()
    }

    fun getBirthday(): String {
        return preferences.getString("birthday", "").orEmpty()
    }

    fun putAddress(address: String) {
        preferences.edit().putString("address", address).apply()
    }

    fun getAddress(): String {
        return preferences.getString("address", "").orEmpty()
    }

    fun putInterests(interests: List<String>) {
        preferences.edit().putString("interests", interests.joinToString(",")).apply()
    }

    fun getInterests(): List<String> {
        return preferences.getString("interests", "").orEmpty().split(",")
    }
}
