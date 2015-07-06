package org.javahispano.javaleague.client.application.register;

/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2013 - 2015 GwtBootstrap3
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

public class CredentialsRegister implements Serializable {

	private static final long serialVersionUID = -1626677647077707091L;

	private String password = null;

	private String email = null;

	private String confirmPassword = null;

	private String confirmEmail = null;

	private String username = null;

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CredentialsRegister other = (CredentialsRegister) obj;
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (confirmPassword == null) {
			if (other.confirmPassword != null) {
				return false;
			}
		} else if (!confirmPassword.equals(other.confirmPassword)) {
			return false;
		}
		if (username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!username.equals(other.username)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (confirmEmail == null) {
			if (other.confirmEmail != null) {
				return false;
			}
		} else if (!confirmEmail.equals(other.confirmEmail)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the password
	 */
	@NotNull
	@Size(min = 4, max = 12)
	public String getPassword() {
		return password;
	}

	/**
	 * @return the confirm password
	 */
	@NotNull
	@Size(min = 4, max = 12)
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * @return the username
	 */
	@NotNull
	@Size(min = 4, max = 128)
	public String getUsername() {
		return username;
	}

	/**
	 * @return the email
	 */
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(?:[a-zA-Z]{2,6})$", message = "Email no válido")
	@NotNull
	public String getEmail() {
		return email;
	}

	/**
	 * @return the confirm email
	 */
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(?:[a-zA-Z]{2,6})$", message = "Email no válido")
	@NotNull
	public String getConfirmEmail() {
		return confirmEmail;
	}

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (username == null ? 0 : username.hashCode());
		result = prime * result + (password == null ? 0 : password.hashCode());
		result = prime * result
				+ (confirmPassword == null ? 0 : confirmPassword.hashCode());
		result = prime * result + (email == null ? 0 : email.hashCode());
		result = prime * result
				+ (confirmEmail == null ? 0 : confirmEmail.hashCode());
		return result;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * @param confirmPassword
	 *            the password to set
	 */
	public void setConfirmPassword(final String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(final String username) {
		this.username = username;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * @param confirmEmail
	 *            the email to set
	 */
	public void setConfirmEmail(final String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "Credentials [username=" + username + ", email=" + email
				+ ", password=" + password + "]";
	}

}
