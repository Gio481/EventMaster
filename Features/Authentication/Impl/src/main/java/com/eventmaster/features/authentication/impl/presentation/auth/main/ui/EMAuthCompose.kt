package com.eventmaster.features.authentication.impl.presentation.auth.main.ui

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eventmaster.core.presentation.base.ui.EMScreenBase
import com.eventmaster.core.presentation.components.or.OrComposable
import com.eventmaster.core.presentation.util.EMDrawable
import com.eventmaster.core.presentation.util.changeStatusBarColor
import com.eventmaster.features.authentication.impl.presentation.auth.main.vm.EMAuthVm
import org.koin.androidx.compose.koinViewModel

@Composable
fun EMAuth(vm: EMAuthVm = koinViewModel()) {
    val context = LocalContext.current

    BackHandler {
        (context as ComponentActivity).moveTaskToBack(true)
    }

    LaunchedEffect(key1 = Unit) {
        context.changeStatusBarColor(Color(0xFF140D28))
    }
    EMScreenBase(vm = vm, showHeader = false) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color(0xFF140D28))
        ) {
            Image(
                painter = painterResource(id = EMDrawable.em_splash_logo),
                modifier = Modifier
                    .padding(top = 122.dp)
                    .size(170.dp)
                    .align(Alignment.TopCenter),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 64.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(size = 15.dp),
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color.Transparent,
                    ),
                    border = BorderStroke(width = 1.dp, color = Color.White),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp, start = 16.dp, end = 16.dp)
                        .height(50.dp)
                ) {
                    Text(
                        text = "Continue with Google",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }

                Button(
                    onClick = {
                    },
                    border = BorderStroke(width = 1.dp, color = Color.White),
                    shape = RoundedCornerShape(size = 15.dp),
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color.Transparent,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, start = 16.dp, end = 16.dp)
                        .height(50.dp)

                ) {
                    Text(
                        text = "Continue with Facebook",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
                OrComposable(Modifier.padding(top = 24.dp, start = 16.5.dp, end = 16.5.dp))
                Button(
                    onClick = { vm.navigateToSignUp() },
                    shape = RoundedCornerShape(size = 15.dp),
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color(0xFF5D6DFF),
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp, start = 16.dp, end = 16.dp)
                        .height(50.dp)
                ) {
                    Text(
                        text = "მომხმარებლის შექმნა",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(com.eventmaster.core.presentation.R.font.em_poppins_medium))
                    )
                }
                MultiStyleText(vm)
            }
        }
    }
}

@Composable
private fun MultiStyleText(vm: EMAuthVm) {
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.White)) {
                append("Already have an account? ")
            }
            withStyle(style = SpanStyle(color = Color(0xFF5D6DFF), fontWeight = FontWeight.Bold)) {
                append("Log In")
            }
        },
        fontSize = 16.sp,
        modifier = Modifier
            .padding(top = 24.dp)
            .clickable {
                vm.navigateToLogIn()
            },
        textAlign = TextAlign.Center
    )
}