/**
 * 
 */
package org.javahispano.javaleague.client.application.login;

import org.eclipse.jetty.util.log.Log;
import org.javahispano.javaleague.client.application.ApplicationPresenter;
import org.javahispano.javaleague.client.model.Credential;
import org.javahispano.javaleague.client.model.SocialUser;
import org.javahispano.javaleague.client.place.NameTokens;
import org.javahispano.javaleague.client.rpc.MyAsyncCallback;
import org.javahispano.javaleague.client.rpc.OAuthLoginService;
import org.javahispano.javaleague.client.util.ClientUtils;

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
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

/**
 * @author adou
 *
 */
public class LoginPresenter extends
		Presenter<LoginPresenter.MyView, LoginPresenter.MyProxy> implements
		LoginUiHandlers {

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
	public void loginGoogle() {
		verifySocialUser();
	}

	private void verifySocialUser() {
		final String authProviderName = ClientUtils
				.getAuthProviderNameFromCookie();
		final int authProvider = ClientUtils.getAuthProviderFromCookieAsInt();
		Log.debug("Verifying " + authProviderName + " user ...");
		final String userName = ""; // loginScreen.getUsernameTextBox().getText();
		final String password = ""; // loginScreen.getPasswordTextBox().getText();

		if (authProvider == ClientUtils.DEFAULT) {
			if (userName.length() == 0) {
				Window.alert("Username is empty");
				return;
			}
			if (password.length() == 0) {
				Window.alert("Password is emtpy");
				return;
			} else {
				/*
				 * if (loginDialog == null) { // we're using login screen
				 * showApp(APPSCREEN_MAIN); }
				 */

			}

		}

		new MyAsyncCallback<SocialUser>() {

			@Override
			public void onSuccess(SocialUser result) {
				ClientUtils.saveSessionId(result.getSessionId());

				String name = "";
				if (result.getName() != null) {
					name = result.getName();
				} else if (result.getNickname() != null) // yahoo
				{
					name = result.getNickname();
				} else if (result.getFirstName() != null) // linkedin
				{
					name = result.getFirstName();
					String lastName = result.getLastName();
					if (lastName != null) {
						name = name + " " + lastName;
					}
				}

				Log.debug(authProviderName + " user '" + name
						+ "' is verified!\n" + result.getJson());
				ClientUtils.saveUsername(name);

				PlaceRequest.Builder myRequestBuilder = new PlaceRequest.Builder()
						.nameToken(NameTokens.HOME);

				placeManager.revealPlace(myRequestBuilder.build());

				// updateLoginStatus();
			}

			@Override
			protected void callService(AsyncCallback<SocialUser> cb) {
				try {
					final Credential credential = ClientUtils.getCredential();
					if (credential == null) {
						Log.debug("verifySocialUser: Could not get credential for "
								+ authProvider + " user");
						return;
					}

					if (authProvider == ClientUtils.DEFAULT) {
						credential.setLoginName(userName);
						credential.setPassword(password);
					}

					OAuthLoginService.Util.getInstance().verifySocialUser(
							credential, cb);
				} catch (Exception e) {
					Window.alert(e.getMessage());
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Coult not verify" + authProvider + " user."
						+ caught);
			}
		}.go("Verifying " + authProviderName + " user..");

		/*
		 * AsyncCallback<SocialUser> callback = new AsyncCallback<SocialUser>()
		 * {
		 * 
		 * @Override public void onFailure(Throwable caught) {
		 * Window.alert("Coult not verify" + authProvider + " user." + caught);
		 * }
		 * 
		 * @Override public void onSuccess(SocialUser result) { log(authProvider
		 * + " user is verified");
		 * ClientUtils.saveSessionId(result.getSessionId());
		 * ClientUtils.saveUsername(result.getName()); updateLoginStatus(); } };
		 * OAuthLoginService
		 * .Util.getInstance().verifySocialUser(credential,callback);
		 */
	}

}
