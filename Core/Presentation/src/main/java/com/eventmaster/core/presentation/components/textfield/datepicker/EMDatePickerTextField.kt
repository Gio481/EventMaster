package com.eventmaster.core.presentation.components.textfield.datepicker

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.eventmaster.core.presentation.components.datepicker.EMDatePickerBottomSheet
import com.eventmaster.core.presentation.components.textfield.config.EMTextFieldConfig
import com.eventmaster.core.presentation.components.textfield.defaults.EMDefaultTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun EMDatePickerTextField(config: EMTextFieldConfig.DatePicker) {
    var showBottomSheet by remember { mutableStateOf(false) }

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
            EMDefaultTextField(
                modifier = Modifier.padding(top = if (config.title == "") 0.dp else 16.dp),
                hint = config.hint,
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

    if (showBottomSheet) {
        EMDatePickerBottomSheet(
            doneAction = { },
            onDismiss = { showBottomSheet = false }
        )
    }

}
