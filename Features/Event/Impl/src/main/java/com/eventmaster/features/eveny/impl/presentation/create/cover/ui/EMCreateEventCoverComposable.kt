package com.eventmaster.features.eveny.impl.presentation.create.cover.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eventmaster.core.presentation.base.ui.EMScreenBase
import com.eventmaster.core.presentation.components.expandable.EMExpandableField
import com.eventmaster.core.presentation.components.textfield.regular.EMRegularTextField
import com.eventmaster.features.event.impl.R
import com.eventmaster.features.eveny.impl.presentation.create.cover.vm.EMCreateEventCoverVm
import org.koin.androidx.compose.get

@Composable
fun EMCreateEventCoverComposable(vm: EMCreateEventCoverVm = get()) {
    EMScreenBase(
        vm = vm,
        headerText = "Create Event",
        showBottomAction = true,
        showBackButton = false,
        bottomAction = { vm.onNextAction() },
    ) {

        var selectedItem by remember { mutableStateOf(-1) }
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            val list = listOf(
                R.drawable.galaxy,
                R.drawable.galaxy,
                R.drawable.galaxy,
                R.drawable.galaxy,
                R.drawable.galaxy
            )
            EMRegularTextField(
                title = "Event name",
                modifier = Modifier.padding(top = 24.dp)
            )
            Text(
                text = "Choose cover",
                fontSize = 16.sp,
                color = Color(0xFFEEEEEE),
                modifier = Modifier.padding(top = 32.dp)
            )
            LazyRow(
                modifier = Modifier.height(130.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .width(110.dp)
                            .height(85.dp)
                            .clip(RoundedCornerShape(11.dp))
                            .background(Color(0xFF2A2A2A)),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(com.eventmaster.core.presentation.R.drawable.em_core_ic_close),
                            contentDescription = "",
                            modifier = Modifier.size(35.dp),
                        )
                    }
                }
                itemsIndexed(list) { index, res ->
                    Image(
                        painter = painterResource(res),
                        contentDescription = "",
                        modifier = Modifier
                            .width(if (index == selectedItem) 130.dp else 110.dp)
                            .height(if (index == selectedItem) 100.dp else 85.dp)
                            .padding(start = 8.dp)
                            .setCustomBackground(index, selectedItem)
                            .clickable { selectedItem = index },
                        contentScale = ContentScale.Crop
                    )
                }
            }

            EMExpandableField(text = "Add description", modifier = Modifier.padding(top = 32.dp)) {
                EMRegularTextField(
                    textFiledModifier = Modifier.height(150.dp),
                    containerColor = Color(0xFF171717)
                )
            }
        }
    }
}

private fun Modifier.setCustomBackground(index: Int, selectedIndex: Int): Modifier {
    return if (index == selectedIndex) {
        border(
            width = 2.dp,
            color = Color.White,
            shape = RoundedCornerShape(11.dp)
        )
    } else {
        clip(RoundedCornerShape(11.dp))
    }
}