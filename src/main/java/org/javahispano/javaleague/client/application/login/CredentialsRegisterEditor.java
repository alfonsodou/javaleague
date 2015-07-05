package org.javahispano.javaleague.client.application.login;

/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2013 - 2015 GwtBootstrap3
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.gwtbootstrap3.client.ui.Form;
import org.gwtbootstrap3.client.ui.Input;
import org.gwtbootstrap3.client.ui.TextBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.inject.Inject;

public class CredentialsRegisterEditor extends Composite implements
		Editor<CredentialsRegister> {

	/**
	 * The Interface Binder.
	 */
	public interface Binder extends UiBinder<Form, CredentialsRegisterEditor> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	@UiField
	protected Input password;

	@UiField
	protected TextBox email;

	@UiField
	protected Input repassword;

	@UiField
	protected TextBox username;

	/**
	 * Instantiates a new credentials editor.
	 */
	@Inject
	public CredentialsRegisterEditor() {
		initWidget(BINDER.createAndBindUi(this));
	}

}
