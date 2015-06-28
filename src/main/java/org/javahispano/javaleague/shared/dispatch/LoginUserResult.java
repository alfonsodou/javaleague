/**
 * 
 */
package org.javahispano.javaleague.shared.dispatch;

import com.gwtplatform.dispatch.rpc.shared.Result;

/**
 * @author alfonso
 *
 */
public class LoginUserResult implements Result {
	private String response;
	/**
	 * 
	 */
	private static final long serialVersionUID = -5721277515413213487L;

	private LoginUserResult() {
		
	}
	
	public LoginUserResult(final String response) {
		this.response = response;
	}
	
	public String getResponse() {
		return response;
	}
}
