/**
 * 
 */
package org.javahispano.javaleague.client.application.register;

import java.util.ArrayList;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import org.gwtbootstrap3.client.ui.PanelBody;
import org.javahispano.javaleague.client.application.register.CredentialsRegister;
import org.javahispano.javaleague.client.application.register.CredentialsRegisterEditor;

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
public class RegisterPageView extends ViewWithUiHandlers<RegisterUiHandlers>
		implements RegisterPagePresenter.MyView {

	interface Binder extends UiBinder<Widget, RegisterPageView> {
	}

	interface CredentialRegisterDriver
			extends
			SimpleBeanEditorDriver<CredentialsRegister, CredentialsRegisterEditor> {
	}

	private static final CredentialRegisterDriver DRIVER = GWT
			.create(CredentialRegisterDriver.class);

	@UiField
	protected PanelBody body;

	@UiField
	protected PanelBody bodyRegister;

	@UiField
	protected DivElement result;

	@Inject
	RegisterPageView(final Binder uiBinder, CredentialsRegisterEditor editor) {
		initWidget(uiBinder.createAndBindUi(this));
		body.add(editor);
		DRIVER.initialize(editor);
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		DRIVER.edit(new CredentialsRegister());
	}

	@UiHandler("resetButton")
	public void onResetClick(ClickEvent event) {
		DRIVER.edit(new CredentialsRegister());	
		result.removeAllChildren();
	}

	@UiHandler("loginButton")
	public void onLoginClick(ClickEvent event) {
		CredentialsRegister creds = DRIVER.flush();

		Validator validator = Validation.buildDefaultValidatorFactory()
				.getValidator();
		Set<ConstraintViolation<CredentialsRegister>> violations = validator.validate(
				creds, Default.class);
		if (violations.size() > 0) {
			DRIVER.setConstraintViolations(new ArrayList<ConstraintViolation<?>>(
					violations));
		}

		if (!DRIVER.hasErrors()) {
			result.setInnerText(creds.toString());
			getUiHandlers().doLogin(creds.getEmail(), creds.getPassword());
		}
	}

}
