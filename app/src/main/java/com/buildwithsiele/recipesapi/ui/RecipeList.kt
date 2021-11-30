package com.buildwithsiele.recipesapi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.buildwithsiele.recipesapi.R
import com.buildwithsiele.recipesapi.adapters.RecipeAdapter
import com.buildwithsiele.recipesapi.data.database.RecipeDatabase
import com.buildwithsiele.recipesapi.databinding.RecipeListFragmentBinding
import com.buildwithsiele.recipesapi.viewmodels.RecipeListViewModel
import com.buildwithsiele.recipesapi.viewmodels.RepositoryViewModelFactory

class RecipeList : Fragment() {

    private lateinit var viewModel: RecipeListViewModel
    private lateinit var binding:RecipeListFragmentBinding
    private lateinit var adapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.recipe_list_fragment, container, false)
        val dataSource = RecipeDatabase.getInstance(requireContext()).recipeDao
        val viewModelFactory = RepositoryViewModelFactory(dataSource)
        viewModel = ViewModelProvider(this,viewModelFactory)[RecipeListViewModel::class.java]

        adapter = RecipeAdapter(RecipeAdapter.OnItemClicked{recipe->
            findNavController().navigate(
                RecipeListDirections.actionRecipeListToRecipeDetails(
                    recipe.id,
                    recipe.name,
                    recipe.image,
                    recipe.headline,
                    recipe.description,
                    recipe.calories,
                    recipe.fats,
                    recipe.proteins,
                    recipe.carbos,
                    recipe.difficulty,
                    recipe.time
                )
            )

        })
        binding.recipeRecyclerview.adapter = adapter
        viewModel.recipesList.observe(viewLifecycleOwner,{
            if (it.isNotEmpty()){
                binding.loadingData.visibility = View.GONE
            adapter.recipeList = it
            }else  binding.loadingData.visibility = View.VISIBLE
        })

        return binding.root
    }



}