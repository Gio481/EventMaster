package com.eventmaster.features.authentication.api.events

import com.eventmaster.core.api.event.EMNavigationEvent

sealed class EMAuthenticationEvents : EMNavigationEvent {
    object SignUp : EMAuthenticationEvents()
    object LogIn : EMAuthenticationEvents()
}