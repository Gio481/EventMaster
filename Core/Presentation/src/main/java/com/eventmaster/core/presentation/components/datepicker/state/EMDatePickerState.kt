package com.eventmaster.core.presentation.components.datepicker.state

data class EMDatePickerState(
    var selectedItem: String = "",
    var callback: ((String) -> Unit)? = null
)