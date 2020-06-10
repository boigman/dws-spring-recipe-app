package com.stauffer.recipe.repositories;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stauffer.recipe.model.UnitOfMeasure;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT2 {

	@Autowired
	UnitOfMeasureRepository unitOfMeasureRepository;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFindByDescription() throws Exception{
		Optional <UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
		assertEquals("Teaspoon", uomOptional.get().getDescription());
//		fail("Not yet implemented");
	}

	@Test
	public void testFindByDescriptionCup() throws Exception{
		Optional <UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("Cup");
		assertEquals("Cup", uomOptional.get().getDescription());
//		fail("Not yet implemented");
	}
}