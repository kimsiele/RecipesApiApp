package com.buildwithsiele.recipesapi.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.buildwithsiele.recipesapi.data.database.RecipeDao
import com.buildwithsiele.recipesapi.data.model.Recipe
import com.buildwithsiele.recipesapi.data.repository.RecipeRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class RecipeDetailsViewModel (dataSource:RecipeDao): ViewModel() {
    private val recipeRepository = RecipeRepository(dataSource)
    fun getRecipe(id:String):Recipe{
        viewModelScope.launch {
            recipeRepository.getRecipe(id)
        }
        return getRecipe(id)
    }
}
class RecipeDetailsViewModelFactory(private val dataSource: RecipeDao): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipeDetailsViewModel::class.java)){
            return RecipeDetailsViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}