package com.eventmaster.features.authentication.impl.presentation.signup.profile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eventmaster.features.authentication.impl.R
import com.eventmaster.features.authentication.impl.presentation.signup.base.type.EMSignUpStepType
import com.eventmaster.features.authentication.impl.presentation.signup.base.ui.EMSignUpBaseComposable
import com.eventmaster.features.authentication.impl.presentation.signup.profile.vm.EMSignUpProfileVm
import org.koin.androidx.compose.get

@Composable
fun EMSignUpProfile(vm: EMSignUpProfileVm = get()) {
    val selectedItem = remember { mutableStateOf(-1) }

    EMSignUpBaseComposable(vm = vm, currentStep = EMSignUpStepType.PROFILE) {
        Box(
            Modifier
                .padding(top = 65.dp)
                .size(124.dp)
                .clip(RoundedCornerShape(50))
                .align(Alignment.CenterHorizontally),
            contentAlignment = Alignment.Center
        ) {
            if (selectedItem.value != -1) {
                Image(
                    painter = painterResource(selectedItem.value),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }

        }
        Text(
            text = "Choose Avatar",
            modifier = Modifier.padding(top = 22.dp),
            color = Color(0xFFEEEEEE),
            fontSize = 22.sp
        )

        ProfileImages(selectedItem)
    }
}

@Composable
private fun ProfileImages(selectedItem: MutableState<Int>) {
    val elements = listOf(
        R.drawable.em_male_profile_1,
        R.drawable.em_female_profile_1,
        R.drawable.em_male_profile_2,
        R.drawable.em_female_profile_2,
        R.drawable.em_male_profile_3,
        R.drawable.em_female_profile_3,
        R.drawable.em_male_profile_4,
        R.drawable.em_female_profile_4,
        R.drawable.em_male_profile_5,
        R.drawable.em_female_profile_5,
        R.drawable.em_male_profile_6,
        R.drawable.em_female_profile_6,
        R.drawable.em_male_profile_7,
        R.drawable.em_female_profile_7,
        R.drawable.em_male_profile_8,
        R.drawable.em_female_profile_8,
        R.drawable.em_male_profile_9,
        R.drawable.em_male_profile_10,
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(5), modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        userScrollEnabled = false
    ) {
        item {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .clickable { }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.em_ic_add),
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .size(56.dp)
                        .background(color = Color(0x4DD9D9D9))
                )
            }
        }
        itemsIndexed(elements) { index, item ->
            val topPadding = if (index > 3) 12.dp else 0.dp
            Box(
                modifier = Modifier
                    .padding(top = topPadding)
                    .clip(RoundedCornerShape(50))
                    .borderIfNeeded(item == selectedItem.value)
                    .clickable { selectedItem.value = item }
            ) {
                Image(
                    painter = painterResource(id = item),
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .size(56.dp)
                        .background(color = Color(0x4DD9D9D9))
                )
            }
        }
    }
}

private fun Modifier.borderIfNeeded(need: Boolean): Modifier {
    if (!need) return this
    return this.border(2.dp, Color(0xFF865DFF), RoundedCornerShape(50))
}