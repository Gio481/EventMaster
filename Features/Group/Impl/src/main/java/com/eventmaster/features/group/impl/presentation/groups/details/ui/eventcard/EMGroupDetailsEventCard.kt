package com.eventmaster.features.group.impl.presentation.groups.details.ui.eventcard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.constraintlayout.compose.layoutId
import com.eventmaster.core.presentation.components.textfield.base.EMBaseTextField
import com.eventmaster.core.presentation.extensions.carouselTransition
import com.eventmaster.core.presentation.extensions.fadingEdge
import com.eventmaster.core.presentation.extensions.readMotionScene
import com.eventmaster.core.presentation.util.EMAnchoredDraggableCardState
import com.eventmaster.core.presentation.util.anchoredDraggableState
import com.eventmaster.core.presentation.util.getAnchoredDraggableStateProgress
import com.eventmaster.features.group.impl.R
import com.eventmaster.features.group.impl.presentation.groups.details.ui.FakeData
import com.eventmaster.features.group.impl.presentation.groups.details.ui.comment.CommentSection
import com.eventmaster.features.group.impl.presentation.groups.details.ui.util.IconWithText
import com.eventmaster.features.group.impl.util.fadeGradient
import com.eventmaster.features.group.impl.util.plusFriendsText

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EventCard(
    page: Int,
    fakeData: FakeData,
    pagerState: PagerState,
    state: AnchoredDraggableState<EMAnchoredDraggableCardState>,
    commentTextFieldState: MutableState<TextFieldValue>,
    showEmojis: MutableState<Boolean>
) {
    val context = LocalContext.current
    val motionScene = remember {
        context.readMotionScene(R.raw.em_event_card_comments_motion_scene)
    }

    val anchoredDraggableState = anchoredDraggableState()
    val progress = getAnchoredDraggableStateProgress(anchoredDraggableState)
    val lazyState = rememberLazyListState()

    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = Modifier
            .padding(bottom = 88.dp)
            .fillMaxSize()
            .carouselTransition(page, pagerState)
            .background(Color(0xF51C1C1C), RoundedCornerShape(24.dp))
            .layoutId("eventCard")
    ) {
        val coverImageCornerRadius = customDistance("coverImage", "cornerRadius")
        val viewAllTextValue = customInt("viewAllText", "textValue")
        val viewAllIconRotation = customFloat("viewAllImage", "rotate")

        Image(
            painter = painterResource(id = R.drawable.gio),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 24.dp,
                        topEnd = 24.dp,
                        bottomStart = coverImageCornerRadius,
                        bottomEnd = coverImageCornerRadius
                    )
                )
                .anchoredDraggable(state, Orientation.Vertical)
                .fadingEdge(fadeGradient())
                .layoutId("coverImage")
        )
        Spacer(
            Modifier
                .background(Brush.verticalGradient(listOf(Color.Transparent, Color(0xF51C1C1C))))
                .layoutId("fade")
        )

        Box(
            modifier = Modifier
                .height(45.dp)
                .width(68.dp)
                .background(Color(0xF1ECEC99), RoundedCornerShape(12.dp))
                .layoutId("dayAndMonthBox"),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "20",
                fontSize = 16.sp,
                color = Color(0xD91B1111),
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Text(
                text = "Dec",
                fontSize = 10.sp,
                color = Color(0xD91B1111),
                modifier = Modifier.padding(top = 15.dp)
            )
        }

        Box(modifier = Modifier.layoutId("goingPersonsBox")) {
            val s = listOf(1, 2, 3)
            s.forEachIndexed { index, _ ->
                Image(
                    painter = painterResource(id = R.drawable.test),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(start = index * 12.dp)
                        .size(24.dp)
                        .clip(RoundedCornerShape(50))
                        .shadow(elevation = index * 1.dp),
                )
            }
            Text(
                text = "+2",
                fontSize = 7.sp,
                color = Color(0xFFEEEEEE),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = s.size * 12.dp)
                    .size(24.dp)
                    .background(plusFriendsText(), RoundedCornerShape(50))
            )
        }

        Button(
            modifier = Modifier
                .width(106.dp)
                .height(36.dp)
                .layoutId("acceptedButton"),
            shape = RoundedCornerShape(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5D6DFF)),
            enabled = !showEmojis.value,
            onClick = { /*TODO*/ },
        ) {
            Text(
                text = "Accepted",
                fontSize = 12.sp,
                color = Color(0xFF201419),
                modifier = Modifier
            )
            Image(
                painter = painterResource(id = R.drawable.em_ic_down),
                contentDescription = "",
                colorFilter = ColorFilter.tint(Color(0xFF212121)),
                modifier = Modifier.padding(start = 10.dp)
            )
        }
        IconWithText(
            R.drawable.em_ic_total_amount,
            "Total amount: 9000$",
            modifier = Modifier.layoutId("totalAmountIcon")
        )
        IconWithText(
            R.drawable.em_ic_time,
            "2 PM",
            modifier = Modifier.layoutId("timeIcon")
        )
        IconWithText(
            R.drawable.em_ic_location,
            "Akhaltsikhe",
            modifier = Modifier.layoutId("locationIcon")
        )
        Text(
            text = "New Car",
            color = Color(0xFFEEEEEE),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.layoutId("eventTitleText"),
            style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
        )

        Text(
            text = "Comments",
            fontSize = 14.sp,
            color = Color(0xFFEEEEEE),
            modifier = Modifier.layoutId("commentsTitleText")
        )

        Text(
            text = if (viewAllTextValue == 0) "View All" else "Collapse",
            fontSize = 12.sp,
            color = Color(0xFFEEEEEE),
            modifier = Modifier.layoutId("viewAllText")
        )

        Image(
            painter = painterResource(id = R.drawable.em_ic_view_all),
            contentDescription = "",
            modifier = Modifier
                .layoutId("viewAllImage")
                .background(Color(0xFF323333), RoundedCornerShape(50))
                .size(21.dp)
                .padding(6.dp)
                .rotate(viewAllIconRotation),
            contentScale = ContentScale.Inside
        )

        EMBaseTextField(
            hint = "Enter comment",
            hintSize = 14.sp,
            hintColor = Color(0x63EEEEEE),
            customTextFieldState = commentTextFieldState,
            containerColor = Color(0xFF2C2C2C),
            readOnly = showEmojis.value,
            enabled = !showEmojis.value,
            trailingIcon = {
                IconButton(
                    onClick = {
                        commentTextFieldState.value = TextFieldValue()
                    },
                    enabled = !showEmojis.value
                ) {
                    Icon(
                        painter = painterResource(id = com.eventmaster.core.presentation.R.drawable.em_ic_send_comment),
                        contentDescription = "",
                        tint = Color(0xFFFFFFFF)
                    )
                }
            },
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .layoutId("commentsTextField"),
        )


        if (fakeData.comments == null) {
            Image(
                painter = painterResource(id = R.drawable.em_ic_empty_comments),
                contentDescription = "",
                modifier = Modifier.layoutId("noCommentsImage")
            )
            Text(
                text = "No comments yet",
                fontSize = 12.sp,
                color = Color(0xABEEEEEE),
                style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)),
                modifier = Modifier.layoutId("noCommentsText")
            )
        }

        if (showEmojis.value) {
            Box(
                modifier = Modifier
                    .layoutId("backgroundLayout")
                    .background(
                        Color(0xF51C1C1C),
                        RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
                    )
            )
        }
        fakeData.comments?.let {
            CommentSection(
                fakeDataComments = it,
                lazyState = lazyState,
                state = anchoredDraggableState,
                showEmojis = showEmojis,
                progress = progress
            )
        }
    }
}