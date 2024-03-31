package com.eventmaster.core.presentation.base.ui.action


sealed class EMActionButton {
    abstract val text: String

    data class Single(override val text: String) : EMActionButton()

    data class SingleWithInfo(
        override val text: String,
        val infoIcon: Int,
        val infoText: String
    ) : EMActionButton()

    data class SingleWithKeyValue(
        override val text: String,
        val keyValueData: List<EMKeyValueData>
    ) : EMActionButton() {

        data class EMKeyValueData(
            val key: String,
            val value: String
        )
    }

}
