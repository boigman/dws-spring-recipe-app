package com.stauffer.recipe.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stauffer.recipe.model.Category;
import com.stauffer.recipe.model.UnitOfMeasurement;
import com.stauffer.recipe.services.RecipeService;

@Controller
public class IndexController {
	
	private final RecipeService recipeService;


	public IndexController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}


	@RequestMapping({"","/","index"})
	public String getIndexPage(Model model) {
		
		model.addAttribute("recipes",recipeService.getRecipes());
		return "index";
	}
}
