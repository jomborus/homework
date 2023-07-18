package ru.otus.basicarchitecture.ui.interests

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.otus.basicarchitecture.data.ProfilePreferences
import javax.inject.Inject

@HiltViewModel
class InterestsViewModel @Inject constructor(
    private val profilePreferences: ProfilePreferences
) : ViewModel() {
    private val _navigate = MutableStateFlow(false)
    val navigate = _navigate.asStateFlow()

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
        profilePreferences.putInterests(interests)
        _navigate.update { true }
    }
}