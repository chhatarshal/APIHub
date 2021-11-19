package com.booknotes.base.entity;

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

@Getter @Setter
@Entity
public class Role {
	
	@Id
	@GeneratedValue
	private long id;
	private String name;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id", nullable=true)
	private User user;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "role")
	private List<Permission> permissions = new ArrayList<>();

}
