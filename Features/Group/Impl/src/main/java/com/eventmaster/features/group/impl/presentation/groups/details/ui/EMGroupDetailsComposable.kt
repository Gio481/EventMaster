package com.eventmaster.features.group.impl.presentation.groups.details.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
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
import com.eventmaster.core.presentation.base.ui.EMScreenBase
import com.eventmaster.core.presentation.components.textfield.regular.EMRegularTextField
import com.eventmaster.core.presentation.extensions.carouselTransition
import com.eventmaster.core.presentation.extensions.fadingEdge
import com.eventmaster.core.presentation.extensions.offsets
import com.eventmaster.core.presentation.extensions.readMotionScene
import com.eventmaster.core.presentation.extensions.rememberImeState
import com.eventmaster.core.presentation.extensions.scrollIfNeeded
import com.eventmaster.core.presentation.util.EMAnchoredDraggableCardState
import com.eventmaster.core.presentation.util.anchoredDraggableState
import com.eventmaster.core.presentation.util.getAnchoredDraggableState
import com.eventmaster.core.presentation.util.getAnchoredDraggableStateProgress
import com.eventmaster.core.presentation.util.getImeHeight
import com.eventmaster.core.presentation.util.nestedScrollConnection
import com.eventmaster.features.group.impl.R
import com.eventmaster.features.group.impl.presentation.groups.details.vm.EMGroupDetailsVm
import org.koin.androidx.compose.get

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EMGroupDetailsComposable(vm: EMGroupDetailsVm = get()) {
    EMScreenBase(
        vm = vm,
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
        val text = rememberSaveable {
            mutableStateOf("")
        }

        HorizontalPager(
            userScrollEnabled = !imeState.value,
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
                    text.value = ""
                }
            }

            MotionLayout(
                motionScene = MotionScene(motionScene),
                progress = getAnchoredDraggableStateProgress(anchoredDraggableState, 50.dp)
            ) {

                EventCard(it, fakeData()[it], pagerState, anchoredDraggableState, text.value)
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
            Box(
                modifier = Modifier
                    .offsets(y = -getImeHeight())
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(
                        Color(0XFF1C1C1C),
                        RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp)
                    )
                    .align(Alignment.BottomCenter)
            ) {
                HorizontalDivider(
                    modifier = Modifier
                        .padding(top = 18.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .width(43.dp)
                        .align(Alignment.TopCenter),
                    color = Color.Black,
                    thickness = 4.dp
                )
                EMRegularTextField(
                    hint = "Enter comment",
                    hintSize = 14.sp,
                    requestFocus = true,
                    text = text.value,
                    hintColor = Color(0x63EEEEEE),
                    containerColor = Color(0xFF2C2C2C),
                    trailingIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.em_ic_send_comment),
                                contentDescription = "",
                                tint = Color(0xFFFFFFFF)
                            )
                        }
                    },
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 43.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    onValueChange = {
                        text.value = it
                    }
                )
            }
        }
    }
}

