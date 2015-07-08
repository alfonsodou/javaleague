/**
 * 
 */
package org.javahispano.javaleague.client.application.login;

import java.util.ArrayList;
import java.util.List;

import org.gwtbootstrap3.client.ui.Form;
import org.gwtbootstrap3.client.ui.Input;
import org.gwtbootstrap3.client.ui.PanelBody;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.form.error.BasicEditorError;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
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
public class LoginPageView extends ViewWithUiHandlers<LoginUiHandlers>
		implements LoginPagePresenter.MyView {

	interface Binder extends UiBinder<Widget, LoginPageView> {
	}

	@UiField
	protected PanelBody body;

	@UiField
	protected PanelBody bodyRegister;

	@UiField
	protected DivElement result;

	@UiField
	protected Form formLogin;

	@UiField
	protected Form formRegister;

	@UiField
	protected TextBox email;

	@UiField
	protected TextBox emailRegister;

	@UiField
	protected TextBox confirmEmail;

	@UiField
	protected TextBox userName;

	@UiField
	protected Input password;

	@UiField
	protected Input passwordRegister;

	@UiField
	protected Input confirmPassword;

	@Inject
	LoginPageView(final Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));

		email.addValidator(new org.gwtbootstrap3.client.ui.form.validator.RegExValidator(
				"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(?:[a-zA-Z]{2,6})$",
				"Email no válido"));

		password.addValidator(new org.gwtbootstrap3.client.ui.form.validator.SizeValidator<String>(
				4, 12, "La contraseña debe tener entre 4 y 12 caracteres"));

		emailRegister
				.addValidator(new org.gwtbootstrap3.client.ui.form.validator.RegExValidator(
						"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(?:[a-zA-Z]{2,6})$",
						"Email no válido"));

		passwordRegister
				.addValidator(new org.gwtbootstrap3.client.ui.form.validator.SizeValidator<String>(
						4, 12,
						"La contraseña debe tener entre 4 y 12 caracteres"));

		confirmEmail
				.addValidator(new org.gwtbootstrap3.client.ui.form.validator.RegExValidator(
						"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(?:[a-zA-Z]{2,6})$",
						"Email no válido"));

		confirmPassword
				.addValidator(new org.gwtbootstrap3.client.ui.form.validator.SizeValidator<String>(
						4, 12,
						"La contraseña debe tener entre 4 y 12 caracteres"));

		passwordRegister
				.addValidator(new org.gwtbootstrap3.client.ui.form.validator.Validator<String>() {

					@Override
					public int getPriority() {
						return Priority.MEDIUM;
					}

					@Override
					public List<EditorError> validate(Editor<String> editor,
							String value) {
						List<EditorError> result = new ArrayList<EditorError>();
						String valueStr = value == null ? "" : value.toString();
						if (!confirmPassword.getText().equals(valueStr)) {
							result.add(new BasicEditorError(passwordRegister,
									value, "Las contraseñas no coinciden"));
						}

						return result;
					}

				});

		emailRegister
				.addValidator(new org.gwtbootstrap3.client.ui.form.validator.Validator<String>() {

					@Override
					public int getPriority() {
						return Priority.MEDIUM;
					}

					@Override
					public List<EditorError> validate(Editor<String> editor,
							String value) {
						List<EditorError> result = new ArrayList<EditorError>();
						String valueStr = value == null ? "" : value.toString();
						if (!confirmEmail.getText().equals(valueStr)) {
							result.add(new BasicEditorError(emailRegister,
									value, "El email no coincide"));
						}

						return result;
					}

				});

	}

	@Override
	protected void onAttach() {
		super.onAttach();

	}

	@UiHandler("resetButton")
	public void onResetClick(ClickEvent event) {
		formLogin.reset();
		result.removeAllChildren();
	}

	@UiHandler("resetRegisterButton")
	public void onResetRegisterClick(ClickEvent event) {
		formRegister.reset();
	}

	@UiHandler("loginButton")
	public void onLoginClick(ClickEvent event) {
		if (formLogin.validate()) {
			result.setInnerText(email.getText() + " :: " + password.getText());
			getUiHandlers().doLogin(email.getText(), password.getText());
		}
	}

	@UiHandler("registerButton")
	public void onRegisterClick(ClickEvent event) {
		if (formRegister.validate()) {
			getUiHandlers().doRegister(emailRegister.getText(),
					passwordRegister.getText(), userName.getText());
		}
	}

}
