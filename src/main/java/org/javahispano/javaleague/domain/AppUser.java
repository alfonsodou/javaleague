package org.javahispano.javaleague.domain;

import com.googlecode.objectify.annotation.Entity;

/**
 * An application user, named with a prefix to avoid confusion with GAE User
 * type
 */
@Entity
public class AppUser extends DatastoreObject {
	private String email;
	private String password;

	public AppUser() {
		// No-arg constructor required by Objectify
	}

	public AppUser(String userEmail) {
		this.email = userEmail;
		this.password = "";
	}
	
	public AppUser(String userEmail, String password) {
		this.email = userEmail;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
