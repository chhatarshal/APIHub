package com.myapp.base.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class User {
	@Id
	@GeneratedValue
	private Long Id;
	private String userName;
	private String password;
	
	@OneToMany(
	        cascade = CascadeType.ALL,
	        orphanRemoval=true,
	        mappedBy="owner"
	    )
	private List<Karooz> karoozs = new ArrayList<>();;
	

	@OneToMany(
	        cascade = CascadeType.ALL,
	        orphanRemoval=true
	    )
	private List<User> myChain = new ArrayList<>();
}
