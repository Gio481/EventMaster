package com.eventmaster.core.presentation.components.textfield.base

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eventmaster.core.presentation.extensions.clearFocusIf
import com.eventmaster.core.presentation.extensions.rememberImeState
import com.eventmaster.core.presentation.extensions.requestFocusIf

@Composable
fun EMBaseTextField(
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    hint: String = "",
    text: String = "",
    hintColor: Color = Color(0xFF707070),
    hintSize: TextUnit = 16.sp,
    readOnly: Boolean = false,
    enabled: Boolean = true,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    imeAction: ImeAction = ImeAction.Done,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    requestFocus: Boolean = false,
    containerColor: Color = Color(0xFF2A2A2A),
    onValueChange: ((TextFieldValue) -> Unit)? = null,
    customTextFieldState: MutableState<TextFieldValue>? = null
) {
    var inputText by remember { mutableStateOf(TextFieldValue(text, TextRange(text.length))) }
    var showHint by rememberSaveable { mutableStateOf(hint.isEmpty().not()) }
    val focusManager = LocalFocusManager.current
    val imeState = rememberImeState()
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(imeState.value) { focusManager.clearFocusIf(imeState.value.not()) }
    LaunchedEffect(Unit) { focusRequester.requestFocusIf(requestFocus) }

    TextField(
        value = customTextFieldState?.value ?: inputText,
        onValueChange = {
            when (customTextFieldState) {
                null -> inputText = it
                else -> customTextFieldState.value = it
            }
            showHint = it.text.isEmpty()
            onValueChange?.invoke(it)
        },
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .focusRequester(focusRequester)
            .clip(RoundedCornerShape(13.dp)),
        singleLine = singleLine,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.White,
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            disabledContainerColor = containerColor,
            disabledIndicatorColor = Color.Transparent
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
        placeholder = {
            if (showHint) {
                Text(
                    text = hint,
                    color = hintColor,
                    fontSize = hintSize,
                    style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)),
                )
            }
        }
    )
}