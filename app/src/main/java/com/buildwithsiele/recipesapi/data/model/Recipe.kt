package com.buildwithsiele.recipesapi.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes_database_table")
data class Recipe(
    @PrimaryKey val id: String,
    @ColumnInfo val name: String,
    @ColumnInfo val image: String,
    @ColumnInfo val difficulty: Int,
    @ColumnInfo val headline: String,
    @ColumnInfo val description: String,
    @ColumnInfo val calories: String,
    @ColumnInfo val carbos: String,
    @ColumnInfo val fats: String,
    @ColumnInfo val proteins: String,
    @ColumnInfo val thumb: String,
    @ColumnInfo val time: String
)