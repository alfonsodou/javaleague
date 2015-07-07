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
	void doRegister(String email, String password, String userName);
}
