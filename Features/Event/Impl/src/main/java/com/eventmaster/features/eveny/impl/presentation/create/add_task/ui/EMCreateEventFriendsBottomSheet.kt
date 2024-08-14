package com.eventmaster.features.eveny.impl.presentation.create.add_task.ui

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.eventmaster.core.presentation.components.datepicker.bottomsheet.base.EMBaseBottomSheet
import com.eventmaster.core.presentation.components.textfield.base.SpinFadeLoaderProgressIndicator
import com.eventmaster.features.event.impl.R

@Composable
fun EMCreateEventFriendsBottomSheet(onDismiss: () -> Unit) {
    var tryAgain by remember { mutableStateOf(false) }

    EMBaseBottomSheet(
        modifier = Modifier.fillMaxSize(),
        title = "Friends list",
        needsDateSelector = false,
        onDismiss = onDismiss,
        content = {
            LottieAnimationFromJson(jsonResId = R.raw.em_lottie_animation)
            Column(
                modifier = Modifier
                    .padding(bottom = 50.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Text(
                    text = "List could not be loaded",
                    fontSize = 16.sp,
                    color = Color(0xEDEEEEEE),
                    fontWeight = FontWeight.Bold
                )
                Button(
                    onClick = { tryAgain = true },
                    modifier = Modifier
                        .padding(top = 36.dp, start = 10.dp)
                        .size(width = 168.dp, height = 47.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonColors(
                        containerColor = Color(0xFF5D6DFF),
                        contentColor = Color(0xFF5D6DFF),
                        disabledContainerColor = Color(0x665D6DFF),
                        disabledContentColor = Color(0x665D6DFF),
                    ),
                    contentPadding = PaddingValues(0.dp),
                    enabled = !tryAgain
                ) {
                    Text(
                        text = "Try again",
                        fontSize = 14.sp,
                        color = Color(0xFF1B1111),
                        fontWeight = FontWeight.Bold
                    )
                    if (tryAgain) {
                        SpinFadeLoaderProgressIndicator(
                            modifier = Modifier
                                .padding(start = 9.dp)
                                .size(16.dp),
                            color = Color(0xFF1B1111),
                            isClockwise = true
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun BoxScope.LottieAnimationFromJson(jsonResId: Int) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(jsonResId)
    )

    LottieAnimation(
        composition = composition,
        modifier = Modifier
            .align(Alignment.TopCenter)
            .padding(bottom = 230.dp)
            .size(250.dp),
        alignment = Alignment.Center,
        contentScale = ContentScale.Crop
    )
}