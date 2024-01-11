package com.eventmaster.features.home.impl.presentation.home.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.eventmaster.core.presentation.base.ui.EMScreenBase
import com.eventmaster.features.home.impl.presentation.home.vm.EMHomeVm
import org.koin.androidx.compose.get

@Composable
fun EMHomeComposable(vm: EMHomeVm = get()) {
    EMScreenBase(
        vm = vm,
        showBottomAction = false,
        showHeader = false
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(createGradientBrush())
        ) {
            val (calendar, event, group, profile, inviteFriend) = createRefs()
            Hexagon(title = "Calendar", modifier = Modifier
                .constrainAs(calendar) {
                    bottom.linkTo(event.top, (-15).dp)
                    end.linkTo(group.end, 5.dp)
                    start.linkTo(event.start)
                }
            )
            Hexagon(
                title = "Events",
                modifier = Modifier
                    .constrainAs(event) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom, 50.dp)
                        end.linkTo(group.start, 25.dp)
                    },
                onClick = { vm.navigateToCreateEvent() }
            )
            Hexagon(
                title = "Groups",
                modifier = Modifier
                    .constrainAs(group) {
                        bottom.linkTo(event.bottom, 5.dp)
                        end.linkTo(parent.end, 15.dp)
                    },
                onClick = { vm.navigateToGroups() }
            )
            Hexagon(title = "Invite\nFriend", modifier = Modifier
                .constrainAs(inviteFriend) {
                    top.linkTo(event.bottom, ((-15).dp))
                    end.linkTo(group.end)
                    start.linkTo(event.start, 10.dp)
                }
            )
            Hexagon(title = "Profile", modifier = Modifier
                .constrainAs(profile) {
                    top.linkTo(inviteFriend.top)
                    end.linkTo(event.end, 50.dp)
                    start.linkTo(parent.start)
                }
            )
        }
    }
}

@Composable
fun Hexagon(
    title: String,
    modifier: Modifier = Modifier,
    hexagonColor: Color = Color(0x33EEEEEE),
    onClick: (() -> Unit) = {},
) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = modifier
            .size(128.dp)
            .clickable(interactionSource = interactionSource, indication = null, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val height = size.height
            val width = size.width

            val path = Path().apply {
                moveTo(width / 2f, 0f)
                lineTo(width, height / 4)
                lineTo(width, height / 4 * 3)
                lineTo(width / 2, height)
                lineTo(0f, height / 4 * 3)
                lineTo(0f, height / 4)
                close()
            }

            drawIntoCanvas { canvas ->
                canvas.drawOutline(
                    outline = Outline.Generic(path),
                    paint = Paint().apply {
                        color = hexagonColor
                        pathEffect = PathEffect.cornerPathEffect(16.dp.toPx())
                    }
                )
            }
        }
        Text(
            text = title,
            color = Color(0xEDEEEEEE),
            fontSize = 14.sp,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 30.dp, start = 28.dp),
            textAlign = TextAlign.Start
        )
    }
}

fun createGradientBrush(): Brush {
    return Brush.verticalGradient(
        colors = listOf(
            Color(0xFF060313),
            Color(0xFF0E091F),
        )
    )
}