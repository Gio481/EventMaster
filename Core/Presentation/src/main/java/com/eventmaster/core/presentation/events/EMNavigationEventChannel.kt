package com.eventmaster.core.presentation.events

import com.eventmaster.core.api.event.EMNavigationEvent
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.receiveAsFlow
import org.koin.dsl.module
import kotlin.coroutines.coroutineContext


class EMNavigationEventChannel {


    private val hashMap = HashMap<Any, Channel<EMNavigationEvent>>()

//    internal val eventFlow = eventChannel.receiveAsFlow()

    @DelicateCoroutinesApi
    suspend fun triggerNavigationEvent(
        event: EMNavigationEvent,
        channel: Channel<EMNavigationEvent>
    ) {
        if (channel.isClosedForSend.not()) {
            hashMap[event::class.java] = channel
            channel.send(event)
            channel.close()
        }
    }

    suspend  fun observe(event: EMNavigationEvent, callback: () -> Unit) {
        val eventFlow = hashMap[event::class.java]?.receiveAsFlow()
        eventFlow?.let {
            it.collectLatest {
                callback.invoke()
            }
        }
    }
}


val navigationEventModule = module {
    factory { EMNavigationEventChannel() }
}