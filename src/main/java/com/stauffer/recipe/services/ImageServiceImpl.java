package com.stauffer.recipe.services;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.stauffer.recipe.repositories.RecipeRepository;
import com.stauffer.recipe.model.Recipe;

/**
 * Created by jt on 7/3/17.
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {
    
    
    private final RecipeRepository recipeRepository;

    public ImageServiceImpl( RecipeRepository recipeService) {

        this.recipeRepository = recipeService;
    }


	@Override
    @Transactional
    public void saveImageFile(Long recipeId, MultipartFile file) {
    	try {
    		Recipe recipe = recipeRepository.findById(recipeId).get();
    		Byte[] byteObjects = new Byte[file.getBytes().length];
    		
    		int i = 0;
    		for(byte b: file.getBytes()) {
    			byteObjects[i++] = b;
    		}
    		
    		recipe.setImage(byteObjects);
    		
    		recipeRepository.save(recipe);
    		
    	} catch (IOException e) {
    	// todo better exception handling here
    		log.error("Error Occurred", e);
    		e.printStackTrace();
    	}

        log.debug("Received a file");

    }
}