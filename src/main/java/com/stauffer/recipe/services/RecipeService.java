package com.stauffer.recipe.services;

import java.util.Set;

import com.stauffer.recipe.model.Recipe;
import com.stauffer.recipe.commands.RecipeCommand;

public interface RecipeService {
	Set<Recipe> getRecipes();
	

	Recipe findById(Long l);
	
    RecipeCommand findCommandById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);


    void deleteById(Long idToDelete);
}

