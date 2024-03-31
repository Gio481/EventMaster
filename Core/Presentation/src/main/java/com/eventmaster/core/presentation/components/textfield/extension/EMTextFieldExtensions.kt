package com.eventmaster.core.presentation.components.textfield.extension

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eventmaster.core.presentation.R
import com.eventmaster.core.presentation.components.textfield.base.EMBaseTextField
import com.eventmaster.core.presentation.util.getImeHeight

@Composable
fun BoxScope.TextFieldWithKeyboard(commentTextFieldState: MutableState<TextFieldValue>) {
    Box(
        modifier = Modifier
            .padding(bottom = if (getImeHeight() < 0.dp) 0.dp else getImeHeight())
            .fillMaxWidth()
            .height(90.dp)
            .background(
                Color(0XFF1C1C1C),
                RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp)
            )
            .align(Alignment.BottomCenter)
    ) {
        EMBaseTextField(
            hint = "Enter comment",
            hintSize = 14.sp,
            requestFocus = true,
            text = commentTextFieldState.value.text,
            hintColor = Color(0x63EEEEEE),
            containerColor = Color(0xFF2C2C2C),
            trailingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.em_ic_send_comment),
                        contentDescription = "",
                        tint = Color(0xFFFFFFFF)
                    )
                }
            },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(16.dp))
                .align(Alignment.Center),
            onValueChange = {
                commentTextFieldState.value = it
            }
        )
    }
}