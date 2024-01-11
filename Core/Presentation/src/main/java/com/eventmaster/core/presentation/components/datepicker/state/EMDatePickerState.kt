package com.eventmaster.core.presentation.components.datepicker.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class EMDatePickerState {
    var selectedItem by mutableStateOf("")
    var callback: ((String) -> Unit)? = null
}