package com.eventmaster.core.presentation.components.textfield.search

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.eventmaster.core.presentation.R
import com.eventmaster.core.presentation.extensions.clearFocusIf
import com.eventmaster.core.presentation.extensions.rememberImeState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun EMSearchTextField(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
    var text by rememberSaveable { mutableStateOf("") }
    var showHint by rememberSaveable { mutableStateOf(true) }
    var showClearTextAction by rememberSaveable { mutableStateOf(false) }
    var progressState by remember { mutableStateOf(0f) }
    val focusManager = LocalFocusManager.current
    val imeState = rememberImeState()

    val motionSceneProgress by animateFloatAsState(
        targetValue = progressState,
        animationSpec = tween(200)
    )
    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current
    val motionScene = remember {
        context.resources.openRawResource(R.raw.em_search_motion_scene)
            .readBytes()
            .decodeToString()
    }

    LaunchedEffect(imeState.value) {
        focusManager.clearFocusIf(imeState.value.not())
    }

    MotionLayout(
        motionScene = MotionScene(motionScene),
        progress = motionSceneProgress,
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        Row(
            modifier = Modifier
                .height(40.dp)
                .background(Color(0xFF202020), RoundedCornerShape(16.dp))
                .layoutId("searchBox"),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.em_ic_search),
                contentDescription = "",
                tint = Color(0xCCEEEEEE),
                modifier = Modifier.padding(start = 20.dp)
            )
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .padding(start = 6.dp)
                    .fillMaxWidth()
            ) {
                BasicTextField(
                    value = text,
                    onValueChange = {
                        text = it
                        showHint = it.isEmpty()
                        showClearTextAction = it.isNotEmpty()
                        progressState = if (it.isEmpty()) 0f else 1f
                        coroutineScope.launch {
                            delay(200)
                            onValueChange.invoke(it)
                        }

                    },
                    singleLine = true,
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        color = Color(0xFFEEEEEE),
                        textAlign = TextAlign.Start,
                    ),
                    maxLines = 1,
                    cursorBrush = SolidColor(Color(0xFFEEEEEE)),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = false,
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions {
                        focusManager.clearFocus()
                    },
                    modifier = Modifier
                        .padding(end = 45.dp)
                        .fillMaxWidth(),
                )
                if (showHint) {
                    Text(text = "Search", fontSize = 14.sp, color = Color(0xFFB1B1B1))
                }
                if (showClearTextAction) {
                    Icon(
                        painter = painterResource(id = R.drawable.em_ic_clear_search),
                        contentDescription = "",
                        tint = Color(0xFFB1B1B1),
                        modifier = Modifier
                            .padding(end = 15.dp)
                            .align(Alignment.CenterEnd)
                            .clickable {
                                text = ""
                                onValueChange.invoke("")
                            }
                    )
                }
            }
        }
        Text(
            text = "Cancel",
            fontSize = 10.sp,
            color = Color(0xFFEEEEEE),
            modifier = Modifier
                .layoutId("cancel")
                .clickable {
                    progressState = 0f
                    text = ""
                    showClearTextAction = false
                    showHint = true
                    onValueChange.invoke("")
                    focusManager.clearFocus()
                }
        )
    }
}



