package com.eventmaster.core.presentation.base.ui.action


sealed class EMActionButton(val type: Type) {
    abstract val text: String

    data class Single(override val text: String) : EMActionButton(Type.Single)

    data class SingleWithInfo(
        override val text: String,
        val infoIcon: Int,
        val infoText: String
    ) : EMActionButton(Type.SingleWithInfo)

    data class SingleWithKeyValue(
        override val text: String,
        val keyValueData: List<EMKeyValueData>
    ) : EMActionButton(Type.SingleWithTitle) {

        data class EMKeyValueData(
            val key: String,
            val value: String
        )
    }

    enum class Type {
        Single,
        SingleWithInfo,
        SingleWithTitle,
    }
}
