package com.eventmaster.core.presentation.base.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eventmaster.core.presentation.R
import com.eventmaster.core.presentation.base.ui.action.EMActionButton
import com.eventmaster.core.presentation.base.vm.EMScreenBaseVm

@Composable
fun EMScreenBase(
    vm: EMScreenBaseVm,
    showHeader: Boolean = true,
    showBackButton: Boolean = true,
    showCloseButton: Boolean = true,
    headerText: String? = null,
    bottomAction: () -> Unit = {},
    showBottomAction: Boolean = false,
    bottomActionButton: EMActionButton = EMActionButton.Single("Continue"),
    bottomActionContent: @Composable BoxScope.() -> Unit = {
        BottomActionComponent(bottomAction, bottomActionButton)
    },
    content: @Composable BoxScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp)
                .padding(top = if (showHeader) 16.dp else 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (showHeader) {
                ShowComponent(showBackButton) {
                    Image(
                        painter = painterResource(id = R.drawable.em_core_ic_arrow),
                        contentDescription = "arrow",
                        modifier = Modifier
                            .padding(top = 2.dp)
                            .clickable {
                                vm.navigateBack()
                            }
                    )
                }
                Text(
                    textAlign = TextAlign.Center,
                    text = headerText ?: "",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )

                ShowComponent(showCloseButton) {
                    Image(
                        painter = painterResource(id = R.drawable.em_core_ic_close),
                        contentDescription = "close",
                        modifier = Modifier
                            .padding(top = 2.dp)
                            .clickable {
                                vm.finishFeature()
                            }
                    )
                }
            }
        }
        Box(content = content)
        if (showBottomAction) {
            Box(
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier.fillMaxSize(),
                content = bottomActionContent
            )
        }
    }
    LaunchedEffect(key1 = Unit) {
        vm.onCreate()
        vm.suspendOnCreate()
    }
}

@Composable
private fun BottomActionComponent(onClick: () -> Unit, actionButton: EMActionButton) {
    when (actionButton.type) {
        EMActionButton.Type.Single -> SingleButtonBottomAction(
            onClick,
            actionButton as EMActionButton.Single
        )

        EMActionButton.Type.SingleWithInfo -> SingleButtonWithInfoBottomAction(
            onClick,
            actionButton as EMActionButton.SingleWithInfo
        )

        EMActionButton.Type.SingleWithTitle -> SingleButtonWithTitleBottomAction(
            onClick,
            actionButton as EMActionButton.SingleWithKeyValue
        )
    }
}

@Composable
private fun SingleButtonBottomAction(
    onClick: () -> Unit,
    singleButtonAction: EMActionButton.Single
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(size = 15.dp),
        colors = ButtonDefaults.textButtonColors(
            containerColor = Color(0xFF865DFF),
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 33.dp)
            .height(50.dp)
    ) {
        Text(
            text = singleButtonAction.text,
            color = Color(0xff1B1111),
            fontSize = 16.sp
        )
    }
}

@Composable
private fun SingleButtonWithInfoBottomAction(
    onClick: () -> Unit,
    data: EMActionButton.SingleWithInfo
) {
    Column(
        modifier = Modifier
            .background(color = Color(0xff2A2A2A))
            .padding(start = 16.dp, end = 16.dp, bottom = 33.dp)
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.padding(top = 36.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(data.infoIcon), contentDescription = "content")
            Text(
                text = data.infoText,
                modifier = Modifier
                    .padding(start = 12.dp),
                color = Color(0xC6C6C6C4),
                fontSize = 14.sp
            )
        }
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(size = 15.dp),
            colors = ButtonDefaults.textButtonColors(
                containerColor = Color(0xFF865DFF),
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
                .height(50.dp)
        ) {
            Text(
                text = data.text,
                color = Color(0xff1B1111),
                fontSize = 16.sp
            )
        }
    }
}

@Composable
private fun SingleButtonWithTitleBottomAction(
    onClick: () -> Unit,
    data: EMActionButton.SingleWithKeyValue
) {
    Column(
        modifier = Modifier
            .background(color = Color(0xff2A2A2A))
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 33.dp)
    ) {
        LazyColumn(modifier = Modifier.padding(top = 4.dp)) {
            items(data.keyValueData) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = it.key,
                        color = Color(0xffEEEEEE),
                        fontSize = 12.sp,
                        modifier = Modifier.alpha(0.9f),
                    )
                    Text(
                        text = it.value,
                        color = Color(0xff865DFF),
                        fontSize = 12.sp,
                        modifier = Modifier.alpha(0.9f)
                    )
                }
            }
        }
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(size = 15.dp),
            colors = ButtonDefaults.textButtonColors(
                containerColor = Color(0xFF865DFF),
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .height(50.dp)
        ) {
            Text(
                text = data.text,
                color = Color(0xff1B1111),
                fontSize = 16.sp
            )
        }
    }
}

@Composable
private fun RowScope.ShowComponent(isVisible: Boolean, content: @Composable RowScope.() -> Unit) {
    if (isVisible) content.invoke(this)
    else Spacer(modifier = Modifier)
}