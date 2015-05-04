/**
 * 
 */
package org.javahispano.javaleague.client.application.login;

import java.util.ArrayList;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import org.gwtbootstrap3.client.ui.ImageAnchor;
import org.gwtbootstrap3.client.ui.PanelBody;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

/**
 * @author adou
 *
 */
public class LoginView extends ViewWithUiHandlers<LoginUiHandlers> implements
		LoginPresenter.MyView {

	interface Binder extends UiBinder<Widget, LoginView> {
	}

	interface CredentialDriver extends
			SimpleBeanEditorDriver<Credentials, CredentialsEditor> {
	}

	private static final CredentialDriver DRIVER = GWT
			.create(CredentialDriver.class);

	@Ignore
	@UiField
	protected PanelBody body;

	@Ignore
	@UiField
	protected DivElement result;

	@Inject
	LoginView(final Binder uiBinder, CredentialsEditor editor) {
		initWidget(uiBinder.createAndBindUi(this));
		body.add(editor);
		DRIVER.initialize(editor);
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		DRIVER.edit(new Credentials());
	}

	@UiHandler("resetButton")
	public void onResetClick(ClickEvent event) {
		DRIVER.edit(new Credentials());
		result.removeAllChildren();
	}
		
	@UiHandler("loginButton")
	public void onLoginClick(ClickEvent event) {
		Credentials creds = DRIVER.flush();

		Validator validator = Validation.buildDefaultValidatorFactory()
				.getValidator();
		Set<ConstraintViolation<Credentials>> violations = validator.validate(
				creds, Default.class);
		if (violations.size() > 0) {
			DRIVER.setConstraintViolations(new ArrayList<ConstraintViolation<?>>(
					violations));
		}

		if (!DRIVER.hasErrors()) {
			result.setInnerText(creds.toString());
		}
	}

}
