package com.buildwithsiele.recipesapi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.buildwithsiele.recipesapi.R
import com.buildwithsiele.recipesapi.data.model.Recipe
import com.squareup.picasso.Picasso

class RecipeAdapter(private val onItemClicked:OnItemClicked):RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {
    var recipeList = listOf<Recipe>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {

        return RecipeViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.recipe_item,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipeList[position]
        holder.recipeName.text = recipe.name
        Picasso.get().load(recipe.image).into(holder.recipeImage)
        holder.itemView.setOnClickListener {
            onItemClicked.onClick(recipe)
        }
    }

    override fun getItemCount(): Int= recipeList.size

    class RecipeViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val recipeImage: ImageView = itemView.findViewById(R.id.recipeImageView)
        val recipeName:TextView = itemView.findViewById(R.id.recipeName)

    }
    class OnItemClicked(val clickListener:(recipe:Recipe)->Unit){
        fun onClick(recipe:Recipe) =clickListener(recipe)
    }
}