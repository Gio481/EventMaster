package com.eventmaster.core.presentation.components.datepicker.bottomsheet.base

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun EMBaseDatePickerBottomSheet(
    doneAction: () -> Unit,
    onDismiss: () -> Unit,
    datePicker: @Composable BoxScope.() -> Unit
) {
    val state = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(onTap = {
                onDismiss.invoke()
            })
        },
        shape = RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp),
        onDismissRequest = {
            onDismiss.invoke()
        },
        windowInsets = WindowInsets.navigationBars,
        dragHandle = null,
        sheetState = state,
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .background(Color(0xFF252525))
        ) {
            HeaderLine()
            Box(
                modifier = Modifier.padding(top = 20.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                datePicker.invoke(this)
                DateSelector()
            }
            DoneButton {
                doneAction.invoke()
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

@Composable
private fun DoneButton(doneAction: () -> Unit) {
    Button(
        onClick = doneAction,
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