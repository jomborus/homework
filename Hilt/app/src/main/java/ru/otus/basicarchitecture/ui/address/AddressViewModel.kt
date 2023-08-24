package ru.otus.basicarchitecture.ui.address

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
import ru.otus.basicarchitecture.data.SuggestionsRepository
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val profilePreferences: ProfilePreferences,
    private val suggestionsRepository: SuggestionsRepository,
) : ViewModel() {

    private val _navigate = MutableSharedFlow<Unit>()
    val navigate = _navigate.asSharedFlow()

    private val _state = MutableStateFlow(listOf(""))
    val state = _state.asStateFlow()

    fun setOnClickListener(address: String) {
        viewModelScope.launch {
            profilePreferences.putAddress(address)
            _navigate.emit(Unit)
        }
    }

    fun doOnTextChanged(text: String) {
        viewModelScope.launch {
            val result = suggestionsRepository.getClue(text)
            result.onSuccess { suggestions ->
                Log.d("ViewModel", suggestions.toString())
                _state.update {
                    suggestions.suggestions.map { it.value }
                }
            }
            result.onFailure {
                Log.d("ViewModel", it.message.toString())
            }
        }
    }
}
