package com.buildwithsiele.recipesapi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgument
import com.buildwithsiele.recipesapi.R
import com.buildwithsiele.recipesapi.data.database.RecipeDatabase
import com.buildwithsiele.recipesapi.databinding.RecipeDetailsFragmentBinding
import com.buildwithsiele.recipesapi.databinding.RecipeListFragmentBinding
import com.buildwithsiele.recipesapi.viewmodels.RecipeDetailsViewModel
import com.buildwithsiele.recipesapi.viewmodels.RecipeDetailsViewModelFactory
import com.squareup.picasso.Picasso

class RecipeDetails : Fragment() {

    private lateinit var viewModel: RecipeDetailsViewModel
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
        val dataSource = RecipeDatabase.getInstance(requireContext()).recipeDao
        val viewModelFactory = RecipeDetailsViewModelFactory(dataSource)
        viewModel= ViewModelProvider(this,viewModelFactory)[RecipeDetailsViewModel::class.java]

        getCurrentRecipe()

        /* Picasso.get().load(argument.getString("recipeImageUrl")).into(binding.currentRecipeImage)
         binding.currentRecipeHeadline.text = argument.getString("recipeHeadline")
         binding.currentRecipeName.text = argument.getString("recipeName")
         binding.currentRecipeDescription.text = argument.getString("recipeDescription")*/

        return binding.root
    }

    private fun getCurrentRecipe() {
        //get Recipe by id and update the fields accordingly
        // val recipe = viewModel.getRecipe(argument.getString("recipeId")!!)
        Picasso.get().load(argument.getString("recipeImageUrl")).into(binding.currentRecipeImage)
        binding.currentRecipeHeadline.text = argument.getString("recipeHeadline")
        binding.currentRecipeName.text = argument.getString("recipeName")
        binding.currentRecipeDescription.text = argument.getString("recipeDescription")
        binding.calorieValue.text = argument.getString("recipeCalories")
        binding.carbosValue.text = argument.getString("recipeCarbos")
        binding.difficultyValue.text = argument.getString("recipeDifficulty")
        binding.proteinsValue.text = argument.getString("recipeProteins")
        binding.fatsValue.text = argument.getString("recipeFats")
        binding.timeValue.text = argument.getString("recipeTime")
    }


}