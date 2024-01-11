package com.eventmaster.core.presentation.components.datepicker.regular

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eventmaster.core.presentation.components.datepicker.extensions.fadingEdge
import com.eventmaster.core.presentation.components.datepicker.extensions.pixelsToDp
import com.eventmaster.core.presentation.components.datepicker.extensions.rememberPickerState
import com.eventmaster.core.presentation.components.datepicker.model.EMDatePickerTextStyle
import com.eventmaster.core.presentation.components.datepicker.state.EMDatePickerState
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EMRegularDatePicker(
    modifier: Modifier = Modifier,
    items: List<String>,
    state: EMDatePickerState = rememberPickerState(),
    startIndex: Int = 0,
    visibleItemsCount: Int = 5,
) {
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = startIndex)
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)

    val itemHeightPixels = remember { mutableStateOf(0) }
    val itemHeightDp = pixelsToDp(itemHeightPixels.value)

    val fadingEdgeGradient = remember {
        Brush.verticalGradient(
            0f to Color.Transparent,
            0.5f to Color(0xFFEEEEEE),
            1f to Color.Transparent
        )
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .map { index -> items[index % items.size + 2] }
            .distinctUntilChanged()
            .collect { item ->
                state.selectedItem = item

            }
    }

    Box(modifier = modifier) {
        LazyColumn(
            state = listState,
            flingBehavior = flingBehavior,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .wrapContentWidth()
                .height((itemHeightDp + 25.dp) * visibleItemsCount)
                .fadingEdge(fadingEdgeGradient)
        ) {
            itemsIndexed(items) { index, item ->
                val selectedIndex = items.indexOf(state.selectedItem)
                val textStyle = textStyle(selectedIndex, index)
                Text(
                    text = getText(index, visibleItemsCount, items, item),
                    fontSize = textStyle.fontSize,
                    fontStyle = FontStyle.Normal,
                    fontWeight = textStyle.fontWeight,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(top = 25.dp)
                        .onSizeChanged { size -> itemHeightPixels.value = size.height }
                )
            }
        }
    }
}

private fun textStyle(selectedIndex: Int, index: Int): EMDatePickerTextStyle {
    return when {
        selectedIndex == index -> EMDatePickerTextStyle(20.sp, FontWeight.Bold)
        selectedIndex - index == 1 || selectedIndex - index == -1 -> EMDatePickerTextStyle(18.sp)
        else -> EMDatePickerTextStyle(16.sp)
    }
}

private fun getText(index: Int, visibleItemsCount: Int, items: List<String>, item: String): String {
    val paddingItemCount = visibleItemsCount / 2
    return when {
        index >= paddingItemCount && index < items.size - paddingItemCount -> item
        else -> ""
    }
}