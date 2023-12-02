package com.eventmaster.core.presentation.components.textfield.password

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eventmaster.core.presentation.R
import com.eventmaster.core.presentation.components.textfield.base.EMBaseTextField
import com.eventmaster.core.presentation.components.textfield.config.EMTextFieldConfig

@Composable
internal fun EMPasswordTextField(config: EMTextFieldConfig.Password) {
    var showPassword by remember { mutableStateOf(false) }

    val iconId = if (showPassword) {
        R.drawable.em_ic_show_password
    } else {
        R.drawable.em_ic_hide_password
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(config.modifier) {
            config.title?.let {
                Text(
                    text = it,
                    color = Color(0xFFEEEEEE),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

            EMBaseTextField(
                modifier = Modifier.padding(top = if (config.title == null) 0.dp else 16.dp),
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
        if (config.withZootopiaAnimation) ImageAnimation(showPassword)
    }
}


@Composable
private fun BoxScope.ImageAnimation(showPassword: Boolean) {
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
        modifier = Modifier
            .padding(bottom = 50.dp)
            .size(150.dp)
            .align(Alignment.CenterEnd)
            .padding(start = 75.dp),
        contentScale = ContentScale.Crop
    )
}