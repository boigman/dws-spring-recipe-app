package com.stauffer.recipe.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.stauffer.recipe.model.Category;
import com.stauffer.recipe.model.Difficulty;
import com.stauffer.recipe.model.Ingredient;
import com.stauffer.recipe.model.Notes;
import com.stauffer.recipe.model.Recipe;
import com.stauffer.recipe.model.UnitOfMeasure;
import com.stauffer.recipe.repositories.CategoryRepository;
import com.stauffer.recipe.repositories.RecipeRepository;
import com.stauffer.recipe.repositories.UnitOfMeasureRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent>{
	
	private final CategoryRepository catRepository;
	private final RecipeRepository recipeRepository;
	private final UnitOfMeasureRepository uomRepository;

	
	

	public RecipeBootstrap(CategoryRepository catRepository, RecipeRepository recipeRepository,
			UnitOfMeasureRepository uomRepository) {
		this.catRepository = catRepository;
		this.recipeRepository = recipeRepository;
		this.uomRepository = uomRepository;
	}



	@Override
//    @Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
        recipeRepository.saveAll(getRecipes());
        log.debug("Loading Bootstrap Data");
		
	}
	
	private List<Recipe> getRecipes() {
		List<Recipe> recipes = new ArrayList<>(2);
		
		Optional<UnitOfMeasure> dashUomOptional = uomRepository.findByDescription("Dash");
		if(!dashUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM not Found");
		}
		Optional<UnitOfMeasure> eachUomOptional = uomRepository.findByDescription("Each");
		if(!eachUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM not Found");
		}
		Optional<UnitOfMeasure> cupUomOptional = uomRepository.findByDescription("Cup");
		if(!cupUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM not Found");
		}
		Optional<UnitOfMeasure> pintUomOptional = uomRepository.findByDescription("Pint");
		if(!pintUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM not Found");
		}
		Optional<UnitOfMeasure> tspUomOptional = uomRepository.findByDescription("Teaspoon");
		if(!tspUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM not Found");
		}
		Optional<UnitOfMeasure> tbspUomOptional = uomRepository.findByDescription("Tablespoon");
		if(!tbspUomOptional.isPresent()) {
			throw new RuntimeException("Expected UOM not Found");
		}
		
		UnitOfMeasure dashUom = dashUomOptional.get();
		UnitOfMeasure eachUom = eachUomOptional.get();
		UnitOfMeasure cupUom = cupUomOptional.get();
		UnitOfMeasure pintUom = pintUomOptional.get();
		UnitOfMeasure tspUom = tspUomOptional.get();
		UnitOfMeasure tbspUom = tbspUomOptional.get();
		
		Optional<Category> mexCatOptional = catRepository.findByDescription("Mexican");
		if(!mexCatOptional.isPresent()) {
			throw new RuntimeException("Expected Category not Found");
		}
		Optional<Category> amerCatOptional = catRepository.findByDescription("American");
		if(!amerCatOptional.isPresent()) {
			throw new RuntimeException("Expected Category not Found");
		}
		
		Category amerCat = amerCatOptional.get();
		Category mexCat = mexCatOptional.get();
		
		Recipe guacRecipe = new Recipe();
		guacRecipe.setDescription("Perfect Guacamole");
		guacRecipe.setPrepTime(10);
		guacRecipe.setServings(4);
		guacRecipe.setCookTime(0);
		guacRecipe.setDifficulty(Difficulty.EASY);
		guacRecipe.setSource("SimplyRecipes.com");
		guacRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
		guacRecipe.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n\n"+
				"2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n\n"+
				"3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n\n"+
				"Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n\n"+
				"Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n\n"+
				"Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n\n"+
				"4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n"
);
		Notes guacNotes = new Notes();
		guacNotes.setRecipeNotes("The trick to making perfect guacamole is using ripe avocados that are just the right amount of ripeness. Not ripe enough and the avocado will be hard and tasteless. Too ripe and the taste will be off.\n\n"+
				"Check for ripeness by gently pressing the outside of the avocado. If there is no give, the avocado is not ripe yet and will not taste good. If there is a little give, the avocado is ripe. If there is a lot of give, the avocado may be past ripe and not good. In this case, taste test first before using.\n");
		guacRecipe.setNotes(guacNotes);
        guacRecipe.getCategories().add(amerCat);
        guacRecipe.getCategories().add(mexCat);
        guacRecipe.addIngredient(new Ingredient("Ripe avocados", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(.5), tspUom));
        guacRecipe.addIngredient(new Ingredient("Fresh lime juice or lemon juice", new BigDecimal(2), tbspUom));
        guacRecipe.addIngredient(new Ingredient("Minced red onion or thinly sliced green onion", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Cilantro", new BigDecimal(2), tbspUom));
        guacRecipe.addIngredient(new Ingredient("Freshly grated black pepper", new BigDecimal(2), dashUom));
        guacRecipe.addIngredient(new Ingredient("Ripe tomato, seeds and pulp removed, chopped", new BigDecimal(.5), eachUom));
        recipes.add(guacRecipe);

		Recipe tacoRecipe = new Recipe();
		tacoRecipe.setDescription("Spicy Grilled Chicken Taco");
		tacoRecipe.setPrepTime(20);
		tacoRecipe.setServings(4);
		tacoRecipe.setCookTime(9);
		tacoRecipe.setDifficulty(Difficulty.MEDIUM);
		tacoRecipe.setSource("SimplyRecipes.com");
		tacoRecipe.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos");
		tacoRecipe.setDirections("First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n\n"+
				"Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n\n"+
				"The ancho chiles I use in the marinade are named for their wide shape. They are large, have a deep reddish brown color when dried, and are mild in flavor with just a hint of heat. You can find ancho chile powder at any markets that sell Mexican ingredients, or online.\n\n"+
				"I like to put all the toppings in little bowls on a big platter at the center of the table: avocados, radishes, tomatoes, red onions, wedges of lime, and a sour cream sauce. I add arugula, as well – this green isn’t traditional for tacos, but we always seem to have some in the fridge and I think it adds a nice green crunch to the tacos.\n\n"+
				"Everyone can grab a warm tortilla from the pile and make their own tacos just they way they like them.\n\n");
		Notes tacoNotes = new Notes();
		tacoNotes.setRecipeNotes("You could also easily double or even triple this recipe for a larger party. A taco and a cold beer on a warm day? Now that’s living!");
		tacoRecipe.setNotes(tacoNotes);
        tacoRecipe.getCategories().add(amerCat);
        tacoRecipe.getCategories().add(mexCat);
        tacoRecipe.addIngredient(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tbspUom));
        tacoRecipe.addIngredient(new Ingredient("Dried Oregano", new BigDecimal(1), tspUom));
        tacoRecipe.addIngredient(new Ingredient("Dried Cumin", new BigDecimal(1), tspUom));
        tacoRecipe.addIngredient(new Ingredient("Sugar", new BigDecimal(1), tspUom));
        tacoRecipe.addIngredient(new Ingredient("Salt", new BigDecimal(".5"), tspUom));
        tacoRecipe.addIngredient(new Ingredient("Clove of Garlic, Chopped", new BigDecimal(1), eachUom));
        tacoRecipe.addIngredient(new Ingredient("finely grated orange zest", new BigDecimal(1), tbspUom));
        tacoRecipe.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tbspUom));
        tacoRecipe.addIngredient(new Ingredient("Olive Oil", new BigDecimal(2), tbspUom));
        tacoRecipe.addIngredient(new Ingredient("boneless chicken thighs", new BigDecimal(4), tbspUom));
        tacoRecipe.addIngredient(new Ingredient("small corn tortillas", new BigDecimal(8), eachUom));
        tacoRecipe.addIngredient(new Ingredient("packed baby arugula", new BigDecimal(3), cupUom));
        tacoRecipe.addIngredient(new Ingredient("medium ripe avocados, slic", new BigDecimal(2), eachUom));
        tacoRecipe.addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4), eachUom));
        tacoRecipe.addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal(".5"), pintUom));
        tacoRecipe.addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"), eachUom));
        tacoRecipe.addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), eachUom));
        tacoRecipe.addIngredient(new Ingredient("Sour cream thinned with 1/4 cup milk", new BigDecimal(4.666), cupUom));
        tacoRecipe.addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal(4), eachUom));
//        System.out.println("Guac Notes: "+tacoRecipe.getNotes().getRecipeNotes());
        recipes.add(tacoRecipe);
		
		
		return recipes;
	}

}
