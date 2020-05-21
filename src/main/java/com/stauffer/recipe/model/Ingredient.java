package com.stauffer.recipe.model;

import java.math.BigDecimal;

import javax.persistence.*;

@Entity
public class Ingredient {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	private BigDecimal amount;
	
	@OneToOne(fetch=FetchType.EAGER)
	private UnitOfMeasurement uom;
	
	//private UnitofMeasurement uom;
	
	@ManyToOne
	private Recipe recipe;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public UnitOfMeasurement getUom() {
		return uom;
	}

	public void setUom(UnitOfMeasurement uom) {
		this.uom = uom;
	}

	
}
