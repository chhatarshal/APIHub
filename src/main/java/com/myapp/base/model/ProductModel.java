package com.myapp.base.model;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductModel {
	private Long Id;
	private String Name;
	private BigDecimal price;
	private String desciption;
}
