package com.eventmaster.features.eveny.impl.presentation.create.add_task.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eventmaster.core.presentation.base.ui.EMScreenBase
import com.eventmaster.core.presentation.base.ui.action.EMActionButton
import com.eventmaster.core.presentation.components.expandable.EMBaseExpandableField
import com.eventmaster.core.presentation.components.textfield.base.EMBaseTextField
import com.eventmaster.features.eveny.impl.presentation.create.add_task.vm.EMCreateEventAddTaskVm
import org.koin.androidx.compose.get

@Composable
fun EMCreateEventAddTask(vm: EMCreateEventAddTaskVm = get()) {
    EMScreenBase(
        vm = vm,
        headerText = "Create Event",
        bottomActionButton = EMActionButton.SingleWithInfo(
            "Continue",
            com.eventmaster.core.presentation.R.drawable.em_ic_info,
            "You’ll be able to add task for later"
        ),
        showBottomAction = false
    ) {
        val testList = remember {
            mutableStateOf(listOf<Int>())
        }

        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .imePadding()
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
            Column(
                modifier = Modifier
                    .background(color = Color(0xFF161616))
                    .padding(start = 16.dp, end = 16.dp, bottom = 33.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
            ) {
                Row(
                    modifier = Modifier.padding(top = 36.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(com.eventmaster.core.presentation.R.drawable.em_ic_info),
                        contentDescription = "content"
                    )
                    Text(
                        text = "data.infoText",
                        modifier = Modifier
                            .padding(start = 12.dp),
                        color = Color(0xC6C6C6C4),
                        fontSize = 14.sp
                    )
                }
                Button(
                    onClick = { },
                    shape = RoundedCornerShape(size = 15.dp),
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color(0xFF5D6DFF),
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp)
                        .height(50.dp)
                ) {
                    Text(
                        text = "data.text",
                        color = Color(0xff1B1111),
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun ContentCard(testList: MutableState<List<Int>>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        EMBaseTextField(
            hint = "Task Name",
        )
        EMBaseTextField(
            hint = "Task Name",
            modifier = Modifier.padding(top = 16.dp)
        )
        EMBaseTextField(
            hint = "Task Name",
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = "Assign to friends",
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
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
}