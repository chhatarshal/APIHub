package com.booknotes.base.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SettingsModel {
	private long id;
	private boolean showMyNotesOnly;
	private boolean showStickyOnTop;
	private long userId;
}
