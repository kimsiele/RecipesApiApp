package com.buildwithsiele.recipesapi.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.buildwithsiele.recipesapi.data.database.RecipeDao
import com.buildwithsiele.recipesapi.data.repository.RecipeRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class RecipeListViewModel(dataSource: RecipeDao): ViewModel() {
    private val recipeRepository = RecipeRepository(dataSource)
    init {
        refreshRecipesFromRepository()
    }

    private fun refreshRecipesFromRepository() {
        viewModelScope.launch {
            try {
                recipeRepository.refreshRecipes()
            }catch (e:Exception){
                Log.d("viewModel", "Error: ${e.message}")
            }

        }
    }
    val recipesList = recipeRepository.recipes
}
class RepositoryViewModelFactory(private val dataSource: RecipeDao):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipeListViewModel::class.java)){
            return RecipeListViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}