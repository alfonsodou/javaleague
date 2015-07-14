/**
 * 
 */
package org.javahispano.javaleague.client.application.authenticate;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.html.Paragraph;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
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

	@UiField
	Paragraph authTrue;
	
	@UiField
	Paragraph authFalse;
	
	@Inject
	AuthenticatePageView(Binder uiBinder) {
		
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@Override
	public void viewAuthTrue(boolean view) {
		authTrue.setVisible(view);
	}
	
	@Override
	public void viewAuthFalse(boolean view) {
		authFalse.setVisible(view);
	}
}