@Composable
private fun HeaderContent() {
    ConstraintLayout(modifier = Modifier.wrapContentSize()) {
        val (image, title) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.gio),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(50))
                .constrainAs(image) {
                    start.linkTo(parent.start, 20.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )
        Text(
            text = "Test Name",
            fontSize = 16.sp,
            color = Color(0xFFEEEEEE),
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top)
                start.linkTo(image.end, 16.dp)
            }
        )
        Image(
            painter = painterResource(id = com.eventmaster.core.presentation.R.drawable.em_ic_edit),
            contentDescription = "",
            modifier = Modifier.constrainAs(createRef()) {
                top.linkTo(title.top)
                bottom.linkTo(title.bottom)
                start.linkTo(title.end, 6.dp)
            }
        )
        Text(
            text = "25 Members",
            fontSize = 12.sp,
            color = Color(0xFFEEEEEE),
            modifier = Modifier.constrainAs(createRef()) {
                top.linkTo(title.bottom)
                start.linkTo(title.start)
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun EventCard(
    page: Int,
    fakeData: FakeData,
    pagerState: PagerState,
    state: AnchoredDraggableState<EMAnchoredDraggableCardState>,
    text: String
) {
    val context = LocalContext.current
    val motionScene = remember {
        context.readMotionScene(R.raw.em_event_card_comments_motion_scene)
    }
    val fadeGradient = Brush.verticalGradient(
        colors = listOf(Color(0x52151419), Color(0xED1A191B)),
        startY = with(LocalDensity.current) { 100.dp.toPx() },
        endY = with(LocalDensity.current) { 210.dp.toPx() }
    )
    val imeState = rememberImeState()

    val gr = Brush.linearGradient(
        0f to Color(0xFFD7D1EA),
        1f to Color(0xFFA080FF)
    )
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
                .fadingEdge(fadeGradient)
                .layoutId("coverImage")
        )
        Spacer(
            Modifier
                .background(Brush.verticalGradient(listOf(Color.Transparent, Color(0xF51C1C1C))))
                .layoutId("fade"))

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
                    .background(gr, RoundedCornerShape(50))
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

        fakeData.comments?.let {
            CommentSection(
                fakeDataComments = it,
                lazyState = lazyState,
                anchoredDraggableState
            )
        }

        if (!imeState.value) {
            EMRegularTextField(
                hint = "Enter comment",
                text = text,
                hintSize = 14.sp,
                hintColor = Color(0x63EEEEEE),
                containerColor = Color(0xFF2C2C2C),
                trailingIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.em_ic_send_comment),
                            contentDescription = "",
                            tint = Color(0xFFFFFFFF)
                        )
                    }
                },
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .layoutId("commentsTextField"),
            )
        }
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
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CommentSection(
    fakeDataComments: List<FakeData.Comment>,
    lazyState: LazyListState,
    state: AnchoredDraggableState<EMAnchoredDraggableCardState>
) {
    LazyColumn(
        state = lazyState,
        modifier = Modifier
            .layoutId("commentSection")
            .anchoredDraggable(state, Orientation.Vertical)
            .scrollIfNeeded(
                true,
                nestedScrollConnection(state)
            )
    ) {
        items(fakeDataComments) {
            Comments(it)
        }
    }
}

@Composable
private fun LazyItemScope.Comments(fakeComment: FakeData.Comment) {
    Column(modifier = Modifier.fillParentMaxWidth()) {
        CommentCard(fakeComment)
        fakeComment.nestedComments?.let { data ->
            data.forEach { CommentCard(it, Modifier.padding(start = 34.dp)) }
        }
    }
}

@Composable
private fun CommentCard(fakeComment: FakeData.Comment, modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier = Modifier
            .padding(top = 16.dp)
            .then(modifier)
            .fillMaxWidth()
    ) {
        val (profile, name, comment) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.test),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(24.dp)
                .clip(RoundedCornerShape(50))
                .constrainAs(profile) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        )
        Text(
            text = fakeComment.name,
            color = Color(0xFFEAEAEA),
            fontSize = 12.sp,
            modifier = Modifier.constrainAs(name) {
                top.linkTo(profile.top)
                start.linkTo(profile.end, 10.dp)
            }
        )
        Text(
            text = fakeComment.hoursAgo,
            color = Color(0xA6EEEEEE),
            fontSize = 12.sp,
            modifier = Modifier
                .constrainAs(createRef()) {
                    top.linkTo(name.top)
                    start.linkTo(name.end, 8.dp)
                }
        )
        Text(
            text = fakeComment.comment,
            color = Color(0xFFEEEEEE),
            fontSize = 14.sp,
            modifier = Modifier.constrainAs(comment) {
                top.linkTo(name.bottom)
                start.linkTo(name.start)
            }
        )
        Image(
            painter = painterResource(id = R.drawable.test),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(24.dp)
                .clip(RoundedCornerShape(50))
                .constrainAs(createRef()) {
                    top.linkTo(comment.top)
                    bottom.linkTo(comment.bottom)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = "Reply",
            color = Color(0xFFBCBCBC),
            fontSize = 12.sp,
            modifier = Modifier
                .padding(top = 1.dp)
                .constrainAs(createRef()) {
                    top.linkTo(comment.bottom)
                    start.linkTo(comment.start)
                }
        )


    }
}

@Composable
private fun IconWithText(resId: Int, text: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Icon(
            painter = painterResource(id = resId),
            contentDescription = "",
            tint = Color(0xFFFFCD29),
        )
        Text(
            text = text,
            fontSize = 12.sp,
            color = Color(0xDBEEEEEE),
            modifier = Modifier.padding(start = 4.dp, top = 1.dp),
            style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
        )
    }
}