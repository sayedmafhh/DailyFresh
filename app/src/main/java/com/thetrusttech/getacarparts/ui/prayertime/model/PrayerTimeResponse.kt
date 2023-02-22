package com.thetrusttech.getacarparts.ui.prayertime.model

data class PrayerTimeResponse(
    val code: Int,
    val `data`: List<Data>,
    val status: String
)