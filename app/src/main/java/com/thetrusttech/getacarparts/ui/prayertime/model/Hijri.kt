package com.thetrusttech.getacarparts.ui.prayertime.model

data class Hijri(
    val date: String,
    val day: String,
    val designation: Designation,
    val format: String,
    val holidays: List<String>,
    val month: MonthX,
    val weekday: WeekdayX,
    val year: String
)