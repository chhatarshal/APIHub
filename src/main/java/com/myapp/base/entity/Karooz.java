package com.myapp.base.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter @Entity
public class Karooz {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="owner_id", nullable=true)
	private User owner;
	private BigDecimal price;
	private String description;
	@OneToMany(
	        cascade = CascadeType.ALL,
	        orphanRemoval=true,
	        mappedBy="karooz"
	    )
	private List<Product> products = new ArrayList<>();
}
