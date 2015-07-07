/**
 * 
 */
package org.javahispano.javaleague.shared.dispatch;

import com.gwtplatform.dispatch.rpc.shared.Result;

/**
 * @author adou
 *
 */
public class RegisterUserResult implements Result {
	private String response;

	/**
	 * 
	 */
	private static final long serialVersionUID = 4830504085973199046L;
	
	@SuppressWarnings("unused")
	private RegisterUserResult() {
		
	}
	
	public RegisterUserResult(String response) {
		this.response = response;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
}
