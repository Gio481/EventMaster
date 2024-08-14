package com.eventmaster.features.eveny.impl.presentation.sections.main.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eventmaster.core.presentation.base.ui.EMScreenBase
import com.eventmaster.core.presentation.components.textfield.search.EMSearchTextField
import com.eventmaster.core.presentation.extensions.fadingEdge
import com.eventmaster.core.presentation.util.EMDrawable
import com.eventmaster.core.presentation.util.IconWithText
import com.eventmaster.core.presentation.util.ImageWithText
import com.eventmaster.core.presentation.util.TextWithIcon
import com.eventmaster.core.presentation.util.changeStatusBarColor
import com.eventmaster.core.presentation.util.fadeGradient
import com.eventmaster.features.event.impl.R
import com.eventmaster.features.eveny.impl.presentation.sections.main.vm.EMEventsSectionsMainVm
import org.koin.androidx.compose.koinViewModel

@Composable
fun EMEventsSectionsMain(vm: EMEventsSectionsMainVm = koinViewModel()) {
    LocalContext.current.changeStatusBarColor(Color(0xFF171717))
    EMScreenBase(
        vm = vm,
        showBottomAction = false,
        showBackButton = false,
        headerText = "My Events"
    ) {
        Column(modifier = Modifier.padding(start = 16.dp)) {
            EMSearchTextField(
                modifier = Modifier.padding(top = 24.dp, end = 16.dp),
                onValueChange = {}
            )
            Column(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                InThisWeekSection()
                SectionComposable(
                    modifier = Modifier.padding(top = 28.dp),
                    sectionTitle = "Created by you",
                    needsOwner = false
                )
                SectionComposable(
                    modifier = Modifier.padding(top = 28.dp),
                    sectionTitle = "Created by others"
                )
            }
        }
    }
}

@Composable
private fun InThisWeekSection() {
    Column {
        Text(
            text = "In this week",
            fontSize = 16.sp,
            color = Color(0xFFEEEEEE)
        )
        LazyRow(
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
        ) {
            itemsIndexed(listOf(1, 2, 3, 4, 5)) { index, _ ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.test_event),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(end = if (index != 4) 12.dp else 0.dp)
                            .width(324.dp)
                            .height(178.dp)
                            .fadingEdge(fadeGradient())
                    )
                    Column(
                        modifier = Modifier
                            .padding(end = 28.dp, top = 16.dp)
                            .align(Alignment.TopEnd)
                            .size(width = 65.dp, height = 43.dp)
                            .background(
                                Color(0x99F1ECEC),
                                RoundedCornerShape(12.dp)
                            ),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "20",
                            fontSize = 14.sp,
                            color = Color(0xFF1B1111),
                            style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)),
                        )
                        Text(
                            text = "December",
                            fontSize = 10.sp,
                            color = Color(0xFF1B1111),
                            style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
                        )
                    }
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(start = 16.dp, bottom = 16.dp)
                    ) {
                        Text(
                            text = "My birthday",
                            fontSize = 18.sp,
                            color = Color(0xFFEEEEEE),
                            fontWeight = FontWeight.Bold
                        )
                        Row(modifier = Modifier.padding(top = 6.dp)) {
                            IconWithText(
                                resId = EMDrawable.em_ic_time,
                                text = "8 PM"
                            )
                            ImageWithText(
                                modifier = Modifier.padding(start = 8.dp),
                                resId = EMDrawable.test,
                                text = "Teonamaila20"
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SectionComposable(
    modifier: Modifier = Modifier,
    sectionTitle: String,
    needsOwner: Boolean = true,
) {
    Column(modifier) {
        Row(
            modifier = Modifier
                .padding(end = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = sectionTitle,
                fontSize = 16.sp,
                color = Color(0xFFEEEEEE)
            )
            TextWithIcon(
                resId = EMDrawable.em_ic_arrow_right,
                text = "view all",
            )
        }

        LazyRow(
            modifier = Modifier
                .padding(top = 12.dp)
        ) {
            itemsIndexed(listOf(1, 2, 3, 4, 5)) { index, _ ->
                Box(
                    modifier = Modifier
                        .padding(end = if (index != 4) 12.dp else 0.dp)
                        .width(156.dp)
                        .height(195.dp)
                        .clip(RoundedCornerShape(20.dp))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.test_event),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxSize()
                            .fadingEdge(fadeGradient()),
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier
                            .padding(end = 16.dp, top = 16.dp)
                            .align(Alignment.TopEnd)
                            .size(width = 65.dp, height = 41.dp)
                            .background(
                                Color(0x99F1ECEC),
                                RoundedCornerShape(12.dp)
                            ),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "20",
                            fontSize = 14.sp,
                            color = Color(0xFF1B1111),
                            style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false)),
                        )
                        Text(
                            text = "December",
                            fontSize = 10.sp,
                            color = Color(0xFF1B1111),
                            style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
                        )
                    }
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(start = 16.dp, bottom = 16.dp)
                    ) {
                        Text(
                            text = "My birthday",
                            fontSize = 18.sp,
                            color = Color(0xFFEEEEEE),
                            fontWeight = FontWeight.Bold
                        )
                        IconWithText(
                            resId = EMDrawable.em_ic_time,
                            text = "8 PM",
                            modifier = Modifier.padding(top = 8.dp),
                        )
                        if (needsOwner) {
                            ImageWithText(
                                modifier = Modifier.padding(top = 6.dp),
                                resId = EMDrawable.test,
                                text = "Teonamaila20"
                            )
                        }
                    }
                }
            }
        }
    }
}
