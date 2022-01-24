package com.buildwithsiele.recipesapi.data.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.buildwithsiele.recipesapi.data.model.Recipe
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class RecipeDatabaseTest{

    private lateinit var recipeDao: RecipeDao
    private lateinit var recipeDatabase: RecipeDatabase

    @Before
    fun createDb(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        recipeDatabase = Room.inMemoryDatabaseBuilder(
            context,RecipeDatabase::class.java).build()
        recipeDao = recipeDatabase.recipeDao
    }
    @After
    fun closeDb(){
        recipeDatabase.close()
    }
    @Test
    fun createAndRead(){
        val recipe =Recipe(
            "533143aaff604d567f8b4571",
            "recipe_name",
            "recipe_image_url",
            4,
        "recipe_headline",
            "recipe_description",
        "100 grams",
            "200 grams",
        "20 grams",
            "600 grams",
            "recipe_thumb",
            "Perfect_time"
        )
        runBlocking {
            recipeDao.insertRecipe(recipe)
            val recipes= recipeDao.getAllRecipes().value
            assertThat(recipes?.contains(recipe))
        }
    }

}