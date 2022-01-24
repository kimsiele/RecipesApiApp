package com.buildwithsiele.recipesapi.data.repository

import com.buildwithsiele.recipesapi.data.api.RecipeApi
import com.buildwithsiele.recipesapi.data.database.RecipeDao

class RecipeRepository(private val dataSource:RecipeDao) {
 suspend fun refreshRecipes(){
         val recipeList = RecipeApi.apiService.getRecipes()
         for (recipe in recipeList){
             dataSource.insertRecipe(recipe)
         }
 }
    val recipes = dataSource.getAllRecipes()
}