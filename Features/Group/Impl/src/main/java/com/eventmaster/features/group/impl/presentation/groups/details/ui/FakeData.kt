package com.eventmaster.features.group.impl.presentation.groups.details.ui

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.constraintlayout.compose.layoutId
import com.eventmaster.core.presentation.components.textfield.regular.EMRegularTextField
import com.eventmaster.core.presentation.extensions.carouselTransition
import com.eventmaster.core.presentation.extensions.fadingEdge
import com.eventmaster.core.presentation.extensions.readMotionScene
import com.eventmaster.core.presentation.extensions.rememberImeState
import com.eventmaster.core.presentation.extensions.scrollIfNeeded
import com.eventmaster.core.presentation.util.EMAnchoredDraggableCardState
import com.eventmaster.core.presentation.util.anchoredDraggableState
import com.eventmaster.core.presentation.util.getAnchoredDraggableStateProgress
import com.eventmaster.core.presentation.util.nestedScrollConnection
import com.eventmaster.features.group.impl.R

data class FakeData(
    val title: String,
    val time: String,
    val location: String,
    val comments: List<Comment>? = null
) {
    data class Comment(
        val name: String,
        val comment: String,
        val hoursAgo: String,
        val nestedComments: List<Comment>? = null
    )
}

fun fakeData(): List<FakeData> {
    val fakeData = FakeData(
        title = "New Car",
        time = "1pm",
        location = "Akhaltsikhe",
        comments = listOf(
            FakeData.Comment(
                name = "Gio",
                comment = "Eventer",
                hoursAgo = "1h ago"
            ),
            FakeData.Comment(
                name = "Gio",
                comment = "second comment",
                hoursAgo = "5min ago",
                nestedComments = listOf(
                    FakeData.Comment(
                        name = "Nested First",
                        comment = "nested comment first",
                        hoursAgo = "4min ago"
                    ),
                    FakeData.Comment(
                        name = "Nested Second",
                        comment = "nested comment second",
                        hoursAgo = "4min ago"
                    )
                )
            ),
            FakeData.Comment(
                name = "Gio",
                comment = "Eventer",
                hoursAgo = "1h ago"
            ),
            FakeData.Comment(
                name = "Gio",
                comment = "second comment",
                hoursAgo = "5min ago",
                nestedComments = listOf(
                    FakeData.Comment(
                        name = "Nested First",
                        comment = "nested comment first",
                        hoursAgo = "4min ago"
                    ),
                    FakeData.Comment(
                        name = "Nested Second",
                        comment = "nested comment second",
                        hoursAgo = "4min ago"
                    )
                )
            ),
        )
    )
    val fakeDataWithoutComments = FakeData(
        title = "Second Event",
        time = "1pm",
        location = "Tbilisi",
    )
    val thirdFakeData = FakeData(
        title = "Third Event",
        time = "1pm",
        location = "Tbilisi",
        comments = listOf(
            FakeData.Comment(
                name = "Comment 1",
                comment = "comment first",
                hoursAgo = "4min ago"
            ),
            FakeData.Comment(
                name = "Comment 2",
                comment = "comment second",
                hoursAgo = "4min ago"
            )
        )
    )
   return listOf(fakeData, fakeDataWithoutComments, thirdFakeData)
}







