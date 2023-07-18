package ru.otus.basicarchitecture.ui.address

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.otus.basicarchitecture.data.ProfilePreferences
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val profilePreferences: ProfilePreferences
) : ViewModel() {

    private val _navigate = MutableStateFlow(false)
    val navigate = _navigate.asStateFlow()

    fun setOnClickListener(country: String, city: String, address: String) {
        profilePreferences.putAddress("$country, $city, $address")
        _navigate.update { true }
    }
}
