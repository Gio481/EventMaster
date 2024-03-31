package com.eventmaster.features.group.impl.presentation.groups.details.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.Dimension.Companion.value
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.constraintlayout.compose.layoutId
import com.eventmaster.core.presentation.base.ui.EMScreenBase
import com.eventmaster.core.presentation.components.textfield.extension.TextFieldWithKeyboard
import com.eventmaster.core.presentation.extensions.clearFocusOnTap
import com.eventmaster.core.presentation.extensions.readMotionScene
import com.eventmaster.core.presentation.extensions.rememberImeState
import com.eventmaster.core.presentation.util.getAnchoredDraggableState
import com.eventmaster.core.presentation.util.getAnchoredDraggableStateProgress
import com.eventmaster.features.group.impl.R
import com.eventmaster.features.group.impl.presentation.groups.details.ui.eventcard.EventCard
import com.eventmaster.features.group.impl.presentation.groups.details.ui.header.HeaderContent
import com.eventmaster.features.group.impl.presentation.groups.details.vm.EMGroupDetailsVm
import org.koin.androidx.compose.get

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EMGroupDetailsComposable(vm: EMGroupDetailsVm = get()) {
    val focusManager = LocalFocusManager.current
    EMScreenBase(
        vm = vm,
        modifier = Modifier.clearFocusOnTap(focusManager),
        showHeader = true,
        headerBackground = Color(0xFF121212),
        showHeaderLine = true,
        customHeaderContent = { HeaderContent() }
    ) {
        val context = LocalContext.current
        val motionScene = remember { context.readMotionScene(R.raw.em_event_card_motion_scene) }
        val pagerState = rememberPagerState { fakeData().size }
        val density = LocalDensity.current
        val imeState = rememberImeState()
        val commentTextFieldState = remember { mutableStateOf(TextFieldValue()) }
        val showEmojis = remember { mutableStateOf(false) }

        HorizontalPager(
            userScrollEnabled = !imeState.value && !showEmojis.value,
            contentPadding = PaddingValues(horizontal = 24.dp),
            pageSpacing = 14.dp,
            state = pagerState,
            modifier = Modifier.alpha(if (imeState.value) 0.3f else 1f)
        ) {
            var anchoredDraggableState by remember {
                mutableStateOf(getAnchoredDraggableState(density, 0.9f, 50.dp))
            }
            LaunchedEffect(pagerState) {
                snapshotFlow { pagerState.currentPage }.collect {
                    anchoredDraggableState = getAnchoredDraggableState(density, 0.9f, 50.dp)
                    commentTextFieldState.value = TextFieldValue()
                }
            }

            MotionLayout(
                motionScene = MotionScene(motionScene),
                progress = getAnchoredDraggableStateProgress(anchoredDraggableState, 50.dp)
            ) {
                EventCard(
                    it,
                    fakeData()[it],
                    pagerState,
                    anchoredDraggableState,
                    commentTextFieldState,
                    showEmojis
                )
                Row(modifier = Modifier.layoutId("addEvent")) {
                    Image(
                        painter = painterResource(id = R.drawable.em_ic_add_event),
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(color = Color(0xFF5D6DFF)),
                        modifier = Modifier
                            .border(1.dp, Color(0xFF5D6DFF), RoundedCornerShape(50))
                            .size(32.dp)
                            .padding(8.dp),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = "Add Event",
                        fontSize = 14.sp,
                        color = Color(0xFF5D6DFF),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 8.dp)
                    )
                }
            }
        }

        if (imeState.value) {
            TextFieldWithKeyboard(commentTextFieldState)
        }
    }
}