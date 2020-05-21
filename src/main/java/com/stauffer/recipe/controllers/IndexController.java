package com.stauffer.recipe.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stauffer.recipe.model.Category;
import com.stauffer.recipe.model.UnitOfMeasurement;
import com.stauffer.recipe.repositories.CategoryRepository;
import com.stauffer.recipe.repositories.UnitOfMeasurementRepository;

@Controller
public class IndexController {
	
	private CategoryRepository categoryRepository;
	private UnitOfMeasurementRepository unitOfMeasurementRepository;
	

	public IndexController(CategoryRepository categoryRepository,
			UnitOfMeasurementRepository unitOfMeasurementRepository) {
		this.categoryRepository = categoryRepository;
		this.unitOfMeasurementRepository = unitOfMeasurementRepository;
	}


	@RequestMapping({"","/","index"})
	public String getIndexPage() {
		
		Optional<Category> optCategory = categoryRepository.findByDescription("Side Dishes");
		Optional<UnitOfMeasurement> optUOM = unitOfMeasurementRepository.findByDescription("Teaspoon");
		System.out.println("Category ID is: "+optCategory.get().getId());
		System.out.println("UOM ID is: "+optUOM.get().getId());
		return "index";
	}
}
