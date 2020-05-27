package com.stauffer.recipe.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.stauffer.recipe.model.UnitOfMeasurement;

public interface UnitOfMeasurementRepository extends CrudRepository<UnitOfMeasurement, Long>{

	Optional<UnitOfMeasurement> findByDescription(String Description);

}
