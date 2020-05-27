package com.stauffer.recipe.repositories;

import org.springframework.data.repository.CrudRepository;

import com.stauffer.recipe.model.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long>{
	

}
