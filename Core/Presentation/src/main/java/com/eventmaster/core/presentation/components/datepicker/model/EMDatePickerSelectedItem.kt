package com.eventmaster.core.presentation.components.datepicker.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EMDatePickerSelectedItem(
    val firstColum: String? = null,
    val secondColum: String? = null,
    val thirdColum: String? = null
) : Parcelable