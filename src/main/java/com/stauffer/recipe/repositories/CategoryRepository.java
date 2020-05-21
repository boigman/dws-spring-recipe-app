package com.stauffer.recipe.repositories;

import org.springframework.data.repository.CrudRepository;

import com.stauffer.recipe.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{

}
