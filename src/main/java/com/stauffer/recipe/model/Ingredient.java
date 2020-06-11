package com.stauffer.recipe.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.thymeleaf.util.StringUtils;

import com.stauffer.recipe.utilities.Rational;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Ingredient {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	@Column(columnDefinition="decimal", precision=10, scale=5)
	private BigDecimal amount;
	
	@OneToOne(fetch=FetchType.EAGER)
	private UnitOfMeasure uom;
	
	@ManyToOne
	private Recipe recipe;

	public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
		this.description = description;
		this.amount = amount;
		this.uom = uom;
	}

	public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom, Recipe recipe) {
		this.description = description;
		this.amount = amount;
		this.uom = uom;
		this.recipe = recipe;
	}

	public String display() {
		String dispUom;
//		System.out.print("Processing amount: "+amount.toString()+", UOM:"+uom.getDescription());
		if(amount.compareTo(new BigDecimal("1")) > 0) {
//			System.out.println("Old UOM: "+uom.getDescription());
			if(uom.getDescription().equals("Each")) {
				dispUom = "";
			} else dispUom = (uom.getDescription()+"s").replaceAll("shs", "shes").replaceAll("chs", "ches");
//			System.out.println("New UOM: "+dispUom);
		} else {
//			System.out.println(" Amount LE Branch");
			if(uom.getDescription().equals("Each")) {
				dispUom = "";
			} else dispUom = uom.getDescription();
		}
		String returnString = new Rational(amount).toString()+" "+dispUom +" "+StringUtils.capitalize(description);
		return returnString;
	}
	
}
