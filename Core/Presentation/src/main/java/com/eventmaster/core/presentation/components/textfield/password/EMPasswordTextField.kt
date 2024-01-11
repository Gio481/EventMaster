package com.eventmaster.core.presentation.components.textfield.password

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.eventmaster.core.presentation.R
import com.eventmaster.core.presentation.components.textfield.base.EMBaseTextField

@Composable
fun EMPasswordTextField(
    modifier: Modifier = Modifier,
    textFiledModifier: Modifier = Modifier,
    singleLine: Boolean = true,
    hint: String = "",
    readOnly: Boolean = false,
    enabled: Boolean = true,
    title: String? = null,
    description: String? = null,
    imeAction: ImeAction = ImeAction.Done,
    withZootopiaAnimation: Boolean = true,
    containerColor: Color = Color(0xFF202020),
) {
    var showPassword by remember { mutableStateOf(false) }

    val iconId = if (showPassword) {
        R.drawable.em_ic_show_password
    } else {
        R.drawable.em_ic_hide_password
    }

    ConstraintLayout(modifier = modifier.fillMaxWidth()) {
        val (fieldTitle, textField) = createRefs()
        title?.let {
            Text(
                text = it,
                color = Color(0xFFEEEEEE),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(fieldTitle) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
            )
        }

        EMBaseTextField(
            modifier = Modifier
                .constrainAs(textField) {
                    start.linkTo(fieldTitle.start)
                    top.linkTo(fieldTitle.bottom, 16.dp)
                }
                .then(textFiledModifier),
            trailingIcon = {
                IconButton(onClick = {
                    showPassword = !showPassword
                }) {
                    Icon(
                        painterResource(id = iconId),
                        contentDescription = null
                    )
                }
            },
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            hint = hint,
            singleLine = singleLine,
            readOnly = readOnly,
            enabled = enabled,
            imeAction = imeAction,
            containerColor = containerColor
        )
        description?.let {
            Text(
                text = it,
                color = Color(0xFFBFBFBF),
                fontSize = 16.sp,
                modifier = Modifier.constrainAs(createRef()) {
                    start.linkTo(textField.start)
                    top.linkTo(textField.bottom, 16.dp)
                }
            )
        }

        if (withZootopiaAnimation) ImageAnimation(
            showPassword,
            modifier
                .size(100.dp)
                .constrainAs(createRef()) {
                    end.linkTo(textField.end, -(16).dp)
                    bottom.linkTo(textField.top, -(38).dp)
                }
        )
    }
}

@Composable
private fun ImageAnimation(showPassword: Boolean, modifier: Modifier = Modifier) {
    val imageFrames = listOf(
        painterResource(id = R.drawable.animation_1),
        painterResource(id = R.drawable.animation_2),
        painterResource(id = R.drawable.animation_3),
        painterResource(id = R.drawable.animation_4),
        painterResource(id = R.drawable.animation_5),
        painterResource(id = R.drawable.animation_6),
        painterResource(id = R.drawable.animation_7)
    )

    val targetValue = if (showPassword) 6f else 0f

    val animateShape = remember { Animatable(targetValue) }

    LaunchedEffect(key1 = showPassword) {
        animateShape.animateTo(
            targetValue = targetValue,
            animationSpec = repeatable(
                animation = tween(
                    durationMillis = 200,
                    easing = LinearEasing,
                ),
                repeatMode = RepeatMode.Restart,
                iterations = 1
            )
        )
    }

    Image(
        painter = imageFrames[animateShape.value.toInt()],
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}