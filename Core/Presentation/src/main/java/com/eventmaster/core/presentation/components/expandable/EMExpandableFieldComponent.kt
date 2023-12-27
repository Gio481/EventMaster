package com.eventmaster.core.presentation.components.expandable

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eventmaster.core.presentation.R

@Composable
fun EMExpandableField(
    text: String,
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    unExpandIcon: Int = R.drawable.em_ic_un_expand,
    expandedState: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    expandedListener: () -> Unit = { expandedState.value = !expandedState.value },
    content: @Composable BoxScope.() -> Unit
) {
    var expandIconState by rememberSaveable { mutableStateOf(R.drawable.em_ic_expand) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = Color(0xFF2A2A2A), shape = RoundedCornerShape(13.dp))
            .animateContentSize()
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 13.dp)
        ) {
            Text(
                text = text,
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterStart)
            )

            Image(
                painter = painterResource(id = expandIconState),
                contentDescription = "content",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable {
                        expandedListener.invoke()
                    }
            )
        }

        if (expandedState.value) {
            Box(modifier = contentModifier.padding(20.dp)) {
                content.invoke(this)
            }
        }

        expandIconState = when (expandedState.value) {
            true -> unExpandIcon
            false -> R.drawable.em_ic_expand
        }
    }
}