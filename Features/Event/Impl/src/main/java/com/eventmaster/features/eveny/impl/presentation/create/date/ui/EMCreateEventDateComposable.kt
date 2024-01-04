package com.eventmaster.features.eveny.impl.presentation.create.date.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eventmaster.core.presentation.base.ui.EMScreenBase
import com.eventmaster.core.presentation.components.textfield.datepicker.EMDatePickerTextField
import com.eventmaster.core.presentation.components.textfield.datepicker.type.EMDatePickerTextFieldType
import com.eventmaster.core.presentation.components.textfield.regular.EMRegularTextField
import com.eventmaster.features.event.impl.R
import com.eventmaster.features.eveny.impl.presentation.create.date.vm.EMCreateEventDateVm
import org.koin.androidx.compose.get

@Composable
fun EMCreateEventDateComposable(vm: EMCreateEventDateVm = get()) {
    EMScreenBase(
        vm = vm,
        showBottomAction = true,
        headerText = "Create event",
        bottomAction = { vm.nextStep() }
    ) {
        var checkboxState by rememberSaveable { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            DatePiker("From")
            Row(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Add due date",
                    fontSize = 16.sp,
                    color = Color(0xffEEEEEE),
                    textAlign = TextAlign.Center
                )
                Box(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .width(26.dp)
                        .height(24.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .border(1.dp, color = Color(0xFF865DFF), RoundedCornerShape(8.dp))
                        .clickable { checkboxState = !checkboxState }
                        .background(
                            if (checkboxState) Color(0xFF865DFF) else Color.Transparent
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (checkboxState) {
                        Image(
                            painter = painterResource(id = R.drawable.em_ic_checked),
                            contentDescription = "",
                        )
                    }
                }
            }
            if (checkboxState) {
                DatePiker("To")
            }
            EMRegularTextField(
                title = "Add location",
                hint = "Enter address",
                modifier = Modifier.padding(top = 24.dp),
                trailingIcon = {
                    Image(
                        painter = painterResource(R.drawable.ic_map),
                        contentDescription = "null",
                        modifier = Modifier.padding(end = 16.dp)
                    )
                }
            )
        }
    }
}

@Composable
private fun DatePiker(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
    ) {
        EMDatePickerTextField(
            hint = "Sat / Dec 20",
            title = title,
            type = EMDatePickerTextFieldType.DayMonth,
            modifier = Modifier.weight(0.6f)
        )
        EMDatePickerTextField(
            hint = "19 : 00",
            title = "",
            type = EMDatePickerTextFieldType.Time,
            modifier = Modifier
                .padding(start = 20.dp)
                .weight(0.4f),
        )
    }
}