package com.stauffer.recipe.services;

import java.util.Set;

import com.stauffer.recipe.model.Recipe;

public interface RecipeService {
	Set<Recipe> getRecipes();
	

	Recipe findById(long l);
}

