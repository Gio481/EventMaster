package com.eventmaster.features.group.impl.presentation.groups.details.ui.comment

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.Dimension.Companion.value
import androidx.constraintlayout.compose.layoutId
import com.eventmaster.core.presentation.extensions.scrollIfNeeded
import com.eventmaster.core.presentation.util.EMAnchoredDraggableCardState
import com.eventmaster.core.presentation.util.nestedScrollConnection
import com.eventmaster.features.group.impl.presentation.groups.details.ui.FakeData

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CommentSection(
    fakeDataComments: List<FakeData.Comment>,
    lazyState: LazyListState,
    state: AnchoredDraggableState<EMAnchoredDraggableCardState>,
    showEmojis: MutableState<Boolean>,
    progress: Float
) {
    LazyColumn(
        state = lazyState,
        userScrollEnabled = !showEmojis.value,
        modifier = Modifier
            .layoutId("commentSection")
            .scrollIfNeeded(
                (lazyState.canScrollForward || progress != 0f) && !showEmojis.value,
                nestedScrollConnection(state)
            )
    ) {
        itemsIndexed(fakeDataComments) { index, fakeComment ->
            val showAllReplies = remember { mutableStateOf(false) }
            Column(
                modifier = Modifier
                    .padding(top = if (index == 0) 5.dp else 0.dp)
                    .fillParentMaxWidth()
            ) {
                EMGroupDetailsCommentCard(
                    fakeComment,
                    showEmojis = showEmojis,
                    showAllReplies = showAllReplies,
                    lazyState = lazyState,
                    index = index
                )
                fakeComment.nestedComments?.let { data ->
                    if (showAllReplies.value) {
                        data.forEach {
                            EMGroupDetailsCommentCard(
                                it,
                                Modifier.padding(start = 34.dp),
                                showEmojis = showEmojis,
                                showAllReplies,
                                lazyState,
                                index
                            )
                        }
                    }
                }
            }
        }
    }
}