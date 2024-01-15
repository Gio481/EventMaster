package com.eventmaster.features.group.impl.presentation.groups.details.ui.comment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.eventmaster.core.presentation.components.emojis.EMEmojisComposable
import com.eventmaster.features.group.impl.R
import com.eventmaster.features.group.impl.presentation.groups.details.ui.FakeData
import kotlinx.coroutines.launch

@Composable
fun EMGroupDetailsCommentCard(
    fakeComment: FakeData.Comment,
    modifier: Modifier = Modifier,
    showEmojis: MutableState<Boolean>,
    showAllReplies: MutableState<Boolean>,
    lazyState: LazyListState,
    index: Int
) {
    ConstraintLayout(
        modifier = Modifier
            .padding(top = 16.dp)
            .then(modifier)
            .fillMaxWidth()
    ) {
        var showCurrentCommentEmojis by remember { mutableStateOf(false) }
        val coroutineScope = rememberCoroutineScope()
        val (profile, name, comment, emojiIcon, reply, emojis) = createRefs()

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
            modifier = Modifier
                .constrainAs(name) {
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
            modifier = Modifier
                .constrainAs(comment) {
                    top.linkTo(name.bottom)
                    start.linkTo(name.start)
                }
        )
        Image(
            painter = painterResource(id = com.eventmaster.core.presentation.R.drawable.em_ic_heart),
            contentDescription = "",
            modifier = Modifier
                .size(24.dp)
                .background(
                    Color(0xFF323333),
                    RoundedCornerShape(50)
                )
                .padding(6.dp)
                .constrainAs(emojiIcon) {
                    top.linkTo(comment.top)
                    bottom.linkTo(comment.bottom)
                    end.linkTo(parent.end, 8.dp)
                }
                .clickable {
                    showEmojis.value = !showEmojis.value
                    showCurrentCommentEmojis = !showCurrentCommentEmojis
                }
        )

        Text(
            text = "Reply",
            color = Color(0xFFBCBCBC),
            fontSize = 12.sp,
            modifier = Modifier
                .padding(top = 1.dp)
                .constrainAs(reply) {
                    top.linkTo(comment.bottom)
                    start.linkTo(comment.start)
                }
                .clickable(enabled = !showEmojis.value) { }
        )

        fakeComment.emojis?.let { emojiList ->
            Row(modifier = Modifier
                .height(22.dp)
                .constrainAs(emojis) {
                    height = Dimension.value(22.dp)
                    start.linkTo(reply.end, 12.dp)
                    top.linkTo(reply.top)
                    bottom.linkTo(reply.bottom)
                }
            ) {
                emojiList.forEachIndexed { index, emoji ->
                    Row(
                        modifier = Modifier
                            .padding(start = if (index == 0) 0.dp else 2.dp)
                            .defaultMinSize(minWidth = 24.dp)
                            .background(Color(0xFF323333), RoundedCornerShape(50)),
                    ) {
                        Image(
                            painter = painterResource(emoji.emojiId),
                            contentDescription = "",
                            modifier = Modifier
                                .padding(start = 6.dp)
                                .wrapContentSize()
                                .padding(vertical = 5.dp)
                        )
                        if (emoji.amount > 1) {
                            Text(
                                text = emoji.amount.toString(),
                                color = Color(0xEEEEEEEE),
                                fontSize = 8.sp,
                                modifier = Modifier.padding(end = 6.dp, start = 3.dp, bottom = 3.dp)
                            )
                        }
                    }
                }
            }
        }
        fakeComment.nestedComments?.let {
            Text(
                text = if (showAllReplies.value) "Hide all replies" else "View all replies",
                fontSize = 10.sp,
                color = Color(0xFF865DFF),
                modifier = Modifier
                    .constrainAs(createRef()) {
                        top.linkTo(reply.bottom, 6.dp)
                        start.linkTo(reply.start)
                    }
                    .clickable(enabled = !showEmojis.value) {
                        showAllReplies.value = !showAllReplies.value
                        coroutineScope.launch {
                            lazyState.animateScrollToItem(index)
                        }
                    }
            )
        }

        if (showEmojis.value) {
            Box(
                modifier = Modifier
                    .background(Color(0xF51C1C1C))
                    .constrainAs(createRef()) {
                        width = Dimension.matchParent
                        height = Dimension.fillToConstraints
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
        }
        EMEmojisComposable(
            showEmojis = showCurrentCommentEmojis,
            modifier = Modifier.constrainAs(createRef()) {
                end.linkTo(emojiIcon.end)
                bottom.linkTo(emojiIcon.bottom)
            }
        )
    }
}