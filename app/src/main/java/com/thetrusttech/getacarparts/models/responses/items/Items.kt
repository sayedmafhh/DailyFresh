package com.thetrusttech.getacarparts.models.responses.items

data class Items(
    val count: Int?,
    val recipes: List<Recipe>?
) {
    data class Recipe(
        val id: String?,
        val imageUrl: String?,
        val publishedId: String?,
        val publisher: String?,
        val socialUrl: Double?,
        val sourceUrl: String?,
        val title: String?
    )
}