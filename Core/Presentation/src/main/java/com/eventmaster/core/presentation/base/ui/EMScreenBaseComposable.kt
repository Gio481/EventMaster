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
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
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
    modifier: Modifier = Modifier,
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
    showHeaderLine: Boolean = false,
    headerLineColor: Color = Color(0xFF2C2C2C),
    headerBackground: Color = Color(0xFF171717),
    customHeaderContent: @Composable (BoxScope.() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.systemBars)
            .fillMaxSize()
            .background(Color(0xFF121212))
    ) {
        Header(
            vm = vm,
            showHeader = showHeader,
            showBackButton = showBackButton,
            showCloseButton = showCloseButton,
            headerText = headerText,
            headerBackground = headerBackground,
            showHeaderLine = showHeaderLine,
            headerLineColor = headerLineColor,
            customHeaderContent = customHeaderContent
        )
        Box(content = content, modifier = Modifier.weight(1f).fillMaxWidth().then(modifier))
        if (showBottomAction) {
            Box(
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier.wrapContentSize(),
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
private fun Header(
    vm: EMScreenBaseVm,
    showHeader: Boolean = true,
    showBackButton: Boolean = true,
    showCloseButton: Boolean = true,
    headerText: String? = null,
    headerBackground: Color = Color(0xFF171717),
    showHeaderLine: Boolean = false,
    headerLineColor: Color = Color(0xFF2C2C2C),
    customHeaderContent: @Composable (BoxScope.() -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = headerBackground)
                .padding(horizontal = 16.dp)
                .padding(vertical = if (showHeader) 16.dp else 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (showHeader) {
                ShowComponent(showBackButton) {
                    Image(
                        painter = painterResource(id = R.drawable.em_core_ic_arrow),
                        contentDescription = "arrow",
                        modifier = Modifier.clickable { vm.navigateBack() }
                    )
                }
                customHeaderContent?.invoke(this@Box) ?: run {
                    Text(
                        textAlign = TextAlign.Center,
                        text = headerText ?: "",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = if (showBackButton) 15.dp else 0.dp)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))
                ShowComponent(showCloseButton) {
                    Image(
                        painter = painterResource(id = R.drawable.em_core_ic_close),
                        contentDescription = "close",
                        modifier = Modifier.clickable { vm.finishFeature() }
                    )
                }
            }
        }
        if (showHeader && showHeaderLine) {
            HorizontalDivider(
                modifier = Modifier.align(Alignment.BottomCenter),
                color = headerLineColor,
                thickness = 0.5.dp
            )
        }
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
            containerColor = Color(0xFF5D6DFF),
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 33.dp, top = 28.dp)
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