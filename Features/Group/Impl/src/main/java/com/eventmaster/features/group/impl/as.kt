package com.eventmaster.features.group.impl

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.eventmaster.core.presentation.extensions.shake

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun G(){
    val shakeAnimation = remember {
        mutableStateOf(false)
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(10) {
            ConstraintLayout(
                modifier = Modifier
                    .padding(top = 10.dp, start = if(it%2==0) 0.dp else 10.dp, end = if(it%2==0) 10.dp else 0.dp)
                    .shake(shakeAnimation.value)
                    .combinedClickable(
                        onLongClick = { shakeAnimation.value = true },
                        onClick = { shakeAnimation.value = false }
                    )
            ) {
                val (icon, image, name, desc) = createRefs()
                Image(
                    painter = painterResource(id = R.drawable.gio),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(33.dp))
                        .constrainAs(image) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        },
                    contentScale = ContentScale.Crop
                )
                if (shakeAnimation.value) {
                    Image(
                        painter = painterResource(id = com.eventmaster.core.presentation.R.drawable.em_ic_un_expand),
                        contentDescription = "",
                        modifier = Modifier.constrainAs(icon) {
                            top.linkTo(image.top)
                            start.linkTo(image.start)
                        }
                    )
                }
                Text(
                    text = "Test Name",
                    fontSize = 16.sp,
                    color = Color(0xFFEEEEEE),
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .constrainAs(name) {
                            top.linkTo(image.bottom)
                            start.linkTo(image.start)
                        }
                )
                Text(
                    text = "Test Members",
                    fontSize = 12.sp,
                    color = Color(0xFFEEEEEE),
                    modifier = Modifier.constrainAs(desc) {
                        top.linkTo(name.bottom)
                        start.linkTo(name.start)
                    }
                )
            }
        }
    }
}