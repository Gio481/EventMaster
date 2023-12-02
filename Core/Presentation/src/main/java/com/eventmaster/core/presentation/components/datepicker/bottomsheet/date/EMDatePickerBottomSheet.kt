package com.eventmaster.core.presentation.components.datepicker.bottomsheet.date

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
import com.eventmaster.core.presentation.components.datepicker.extensions.monthsList
import com.eventmaster.core.presentation.components.datepicker.extensions.rememberPickerState
import com.eventmaster.core.presentation.components.datepicker.model.EMDatePickerSelectedItem
import com.eventmaster.core.presentation.components.datepicker.state.EMDatePickerState

@Composable
fun EMDatePickerBottomSheet(
    doneAction: (EMDatePickerSelectedItem) -> Unit,
    onDismiss: () -> Unit
) {
    val daysPickerState = rememberPickerState()
    val monthsPickerState = rememberPickerState()
    val yearsPickerState = rememberPickerState()

    EMBaseDatePickerBottomSheet(
        doneAction = {
            doneAction.invoke(
                EMDatePickerSelectedItem(
                    firstColum = daysPickerState.selectedItem,
                    secondColum = monthsPickerState.selectedItem,
                    thirdColum = yearsPickerState.selectedItem
                )
            )
        },
        onDismiss = onDismiss,
        datePicker = {
            DatePicker(daysPickerState, monthsPickerState, yearsPickerState)
        }
    )
}

@Composable
private fun DatePicker(
    daysPickerState: EMDatePickerState,
    monthsPickerState: EMDatePickerState,
    yearsPickerState: EMDatePickerState
) {
    val days by remember { mutableStateOf((1..31).map { it.toString() }) }
    val year = remember { (1970..2023).map { it.toString() } }

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row {
            EMEndlessDatePicker(
                state = daysPickerState,
                items = days,
                modifier = Modifier.weight(1f)
            )
            EMEndlessDatePicker(
                state = monthsPickerState,
                items = monthsList(),
                modifier = Modifier.weight(1f),
            )
            EMEndlessDatePicker(
                state = yearsPickerState,
                items = year,
                modifier = Modifier.weight(1f),
            )
        }
    }
}