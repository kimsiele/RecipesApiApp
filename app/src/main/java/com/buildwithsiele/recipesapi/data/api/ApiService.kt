package com.buildwithsiele.recipesapi.data.api

import com.buildwithsiele.recipesapi.data.model.Recipe
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://hf-android-app.s3-eu-west-1.amazonaws.com/android-test/"

private val logger = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BASIC
}
private val client = OkHttpClient.Builder()
    .addInterceptor(logger)
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .client(client)
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @GET("recipes.json")
    suspend fun getRecipes(): List<Recipe>
}

object RecipeApi {
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}