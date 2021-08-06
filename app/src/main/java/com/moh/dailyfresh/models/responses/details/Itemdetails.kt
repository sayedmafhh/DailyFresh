package com.moh.dailyfresh.models.responses.details

data class Itemdetails(
    val recipe: Recipe
) {

    data class Recipe(
        val id: String,
        val imageUrl: String,
        val ingredients: List<String>,
        val publishedId: String,
        val publisher: String,
        val socialUrl: Double,
        val sourceUrl: String,
        val title: String
    )
}