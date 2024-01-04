package com.eventmaster.features.authentication.impl.presentation.login.main.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eventmaster.core.presentation.base.ui.EMScreenBase
import com.eventmaster.core.presentation.components.or.OrComposable
import com.eventmaster.core.presentation.components.textfield.regular.EMRegularTextField
import com.eventmaster.features.authentication.impl.presentation.login.main.vm.EMLogInVm
import org.koin.androidx.compose.get

@Composable
fun EMLogIn(vm: EMLogInVm = get()) {
    EMScreenBase(
        vm = vm,
        showBackButton = false,
        headerText = "Log in"
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxHeight()
        ) {
            EMRegularTextField(
                hint = "Email",
                modifier = Modifier.padding(top = 88.dp)
            )
            EMRegularTextField(
                hint = "Password",
                modifier = Modifier.padding(top = 20.dp)
            )
            OrComposable(
                modifier = Modifier
                    .padding(top = 43.dp)
                    .padding(horizontal = 5.dp)
            )

            Button(
                onClick = {
                },
                shape = RoundedCornerShape(size = 15.dp),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color.Transparent,
                ),
                border = BorderStroke(width = 1.dp, color = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 33.dp)
                    .height(50.dp)
            ) {
                Text(
                    text = "Log In with Google",
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
                    .padding(top = 20.dp)
                    .height(50.dp)

            ) {
                Text(
                    text = "Log In with Facebook",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(size = 15.dp),
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color(0xFF8F5FEA),
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 50.dp)
                        .height(50.dp)
                ) {
                    Text(
                        text = "Continue",
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}