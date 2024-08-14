package com.eventmaster.features.home.impl.presentation.widgets.states.empty

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.eventmaster.core.presentation.util.EMDrawable
import com.eventmaster.features.home.impl.presentation.widgets.states.model.EMHomeWidgetState

@Composable
internal fun EMHomeWidgetEmptyState(config: EMHomeWidgetState.Empty) {
    Row(
        modifier = Modifier
            .background(config.background)
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            config.title,
            fontSize = 14.sp,
            color = Color.White,
        )
        Icon(painter = painterResource(id = EMDrawable.em_ic_pickup_arrow), contentDescription = "")
    }
}