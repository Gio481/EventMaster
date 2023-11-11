package com.eventmaster.core.presentation.base.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eventmaster.core.presentation.R
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
    bottomActionContent: @Composable ColumnScope.() -> Unit = { BottomActionComponent(bottomAction) },
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
        Box {
            content.invoke(this)
        }
        if (showBottomAction) bottomActionContent.invoke(this)
    }
    LaunchedEffect(key1 = null) {
        vm.onCreate()
        vm.suspendOnCreate()
    }
}

@Composable
private fun BottomActionComponent(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
            .padding(bottom = 64.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(size = 15.dp),
            colors = ButtonDefaults.textButtonColors(
                containerColor = Color(0xFF8F5FEA),
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(
                text = "Continue",
                color = Color.White,
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