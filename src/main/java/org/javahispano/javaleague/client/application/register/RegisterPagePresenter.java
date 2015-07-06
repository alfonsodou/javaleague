/**
 * 
 */
package org.javahispano.javaleague.client.application.register;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Modal;
import org.gwtbootstrap3.client.ui.ModalBody;
import org.gwtbootstrap3.client.ui.ModalFooter;
import org.gwtbootstrap3.client.ui.html.Span;
import org.javahispano.javaleague.client.application.ApplicationPresenter;
import org.javahispano.javaleague.client.place.NameTokens;
import org.javahispano.javaleague.shared.dispatch.LoginUserAction;
import org.javahispano.javaleague.shared.dispatch.LoginUserResult;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

/**
 * @author adou
 *
 */
public class RegisterPagePresenter extends
		Presenter<RegisterPagePresenter.MyView, RegisterPagePresenter.MyProxy>
		implements RegisterUiHandlers {
	public interface MyView extends View, HasUiHandlers<RegisterUiHandlers> {
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.REGISTER)
	public interface MyProxy extends ProxyPlace<RegisterPagePresenter> {
	}

	private final DispatchAsync dispatcher;
	private final PlaceManager placeManager;

	@Inject
	RegisterPagePresenter(EventBus eventBus, MyView view, MyProxy proxy,
			PlaceManager placeManager, DispatchAsync dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_SetMainContent);

		this.placeManager = placeManager;
		this.dispatcher = dispatcher;

		getView().setUiHandlers(this);
	}

	@Override
	public void doLogin(String email, String password) {
		dispatcher.execute(new LoginUserAction(email, password),
				new AsyncCallback<LoginUserResult>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(LoginUserResult result) {
						
						if (result.getResponse().equals("KO!")) {
							final Modal modal = new Modal();
							modal.setTitle("Login");
							modal.setClosable(true);

							final ModalBody modalBody = new ModalBody();
							modalBody.add(new Span(
									"Email y/o contraseña incorrectos"));

							final ModalFooter modalFooter = new ModalFooter();
							modalFooter.add(new Button("Cerrar",
									new ClickHandler() {
										@Override
										public void onClick(
												final ClickEvent event) {
											modal.hide();
										}

									}));
							
							modal.add(modalBody);
							modal.add(modalFooter);
							
							modal.show();
						}
						
						GWT.log("It's works!");

					}

				});

	}
}
