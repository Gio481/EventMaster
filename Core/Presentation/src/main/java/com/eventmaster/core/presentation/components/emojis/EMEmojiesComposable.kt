package com.eventmaster.core.presentation.components.emojis

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.eventmaster.core.presentation.R


@Composable
fun EMEmojisComposable(
    modifier: Modifier = Modifier,
    showEmojis: Boolean
) {
    if (showEmojis) {
        Box(
            Modifier
                .wrapContentSize()
                .then(modifier)
        ) {
            Box(
                modifier = Modifier
                    .padding(end = 47.dp, bottom = 12.dp)
                    .width(180.dp)
                    .height(45.dp)
                    .background(Color(0xFF323333), RoundedCornerShape(25.dp))
                    .align(Alignment.BottomEnd)
            )
            Box(
                modifier = Modifier
                    .padding(end = 37.dp, bottom = 15.dp)
                    .width(27.dp)
                    .height(26.dp)
                    .background(Color(0xFF323333), RoundedCornerShape(25.dp))
                    .align(Alignment.BottomEnd)
            )
            Box(
                modifier = Modifier
                    .padding(end = 26.dp, bottom = 13.dp)
                    .size(15.dp)
                    .background(Color(0xFF323333), RoundedCornerShape(50))
                    .align(Alignment.BottomEnd)
            )
            Image(
                painter = painterResource(id = R.drawable.em_ic_heart),
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
                    .background(
                        Color(0xFF323333),
                        RoundedCornerShape(50)
                    )
                    .padding(6.dp)
                    .align(Alignment.BottomEnd)
            )
            Row(
                modifier
                    .width(180.dp)
                    .height(45.dp)
                    .align(Alignment.CenterStart),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.em_ic_heart_fill),
                    contentDescription = "",
                    modifier = Modifier.padding(start = 20.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.em_ic_party),
                    contentDescription = ""
                )
                Image(
                    painter = painterResource(id = R.drawable.em_ic_excited),
                    contentDescription = ""
                )
                Image(
                    painter = painterResource(id = R.drawable.em_ic_stars),
                    contentDescription = "",
                    modifier = Modifier.padding(end = 20.dp)
                )
            }
        }
    }
}