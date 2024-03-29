package com.APIHub.cookidea.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
}
