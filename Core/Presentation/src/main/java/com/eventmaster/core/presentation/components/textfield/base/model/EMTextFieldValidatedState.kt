package com.eventmaster.core.presentation.components.textfield.base.model

import android.os.Parcelable
import com.eventmaster.core.presentation.R
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class EMTextFieldValidatedState(
    open val text: String? = null,
    open val iconRes: Int? = null,
    open val colorTintLongRes: Long? = null
) : Parcelable {

    @Parcelize
    data class Success(
        override val colorTintLongRes: Long = 0xFF26BF7F,
        override val iconRes: Int = R.drawable.em_checkmark,
        override val text: String = "All done"
    ) : EMTextFieldValidatedState(text, iconRes, colorTintLongRes), Parcelable

    data class Error(
        override val colorTintLongRes: Long = 0xFFEA4335,
        override val iconRes: Int = R.drawable.em_ic_error_circle_filled,
        override val text: String,
        val spannableClickAction: (() -> Unit)? = null
    ) : EMTextFieldValidatedState(text, iconRes, colorTintLongRes)

    @Parcelize
    data class Loading(
        override val text: String = "Validating",
        override val colorTintLongRes: Long = 0xFFB4B4B4
    ) : EMTextFieldValidatedState(text), Parcelable

    object Default : EMTextFieldValidatedState()
}