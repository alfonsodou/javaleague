/**
 * 
 */
package org.javahispano.javaleague.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * @author adou
 *
 */
public interface JavaleagueClientBundle extends ClientBundle {
	static final JavaleagueClientBundle INSTANCE = GWT
			.create(JavaleagueClientBundle.class);

	@Source("resource/js/prettify.js")
	TextResource prettify();

}
