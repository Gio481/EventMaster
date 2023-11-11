package com.eventmaster.core.presentation.components.textfield.defaults

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EMDefaultTextField(
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    hint: String = "",
    readOnly: Boolean = false,
    enabled: Boolean = true,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    imeAction: ImeAction = ImeAction.Default
) {
    var text by rememberSaveable { mutableStateOf("") }
    var showPrefix by rememberSaveable { mutableStateOf(hint.isEmpty().not()) }

    TextField(
        value = text,
        onValueChange = {
            text = it
            showPrefix = it.isEmpty()
        },
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(13.dp))
            .background(Color(0xFF212121)),
        singleLine = singleLine,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.White,
        ),
        textStyle = TextStyle(
            textAlign = TextAlign.Start,
            color = Color.White,
            fontSize = 16.sp
        ),
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrect = false,
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        ),
        readOnly = readOnly,
        enabled = enabled,
        keyboardActions = keyboardActions,
        prefix = {
            if (showPrefix) {
                Text(
                    text = hint,
                    color = Color(0xFF707070),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            }
        },
    )
}
