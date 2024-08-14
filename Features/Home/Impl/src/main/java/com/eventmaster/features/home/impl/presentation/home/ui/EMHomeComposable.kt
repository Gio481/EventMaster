package com.eventmaster.features.home.impl.presentation.home.ui

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eventmaster.core.presentation.base.ui.EMScreenBase
import com.eventmaster.core.presentation.util.changeStatusBarColor
import com.eventmaster.features.home.impl.R
import com.eventmaster.features.home.impl.presentation.home.vm.EMHomeVm
import com.eventmaster.features.home.impl.presentation.widgets.base.hexagon.EMHexagon
import org.koin.androidx.compose.koinViewModel

@Composable
fun EMHomeComposable(vm: EMHomeVm = koinViewModel()) {
    val activity = (LocalContext.current as Activity)
    activity.changeStatusBarColor(Color(0xFF140D28))

//    BackHandler { activity.moveTaskToBack(true) }
    EMScreenBase(
        vm = vm,
        showBottomAction = false,
        showHeader = false,
        modifier = Modifier.background(Color(0xFF140D28))
    ) {
        ProfileWidget()
        EMHexagon(
            size = DpSize(170.dp, 170.dp),
            paddingValues = PaddingValues(top = 100.dp, end = 16.dp),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .clickable {
                    vm.navigateToCreateGroup()
                }
        ) {}

        EMHexagon(
            size = DpSize(170.dp, 170.dp),
            paddingValues = PaddingValues(bottom = 120.dp, start = 20.dp),
            modifier = Modifier
                .align(Alignment.Center)
                .clickable {
                    vm.navigateToCreateEvent()
                }
        ) {}

        EMHexagon(
            size = DpSize(170.dp, 170.dp),
            paddingValues = PaddingValues(top = 215.dp, start = 20.dp),
            modifier = Modifier
                .align(Alignment.Center)
                .clickable {
                    vm.navigateToGroups()
                }
        ) {}

        EMHexagon(
            size = DpSize(170.dp, 170.dp),
            paddingValues = PaddingValues(bottom = 50.dp, end = 16.dp),
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {}
    }
}

@Composable
private fun ProfileWidget() {
    Row(verticalAlignment = Alignment.Bottom) {
        EMHexagon(
            paddingValues = PaddingValues(start = 16.dp, top = 20.dp),
            cornerRadius = 8.dp,
            content = {
                Image(painter = painterResource(id = R.drawable.testtt), contentDescription = "")
            }
        )
        Column(
            modifier = Modifier.padding(top = 9.dp, start = 9.dp, bottom = 3.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Welcome", fontSize = 20.sp, color = Color(0xFFEEEEEE))
            Text(text = "Teona_20", fontSize = 14.sp, color = Color(0xFFEEEEEE))
        }
    }
}