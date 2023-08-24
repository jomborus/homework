package ru.otus.basicarchitecture.ui.interests

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
class InterestsViewModel @Inject constructor(
    private val profilePreferences: ProfilePreferences
) : ViewModel() {

    private val _navigate = MutableSharedFlow<Unit>()
    val navigate = _navigate.asSharedFlow()

    private val _interests = MutableStateFlow(listOf(
        "read",
        "write",
        "run",
        "music",
        "game",
        "films",
        "books",
        "serials",
        "theater",
        "punk",
        "rock",
        "slam"
    ))
    val interests = _interests.asStateFlow()

    fun onClickListener(interests: List<String>) {
        viewModelScope.launch {
            profilePreferences.putInterests(interests)
            _navigate.emit(Unit)
        }
    }
}