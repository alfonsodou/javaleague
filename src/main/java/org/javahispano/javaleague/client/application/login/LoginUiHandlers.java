/**
 * 
 */
package org.javahispano.javaleague.client.application.login;

import com.google.gwt.event.dom.client.ClickEvent;
import com.gwtplatform.mvp.client.UiHandlers;

/**
 * @author adou
 *
 */
public interface LoginUiHandlers extends UiHandlers {
	void doLogin(String password, String email);
}
