package com.thetrusttech.getacarparts.ui.quran

data class SurahList(
    var surah: List<ListItem> = listOf(),
    var juz: List<ListItem> = listOf(),
) {
    class ListItem(
        var id: Int,
        var name: String,
        var name_ar: String,
        val description: String,
        val total_verses: String,
        var viewType: Int
    )
}
