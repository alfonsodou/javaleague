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
	void doLogin(String password, String email);
}
