package com.stauffer.recipe.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stauffer.recipe.commands.RecipeCommand;
import com.stauffer.recipe.model.Recipe;
import com.stauffer.recipe.repositories.RecipeRepository;
import com.stauffer.recipe.converters.RecipeCommandToRecipe;
import com.stauffer.recipe.converters.RecipeToRecipeCommand;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService{
	
	private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;	


    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }


	@Override
	public Set<Recipe> getRecipes() {
		log.debug("I'm in the service now.");
		Set<Recipe> recipeSet = new HashSet<>();
		recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
		return recipeSet;
	}
	
	@Override
	public Recipe findById(long l) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(l);
		if (!recipeOptional.isPresent()) {
			throw new RuntimeException("Recipe Not Found!");
		}
		return recipeOptional.get();
	}

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long l) {
        return recipeToRecipeCommand.convert(findById(l));
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved RecipeId:" + savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }


    @Override
    public void deleteById(Long idToDelete) {
        recipeRepository.deleteById(idToDelete);
    }


}
