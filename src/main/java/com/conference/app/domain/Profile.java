/**
 * 
 */
package com.conference.app.domain;

import com.conference.app.form.ProfileForm.TeeShirtSize;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * @author ankit.jaiswal
 *
 */
@Entity
public class Profile {
	@Id
	String userId;
	String displayName;
	String mainEmail;
	TeeShirtSize teeShirtSize;

	private Profile() {
	}

	public Profile(String userId, String displayName, String mainEmail, TeeShirtSize teeShirtSize) {
		this.userId = userId;
		this.displayName = displayName;
		this.mainEmail = mainEmail;
		this.teeShirtSize = teeShirtSize;
	}

	public String getUserId() {
		return userId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getMainEmail() {
		return mainEmail;
	}

	public TeeShirtSize getTeeShirtSize() {
		return teeShirtSize;
	}

	public void update(String displayName, TeeShirtSize teeShirtSize) {
		if (displayName != null) {
			this.displayName = displayName;
		}

		if (teeShirtSize != null) {
			this.teeShirtSize = teeShirtSize;
		}
	}
}
