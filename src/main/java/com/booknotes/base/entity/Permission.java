package com.booknotes.base.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Permission {
	
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private boolean enable;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="role_id", nullable=true)
	private Role role;
}
