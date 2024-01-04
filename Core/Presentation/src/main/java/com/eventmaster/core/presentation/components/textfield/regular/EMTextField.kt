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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eventmaster.core.presentation.components.textfield.base.EMBaseTextField

@Composable
fun EMRegularTextField(
    modifier: Modifier = Modifier,
    textFiledModifier: Modifier = Modifier,
    singleLine: Boolean = true,
    hint: String = "",
    readOnly: Boolean = false,
    enabled: Boolean = true,
    title: String? = null,
    description: String? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    imeAction: ImeAction = ImeAction.Default,
    containerColor: Color = Color(0xFF202020),
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
                containerColor = containerColor,
                trailingIcon = trailingIcon,
                singleLine = singleLine,
                readOnly = readOnly,
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