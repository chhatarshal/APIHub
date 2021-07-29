package com.myapp.base.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity @Setter @Getter
public class Product {
	
	@Id @GeneratedValue
	private Long Id;
	private String Name;
	private BigDecimal price;
	private String desciption;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="karooz_id", nullable=true)
	private Karooz karooz;
}
