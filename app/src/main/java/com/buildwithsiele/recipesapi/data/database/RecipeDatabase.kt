package com.buildwithsiele.recipesapi.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.buildwithsiele.recipesapi.data.model.Recipe

@Database(entities = [Recipe::class],version = 1)
abstract class RecipeDatabase :RoomDatabase(){
    abstract val recipeDao:RecipeDao

    companion object{
        @Volatile
        private var INSTANCE:RecipeDatabase? = null

        fun getInstance(context: Context):RecipeDatabase{
            synchronized(this){
                var instance = INSTANCE
                if (instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RecipeDatabase::class.java,
                        "recipes_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE= instance
                }
                return instance
            }
        }

    }
}