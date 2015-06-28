/**
 * 
 */
package org.javahispano.javaleague.shared.dispatch;

import com.gwtplatform.dispatch.rpc.shared.UnsecuredActionImpl;

/**
 * @author alfonso
 *
 */
public class LoginUserAction extends UnsecuredActionImpl<LoginUserResult> {
	private String email;
	private String password;
	
	@SuppressWarnings("unused")
	private LoginUserAction() {
		this.email = null;
		this.password = null;
	}
	
	public LoginUserAction(String email, String password) {
		this.email = email;
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
