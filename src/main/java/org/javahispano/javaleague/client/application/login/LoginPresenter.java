/**
 * 
 */
package org.javahispano.javaleague.client.application.login;

import org.javahispano.javaleague.client.application.ApplicationPresenter;
import org.javahispano.javaleague.client.place.NameTokens;

import com.google.gwt.editor.client.Editor;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

/**
 * @author adou
 *
 */
public class LoginPresenter
		extends
		Presenter<LoginPresenter.MyView, LoginPresenter.MyProxy> {

	@NameToken(NameTokens.LOGIN)
	@ProxyCodeSplit
	public interface MyProxy extends ProxyPlace<LoginPresenter> {
	}

	public interface MyView extends View, Editor<Credentials> {
	}

	@Inject
	public LoginPresenter(final EventBus eventBus,
			final MyView view, final MyProxy proxy) {
		super(eventBus, view, proxy, ApplicationPresenter.TYPE_SetMainContent);
	}

}