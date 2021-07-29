package com.myapp.base.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class KaroozModel {

	private Long id;
	private UserModel owner;
	private BigDecimal price;
	private String description;
}
