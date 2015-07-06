/**
 * 
 */
package org.javahispano.javaleague.client.application.register;

import com.gwtplatform.mvp.client.UiHandlers;

/**
 * @author adou
 *
 */
public interface RegisterUiHandlers extends UiHandlers {
	void doLogin(String email, String password);
}
