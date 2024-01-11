package com.eventmaster.features.eveny.impl.presentation.create.friends_list.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.eventmaster.core.presentation.base.ui.EMScreenBase
import com.eventmaster.core.presentation.components.textfield.search.EMSearchTextField
import com.eventmaster.core.presentation.extensions.scrollIfNeeded
import com.eventmaster.core.presentation.util.EMAnchoredDraggableCardState
import com.eventmaster.core.presentation.util.anchoredDraggableState
import com.eventmaster.core.presentation.util.getAnchoredDraggableStateProgress
import com.eventmaster.core.presentation.util.nestedScrollConnection
import com.eventmaster.features.event.impl.R
import com.eventmaster.features.eveny.impl.presentation.create.friends_list.vm.EMCreateEventFriendsListVm
import org.koin.androidx.compose.get

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EMCreateEventFriendsListComposable(vm: EMCreateEventFriendsListVm = get()) {
    EMScreenBase(
        vm = vm,
        showBottomAction = true,
        headerText = "Create event"
    ) {
        val lazyState = rememberLazyListState()
        val context = LocalContext.current
        val motionScene = remember {
            context.resources.openRawResource(R.raw.em_friends_list_motion_scene)
                .readBytes()
                .decodeToString()
        }
        val anchoredDraggableState = anchoredDraggableState()
        val progress = getAnchoredDraggableStateProgress(anchoredDraggableState)

        MotionLayout(
            motionScene = MotionScene(content = motionScene),
            progress = progress,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .scrollIfNeeded(
                    lazyState.canScrollForward || progress != 0f,
                    nestedScrollConnection(anchoredDraggableState)
                )
        ) {
            ShareAppContent()
            InviteFriendContent(anchoredDraggableState, lazyState)
        }
    }
}

@Composable
private fun ShareAppContent() {
    Column(modifier = Modifier.layoutId("share")) {
        Text(
            text = "Add out of app",
            color = Color(0xFFEEEEEE),
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 24.dp)
        )
        ShareLink()
        Row(modifier = Modifier.padding(top = 13.dp)) {
            ShareIcon(R.drawable.em_ic_messenger)
            ShareIcon(R.drawable.em_ic_instagram, 8.dp)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun InviteFriendContent(
    state: AnchoredDraggableState<EMAnchoredDraggableCardState>,
    lazyListState: LazyListState
) {

    val gio = remember {
        mutableStateOf(getMockData())
    }

    Column(modifier = Modifier.layoutId("invite")) {
        Text(
            text = "Invite friends ",
            color = Color(0xFFEEEEEE),
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 32.dp)
        )

        EMSearchTextField(modifier = Modifier.padding(top = 8.dp)) {
            gio.value = getMockData().filter { mockData ->
                it in mockData.title.lowercase() || it in mockData.description.lowercase()
            }
            if (it.isEmpty()) {
                gio.value = getMockData()
            }
        }

        LazyColumn(
            userScrollEnabled = true,
            state = lazyListState,
            modifier = Modifier
        ) {
            itemsIndexed(gio.value) { index, item ->
                Row(
                    modifier = Modifier.padding(top = if (index == 0) 8.dp else 16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.galaxy),
                        contentDescription = "",
                        modifier = Modifier
                            .size(44.dp)
                            .clip(RoundedCornerShape(50))
                    )
                    Column(modifier = Modifier.padding(start = 14.dp)) {
                        Text(
                            text = item.title,
                            color = Color(0xFFEEEEEE),
                            fontSize = 16.sp
                        )
                        Text(
                            text = item.description,
                            color = Color(0xFFAAAAAA),
                            fontSize = 13.sp
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8F5FEA))
                    ) {
                        Text(
                            text = "Invite",
                            color = Color(0xFF1B1111),
                            fontSize = 12.sp,
                        )
                    }
                }

            }
        }
    }
}

@Composable
private fun ShareLink() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .height(40.dp)
            .border(1.dp, Color(0xFF443169), RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = "https://Share.EventApp/invitation",
            color = Color(0xFFEEEEEE),
            textAlign = TextAlign.Start,
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 16.dp)
        )
        Box(
            modifier = Modifier
                .padding(top = 4.dp, bottom = 4.dp, end = 4.dp)
                .align(Alignment.CenterEnd)
                .clip(RoundedCornerShape(13.dp))
                .background(Color(0xFF8F5FEA))
                .fillMaxHeight(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 17.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Copy",
                    fontSize = 12.sp,
                    color = Color(0xFF1B1111),
                )
                Icon(
                    painter = painterResource(id = R.drawable.em_ic_share_link),
                    contentDescription = "",
                    tint = Color(0xFF000000),
                    modifier = Modifier
                        .padding(start = 5.dp)
                )
            }
        }

    }
}

@Composable
private fun ShareIcon(resId: Int, startPadding: Dp = 0.dp) {
    Image(
        painter = painterResource(id = resId),
        contentDescription = "",
        modifier = Modifier
            .padding(start = startPadding)
            .clip(RoundedCornerShape(50))
            .background(color = Color(0xFF202020))
            .size(32.dp)
            .padding(8.dp)
    )
}

private fun getMockData(): List<MockData> {
    return listOf(
        MockData("G_Beridze_14", "Giorgi Beridze"),
        MockData("Best_Developer", "Giorgi Beridze"),
        MockData("Best_Android", "Giorgi Beridze"),
        MockData("TheBest", "Giorgi Beridze"),
        MockData("TheBestOfBest", "Giorgi Beridze"),
        MockData("G.O.A.T", "Giorgi Beridze"),
        MockData("Teona_20", "Teona Mailashvili"),
        MockData("UI/UX", "Teona Mailashvili"),
        MockData("UI/UX", "Teona Mailashvili"),
        MockData("UI/UX", "Teona Mailashvili"),
        MockData("UI/UX", "Teona Mailashvili"),
        MockData("UI/UX", "Teona Mailashvili"),
        MockData("UI/UX", "Teona Mailashvili"),
        MockData("UI/UX", "Teona Mailashvili"),
    )
}

private data class MockData(
    val title: String,
    val description: String
)