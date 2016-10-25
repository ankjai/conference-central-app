package com.conference.app.form;

public class ProfileForm {
	private String displayName;
	private TeeShirtSize teeShirtSize;

	public String getDisplayName() {
		return displayName;
	}

	public TeeShirtSize getTeeShirtSize() {
		return teeShirtSize;
	}

	public static enum TeeShirtSize {
		NOT_SPECIFIED, XS, S, M, L, XL, XXL, XXXL
	}
}
