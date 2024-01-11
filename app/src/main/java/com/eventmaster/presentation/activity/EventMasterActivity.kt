package com.eventmaster.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.eventmaster.extensions.initializeComponents
import com.eventmaster.presentation.navhost.AppNavHost
import com.eventmaster.presentation.theme.EventMasterTheme
import org.koin.androidx.compose.get

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            initializeComponents(rememberNavController())
            WindowCompat.setDecorFitsSystemWindows(window, false)
            EventMasterTheme {
                AppNavHost(
                    navController = get(),
                    graphBuilder = get(),
                )
            }
        }
    }
}


