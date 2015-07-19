package org.javahispano.javaleague.server.domain;

import com.googlecode.objectify.annotation.Entity;

/**
 * An application user, named with a prefix to avoid confusion with GAE User
 * type
 */
@Entity
public class AppUser extends DatastoreObject {
	private String email;
	private String password;
	private String userName;
	private boolean active;
	private String tokenActivate;

	public AppUser() {
		// No-arg constructor required by Objectify
	}

	public AppUser(String userEmail) {
		this.email = userEmail;
		this.password = "";
		this.userName = "";
		this.active = false;
		this.tokenActivate = "";
	}
	
	public AppUser(String userEmail, String password, String userName) {
		this.email = userEmail;
		this.password = password;
		this.userName = userName;
		this.active = false;
		this.tokenActivate = "";
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getTokenActivate() {
		return tokenActivate;
	}

	public void setTokenActivate(String tokenActivate) {
		this.tokenActivate = tokenActivate;
	}
	
	
}
