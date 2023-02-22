package com.thetrusttech.getacarparts.ui.prayertime.model

data class Date(
    val gregorian: Gregorian,
    val hijri: Hijri,
    val readable: String,
    val timestamp: String
)