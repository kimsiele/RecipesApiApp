package com.buildwithsiele.recipesapi.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.buildwithsiele.recipesapi.R
import com.buildwithsiele.recipesapi.databinding.RecipeDetailsFragmentBinding
import com.squareup.picasso.Picasso

class RecipeDetails : Fragment() {

    private lateinit var binding: RecipeDetailsFragmentBinding
    private lateinit var argument:Bundle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.recipe_details_fragment,
            container,
            false
        )
        argument = requireArguments()
        getCurrentRecipe()

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun getCurrentRecipe() {
        Picasso.get().load(argument.getString("recipeImageUrl")).into(binding.currentRecipeImage)
        binding.currentRecipeName.text =
            "${argument.getString("recipeName")}${argument.getString("recipeHeadline")}"
        binding.currentRecipeDescription.text = argument.getString("recipeDescription")
        binding.calorieValue.text = argument.getString("recipeCalories")
        binding.carbosValue.text = argument.getString("recipeCarbos")
        binding.difficultyValue.text = argument.getString("recipeDifficulty")
        binding.proteinsValue.text = argument.getString("recipeProteins")
        binding.fatsValue.text = argument.getString("recipeFats")
        val time = argument.getString("recipeTime")
        binding.timeValue.text = " ${time?.filter { it.isDigit() }} Minutes (PT)"
    }


}