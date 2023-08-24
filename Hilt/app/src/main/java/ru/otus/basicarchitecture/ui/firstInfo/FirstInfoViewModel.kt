package ru.otus.basicarchitecture.ui.firstInfo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.otus.basicarchitecture.data.ProfilePreferences
import javax.inject.Inject

@HiltViewModel
class FirstInfoViewModel @Inject constructor(
    private val profilePreferences: ProfilePreferences
) : ViewModel() {
    private val _navigate = MutableSharedFlow<Unit>()
    val navigate = _navigate.asSharedFlow()

    private val _errorText = MutableStateFlow(false)
    val errorText = _errorText.asStateFlow()

    fun onClickListener(name: String, surname: String, date: String) {
        viewModelScope.launch {
            if (!_errorText.value) {
                profilePreferences.putName(name)
                profilePreferences.putSurname(surname)
                profilePreferences.putBirthday(date)
                _navigate.emit(Unit)
            }
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
