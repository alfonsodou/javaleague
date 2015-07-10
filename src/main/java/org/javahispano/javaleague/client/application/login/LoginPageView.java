/**
 * 
 */
package org.javahispano.javaleague.client.application.login;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
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

		confirmPassword
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
						if (!passwordRegister.getText().equals(valueStr)) {
							result.add(new BasicEditorError(confirmPassword,
									value, "Las contraseñas no coinciden"));
						}

						return result;
					}

				});

		confirmEmail
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
						if (!emailRegister.getText().equals(valueStr)) {
							result.add(new BasicEditorError(confirmEmail,
									value, "El email no coincide"));
						}

						return result;
					}

				});

		emailRegister.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				formRegister.validate();
			}
		});

		passwordRegister
				.addValueChangeHandler(new ValueChangeHandler<String>() {
					@Override
					public void onValueChange(ValueChangeEvent<String> event) {
						formRegister.validate();
					}

				});

		confirmEmail.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				formRegister.validate();
			}
		});

		confirmPassword.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				formRegister.validate();
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
			getUiHandlers().doLogin(email.getText(), digest_MD5(password.getText()));
		}
	}

	@UiHandler("registerButton")
	public void onRegisterClick(ClickEvent event) {
		if (formRegister.validate()) {
			getUiHandlers().doRegister(emailRegister.getText(),
					digest_MD5(passwordRegister.getText()), userName.getText());
		}
	}

	public String digest_MD5(String password) {
		MessageDigest crypt = null;

		try {
			crypt = java.security.MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			Window.alert("MD5 not supported");
			return null;
		}

		byte[] digested = crypt.digest(password.getBytes());

		String crypt_password = new String();

		// Converts bytes to string
		for (byte b : digested)
			crypt_password += Integer.toHexString(0xFF & b);

		return crypt_password;
	}
}
