/**
 * 
 */
package org.javahispano.javaleague.client.application.authenticate;

import javax.inject.Inject;

import org.javahispano.javaleague.client.application.ApplicationPresenter;
import org.javahispano.javaleague.client.place.NameTokens;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

/**
 * @author alfonso
 *
 */
public class AuthenticatePagePresenter
		extends
		Presenter<AuthenticatePagePresenter.MyView, AuthenticatePagePresenter.MyProxy> {
	interface MyView extends View {
	}

	@ProxyStandard
	@NameToken({NameTokens.AUTHENTICATE + "/{isAuthenticate}", NameTokens.AUTHENTICATE})
	interface MyProxy extends ProxyPlace<AuthenticatePagePresenter> {
	}

	@Inject
	AuthenticatePagePresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_SetMainContent);
	}
	
	@Override
	public void prepareFromRequest(final PlaceRequest placeRequest) {
	    super.prepareFromRequest(placeRequest);

	    String itemId = placeRequest.getParameter("isAuthenticate", "");
	    if (itemId.equals("true")) {
	    	
	    } else {
	    	
	    }
	}
}
