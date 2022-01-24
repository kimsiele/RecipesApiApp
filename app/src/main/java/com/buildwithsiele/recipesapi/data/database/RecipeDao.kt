package com.buildwithsiele.recipesapi.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.buildwithsiele.recipesapi.data.model.Recipe

@Dao
interface RecipeDao {
    @Insert
    suspend fun insertRecipe(recipe: Recipe)

    @Query("SELECT * FROM recipes_database_table")
    fun getAllRecipes():LiveData<List<Recipe>>

    @Query("SELECT * FROM recipes_database_table WHERE id=:recipeId")
    fun getRecipe(recipeId:String):Recipe
}