package com.stauffer.recipe.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.stauffer.recipe.model.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long>{

	Optional<UnitOfMeasure> findByDescription(String Description);

}
