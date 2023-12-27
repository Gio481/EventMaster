package com.eventmaster.features.authentication.impl.presentation.signup.profile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.Modifier
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
    EMSignUpBaseComposable(vm = vm, currentStep = EMSignUpStepType.PROFILE) {
        Text(
            text = "Choose Avatar",
            modifier = Modifier.padding(top = 32.dp),
            color = Color.White,
            fontSize = 22.sp
        )

        ProfileImages()
    }
}

@Composable
private fun ProfileImages() {
    val elements = listOf(
        R.drawable.em_ic_add,
        R.drawable.em_ic_add,
        R.drawable.em_ic_add,
        R.drawable.em_ic_add,
        R.drawable.em_ic_add,
        R.drawable.em_ic_add,
        R.drawable.em_ic_add,
        R.drawable.em_ic_add,
        R.drawable.em_ic_add,
        R.drawable.em_ic_add,
        R.drawable.em_ic_add,
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(5), modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        userScrollEnabled = false
    ) {
        itemsIndexed(elements) { index, item ->
            val topPadding = if (index > 4) 12.dp else 0.dp

            Box(modifier = Modifier.padding(top = topPadding)) {
                Image(
                    painter = painterResource(id = item),
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .size(56.dp)
                        .background(color = Color(0x4DD9D9D9), shape = RoundedCornerShape(50))
                )
            }
        }
    }
}