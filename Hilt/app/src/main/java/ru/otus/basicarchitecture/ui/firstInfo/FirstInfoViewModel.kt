package ru.otus.basicarchitecture.ui.firstInfo

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.otus.basicarchitecture.data.ProfilePreferences
import javax.inject.Inject

@HiltViewModel
class FirstInfoViewModel @Inject constructor(
    private val profilePreferences: ProfilePreferences
) : ViewModel() {
    private val _navigate = MutableStateFlow(false)
    val navigate = _navigate.asStateFlow()

    private val _errorText = MutableStateFlow(false)
    val errorText = _errorText.asStateFlow()

    fun onClickListener(name: String, surname: String, date: String) {
        if (!_errorText.value) {
            profilePreferences.putName(name)
            profilePreferences.putSurname(surname)
            profilePreferences.putBirthday(date)
            _navigate.update { true }
        }
    }

    fun doOnTextChanged(text: String) {
        val format = text.split(".")
        when {
            format.size == 1 -> _errorText.update { false }
            format.size == 2 && format[0].toInt() <= 31 -> _errorText.update { false }
            format.size == 3 && format[0].toInt() <= 31 && format[1].toInt() <= 12 -> _errorText.update { false }
            else -> _errorText.update { true }
        }
    }
}
