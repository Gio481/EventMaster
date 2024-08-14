package com.eventmaster.core.presentation.components.datepicker.model

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.util.Calendar

@Parcelize
data class EMDatePickerSelectedItem(
    val firstColum: String? = null,
    val secondColum: String? = null,
    val thirdColum: String? = null
) : Parcelable {

    @IgnoredOnParcel
    val fullDate = "$firstColum $secondColum $thirdColum"

    @IgnoredOnParcel
    val dayMonthDate = "$firstColum $secondColum ${Calendar.getInstance().get(Calendar.YEAR)}"
}