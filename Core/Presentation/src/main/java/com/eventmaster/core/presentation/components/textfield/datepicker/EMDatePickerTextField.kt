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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eventmaster.core.presentation.R
import com.eventmaster.core.presentation.components.datepicker.bottomsheet.date.EMDatePickerBottomSheet
import com.eventmaster.core.presentation.components.textfield.base.EMBaseTextField
import com.eventmaster.core.presentation.components.textfield.config.EMTextFieldConfig

@Composable
internal fun EMDatePickerTextField(config: EMTextFieldConfig.DatePicker) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val hint by remember { mutableStateOf(config.hint) }

    Box(
        modifier = config.modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column {
            config.title?.let {
                Text(
                    text = it,
                    color = Color(0xFFEEEEEE),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            EMBaseTextField(
                modifier = Modifier.padding(top = if (config.title == "") 0.dp else 16.dp),
                hint = hint,
                readOnly = true,
                enabled = false,
                trailingIcon = {
                    IconButton(onClick = { showBottomSheet = true }) {
                        Icon(
                            painter = painterResource(id = R.drawable.em_ic_pickup_arrow),
                            contentDescription = null
                        )
                    }
                },
            )
            config.description?.let {
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
        EMDatePickerBottomSheet(
            doneAction = { },
            onDismiss = { showBottomSheet = false }
        )
    }
}