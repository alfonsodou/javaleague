/**
 * 
 */
package org.javahispano.javaleague.server.service;


import org.javahispano.javaleague.domain.AppUser;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

/**
 * @author adou
 * 
 */
public class OfyService {
	static {
		factory().register(AppUser.class);
	}

	public static Objectify ofy() {
		return ObjectifyService.ofy();
	}

	public static ObjectifyFactory factory() {
		return ObjectifyService.factory();
	}
}
