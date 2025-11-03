package com.example.android_tv_frontend.model

/**
 * PUBLIC_INTERFACE
 * Simple data class representing a recipe displayed in the TV UI.
 */
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val videoUrl: String? = null,
    val category: String
) : Parcelable
