package com.eventmaster.core.presentation.components.datepicker

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Calendar
import java.util.TimeZone
import kotlin.math.abs

@ExperimentalMaterial3Api
@Composable
fun EMDatePickerBottomSheet(
    doneAction: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val daysPickerState = rememberPickerState()
    val monthsPickerState = rememberPickerState()
    val yearsPickerState = rememberPickerState()


    ModalBottomSheet(
        modifier = Modifier,
        shape = RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp),
        onDismissRequest = {
            onDismiss.invoke()
            SheetValue.Hidden
        },
        windowInsets = BottomSheetDefaults.windowInsets,
        dragHandle = null,
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .background(
                    Color(0xFF252525),
                )
        ) {
            HeaderLine()
            Box(
                modifier = Modifier.padding(top = 20.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                DatePicker(daysPickerState, monthsPickerState, yearsPickerState)
                DateSelector()
            }
            DoneButton {
                val selectedDate = "${daysPickerState.selectedItem} "
                doneAction.invoke("")
                onDismiss.invoke()
            }
        }
    }
}

@Composable
private fun HeaderLine() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 18.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Spacer(
            modifier = Modifier
                .width(43.dp)
                .height(4.dp)
                .background(Color.Black)
        )
    }
}

@Composable
private fun DatePicker(
    daysPickerState: PickerState,
    monthsPickerState: PickerState,
    yearsPickerState: PickerState
) {
    val calendar = Calendar.getInstance()
    val currentTime = System.currentTimeMillis()

    val s = mutableListOf<String>().apply {
        for (i in 1..getDaysInMonth(10, 2023)) {
            add(i.toString())
        }
    }
    var days by remember {
        mutableStateOf(s)
    }
    val daysPickerState1 = rememberPickerState()
    val monthsPickerState1 = rememberPickerState()
    val yearsPickerState1 = rememberPickerState()
    val year = remember { (1970..2023).map { it.toString() } }

    val months = listOf(
        "January",
        "February",
        "Mart",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"
    )

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row {
            EMDatePicker(
                state = daysPickerState1,
                items = days,
                modifier = Modifier.weight(1f)
            )
            EMDatePicker(
                state = monthsPickerState1,
                items = months,
                modifier = Modifier.weight(1f),
                startIndex = 10
            )
            EMDatePicker(
                state = yearsPickerState1,
                items = year,
                modifier = Modifier.weight(1f),
                startIndex = 54
            )
        }
    }

    monthsPickerState1.callback = {
        val s2 = mutableListOf<String>().apply {
            for (i in -1..getDaysInMonth(it, 2023) + 2) {
                add(i.toString())
            }
        }
        days = s2
    }

}

@Composable
private fun BoxScope.DateSelector() {
    Box(
        modifier = Modifier
            .padding(horizontal = 22.dp)
            .padding(top = 27.dp)
            .fillMaxWidth()
            .height(44.dp)
            .align(Alignment.Center)
            .border(
                width = 1.dp,
                color = Color(0xFF8F5FEA),
                shape = RoundedCornerShape(12.dp)
            )
    )
}

fun Modifier.verticalScrollDisabled() =
    pointerInput(Unit) {
        awaitPointerEventScope {
            while (true) {
                awaitPointerEvent(pass = PointerEventPass.Initial).changes.forEach {
                    val offset = it.positionChange()
                    if (abs(offset.y) > 0f) {
                        it.consume()
                    }
                }
            }
        }
    }

@Composable
private fun DoneButton(doneAction: () -> Unit) {
    Button(
        onClick = {
            doneAction.invoke()
        },
        shape = RoundedCornerShape(size = 15.dp),
        colors = ButtonDefaults.textButtonColors(
            containerColor = Color(0xFF8F5FEA),
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 30.dp)
            .height(50.dp)

    ) {
        Text(
            text = "Done",
            color = Color(0xFF1B1111),
            fontSize = 16.sp
        )
    }
}

fun getLongAsDate(year: Int, month: Int, date: Int): Long {
    val timeZone = TimeZone.getTimeZone("UTC")
    val calendar = Calendar.getInstance(timeZone)
    calendar.set(Calendar.DAY_OF_MONTH, date)
    calendar.set(Calendar.MONTH, month - 1)
    calendar.set(Calendar.YEAR, year)

    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)

    return calendar.timeInMillis
}

fun getDaysInMonth(month: Int, year: Int): Int {
    val correctMonth = month
    return if (correctMonth == 2) {
        28 + (if (year % 4 == 0) 1 else 0) - (if (year % 100 == 0) if (year % 400 == 0) 0 else 1 else 0)
    } else {
        31 - (correctMonth - 1) % 7 % 2
    }
}