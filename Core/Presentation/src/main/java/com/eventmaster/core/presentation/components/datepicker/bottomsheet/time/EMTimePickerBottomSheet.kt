package com.eventmaster.core.presentation.components.datepicker.bottomsheet.time

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.eventmaster.core.presentation.components.datepicker.bottomsheet.base.EMBaseDatePickerBottomSheet
import com.eventmaster.core.presentation.components.datepicker.endless.EMEndlessDatePicker
import com.eventmaster.core.presentation.components.datepicker.extensions.rememberPickerState
import com.eventmaster.core.presentation.components.datepicker.model.EMDatePickerSelectedItem
import com.eventmaster.core.presentation.components.datepicker.regular.EMRegularDatePicker
import com.eventmaster.core.presentation.components.datepicker.state.EMDatePickerState

@Composable
fun EMTimePickerBottomSheet(
    doneAction: (EMDatePickerSelectedItem) -> Unit,
    onDismiss: () -> Unit
) {
    val hoursPickerState = rememberPickerState()
    val minutesPickerState = rememberPickerState()
    val amPmPickerState = rememberPickerState()

    EMBaseDatePickerBottomSheet(
        doneAction = {
            doneAction.invoke(
                EMDatePickerSelectedItem(
                    firstColum = hoursPickerState.selectedItem,
                    secondColum = minutesPickerState.selectedItem,
                    thirdColum = amPmPickerState.selectedItem
                )
            )
        },
        onDismiss = onDismiss,
        datePicker = {
            TimePicker(hoursPickerState, minutesPickerState, amPmPickerState)
        }
    )
}

@Composable
private fun TimePicker(
    hoursPickerState: EMDatePickerState,
    minutesPickerState: EMDatePickerState,
    amPmPickerState: EMDatePickerState
) {
    val hours by remember { mutableStateOf((1..24).map { it.toString() }) }
    val minutes by remember { mutableStateOf((0..60).map { it.toString() }) }
    val amPm by remember { mutableStateOf(listOf("", "", "AM", "PM", "", "")) }

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row {
            EMEndlessDatePicker(
                state = hoursPickerState,
                items = hours,
                modifier = Modifier.weight(1f)
            )
            EMEndlessDatePicker(
                state = minutesPickerState,
                items = minutes,
                modifier = Modifier.weight(1f),
            )
            EMRegularDatePicker(
                state = amPmPickerState,
                items = amPm,
                modifier = Modifier.weight(1f),
            )
        }
    }
}