package com.stauffer.recipe.model;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by jt on 6/17/17.
 */
public class CategoryTest {

    Category category;

    @Before
    public void setUp(){
        category = new Category();
    }

    @Test
    public void getId() throws Exception {
    	System.out.println("In getId test");
        Long idValue = 4L;

        category.setId(idValue);

        assertEquals(idValue, category.getId());
    }

    @Test
    public void getDescription() throws Exception {
       	System.out.println("In getDescription test");
        String descValue = "This is a test";

        category.setDescription(descValue);
        assertEquals(descValue, category.getDescription());
    }

    @Test
    public void getRecipes() throws Exception {
       	System.out.println("In getRecipes test");
//       	Set<Recipes> recipes = new HashMap<>();
//       	Recipe recipe = new Recipe();
//       	category.setRecipes(recipes);
//
//        category.setDescription(descValue);
//        assertEquals(descValue, category.getDescription());
    }

}
