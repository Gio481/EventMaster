package com.eventmaster.core.presentation.components.datepicker.bottomsheet.fulldate

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eventmaster.core.presentation.components.datepicker.bottomsheet.base.EMBaseBottomSheet
import com.eventmaster.core.presentation.components.datepicker.endless.EMEndlessDatePicker
import com.eventmaster.core.presentation.components.datepicker.extensions.getDaysInMonth
import com.eventmaster.core.presentation.components.datepicker.extensions.monthsList
import com.eventmaster.core.presentation.components.datepicker.extensions.rememberPickerState
import com.eventmaster.core.presentation.components.datepicker.model.EMDatePickerSelectedItem
import com.eventmaster.core.presentation.components.datepicker.state.EMDatePickerState
import com.eventmaster.core.presentation.extensions.getIndexOrNull
import java.util.Calendar

@Composable
fun EMDatePickerBottomSheet(
    doneAction: (EMDatePickerSelectedItem) -> Unit,
    selectedItem: EMDatePickerSelectedItem? = null,
    onDismiss: () -> Unit
) {
    val daysPickerState = rememberPickerState(selectedItem?.firstColum)
    val monthsPickerState = rememberPickerState(selectedItem?.secondColum)
    val yearsPickerState = rememberPickerState(selectedItem?.thirdColum)

    var month by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }

    EMBaseBottomSheet(
        doneAction = {
            monthsPickerState.selectedItem = month
            yearsPickerState.selectedItem = year
            doneAction.invoke(
                EMDatePickerSelectedItem(
                    firstColum = daysPickerState.selectedItem,
                    secondColum = monthsPickerState.selectedItem,
                    thirdColum = yearsPickerState.selectedItem
                )
            )
        },
        onDismiss = onDismiss
    ) {
        DatePicker(
            daysPickerState = daysPickerState,
            monthsPickerState = monthsPickerState,
            yearsPickerState = yearsPickerState,
            monthItemSelectedCallback = { month = it },
            yearItemSelectedCallback = { year = it }
        )
    }
}

@Composable
private fun DatePicker(
    daysPickerState: EMDatePickerState,
    monthsPickerState: EMDatePickerState,
    yearsPickerState: EMDatePickerState,
    monthItemSelectedCallback: (month: String) -> Unit,
    yearItemSelectedCallback: (month: String) -> Unit,
) {
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    var days by remember { mutableStateOf((1..31).map { it.toString() }) }
    val year = remember { (1970..currentYear).map { it.toString() } }

    var selectedMonth by remember { mutableStateOf("") }
    var selectedYear by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(modifier = Modifier.padding(horizontal = 20.dp)) {
            EMEndlessDatePicker(
                state = daysPickerState,
                items = orderedList(days, daysPickerState.selectedItem),
                itemsNeedUpdate = true,
                modifier = Modifier
                    .wrapContentWidth()
                    .weight(1f)
            )
            EMEndlessDatePicker(
                state = monthsPickerState,
                items = orderedList(monthsList(), monthsPickerState.selectedItem),
                modifier = Modifier
                    .wrapContentWidth()
                    .weight(1f),
                itemSelectedCallBack = { month ->
                    selectedMonth = month
                    val newDays = getDaysInMonth(
                        monthsList().getIndexOrNull(selectedMonth) ?: 0,
                        selectedYear.toIntOrNull() ?: 1970
                    )
                    days = (1..newDays).map { it.toString() }
                    monthItemSelectedCallback.invoke(month)
                }
            )
            EMEndlessDatePicker(
                state = yearsPickerState,
                items = orderedList(year, yearsPickerState.selectedItem),
                modifier = Modifier
                    .wrapContentWidth()
                    .weight(1f),
                itemSelectedCallBack = { year ->
                    selectedYear = year
                    val newDays = getDaysInMonth(
                        monthsList().getIndexOrNull(selectedMonth) ?: 0,
                        selectedYear.toIntOrNull() ?: 1970
                    )
                    days = (1..newDays).map { it.toString() }
                    yearItemSelectedCallback.invoke(year)
                }
            )
        }
    }
}

private fun orderedList(items: List<String>, state: String): List<String> {
    val index = items.getIndexOrNull(state) ?: return items
    val beforeSelectedItem = items.subList(index + 1, items.size)
    val afterSelectedItem = items.subList(0, index)
    return listOf(state) + beforeSelectedItem + afterSelectedItem
}