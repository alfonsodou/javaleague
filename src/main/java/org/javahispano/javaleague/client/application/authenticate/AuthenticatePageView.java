/**
 * 
 */
package org.javahispano.javaleague.client.application.authenticate;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

/**
 * @author alfonso
 *
 */
public class AuthenticatePageView extends ViewImpl implements
		AuthenticatePagePresenter.MyView {

	interface Binder extends UiBinder<Widget, AuthenticatePageView> {
	}

	@Inject
	AuthenticatePageView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}
}
