package com.booknotes.base.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Settings {
	
	@Id
	@GeneratedValue
	private long id;
	private boolean showMyNotesOnly;
	private boolean showStickyOnTop;
	@OneToOne(targetEntity=User.class)  
	private User userSettings;
	private long userId;
}
