package com.eventmaster.features.group.impl.presentation.groups.multi_groups.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.constraintlayout.compose.layoutId
import com.eventmaster.core.presentation.base.ui.EMScreenBase
import com.eventmaster.core.presentation.components.textfield.search.EMSearchTextField
import com.eventmaster.core.presentation.extensions.listShimmer
import com.eventmaster.core.presentation.extensions.readMotionScene
import com.eventmaster.core.presentation.extensions.scrollIfNeeded
import com.eventmaster.core.presentation.extensions.shimmerGradient
import com.eventmaster.core.presentation.extensions.shimmerLoadingAnimation
import com.eventmaster.core.presentation.util.EMDrawable
import com.eventmaster.core.presentation.util.EMFont
import com.eventmaster.core.presentation.util.anchoredDraggableState
import com.eventmaster.core.presentation.util.changeStatusBarColor
import com.eventmaster.core.presentation.util.getAnchoredDraggableStateProgress
import com.eventmaster.core.presentation.util.nestedScrollConnection
import com.eventmaster.features.group.impl.R
import com.eventmaster.features.group.impl.presentation.groups.multi_groups.model.EMMultiGroupState
import com.eventmaster.features.group.impl.presentation.groups.multi_groups.vm.EMMultiGroupsVm
import org.koin.androidx.compose.koinViewModel

@Composable
fun EMMultiGroupsComposable(vm: EMMultiGroupsVm = koinViewModel()) {
    LocalContext.current.changeStatusBarColor(Color(0xFF171717))
    val groupState = vm.groupState.collectWithInitialValue()

    EMScreenBase(
        vm = vm,
        showHeader = true,
        headerText = "Groups",
        showBottomAction = false,
        showBackButton = false,
        showHeaderLine = false
    ) {
        when (groupState) {
            is EMMultiGroupState.Empty -> EmptyState()
            is EMMultiGroupState.Success -> SuccessState(vm)
            is EMMultiGroupState.Loading -> LoadingState()
            is EMMultiGroupState.Error -> {}
        }
    }
}

@Composable
private fun LoadingState() {
    Column {
        Box(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 24.dp)
                .fillMaxWidth()
                .height(40.dp)
                .clip(RoundedCornerShape(16.dp))
                .shimmerLoadingAnimation(),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(modifier = Modifier.padding(start = 16.dp)) {
                Box(
                    modifier = Modifier
                        .size(22.dp)
                        .clip(RoundedCornerShape(50))
                        .shimmerGradient()
                )
                Box(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .size(width = 100.dp, height = 20.dp)
                        .clip(RoundedCornerShape(50))
                        .shimmerGradient()
                )
            }
        }

        Box(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 24.dp)
                .fillMaxWidth()
                .height(47.dp)
                .clip(RoundedCornerShape(16.dp))
                .shimmerLoadingAnimation(),
            contentAlignment = Alignment.Center
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .size(22.dp)
                        .clip(RoundedCornerShape(50))
                        .shimmerGradient()
                )
                Box(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .size(width = 100.dp, height = 20.dp)
                        .clip(RoundedCornerShape(50))
                        .shimmerGradient()
                )
            }
        }
        @Composable
        fun ShimmeringGroupItem() {
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                    .fillMaxWidth()
                    .height(72.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .shimmerLoadingAnimation(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .size(48.dp)
                        .clip(RoundedCornerShape(50))
                        .shimmerGradient(),
                )
                Column(
                    modifier = Modifier.padding(start = 16.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .size(width = 100.dp, height = 20.dp)
                            .clip(RoundedCornerShape(50))
                            .shimmerGradient()
                    )
                    Box(
                        modifier = Modifier
                            .padding(start = 10.dp, top = 5.dp)
                            .size(width = 100.dp, height = 15.dp)
                            .clip(RoundedCornerShape(50))
                            .shimmerGradient()
                    )
                }
            }
        }
        listShimmer(
            count = 10,
            content = { ShimmeringGroupItem() }
        )
    }
}

