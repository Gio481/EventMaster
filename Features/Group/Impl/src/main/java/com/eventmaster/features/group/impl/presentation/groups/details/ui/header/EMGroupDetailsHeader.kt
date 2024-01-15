package com.eventmaster.features.group.impl.presentation.groups.details.ui.header

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.eventmaster.features.group.impl.R

@Composable
fun HeaderContent() {
    ConstraintLayout(modifier = Modifier.wrapContentSize()) {
        val (image, title) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.gio),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(50))
                .constrainAs(image) {
                    start.linkTo(parent.start, 20.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )
        Text(
            text = "Test Name",
            fontSize = 16.sp,
            color = Color(0xFFEEEEEE),
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top)
                start.linkTo(image.end, 16.dp)
            }
        )
        Image(
            painter = painterResource(id = com.eventmaster.core.presentation.R.drawable.em_ic_edit),
            contentDescription = "",
            modifier = Modifier.constrainAs(createRef()) {
                top.linkTo(title.top)
                bottom.linkTo(title.bottom)
                start.linkTo(title.end, 6.dp)
            }
        )
        Text(
            text = "25 Members",
            fontSize = 12.sp,
            color = Color(0xFFEEEEEE),
            modifier = Modifier.constrainAs(createRef()) {
                top.linkTo(title.bottom)
                start.linkTo(title.start)
            }
        )
    }
}