//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//private fun EventCard(
//    page: Int,
//    fakeData: FakeData,
//    pagerState: PagerState,
//    state: AnchoredDraggableState<EMAnchoredDraggableCardState>,
//    text: String
//) {
//    val context = LocalContext.current
//    val motionScene = remember {
//        context.readMotionScene(R.raw.em_event_card_comments_motion_scene)
//    }
//    val fadeGradient = Brush.verticalGradient(
//        colors = listOf(Color(0x52151419), Color(0xED1A191B)),
//        startY = with(LocalDensity.current) { 100.dp.toPx() },
//        endY = with(LocalDensity.current) { 210.dp.toPx() }
//    )
//    val imeState = rememberImeState()
//
//    val gr = Brush.linearGradient(
//        0f to Color(0xFFD7D1EA),
//        1f to Color(0xFFA080FF)
//    )
//    val anchoredDraggableState = anchoredDraggableState()
//    val progress = getAnchoredDraggableStateProgress(anchoredDraggableState)
//    val lazyState = rememberLazyListState()
//
//    MotionLayout(
//        motionScene = MotionScene(content = motionScene),
//        progress = progress,
//        modifier = Modifier
//            .padding(bottom = 88.dp)
//            .fillMaxWidth()
//            .carouselTransition(page, pagerState)
//            .layoutId("eventCard")
//            .scrollIfNeeded(
//                true,
//                nestedScrollConnection(anchoredDraggableState)
//            )
//    ) {
//        ConstraintLayout(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color(0xF51C1C1C), RoundedCornerShape(24.dp))
//                .layoutId("gui")
//        ) {}
//        val (coverImage, friends, totalAmount, time, emptyComments, commentsTitle, commentField, commentSection) = createRefs()
//        Image(
//            painter = painterResource(id = R.drawable.gio),
//            contentDescription = "",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(210.dp)
//                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
//                .anchoredDraggable(state, Orientation.Vertical)
//                .fadingEdge(fadeGradient)
//                .constrainAs(coverImage) {
//                    start.linkTo(parent.start)
//                    top.linkTo(parent.top)
//                }
//        )
//
//        Spacer(
//            Modifier
//                .fillMaxWidth()
//                .height(32.dp)
//                .background(
//                    brush = Brush.verticalGradient(
//                        colors = listOf(
//                            Color.Transparent,
//                            Color(0xF51C1C1C)
//                        )
//                    )
//                )
//                .constrainAs(createRef()) {
//                    top.linkTo(parent.top, 190.dp)
//                }
//        )
//
//        Box(
//            modifier = Modifier
//                .height(45.dp)
//                .width(68.dp)
//                .background(Color(0xF1ECEC99), RoundedCornerShape(12.dp))
//                .constrainAs(createRef()) {
//                    end.linkTo(parent.end, 16.dp)
//                    top.linkTo(parent.top, 16.dp)
//                },
//            contentAlignment = Alignment.Center
//        ) {
//            Text(
//                text = "20",
//                fontSize = 16.sp,
//                color = Color(0xD91B1111),
//                modifier = Modifier.padding(bottom = 10.dp)
//            )
//            Text(
//                text = "Dec",
//                fontSize = 10.sp,
//                color = Color(0xD91B1111),
//                modifier = Modifier.padding(top = 15.dp)
//            )
//        }
//
//        Box(
//            modifier = Modifier.constrainAs(friends) {
//                bottom.linkTo(coverImage.bottom)
//                start.linkTo(parent.start, 16.dp)
//            }
//        ) {
//            val s = listOf(1, 2, 3)
//            s.forEachIndexed { index, _ ->
//                Image(
//                    painter = painterResource(id = R.drawable.test),
//                    contentDescription = "",
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .padding(start = index * 12.dp)
//                        .size(24.dp)
//                        .clip(RoundedCornerShape(50))
//                        .shadow(elevation = index * 1.dp),
//                )
//            }
//            Text(
//                text = "+2",
//                fontSize = 7.sp,
//                color = Color(0xFFEEEEEE),
//                textAlign = TextAlign.Center,
//                modifier = Modifier
//                    .padding(start = s.size * 12.dp)
//                    .size(24.dp)
//                    .background(gr, RoundedCornerShape(50))
//            )
//        }
//
//        Button(
//            modifier = Modifier
//                .width(106.dp)
//                .height(36.dp)
//                .constrainAs(createRef()) {
//                    end.linkTo(parent.end, 16.dp)
//                    bottom.linkTo(coverImage.bottom)
//                },
//            shape = RoundedCornerShape(16.dp),
//            contentPadding = PaddingValues(horizontal = 16.dp),
//            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5D6DFF)),
//            onClick = { /*TODO*/ },
//        ) {
//            Text(
//                text = "Accepted",
//                fontSize = 12.sp,
//                color = Color(0xFF201419),
//                modifier = Modifier
//            )
//            Image(
//                painter = painterResource(id = R.drawable.em_ic_down),
//                contentDescription = "",
//                colorFilter = ColorFilter.tint(Color(0xFF212121)),
//                modifier = Modifier.padding(start = 10.dp)
//            )
//        }
//        IconWithText(
//            R.drawable.em_ic_total_amount,
//            "Total amount: 9000$",
//            modifier = Modifier.constrainAs(totalAmount) {
//                bottom.linkTo(friends.top, 16.dp)
//                start.linkTo(parent.start, 16.dp)
//            }
//        )
//        IconWithText(
//            R.drawable.em_ic_time,
//            "2 PM",
//            modifier = Modifier.constrainAs(time) {
//                bottom.linkTo(totalAmount.top, 4.dp)
//                start.linkTo(parent.start, 16.dp)
//            }
//        )
//        IconWithText(
//            R.drawable.em_ic_location,
//            "Akhaltsikhe",
//            modifier = Modifier.constrainAs(createRef()) {
//                bottom.linkTo(totalAmount.top, 4.dp)
//                start.linkTo(time.end, 12.dp)
//            }
//        )
//        Text(
//            text = "New Car",
//            color = Color(0xFFEEEEEE),
//            fontSize = 16.sp,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier.constrainAs(createRef()) {
//                bottom.linkTo(time.top, 6.dp)
//                start.linkTo(parent.start, 16.dp)
//            },
//            style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
//        )
//
//        Text(
//            text = "Comments",
//            fontSize = 14.sp,
//            color = Color(0xFFEEEEEE),
//            modifier = Modifier.constrainAs(commentsTitle) {
//                top.linkTo(coverImage.bottom, 27.dp)
//                start.linkTo(parent.start, 16.dp)
//            }
//        )
//
//        fakeData.comments?.let {
//            CommentSection(
//                fakeDataComments = it,
//                commentSection = commentSection,
//                commentsTitle = commentsTitle,
//                commentField = commentField,
//                lazyState = lazyState,
//                anchoredDraggableState
//            )
//        }
//
//        if (!imeState.value) {
//            EMRegularTextField(
//                hint = "Enter comment",
//                text = text,
//                hintSize = 14.sp,
//                hintColor = Color(0x63EEEEEE),
//                containerColor = Color(0xFF2C2C2C),
//                trailingIcon = {
//                    IconButton(onClick = { /*TODO*/ }) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.em_ic_send_comment),
//                            contentDescription = "",
//                            tint = Color(0xFFFFFFFF)
//                        )
//                    }
//                },
//                modifier = Modifier
//                    .padding(horizontal = 16.dp)
//                    .clip(RoundedCornerShape(16.dp))
//                    .constrainAs(commentField) {
//                        bottom.linkTo(parent.bottom, 24.dp)
//                    },
//            )
//        }
//        if (fakeData.comments == null) {
//            Image(
//                painter = painterResource(id = R.drawable.em_ic_empty_comments),
//                contentDescription = "",
//                modifier = Modifier.constrainAs(emptyComments) {
//                    top.linkTo(commentsTitle.top)
//                    bottom.linkTo(commentField.top)
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                }
//            )
//            Text(
//                text = "No comments yet",
//                fontSize = 12.sp,
//                color = Color(0xABEEEEEE),
//                style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)),
//                modifier = Modifier.constrainAs(createRef()) {
//                    top.linkTo(emptyComments.bottom, 13.dp)
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                }
//            )
//        }
//    }
//}
//}