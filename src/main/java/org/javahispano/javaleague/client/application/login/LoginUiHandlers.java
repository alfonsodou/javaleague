/**
 * 
 */
package org.javahispano.javaleague.client.application.login;

import com.gwtplatform.mvp.client.UiHandlers;

/**
 * @author adou
 *
 */
public interface LoginUiHandlers extends UiHandlers {
	void doLogin(String email, String password);
	void doRegister(String email, String password, String userName);	
}
