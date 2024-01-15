package com.eventmaster.core.presentation.components.textfield.regular

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eventmaster.core.presentation.components.textfield.base.EMBaseTextField

@Composable
fun EMRegularTextField(
    modifier: Modifier = Modifier,
    textFiledModifier: Modifier = Modifier,
    singleLine: Boolean = true,
    hint: String = "",
    text: String = "",
    hintColor: Color = Color(0xFF707070),
    hintSize: TextUnit = 16.sp,
    readOnly: Boolean = false,
    enabled: Boolean = true,
    title: String? = null,
    description: String? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    imeAction: ImeAction = ImeAction.Done,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    requestFocus: Boolean = false,
    containerColor: Color = Color(0xFF202020),
    onValueChange: ((TextFieldValue) -> Unit)? = null
) {
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
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            EMBaseTextField(
                modifier = Modifier
                    .padding(top = if (title == null) 0.dp else 16.dp)
                    .then(textFiledModifier),
                hint = hint,
                hintColor = hintColor,
                requestFocus = requestFocus,
                hintSize = hintSize,
                containerColor = containerColor,
                trailingIcon = trailingIcon,
                singleLine = singleLine,
                readOnly = readOnly,
                onValueChange = onValueChange,
                text = text,
                enabled = enabled,
                visualTransformation = visualTransformation,
                keyboardActions = keyboardActions,
                imeAction = imeAction
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
}