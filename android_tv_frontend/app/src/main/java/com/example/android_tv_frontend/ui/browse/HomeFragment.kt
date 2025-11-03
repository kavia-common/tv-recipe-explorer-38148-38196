package com.example.android_tv_frontend.ui.browse

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.android_tv_frontend.R
import com.example.android_tv_frontend.data.RecipeRepository
import com.example.android_tv_frontend.model.Recipe
import kotlinx.coroutines.launch

/**
 * Fragment displaying horizontal carousels of recipe rows grouped by category.
 */
class HomeFragment : BrowseSupportFragment() {

    private val viewModel: HomeViewModel by viewModels { HomeViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = getString(R.string.app_name)
        badgeDrawable = requireContext().getDrawable(R.drawable.ic_launcher)

        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true

        setBrandColorResources(R.color.tv_primary)
        setupUI()
        observeData()
    }

    private fun setupUI() {
        adapter = ArrayObjectAdapter(ListRowPresenter().apply {
            shadowEnabled = true
            setNumRows(1)
        })

        onItemViewClickedListener =
            OnItemViewClickedListener { _, item, _, _ ->
                if (item is Recipe) {
                    parentFragmentManager.beginTransaction()
                        .replace(
                            R.id.fragment_container,
                            com.example.android_tv_frontend.ui.detail.RecipeDetailFragment.newInstance(item)
                        )
                        .addToBackStack(null)
                        .commit()
                }
            }
    }

    private fun observeData() {
        viewModel.load()
        viewModel.rows.observe(this) { rows ->
            adapter = ArrayObjectAdapter(ListRowPresenter()).apply {
                rows.forEach { add(it) }
            }
        }
    }

    class HomeViewModel(private val repository: RecipeRepository) : ViewModel() {
        val rows = androidx.lifecycle.MutableLiveData<List<ListRow>>()

        fun load() {
            viewModelScope.launch {
                val listRows = repository.getCategories().map { category ->
                    val recipes = repository.getRecipesForCategory(category)
                    val cardPresenter = RecipeCardPresenter()
                    val listAdapter = ArrayObjectAdapter(cardPresenter).apply {
                        recipes.forEach { add(it) }
                    }
                    ListRow(HeaderItem(category), listAdapter)
                }
                rows.postValue(listRows)
            }
        }

        object Factory : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(RecipeRepository()) as T
            }
        }
    }
}
