/**
 * 
 */
package org.javahispano.javaleague.client.application.login;

import org.javahispano.javaleague.client.application.ApplicationPresenter;
import org.javahispano.javaleague.client.place.NameTokens;
import org.javahispano.javaleague.shared.dispatch.LoginUserAction;
import org.javahispano.javaleague.shared.dispatch.LoginUserResult;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
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
public class LoginPagePresenter extends
		Presenter<LoginPagePresenter.MyView, LoginPagePresenter.MyProxy>
		implements LoginUiHandlers {
	public interface MyView extends View {
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.LOGIN)
	public interface MyProxy extends ProxyPlace<LoginPagePresenter> {
	}

	private final DispatchAsync dispatcher;
	private final PlaceManager placeManager;

	@Inject
	LoginPagePresenter(EventBus eventBus, MyView view, MyProxy proxy,
			PlaceManager placeManager, DispatchAsync dispatcher) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_SetMainContent);
		
		this.placeManager = placeManager;
		this.dispatcher = dispatcher;
	}

	@Override
	public void doLogin(String password, String email) {
		dispatcher.execute(new LoginUserAction(email, password), new AsyncCallback<LoginUserResult>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(LoginUserResult result) {
				GWT.log("It's works!");
				
			}
			
		});

	}
}
