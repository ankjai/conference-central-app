/**
 * 
 */
package com.conference.app.spi;

import static com.conference.app.service.OfyService.ofy;

import com.conference.app.Constants;
import com.conference.app.domain.Profile;
import com.conference.app.form.ProfileForm;
import com.conference.app.form.ProfileForm.TeeShirtSize;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.users.User;
import com.googlecode.objectify.Key;

/**
 * @author ankit.jaiswal
 *
 */
@Api(name = "conference", version = "v1", scopes = { Constants.EMAIL_SCOPE }, clientIds = { Constants.WEB_CLIENT_ID,
		Constants.API_EXPLORER_CLIENT_ID }, description = "API for the Conference Central Backend application.")
public class ConferenceApi {

	/**
	 * Get the display name from the user's email. For example, if the email is
	 * lemoncake@example.com, then the display name becomes "lemoncake."
	 * 
	 * @param email
	 * @return
	 */
	private static String extractDefaultDisplayNameFromEmail(String email) {
		return email == null ? null : email.substring(0, email.indexOf("@"));
	}

	@ApiMethod(name = "saveProfile", path = "profile", httpMethod = HttpMethod.POST)
	public Profile saveProfile(final User user, ProfileForm profileForm) throws UnauthorizedException {
		String userId = null;
		String displayName = profileForm.getDisplayName();
		String mainEmail = "Not yet specified";
		TeeShirtSize teeShirtSize = TeeShirtSize.NOT_SPECIFIED;

		// chk if user is logged in or not
		if (user == null) {
			throw new UnauthorizedException("Authorization required.");
		}

		// chk if profile exists
		// if yes update, if no create
		Profile profile = getProfile(user);

		if (profile == null) {
			// user info from user obj
			userId = user.getUserId();
			mainEmail = user.getEmail();

			// update displayname
			if (displayName == null) {
				displayName = extractDefaultDisplayNameFromEmail(mainEmail);
			}

			// set teeShirt size if set in profile form
			if (profileForm.getTeeShirtSize() != null) {
				teeShirtSize = profileForm.getTeeShirtSize();
			}

			profile = new Profile(userId, displayName, mainEmail, teeShirtSize);
		} else {
			// update profile
			profile.update(displayName, profileForm.getTeeShirtSize());
		}

		// save profile entity
		ofy().save().entities(profile).now();

		return profile;
	}

	@ApiMethod(name = "getProfile", path = "profile", httpMethod = HttpMethod.GET)
	public Profile getProfile(final User user) throws UnauthorizedException {
		if (user == null) {
			throw new UnauthorizedException("Authorization required.");
		}

		String userId = user.getUserId();
		Key<Profile> key = Key.create(Profile.class, userId);

		Profile profile = ofy().load().key(key).now();
		return profile;
	}
}
