package com.stauffer.recipe.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.stauffer.recipe.model.Owner;
import com.stauffer.recipe.repositories.OwnerRepository;

@Service
//@Profile("springdatajpa")
public class OwnerSDJpaService implements OwnerService{
	
	private final OwnerRepository ownerRepository;  

	public OwnerSDJpaService(OwnerRepository ownerRepository
			) {
		this.ownerRepository = ownerRepository;
	}

	@Override
	public Owner findByLastName(String lastName) {
		return ownerRepository.findByLastName(lastName);
	}

	@Override
	public Set<Owner> findAll() {
		Set<Owner> owners = new HashSet<>();
		ownerRepository.findAll().forEach(owners::add);
		return owners;
	}

	@Override
	public Owner findById(Long aLong) {
		return ownerRepository.findById(aLong).orElse(null);
	}

	@Override
	public Owner save(Owner object) {
		System.out.println("Saving Owner:"+object.getLastName());
		return ownerRepository.save(object);
	}

	@Override
	public void delete(Owner object) {
		ownerRepository.delete(object);
	}

	@Override
	public void deleteById(Long aLong) {
		ownerRepository.deleteById(aLong);
	}

	
}
