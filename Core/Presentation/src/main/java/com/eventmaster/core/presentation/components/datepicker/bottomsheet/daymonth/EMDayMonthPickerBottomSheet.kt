package com.eventmaster.core.presentation.components.datepicker.bottomsheet.daymonth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eventmaster.core.presentation.components.datepicker.bottomsheet.base.EMBaseDatePickerBottomSheet
import com.eventmaster.core.presentation.components.datepicker.endless.EMEndlessDatePicker
import com.eventmaster.core.presentation.components.datepicker.extensions.monthsList
import com.eventmaster.core.presentation.components.datepicker.extensions.rememberPickerState
import com.eventmaster.core.presentation.components.datepicker.model.EMDatePickerSelectedItem
import com.eventmaster.core.presentation.components.datepicker.state.EMDatePickerState

@Composable
fun EMDayMonthPickerBottomSheet(
    doneAction: (EMDatePickerSelectedItem) -> Unit,
    onDismiss: () -> Unit
) {
    val daysPickerState = rememberPickerState()
    val monthsPickerState = rememberPickerState()

    EMBaseDatePickerBottomSheet(
        doneAction = {
            doneAction.invoke(
                EMDatePickerSelectedItem(
                    firstColum = daysPickerState.selectedItem,
                    secondColum = monthsPickerState.selectedItem,
                )
            )
        },
        onDismiss = onDismiss,
        datePicker = {
            DayMonthPicker(daysPickerState, monthsPickerState)
        }
    )
}

@Composable
private fun DayMonthPicker(
    daysPickerState: EMDatePickerState,
    monthsPickerState: EMDatePickerState,
) {
    val days by remember { mutableStateOf((1..31).map { it.toString() }) }

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row {
            EMEndlessDatePicker(
                state = daysPickerState,
                items = days,
                modifier = Modifier.width(50.dp)
            )
            Spacer(modifier = Modifier.padding(horizontal = 10.dp))
            EMEndlessDatePicker(
                state = monthsPickerState,
                items = monthsList(),
                modifier = Modifier.width(130.dp)
            )
        }
    }
}