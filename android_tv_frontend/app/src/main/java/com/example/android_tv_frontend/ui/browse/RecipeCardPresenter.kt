package com.example.android_tv_frontend.ui.browse

import android.view.View
import android.view.ViewGroup
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.example.android_tv_frontend.R
import com.example.android_tv_frontend.model.Recipe

/**
 * Presenter displaying Recipe objects in ImageCardViews.
 */
class RecipeCardPresenter : Presenter() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val cardView = ImageCardView(parent.context).apply {
            isFocusable = true
            isFocusableInTouchMode = true
            setBackgroundColor(resources.getColor(R.color.tv_card_background, null))
        }
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        val recipe = item as Recipe
        val cardView = viewHolder.view as ImageCardView

        cardView.titleText = recipe.title
        cardView.contentText = recipe.description
        cardView.setMainImageDimensions(313, 176)

        Glide.with(cardView.context)
            .load(recipe.imageUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_fallback)
            .into(cardView.mainImageView)
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {
        val cardView = viewHolder.view as ImageCardView
        // Clear image to free memory
        cardView.mainImage = null
    }
}
