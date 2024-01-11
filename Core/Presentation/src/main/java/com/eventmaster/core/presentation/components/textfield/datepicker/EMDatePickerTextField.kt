package com.eventmaster.core.presentation.components.textfield.datepicker

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eventmaster.core.presentation.R
import com.eventmaster.core.presentation.components.datepicker.bottomsheet.daymonth.EMDayMonthPickerBottomSheet
import com.eventmaster.core.presentation.components.datepicker.bottomsheet.fulldate.EMDatePickerBottomSheet
import com.eventmaster.core.presentation.components.datepicker.bottomsheet.time.EMTimePickerBottomSheet
import com.eventmaster.core.presentation.components.textfield.base.EMBaseTextField
import com.eventmaster.core.presentation.components.textfield.datepicker.type.EMDatePickerTextFieldType

@Composable
fun EMDatePickerTextField(
    modifier: Modifier = Modifier,
    textFiledModifier: Modifier = Modifier,
    singleLine: Boolean = true,
    hint: String = "",
    hintColor: Color = Color(0xFF707070),
    hintSize: TextUnit = 16.sp,
    readOnly: Boolean = true,
    enabled: Boolean = false,
    title: String? = null,
    description: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    imeAction: ImeAction = ImeAction.Done,
    containerColor: Color = Color(0xFF202020),
    type: EMDatePickerTextFieldType = EMDatePickerTextFieldType.FullDate,
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val selectedDate by remember { mutableStateOf(hint) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column {
            title?.let {
                Text(
                    text = it,
                    color = Color(0xFFEEEEEE),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            EMBaseTextField(
                modifier = Modifier
                    .padding(top = if (title == null) 0.dp else 16.dp)
                    .then(textFiledModifier),
                hint = hint,
                hintColor = hintColor,
                hintSize = hintSize,
                readOnly = readOnly,
                enabled = enabled,
                trailingIcon = {
                    IconButton(onClick = { showBottomSheet = true }) {
                        Icon(
                            painter = painterResource(id = R.drawable.em_ic_pickup_arrow),
                            contentDescription = null
                        )
                    }
                },
                singleLine = singleLine,
                visualTransformation = visualTransformation,
                imeAction = imeAction,
                containerColor = containerColor
            )
            description?.let {
                Text(
                    text = it,
                    color = Color(0xFFBFBFBF),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }

    //TODO try factory pattern to create picker
    if (showBottomSheet) {
        when (type) {
            EMDatePickerTextFieldType.FullDate -> {
                EMDatePickerBottomSheet(
                    doneAction = { },
                    onDismiss = { showBottomSheet = false }
                )
            }

            EMDatePickerTextFieldType.DayMonth -> {
                EMDayMonthPickerBottomSheet(
                    doneAction = {},
                    onDismiss = { showBottomSheet = false }
                )
            }

            EMDatePickerTextFieldType.Time -> {
                EMTimePickerBottomSheet(
                    doneAction = {},
                    onDismiss = { showBottomSheet = false }
                )
            }
        }
    }
}