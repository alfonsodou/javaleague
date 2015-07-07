/**
 * 
 */
package org.javahispano.javaleague.shared.dispatch;

import com.gwtplatform.dispatch.rpc.shared.UnsecuredActionImpl;

/**
 * @author adou
 *
 */
public class RegisterUserAction extends UnsecuredActionImpl<RegisterUserResult> {
	private String email;
	private String password;
	private String userName;
	
	@SuppressWarnings("unused")
	private RegisterUserAction() {
		this.email = null;
		this.password = null;
		this.userName = null;
	}
	
	public RegisterUserAction(String email, String password, String userName) {
		this.email = email;
		this.password = password;
		this.userName = userName;
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


}
