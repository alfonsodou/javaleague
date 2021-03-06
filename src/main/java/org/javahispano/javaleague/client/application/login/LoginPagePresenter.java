/**
 * 
 */
package org.javahispano.javaleague.client.application.login;

import java.util.logging.Logger;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Modal;
import org.gwtbootstrap3.client.ui.ModalBody;
import org.gwtbootstrap3.client.ui.ModalFooter;
import org.gwtbootstrap3.client.ui.html.Span;
import org.javahispano.javaleague.client.application.ApplicationPresenter;
import org.javahispano.javaleague.client.place.NameTokens;
import org.javahispano.javaleague.client.resources.LoginMessages;
import org.javahispano.javaleague.client.security.CurrentUser;
import org.javahispano.javaleague.shared.api.SessionResource;
import org.javahispano.javaleague.shared.dispatch.LoginUserAction;
import org.javahispano.javaleague.shared.dispatch.LoginUserResult;
import org.javahispano.javaleague.shared.dispatch.RegisterUserAction;
import org.javahispano.javaleague.shared.dispatch.RegisterUserResult;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

/**
 * @author adou
 *
 */
public class LoginPagePresenter extends
		Presenter<LoginPagePresenter.MyView, LoginPagePresenter.MyProxy>
		implements LoginUiHandlers {

	public interface MyView extends View, HasUiHandlers<LoginUiHandlers> {
	}

	@ProxyStandard
	@NameToken(NameTokens.LOGIN)
	@NoGatekeeper
	interface MyProxy extends ProxyPlace<LoginPagePresenter> {
	}

    private static final Logger LOGGER = Logger.getLogger(LoginPagePresenter.class.getName());
    private static final int REMEMBER_ME_DAYS = 14;
    
	private final DispatchAsync dispatcher;
	private final PlaceManager placeManager;
	private final ResourceDelegate<SessionResource> sessionResource;
    private final CurrentUser currentUser;
    private final LoginMessages messages;

	@Inject
	LoginPagePresenter(EventBus eventBus, MyView view, MyProxy proxy,
			PlaceManager placeManager, DispatchAsync dispatcher,
			ResourceDelegate<SessionResource> sessionResource,
			CurrentUser currentUser, LoginMessages messages) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_SetMainContent);

		this.placeManager = placeManager;
		this.dispatcher = dispatcher;
		this.sessionResource = sessionResource;
        this.currentUser = currentUser;
        this.messages = messages;

		getView().setUiHandlers(this);
	}

	@Override
	public void doLogin(String email, String password) {
		dispatcher.execute(new LoginUserAction(email, password),
				new AsyncCallback<LoginUserResult>() {

					@Override
					public void onFailure(Throwable caught) {
						final Modal modal = new Modal();
						modal.setTitle("Login");
						modal.setClosable(true);

						final ModalBody modalBody = new ModalBody();
						modalBody
								.add(new Span(
										"Error al conectar con el servidor. Inténtalo más tarde!"));

						final ModalFooter modalFooter = new ModalFooter();
						modalFooter.add(new Button("Cerrar",
								new ClickHandler() {
									@Override
									public void onClick(final ClickEvent event) {
										modal.hide();
									}

								}));

						modal.add(modalBody);
						modal.add(modalFooter);

						modal.show();
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
						} else {
							placeManager.revealPlace(new PlaceRequest.Builder()
									.nameToken(NameTokens.getHome()).build());
						}
					}

				});

	}

	@Override
	public void doRegister(String email, String password, String userName) {
		dispatcher.execute(new RegisterUserAction(email, password, userName),
				new AsyncCallback<RegisterUserResult>() {
					@Override
					public void onFailure(Throwable caught) {
						final Modal modal = new Modal();
						modal.setTitle("Registro");
						modal.setClosable(true);

						final ModalBody modalBody = new ModalBody();
						modalBody
								.add(new Span(
										"Error al conectar con el servidor. Error al registrar el usuario!"));

						final ModalFooter modalFooter = new ModalFooter();
						modalFooter.add(new Button("Cerrar",
								new ClickHandler() {
									@Override
									public void onClick(final ClickEvent event) {
										modal.hide();
									}

								}));

						modal.add(modalBody);
						modal.add(modalFooter);

						modal.show();
					}

					@Override
					public void onSuccess(RegisterUserResult result) {
						if (result.getResponse().equals("KO!")) {
							final Modal modal = new Modal();
							modal.setTitle("Registro");
							modal.setClosable(true);

							final ModalBody modalBody = new ModalBody();
							modalBody.add(new Span(
									"Error al registrar el usuario!"));

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
					}
				});

	}

}
