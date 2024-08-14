package com.eventmaster.features.eveny.impl.presentation.create.add_task.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eventmaster.core.presentation.base.ui.EMScreenBase
import com.eventmaster.core.presentation.base.ui.action.EMActionButton
import com.eventmaster.core.presentation.components.expandable.EMBaseExpandableField
import com.eventmaster.core.presentation.components.textfield.base.EMBaseTextField
import com.eventmaster.core.presentation.util.EMDrawable
import com.eventmaster.features.eveny.impl.presentation.create.add_task.vm.EMCreateEventAddTaskVm
import org.koin.androidx.compose.get

@Composable
fun EMCreateEventAddTask(vm: EMCreateEventAddTaskVm = get()) {
    EMScreenBase(
        vm = vm,
        headerText = "Create Event",
        bottomActionButton = EMActionButton.SingleWithInfo(
            "Continue",
            EMDrawable.em_ic_info,
            "You’ll be able to add task for later"
        ),
        showBottomAction = true,
        bottomAction = {}
    ) {
        val testList = remember {
            mutableStateOf(listOf<Int>())
        }

        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
            ) {
                if (testList.value.isEmpty()) {
                    item {
                        Column(
                            modifier = Modifier
                                .fillParentMaxWidth()
                                .background(Color(0xFF272727), RoundedCornerShape(16.dp))
                                .padding(20.dp)
                                .animateContentSize()
                        ) {
                            Text(text = "Add Task", color = Color.White, fontSize = 16.sp)
                            ContentCard(testList)
                        }
                    }
                }

                items(testList.value) {
                    EMBaseExpandableField(text = "Add") {
                        ContentCard(testList)
                    }
                }

                if (testList.value.isNotEmpty()) {
                    item {
                        EMBaseExpandableField(text = "Add Another task") {
                            ContentCard(testList)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ContentCard(testList: MutableState<List<Int>>) {
    val showBottomSheetState = remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        EMBaseTextField(
            hint = "Task Name",
            containerColor = Color(0xC7464646),
            modifier = Modifier.padding(top = 24.dp)
        )
        EMBaseTextField(
            hint = "Task Name",
            modifier = Modifier.padding(top = 16.dp),
            containerColor = Color(0xC7464646)
        )
        EMBaseTextField(
            hint = "Task Name",
            modifier = Modifier.padding(top = 16.dp),
            containerColor = Color(0xC7464646)
        )
        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .clickable { showBottomSheetState.value = true },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color(0xFF5D6DFF)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = EMDrawable.em_ic_add_people),
                    contentDescription = "",
                    tint = Color.Black
                )
            }
            Text(
                text = "Assign to friends",
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 6.dp)
            )
        }

        Button(
            onClick = { testList.value = testList.value.toMutableList().apply { add(12) } },
            border = BorderStroke(1.dp, Color(0xFF5D6DFF)),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
                .height(42.dp),
            colors = buttonColors(
                contentColor = Color(0xFF272727),
                containerColor = Color(0xFF272727)
            )
        ) {
            Text(text = "Done", color = Color(0xFF5D6DFF), fontSize = 16.sp)
        }
    }

    if (showBottomSheetState.value) {
        EMCreateEventFriendsBottomSheet {
            showBottomSheetState.value = false
        }
    }
}