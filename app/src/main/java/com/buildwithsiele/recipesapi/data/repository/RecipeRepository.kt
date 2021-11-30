package com.buildwithsiele.recipesapi.data.repository

import com.buildwithsiele.recipesapi.data.api.RecipeApi
import com.buildwithsiele.recipesapi.data.database.RecipeDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipeRepository(private val dataSource:RecipeDao) {
 suspend fun refreshRecipes(){
     withContext(Dispatchers.IO){
         val recipeList = RecipeApi.apiService.getRecipes()
         for (recipe in recipeList){
             dataSource.insertRecipe(recipe)
         }
     }
 }
    val recipes = dataSource.getAllRecipes()

    suspend fun getRecipe(recipeId:String){
        val recipe = dataSource.getRecipe(recipeId)
    }

}