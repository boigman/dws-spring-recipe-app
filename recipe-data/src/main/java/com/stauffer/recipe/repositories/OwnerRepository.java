package com.stauffer.recipe.repositories;

import org.springframework.data.repository.CrudRepository;

import com.stauffer.recipe.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long>{
	
	Owner findByLastName(String lastName);

}
