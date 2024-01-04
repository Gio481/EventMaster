package com.eventmaster.core.presentation.components.datepicker.bottomsheet.time

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        modifier = Modifier
            .padding(horizontal = 50.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center

    ) {
        Row {
            EMEndlessDatePicker(
                state = hoursPickerState,
                items = hours,
                modifier = Modifier
                    .wrapContentWidth()
                    .weight(1f)
            )
            Text(
                text = ":",
                color = Color(0xFFEEEEEE),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterVertically)
                    .padding(top = 23.dp),
            )
            EMEndlessDatePicker(
                state = minutesPickerState,
                items = minutes,
                modifier = Modifier
                    .wrapContentWidth()
                    .weight(1f)
            )
            EMRegularDatePicker(
                state = amPmPickerState,
                items = amPm,
                modifier = Modifier
                    .wrapContentWidth()
                    .weight(1f)
            )
        }
    }
}