@Composable
private fun EmptyState() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(bottom = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.em_ill_empty_group),
                contentDescription = ""
            )
            Text(
                text = "Nothing is here yet",
                fontSize = 16.sp,
                color = Color(0xFFEEEEEE),
                fontFamily = FontFamily(Font(EMFont.em_poppins_medium))
            )
            CreateGroupButton(
                color = Color(0xFF5D6DFF),
                text = "Create group",
                modifier = Modifier
                    .padding(top = 48.dp)
                    .width(216.dp),
                plusIconBoxSize = 18.dp,
                plusIconBoxColor = Color(0xFF1B1111),
                textColor = Color(0xFF1B1111),
                textSize = 14.sp,
                plusIconTint = Color(0xFF5D6DFF)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun SuccessState(vm: EMMultiGroupsVm) {
    val anchoredDraggableState = anchoredDraggableState(draggedDownAnchorTopDp = 100.dp)
    val progress = getAnchoredDraggableStateProgress(anchoredDraggableState, 100.dp)
    val scene = LocalContext.current.readMotionScene(R.raw.em_multi_group_motion_scene)
    val lazyState = rememberLazyListState()

    MotionLayout(
        motionScene = MotionScene(scene),
        progress = progress,
        modifier = Modifier.scrollIfNeeded(
            lazyState.canScrollForward || progress != 0f,
            nestedScrollConnection(anchoredDraggableState)
        )
    ) {
        EMSearchTextField(
            modifier = Modifier.layoutId("searchField"),
            onValueChange = {}
        )
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFF5D6DFF), RoundedCornerShape(50))
                .layoutId("roundAddGroupButton"),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = EMDrawable.em_ic_add_size_16),
                contentDescription = "",
                tint = Color.Black
            )
        }

        CreateGroupButton(
            color = Color(0xFF272727),
            text = "New group",
            plusIconBoxSize = 20.dp,
            plusIconBoxColor = Color(0xFF5D6DFF),
            textColor = Color(0xFFEEEEEE),
            textSize = 16.sp,
            plusIconTint = Color.Black
        )

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .layoutId("groupItemsLazyColumn"),
            state = lazyState
        ) {
            items(listOf(1, 2, 3, 4, 5, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7)) {
                val dismissState = rememberSwipeToDismissBoxState()
                SwipeToDismissBox(
                    state = dismissState,
                    enableDismissFromStartToEnd = false,
                    backgroundContent = {
                        Box(
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .fillMaxSize(),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Image(
                                painter = painterResource(id = EMDrawable.em_ic_remove_circle_size_24),
                                contentDescription = null
                            )
                        }
                    },
                    content = { GroupItem(vm) }
                )
            }
        }
    }
}

@Composable
private fun CreateGroupButton(
    color: Color,
    text: String,
    modifier: Modifier = Modifier,
    plusIconBoxSize: Dp,
    plusIconBoxColor: Color,
    textColor: Color,
    textSize: TextUnit,
    plusIconTint: Color,
) {
    Button(
        modifier = Modifier
            .then(modifier)
            .height(47.dp)
            .layoutId("newGroupButton"),
        colors = ButtonColors(
            containerColor = color,
            contentColor = color,
            disabledContainerColor = color,
            disabledContentColor = color,
        ),
        shape = RoundedCornerShape(16.dp),
        contentPadding = PaddingValues(0.dp),
        onClick = {},
        content = {
            Box(
                modifier = Modifier
                    .padding(start = 5.dp)
                    .size(plusIconBoxSize)
                    .clip(RoundedCornerShape(50))
                    .background(plusIconBoxColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.em_ic_add_size_9),
                    contentDescription = "",
                    tint = plusIconTint
                )
            }
            Text(
                text = text,
                fontSize = textSize,
                color = textColor,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    )
}

@Composable
private fun GroupItem(vm: EMMultiGroupsVm) {
    Row(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .height(72.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF282828))
            .clickable { vm.navigateToDetails() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .padding(start = 16.dp)
                .size(48.dp),
            painter = painterResource(id = R.drawable.test_group_image),
            contentDescription = ""
        )
        Column(
            modifier = Modifier.padding(start = 14.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "Paris vacation", fontSize = 16.sp, color = Color(0xFFEEEEEE))
            Text(text = "3 Members", fontSize = 12.sp, color = Color(0xFFEEEEEE))
        }
    }
}