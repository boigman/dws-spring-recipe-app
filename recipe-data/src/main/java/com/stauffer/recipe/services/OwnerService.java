package com.stauffer.recipe.services;

import com.stauffer.recipe.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

	Owner findByLastName(String lastName);
	
}
