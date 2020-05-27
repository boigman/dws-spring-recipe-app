package com.stauffer.recipe.controllers;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stauffer.recipe.services.RecipeService;

@Controller
@ComponentScan(basePackages = "com.stauffer.recipe") 
public class IndexController {
	
	private final RecipeService recipeService;


	public IndexController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}


	@RequestMapping({"","/","index"})
	public String getIndexPage(Model model) {
		System.out.println("Welcome from Index Controller!");
		model.addAttribute("recipes",recipeService.getRecipes());
		return "index";
	}
}
