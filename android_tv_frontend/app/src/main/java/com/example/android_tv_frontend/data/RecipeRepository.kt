package com.example.android_tv_frontend.data

import com.example.android_tv_frontend.model.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.util.UUID

/**
 * PUBLIC_INTERFACE
 * Mock implementation that pretends to fetch data from the `recipe_database` dependency.
 * Replace with Retrofit implementation once the backend API becomes available.
 */
class RecipeRepository {

    private val mockCategories = listOf("Breakfast", "Lunch", "Dinner", "Dessert")

    // Generates ten recipes per category with dummy images.
    private val mockRecipes: List<Recipe> = mockCategories.flatMap { category ->
        (1..10).map { index ->
            Recipe(
                id = UUID.randomUUID().toString(),
                title = "$category Recipe $index",
                description = "Delicious $category dish number $index prepared with love.",
                imageUrl = "https://picsum.photos/seed/${category}_${index}/600/400",
                category = category
            )
        }
    }

    suspend fun getCategories(): List<String> =
        withContext(Dispatchers.IO) {
            delay(150) // simulate network
            mockCategories
        }

    suspend fun getRecipesForCategory(category: String): List<Recipe> =
        withContext(Dispatchers.IO) {
            delay(200)
            mockRecipes.filter { it.category == category }
        }

    suspend fun search(query: String): List<Recipe> =
        withContext(Dispatchers.IO) {
            delay(250)
            mockRecipes.filter { it.title.contains(query, ignoreCase = true) }
        }
}
