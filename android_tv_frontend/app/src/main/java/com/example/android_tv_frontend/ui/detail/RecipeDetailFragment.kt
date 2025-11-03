package com.example.android_tv_frontend.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.android_tv_frontend.databinding.FragmentRecipeDetailBinding
import com.example.android_tv_frontend.model.Recipe

/**
 * Fragment displaying recipe details with rich imagery.
 */
class RecipeDetailFragment : Fragment() {

    private var _binding: FragmentRecipeDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recipe = requireArguments().getParcelable<Recipe>(ARG_RECIPE)!!

        binding.title.text = recipe.title
        binding.description.text = recipe.description

        Glide.with(this)
            .load(recipe.imageUrl)
            .centerCrop()
            .into(binding.image)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_RECIPE = "arg_recipe"

        fun newInstance(recipe: Recipe): RecipeDetailFragment =
            RecipeDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_RECIPE, recipe)
                }
            }
    }
}
