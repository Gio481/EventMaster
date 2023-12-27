package com.eventmaster.core.presentation.components.textfield.regular

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eventmaster.core.presentation.components.textfield.config.EMTextFieldConfig
import com.eventmaster.core.presentation.components.textfield.base.EMBaseTextField

@Composable
internal fun EMRegularTextField(config: EMTextFieldConfig.Regular) {
    Box(
        modifier = config.modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column {
            config.title?.let {
                Text(
                    text = it,
                    color = Color(0xFFEEEEEE),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            EMBaseTextField(
                modifier = Modifier.padding(top = if (config.title == null) 0.dp else 16.dp),
                hint = config.hint
            )
            config.description?.let {
                Text(
                    text = it,
                    color = Color(0xFFBFBFBF),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}
