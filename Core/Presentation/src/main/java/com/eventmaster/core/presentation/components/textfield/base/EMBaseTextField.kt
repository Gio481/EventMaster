package com.eventmaster.core.presentation.components.textfield.base

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
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
import com.eventmaster.core.presentation.R
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
    customTextFieldState: MutableState<TextFieldValue>? = null,
    onFocusChanged: ((FocusState) -> Unit)? = null
) {
    var inputText by remember { mutableStateOf(TextFieldValue(text, TextRange(text.length))) }
    var showHint by rememberSaveable { mutableStateOf(hint.isEmpty().not()) }
    var isFocused by remember { mutableStateOf(false) }
    val borderColor by animateColorAsState(
        targetValue = if (isFocused) Color(0xCC5D6DFF) else Color.Transparent
    )
    val focusManager = LocalFocusManager.current
    val imeState = rememberImeState()
    val focusRequester = remember { FocusRequester() }
    var showClearTextAction by rememberSaveable { mutableStateOf(false) }
    LaunchedEffect(imeState.value) { focusManager.clearFocusIf(imeState.value.not()) }
    LaunchedEffect(Unit) { focusRequester.requestFocusIf(requestFocus) }

    Box(
        modifier = Modifier
            .then(modifier)
            .fillMaxWidth()
            .height(48.dp)
            .background(containerColor, RoundedCornerShape(13.dp))
            .border(1.dp, borderColor, RoundedCornerShape(13.dp)),
        contentAlignment = Alignment.CenterStart
    ) {
        BasicTextField(
            value = customTextFieldState?.value ?: inputText,
            onValueChange = {
                when (customTextFieldState) {
                    null -> inputText = it
                    else -> customTextFieldState.value = it
                }
                showHint = it.text.isEmpty()
                showClearTextAction = it.text.isNotEmpty() && trailingIcon == null
                onValueChange?.invoke(it)
            },
            singleLine = singleLine,
            textStyle = TextStyle(
                textAlign = TextAlign.Start,
                color = Color.White,
                fontSize = 16.sp
            ),
            readOnly = readOnly,
            enabled = enabled,
            cursorBrush = SolidColor(Color(0xEDEEEEEE)),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Password,
                imeAction = imeAction
            ),
            visualTransformation = visualTransformation,
            keyboardActions = keyboardActions,
            modifier = Modifier
                .focusRequester(focusRequester)
                .padding(start = 16.dp, end = 30.dp)
                .fillMaxWidth()
                .onFocusChanged {
                    isFocused = it.isFocused
                    onFocusChanged?.invoke(it)
                }
        )
        if (showHint) {
            Text(
                text = hint,
                fontSize = hintSize,
                color = Color(0xFFB1B1B1),
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.CenterEnd
        ) {
            trailingIcon?.invoke() ?: run {
                if (showClearTextAction) {
                    Icon(
                        painter = painterResource(id = R.drawable.em_ic_clear_search),
                        contentDescription = "",
                        tint = Color(0xFFB1B1B1),
                        modifier = Modifier
                            .padding(end = 15.dp)
                            .clickable {
                                showClearTextAction = false
                                customTextFieldState?.value = TextFieldValue()
                                inputText = TextFieldValue()
                                onValueChange?.invoke(TextFieldValue())
                            }
                    )
                }
            }
        }
    }
}