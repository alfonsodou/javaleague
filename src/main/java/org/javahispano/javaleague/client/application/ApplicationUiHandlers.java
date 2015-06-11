/**
 * 
 */
package org.javahispano.javaleague.client.application;

import com.gwtplatform.mvp.client.UiHandlers;

/**
 * @author alfonso
 *
 */
public interface ApplicationUiHandlers extends UiHandlers {
	void doShowHomePage();
	void doShowLoginPage();
	void doShowRulesPage();
	void doShowDownloadPage();
	void doShowTournamentPage();
}
