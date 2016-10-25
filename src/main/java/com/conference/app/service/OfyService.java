package com.conference.app.service;

import com.conference.app.domain.Profile;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

public class OfyService {
	static {
		factory().register(Profile.class);
	}

	// public OfyService() {
	// factory().register(Profile.class);
	// }

	/**
	 * Use this static method for getting the Objectify service object in order
	 * to make sure the above static block is executed before using Objectify.
	 * 
	 * @return Objectify service object.
	 */
	public static Objectify ofy() {
		return ObjectifyService.ofy();
	}

	/**
	 * Use this static method for getting the Objectify service factory.
	 * 
	 * @return ObjectifyFactory.
	 */
	public static ObjectifyFactory factory() {
		return ObjectifyService.factory();
	}
}
