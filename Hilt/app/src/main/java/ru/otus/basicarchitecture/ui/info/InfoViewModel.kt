package ru.otus.basicarchitecture.ui.info

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.otus.basicarchitecture.data.ProfilePreferences
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val profilePreferences: ProfilePreferences,
) : ViewModel() {
    private val _state = MutableStateFlow<State?>(null)
    val state = _state.asStateFlow()

    init {
        _state.update {
            State(
                name = profilePreferences.getName(),
                surname = profilePreferences.getSurname(),
                birthday = profilePreferences.getBirthday(),
                address = profilePreferences.getAddress(),
                interests = profilePreferences.getInterests()
            )
        }
    }
}