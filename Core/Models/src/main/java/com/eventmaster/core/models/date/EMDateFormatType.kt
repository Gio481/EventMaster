package com.eventmaster.core.models.date


enum class EMDateFormatType(val format: String, val parsedFormat: String) {
    FULL(format = "dd / MMM / yyyy", parsedFormat = "dd MMM yyy"),
    DAY_MONTH(format = "dd / MMM / yyyy", parsedFormat = "dd MMM yyy"),
}