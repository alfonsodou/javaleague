/**
 * 
 */
package org.javahispano.javaleague.client.application.login;

import org.javahispano.javaleague.client.application.ApplicationPresenter;
import org.javahispano.javaleague.client.place.NameTokens;
import org.javahispano.javaleague.client.service.AppUserService;
import org.javahispano.javaleague.client.service.AppUserServiceAsync;
import org.javahispano.javaleague.client.service.RPCCall;
import org.javahispano.javaleague.shared.domain.AppUser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
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
public class LoginPresenter extends
		Presenter<LoginPresenter.MyView, LoginPresenter.MyProxy> implements
		LoginUiHandlers {

	private AppUserServiceAsync appUserService = GWT
			.create(AppUserService.class);
	
	private final PlaceManager placeManager;

	@NameToken(NameTokens.LOGIN)
	@ProxyCodeSplit
	public interface MyProxy extends ProxyPlace<LoginPresenter> {
	}

	public interface MyView extends View, Editor<Credentials>,
			HasUiHandlers<LoginUiHandlers> {
	}

	@Inject
	public LoginPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, final PlaceManager placeManager) {
		super(eventBus, view, proxy, ApplicationPresenter.TYPE_SetMainContent);

		this.placeManager = placeManager;

		view.setUiHandlers(this);
	}

	@Override
	public void doLogin(String email, String password) {
		final AppUser appUser = new AppUser();
		appUser.setEmail(email);
		appUser.setPassword(password);
		appUser.setAppUserName("Prueba");
		new RPCCall<AppUser>() {
			@Override
			protected void callService(AsyncCallback<AppUser> cb) {
				appUserService.newUser(appUser, "Prueba", callback);
			}
						
			@Override
			public void onFailure(Throwable caught) {
				GWT.log(caught.getMessage());
				Window.alert("Error user login ...");
			}

			@Override
			public void onSuccess(AppUser result) {
				if (result != null) {
					clientFactory.setAppUser(result);
					formLoginUser.reset();

					goTo(new MyTacticPlace());
				} else {
					errorLogin.setVisible(true);
				}
				loginButton.setEnabled(true);
			}
		}.retry(3);		
	}
}
